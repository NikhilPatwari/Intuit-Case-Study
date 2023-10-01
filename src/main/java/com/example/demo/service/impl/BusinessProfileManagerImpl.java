package com.example.demo.service.impl;

import com.example.demo.dto.ApprovalResponse;
import com.example.demo.dto.Status;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.ServiceUnavailableException;
import com.example.demo.exception.ValidationFailureException;
import com.example.demo.models.BusinessProfile;
import com.example.demo.repository.BusinessProfileRepository;
import com.example.demo.resource.AsyncCallService;
import com.example.demo.resource.ProductResource;
import com.example.demo.service.BusinessProfileManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BusinessProfileManagerImpl implements BusinessProfileManager {
    private final BusinessProfileRepository repository;
    private final ProductResource productResource;
    private final AsyncCallService asyncCallService;
    @Override
    public BusinessProfile getBusinessProfileById(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(BusinessProfile.class.getSimpleName()));
    }

    @Override
    public Status createProfile(BusinessProfile profile, String product) {
        if (StringUtils.isNotBlank(product)) {
            profile.setSubscribedProducts(Collections.singletonList(product));
        }
        long count = getCountByPanOrEin(profile.getPan(), profile.getEin());
        if (count != 0) {
            throw new AlreadyExistsException(BusinessProfile.class.getSimpleName());
        }
        repository.save(profile);
        return Status.getSuccessStatus();
    }

    private long getCountByPanOrEin(String pan, String ein) {
        if (StringUtils.isBlank(pan) && StringUtils.isBlank(ein)) {
            return 0;
        } else if (StringUtils.isNotBlank(pan) && StringUtils.isNotBlank(ein)) {
            return repository.countByPanOrEin(pan, ein);
        } else if (StringUtils.isNotBlank(pan)) {
            return repository.countByPan(pan);
        } else {
            return repository.countByEin(ein);
        }
    }

    @Override
    public Status updateProfile(BusinessProfile profile, String product) {
        BusinessProfile existingProfile = repository.getProductsByProfileId(profile.getId()).orElseThrow(() -> new NotFoundException(BusinessProfile.class.getSimpleName()));
        List<String> subscribedProducts = existingProfile.getSubscribedProducts().stream().filter(p -> !p.equals(product)).collect(Collectors.toList());
        validateProfile(profile, subscribedProducts);
        profile.setSubscribedProducts(existingProfile.getSubscribedProducts());
        repository.save(profile);
        return Status.getSuccessStatus();
    }

    private void validateProfile(BusinessProfile profile, List<String> products) {
        List<Future<ApprovalResponse>> futureResponses = getFutures(profile, products);
        validateFutureResponses(futureResponses);
    }

    private void validateFutureResponses(List<Future<ApprovalResponse>> futureResponses) {
        while(true) {
            try {
                for (Future<ApprovalResponse> future : futureResponses) {
                    if (future.isDone()) {
                        ApprovalResponse response = future.get();
                        validateResponse(response);
                    }
                }
                if(futureResponses.stream().allMatch(Future::isDone)){
                    break;
                }
                Thread.sleep(10);
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void validateResponse(ApprovalResponse response) {
        if (response.getStatus().equals("FAILED")) {
            throw new ServiceUnavailableException("Product Validation service");
        } else if (!response.getStatus().equals("APPROVED")) {
            throw new ValidationFailureException("Failed to validate profile. Errors : "+ response.getErrors());
        }
    }

    private List<Future<ApprovalResponse>> getFutures(BusinessProfile profile, List<String> products) {
        List<Future<ApprovalResponse>> futureResponses = new ArrayList<>();
        for (String product : products) {
            String url = productResource.getProductUrl(product);
            if (StringUtils.isNotBlank(url)) {
                futureResponses.add(asyncCallService.callWithRetry(profile, url));
            }
        }
        return futureResponses;
    }

    @Override
    public Status deleteProfile(String id) {
        repository.deleteById(id);
        return Status.getSuccessStatus();
    }
}

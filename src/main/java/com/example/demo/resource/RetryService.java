package com.example.demo.resource;

import com.example.demo.dto.ApprovalResponse;
import com.example.demo.models.BusinessProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RetryService {
    private final ApprovalService approvalService;

    @Retryable(value = HttpServerErrorException.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
    public ApprovalResponse callWithRetry(BusinessProfile profile, String url) {
        log.info("Inside retry Service. Call for Approval Service");
        ApprovalResponse response = approvalService.getApproval(profile, url);
        if (StringUtils.equalsIgnoreCase(response.getStatus(), "FAILED")) {
            throw new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE, response.getErrors().toString());
        }
        return response;
    }

    @Recover
    public ApprovalResponse recover(HttpServerErrorException ex, BusinessProfile profile, String url) {
        log.info("Inside retry service. Recover method called");
        return ApprovalResponse.builder().status("FAILED").errors(Collections.singletonList("Internal Server error")).build();
    }
}

package com.example.demo.resource;

import com.example.demo.dto.ApprovalResponse;
import com.example.demo.models.BusinessProfile;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@Slf4j
public class ApprovalService {
    private final RestTemplate restTemplate;

    @Autowired
    public ApprovalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getFallBackApproval",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
            }
    )
    public ApprovalResponse getApproval(BusinessProfile profile, String productUrl) {
        log.info("sending approval request");
        return restTemplate.postForObject(productUrl, profile, ApprovalResponse.class);
    }

    public ApprovalResponse getFallBackApproval(BusinessProfile profile, String productUrl) {
        return ApprovalResponse.builder().status("FAILED").errors(Collections.singletonList("Service Unavailable")).build();
    }
}

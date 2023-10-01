package com.example.demo.resource;

import com.example.demo.dto.ApprovalResponse;
import com.example.demo.models.BusinessProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class AsyncCallService {
    @Autowired
    private RetryService retryService;

    @Async
    public Future<ApprovalResponse> callWithRetry(BusinessProfile profile, String url) {
        ApprovalResponse response = retryService.callWithRetry(profile,url);
        return new AsyncResult<>(response);
    }

}

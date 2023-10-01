package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Status {
    @JsonProperty("status")
    private String status;

    @JsonProperty("errors")
    private List<String> errors;
    public static Status getSuccessStatus(){
        return Status.builder()
                .status("SUCCESS")
                .build();
    }

    public static Status getErrorStatus(List<String> errors){
        return Status.builder()
                .status("FAILED")
                .errors(errors)
                .build();
    }

}

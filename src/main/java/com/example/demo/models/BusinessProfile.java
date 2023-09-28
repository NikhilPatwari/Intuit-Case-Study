package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "businessProfile")
@Data
public class BusinessProfile {
    @Id
    private String id;
    private String companyName;
    private String legalName;
    private Address legalAddress;
    private Address businessAddress;
    private String PAN;
    private String EIN;
    private String email;
    private String website;
}

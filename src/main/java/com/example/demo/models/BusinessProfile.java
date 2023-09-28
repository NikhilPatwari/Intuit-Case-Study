package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "businessProfile")
public class BusinessProfile {
    @Id
    @JsonProperty("id")
    private String id;

    @NotBlank(message = "Field : 'companyName' cannot be blank")
    @JsonProperty("companyName")
    private String companyName;

    @NotBlank(message = "Field : 'legalName' cannot be blank")
    @JsonProperty("legalName")
    private String legalName;

    @Valid
    @NotNull(message = "Field : 'legalAddress' cannot be null")
    @JsonProperty("legalAddress")
    private Address legalAddress;

    @Valid
    @NotNull(message = "Field : 'businessAddress' cannot be null")
    @JsonProperty("businessAddress")
    private Address businessAddress;

    @JsonProperty("PAN")
    private String PAN;

    @JsonProperty("EIN")
    private String EIN;

    @NotBlank(message = "Field : 'email' cannot be blank")
    @Email(message = "Field : 'email' must be a valid email")
    @JsonProperty("email")
    private String email;

    @JsonProperty("website")
    private String website;
}

package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "businessProfile")
public class BusinessProfile {
    @Id
    @NotBlank(message = "Field : 'id' cannot be null", groups = UpdateProfileGrp.class)
    @JsonProperty("id")
    private String id;

    @NotBlank(message = "Field : 'companyName' cannot be blank", groups = {CreateProfileGrp.class, UpdateProfileGrp.class})
    @JsonProperty("companyName")
    private String companyName;

    @NotBlank(message = "Field : 'legalName' cannot be blank", groups = {CreateProfileGrp.class, UpdateProfileGrp.class})
    @JsonProperty("legalName")
    private String legalName;

    @Valid
    @NotNull(message = "Field : 'legalAddress' cannot be null", groups = {CreateProfileGrp.class, UpdateProfileGrp.class})
    @JsonProperty("legalAddress")
    private Address legalAddress;

    @Valid
    @NotNull(message = "Field : 'businessAddress' cannot be null", groups = {CreateProfileGrp.class, UpdateProfileGrp.class})
    @JsonProperty("businessAddress")
    private Address businessAddress;

    @JsonProperty("PAN")
    private String pan;

    @JsonProperty("EIN")
    private String ein;

    @NotBlank(message = "Field : 'email' cannot be blank", groups = {CreateProfileGrp.class, UpdateProfileGrp.class})
    @Email(message = "Field : 'email' must be a valid email", groups = {CreateProfileGrp.class, UpdateProfileGrp.class})
    @JsonProperty("email")
    private String email;

    @JsonProperty("website")
    private String website;

    @JsonProperty("subscribedProducts")
    private List<String> subscribedProducts = new ArrayList<>(0);
}
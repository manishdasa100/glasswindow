package com.glasswindow.jobsearch.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jobdto {

    @NotBlank
    private String companyName;
    
    private String location;
    
    private String jobDescription;

    @JsonProperty(access = Access.WRITE_ONLY)
    private List<String> skillKeywords;

    private String jobTitle;

    @JsonProperty(access = Access.WRITE_ONLY)
    private long expiryDuration = 60;
    
}

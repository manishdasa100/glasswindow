package com.crio.jobsearch.data;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Jobs")
public class Jobentity {

    @Id
    private String jobId;
    private String companyName;
    private String location;
    private String jobDescription;
    private List<String> skillKeywords;
    private String jobTitle;
    private LocalDate expiryDate;
    
}

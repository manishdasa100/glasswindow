package com.glasswindow.jobsearch.exchange;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetJobRequest {

    private String location;
    private List<String> skillKeywords;
    
}

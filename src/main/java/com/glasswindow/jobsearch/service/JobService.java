package com.glasswindow.jobsearch.service;

import java.util.List;

import com.glasswindow.jobsearch.dto.Jobdto;
import com.glasswindow.jobsearch.exchange.GetJobResponse;

public interface JobService {

    public String saveJob(Jobdto jobEntry);

    public GetJobResponse getJobs(String locaton, List<String> skillKeywords);
    
}

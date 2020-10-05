package com.crio.jobsearch.service;

import java.util.List;

import com.crio.jobsearch.dto.Jobdto;
import com.crio.jobsearch.exchange.GetJobResponse;

public interface JobService {

    public String saveJob(Jobdto jobEntry);

    public GetJobResponse getJobs(String locaton, List<String> skillKeywords);
    
}

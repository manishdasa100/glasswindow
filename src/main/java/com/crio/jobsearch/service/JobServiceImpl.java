package com.crio.jobsearch.service;

import java.time.LocalDate;
import java.util.List;

import com.crio.jobsearch.dto.Jobdto;
import com.crio.jobsearch.exchange.GetJobResponse;
import com.crio.jobsearch.repositoryService.JobRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepositoryService jobRepositoryService;

    @Override
    public String saveJob(Jobdto jobEntry) {
        
        long expiryDuration = jobEntry.getExpiryDuration();

        LocalDate currentDate = LocalDate.now();
        LocalDate expiryDate = currentDate.plusDays(expiryDuration);

        String jobId = jobRepositoryService.saveJob(jobEntry, expiryDate);
        
        return jobId;
    }

    @Override
    public GetJobResponse getJobs(String location, List<String> skillKeywords) {

        List<Jobdto> activeJobs = jobRepositoryService.getActiveJobs(location, skillKeywords);

        GetJobResponse getJobResponse = new GetJobResponse();
        getJobResponse.setActiveJobs(activeJobs);
        
        return getJobResponse;
    }
    
    
    
}
package com.glasswindow.jobsearch.service;

import java.time.LocalDate;
import java.util.List;

import com.glasswindow.jobsearch.dto.Jobdto;
import com.glasswindow.jobsearch.exchange.GetJobResponse;
import com.glasswindow.jobsearch.repositoryService.JobRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepositoryService jobRepositoryService;

    @Override
    public String saveJob(Jobdto jobEntry) {
        
        long expiryDuration = jobEntry.getExpiryDuration();

        LocalDate expiryDate = LocalDate.now().plusDays(expiryDuration);

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

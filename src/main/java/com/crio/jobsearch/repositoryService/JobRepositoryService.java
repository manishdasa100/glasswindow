package com.crio.jobsearch.repositoryService;

import java.time.LocalDate;
import java.util.List;

import com.crio.jobsearch.dto.Jobdto;

public interface JobRepositoryService {

    public String saveJob(Jobdto jobEntry, LocalDate jobExpiryDate);

    public List<Jobdto> getActiveJobs(String location, List<String> skillKeywords);
    
}

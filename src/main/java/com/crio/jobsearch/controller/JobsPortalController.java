package com.crio.jobsearch.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.validation.Valid;

import com.crio.jobsearch.dto.Jobdto;
import com.crio.jobsearch.exchange.GetJobRequest;
import com.crio.jobsearch.exchange.GetJobResponse;
import com.crio.jobsearch.service.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JobsPortalController {

  @Autowired
  private JobService jobService;

  public static final String BASE_URL = "/jobs";

  @PutMapping(BASE_URL)
  public ResponseEntity<String> saveJob(@RequestBody @Valid Jobdto jobEntry) {
    String jobId = jobService.saveJob(jobEntry);
    
    String response = "Job created with ID :" + jobId;
    
    return ResponseEntity.ok().body(response);
  }

  
  @GetMapping(BASE_URL)
  public ResponseEntity<GetJobResponse> getJobs(GetJobRequest jobRequest) {

    GetJobResponse getJobResponse = jobService.getJobs(
      jobRequest.getLocation(), jobRequest.getSkillKeywords());

    return ResponseEntity.ok().body(getJobResponse);
  }

}

package com.crio.jobsearch.exchange;

import java.util.List;

import com.crio.jobsearch.dto.Jobdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetJobResponse {

    private List<Jobdto> activeJobs;
    
}

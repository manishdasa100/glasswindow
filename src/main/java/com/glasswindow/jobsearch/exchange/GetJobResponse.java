package com.glasswindow.jobsearch.exchange;

import java.util.List;

import com.glasswindow.jobsearch.dto.Jobdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetJobResponse {

    private List<Jobdto> activeJobs;
    
}

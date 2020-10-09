package com.crio.jobSearch.repositoryService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.crio.jobsearch.data.Jobentity;
import com.crio.jobsearch.dto.Jobdto;
import com.crio.jobsearch.repositoryService.JobRepositoryServiceImpl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import net.bytebuddy.asm.Advice.This;

public class repositoryServiceTest {
    
    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private JobRepositoryServiceImpl jobRepositoryServiceImpl;

    private static List<Jobentity> dummyJobEntities;

    @BeforeAll
    public static void populateDummyJobEntities() {

        Jobentity job1 = new Jobentity(
            "001",
            "JOB1",
            "Bangalore",
            "CCC",
            Arrays.asList("a","b","c"),
            "DDD",
            LocalDate.of(2020, 11, 05));

        Jobentity job2 = new Jobentity(
            "002",
            "JOB2",
            "Bangalore",
            "CCC",
            Arrays.asList("a","b","c"),
            "DDD",
            LocalDate.of(2020, 12, 12));   
            
        
        Jobentity job3 = new Jobentity(
            "003",
            "JOB3",
            "Bangalore",
            "CCC",
            Arrays.asList("a","b","c"),
            "DDD",
            LocalDate.of(2020, 07, 05));

        dummyJobEntities = new ArrayList<Jobentity>();

        dummyJobEntities.add(job1);
        dummyJobEntities.add(job2);
        dummyJobEntities.add(job3);

    }

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveJobTest() {

        jobRepositoryServiceImpl.saveJob(new Jobdto(), LocalDate.now());

        verify(mongoTemplate, times(1)).save(any(Jobentity.class));

    }

    @Test
    public void getActiveJobsTest() {
        when(mongoTemplate.find(any(Query.class), eq(Jobentity.class))).thenReturn(dummyJobEntities);

        List<Jobdto> activeJobs = jobRepositoryServiceImpl.getActiveJobs("Bangalore", Arrays.asList("a","b","c"));

        assertEquals(2, activeJobs.size());
        assertEquals("JOB1", activeJobs.get(0).getCompanyName());
        assertEquals("JOB2", activeJobs.get(1).getCompanyName());

    }

}

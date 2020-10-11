package com.glasswindow.jobsearch.repository;

import com.glasswindow.jobsearch.data.Jobentity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<Jobentity, String>{
    
}

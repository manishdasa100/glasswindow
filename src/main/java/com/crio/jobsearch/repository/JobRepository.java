package com.crio.jobsearch.repository;

import com.crio.jobsearch.data.Jobentity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<Jobentity, String>{
    
}

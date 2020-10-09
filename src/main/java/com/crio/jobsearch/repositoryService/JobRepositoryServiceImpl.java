package com.crio.jobsearch.repositoryService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.crio.jobsearch.data.Jobentity;
import com.crio.jobsearch.dto.Jobdto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class JobRepositoryServiceImpl implements JobRepositoryService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private ModelMapper modelMapper = new ModelMapper();

    private boolean isJobActiveAndRelavant(Jobentity jobentity, List<String> candidateSkills) {

        LocalDate expiryDate = jobentity.getExpiryDate();

        LocalDate currentDate = LocalDate.now();

        if (currentDate.isAfter(expiryDate)) {
            return false;
        }

        List<String> requiredSkills = jobentity.getSkillKeywords();

        for (String candidateSkill : candidateSkills) {
            for (String requiredSkill : requiredSkills) {
                if (candidateSkill.equals(requiredSkill)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String saveJob(Jobdto jobEntry, LocalDate jobExpiryDate) {

        Jobentity jobEntity = modelMapper.map(jobEntry, Jobentity.class);
        jobEntity.setExpiryDate(jobExpiryDate);

        mongoTemplate.save(jobEntity);

        return jobEntity.getJobId();
    }

    @Override
    public List<Jobdto> getActiveJobs(String location, List<String> candidateSkills) {
        
        Query query = new Query(Criteria.where("location").is(location));
        List<Jobentity> allJobentities = mongoTemplate.find(query, Jobentity.class);

        List<Jobdto> activeRelevantJobs = new ArrayList<>();

        for (Jobentity jobentity : allJobentities) {
            if (isJobActiveAndRelavant(jobentity, candidateSkills)) {
                activeRelevantJobs.add(modelMapper.map(jobentity, Jobdto.class));
            }
        }

        return activeRelevantJobs;
    }
    
}

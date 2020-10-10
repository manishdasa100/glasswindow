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

    private boolean isJobActiveAndRelavant(LocalDate expiryDate, List<String> requiredSkills, List<String> candidateSkills) {

        LocalDate currentDate = LocalDate.now();

        if (currentDate.isAfter(expiryDate)) {
            return false;
        }

        for (String candidateSkill : candidateSkills) {
            candidateSkill = candidateSkill.toLowerCase();
            for (String requiredSkill : requiredSkills) {
                requiredSkill = requiredSkill.toLowerCase();
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
        
        // Case insensitive query with "i" option.
        Query query = new Query(Criteria.where("location").regex("^"+location+"$","i"));
        List<Jobentity> allJobentities = mongoTemplate.find(query, Jobentity.class);

        List<Jobdto> activeRelevantJobs = new ArrayList<>();

        for (Jobentity jobentity : allJobentities) {
            if (isJobActiveAndRelavant(jobentity.getExpiryDate(), jobentity.getSkillKeywords(), candidateSkills)) {
                activeRelevantJobs.add(modelMapper.map(jobentity, Jobdto.class));
            }
        }

        return activeRelevantJobs;
    }
    
}

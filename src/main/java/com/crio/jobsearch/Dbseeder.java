package com.crio.jobsearch;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.crio.jobsearch.data.Jobentity;
import com.crio.jobsearch.dto.Jobdto;
import com.crio.jobsearch.repository.JobRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Dbseeder implements CommandLineRunner{

    @Autowired
    private JobRepository jobRepo;

    private static final String filename = "initial_data_load.json";

    @Override
    public void run(String... args) throws Exception {
        
        ObjectMapper mapper = new ObjectMapper();

        ModelMapper modelMapper = new ModelMapper();

        String content = resolveFileAsString(filename);

        List<Jobdto> jobs = mapper.readValue(content, new TypeReference<List<Jobdto>>() {});

        List<Jobentity> jobEntities = new ArrayList<>();

        for (Jobdto jobdto : jobs) {
            Jobentity entity = modelMapper.map(jobdto, Jobentity.class);
            entity.setExpiryDate(LocalDate.now().plusDays(jobdto.getExpiryDuration()));
            jobEntities.add(entity);
        }

        jobRepo.deleteAll();

        jobRepo.saveAll(jobEntities);

    }

    public static String resolveFileAsString(String filename) throws URISyntaxException, IOException{
        File inputFile = new File(Thread.currentThread().getContextClassLoader().
                    getResource(filename).toURI());
        return new String(Files.readAllBytes(inputFile.toPath()), "utf-8");
    }
    
}

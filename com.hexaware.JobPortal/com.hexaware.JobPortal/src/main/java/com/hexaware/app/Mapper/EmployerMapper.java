package com.hexaware.app.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.app.Dto.EmployerDTO;
import com.hexaware.app.Entity.Employer;
import com.hexaware.app.Entity.JobListing;
import com.hexaware.app.Service.EmployerService;

@Component
public class EmployerMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployerService employerService;

    public EmployerDTO toDTO(Employer employer) {
        return modelMapper.map(employer, EmployerDTO.class);
    }

    public Employer toEntity(EmployerDTO employerDTO) {
        Employer employer = modelMapper.map(employerDTO, Employer.class);
        
        // Fetch job listings from the database using employerId from employerDTO if necessary
        if (employerDTO.getJobListings() != null) {
            employerDTO.getJobListings().forEach(jobListingDTO -> {
                JobListing jobListing = modelMapper.map(jobListingDTO, JobListing.class);
                jobListing.setEmployer(employer);
                employer.getJobListings().add(jobListing);
            });
        }

        return employer;
    }
}

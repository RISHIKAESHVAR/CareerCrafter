package com.hexaware.app.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.app.Dto.JobListingDTO;
import com.hexaware.app.Entity.JobListing;
import com.hexaware.app.Service.EmployerService;

@Component
public class JobListingMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployerService employerService;

    public JobListingDTO toDTO(JobListing jobListing) {
        JobListingDTO jobListingDTO = modelMapper.map(jobListing, JobListingDTO.class);
        jobListingDTO.setEmployerId(jobListing.getEmployer().getEmpid());
        return jobListingDTO;
    }

    public JobListing toEntity(JobListingDTO jobListingDTO) {
        JobListing jobListing = modelMapper.map(jobListingDTO, JobListing.class);
        
        jobListing.setEmployer(employerService.findById(jobListingDTO.getEmployerId()));

        return jobListing;
    }
}

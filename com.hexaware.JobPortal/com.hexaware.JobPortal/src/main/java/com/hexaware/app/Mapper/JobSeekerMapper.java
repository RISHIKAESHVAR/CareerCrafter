package com.hexaware.app.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.app.Entity.JobSeeker;
import com.hexaware.app.Dto.JobSeekerDTO;

@Component
public class JobSeekerMapper {
    
    @Autowired
    private ModelMapper modelMapper;
    
    public JobSeekerDTO toDTO(JobSeeker jobSeeker) {
        return modelMapper.map(jobSeeker, JobSeekerDTO.class);
    }

    public JobSeeker toEntity(JobSeekerDTO jobSeekerDTO) {
        return modelMapper.map(jobSeekerDTO, JobSeeker.class);
    }
}

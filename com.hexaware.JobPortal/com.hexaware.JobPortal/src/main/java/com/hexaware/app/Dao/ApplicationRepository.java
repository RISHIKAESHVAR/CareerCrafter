package com.hexaware.app.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.app.Entity.Application;
import com.hexaware.app.Entity.JobListing;
import com.hexaware.app.Entity.JobSeeker;
@Repository
public interface ApplicationRepository extends JpaRepository<Application , Integer>{

	
	List<Application> findByJobSeekerJobSeekerId(int jobSeekerId);

	
	List<Application> findByJobListingJobId(int jobId);


	Optional<Application> findByJobListingAndJobSeeker(JobListing jobListing, JobSeeker jobSeeker);

}

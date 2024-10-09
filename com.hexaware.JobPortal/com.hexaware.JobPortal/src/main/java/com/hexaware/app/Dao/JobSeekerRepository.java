package com.hexaware.app.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.app.Entity.JobSeeker;
@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker , Integer> {

	JobSeeker findByEmail(String email);
	
	

}

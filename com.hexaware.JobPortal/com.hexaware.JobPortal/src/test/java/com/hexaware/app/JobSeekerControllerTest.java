package com.hexaware.app;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.app.Dao.ApplicationRepository;
import com.hexaware.app.Dao.JobListingRepository;
import com.hexaware.app.Dao.JobSeekerRepository;
import com.hexaware.app.Entity.Application;
import com.hexaware.app.Entity.JobListing;
import com.hexaware.app.Entity.JobSeeker;

@SpringBootTest
public class JobSeekerControllerTest {
	
    @Autowired
    private ApplicationRepository app ;
    
    @Autowired
    private JobListingRepository job;
    
    @Autowired
    private JobSeekerRepository js;

    
/*
    @Test
    void JobSeekersaveTest() {
		JobSeeker j= new JobSeeker (4,"Suresh","raina","raina@gmail.com","14-06-2002","9345818942","Theni" );
		js.save(j);
	}*/
    /*
    @Test
	void JobSeekerremoveTest() {
		int jobSeekerId=8;
		js.deleteById(jobSeekerId);
	}
*/
    
   
/*
    @Test
	void JobSeekerupdateTest() {
		int jobSeekerId=4;
		JobSeeker j=js.findById(jobSeekerId).orElse(null);
		j.setEmail("sureshraina@gmail.com");
		
		js.save(j);
    }
*/
    
    
/*
    @Test
    void getJobSeekerByIdTest() {
        int jobSeekerId = 4;
        int jobSeekerId1 = 4; 
        JobSeeker jobSeekerFromDb = js.findById(jobSeekerId)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found with id " + jobSeekerId));

        JobSeeker expectedJobSeeker = js.findById(jobSeekerId1)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found with id " + jobSeekerId));

        assertNotNull(jobSeekerFromDb);
        assertEquals(expectedJobSeeker.getJobSeekerId(), jobSeekerFromDb.getJobSeekerId());
        assertEquals(expectedJobSeeker.getFirstName(), jobSeekerFromDb.getFirstName());
        assertEquals(expectedJobSeeker.getLastName(), jobSeekerFromDb.getLastName());
        assertEquals(expectedJobSeeker.getEmail(), jobSeekerFromDb.getEmail());
        assertEquals(expectedJobSeeker.getDob(), jobSeekerFromDb.getDob());
        assertEquals(expectedJobSeeker.getMobileNumber(), jobSeekerFromDb.getMobileNumber());
        assertEquals(expectedJobSeeker.getAddress(), jobSeekerFromDb.getAddress());
    }

	private void assertEquals(int jobSeekerId, int jobSeekerId2) {
		// TODO Auto-generated method stub
		
	}

	private void assertEquals(String firstName, String firstName2) {
		// TODO Auto-generated method stub
		
	}

	private void assertNotNull(JobSeeker jobSeekerFromDb) {
		// TODO Auto-generated method stub
		
	}
*/
    
    
/*
    @Test
    void applicationSaveTest() {
        LocalDate applicationDate = LocalDate.of(2024, 9, 13);
        Application a = new Application();
        a.setStatus("Applied");
        a.setAppliedDate(applicationDate);

        JobListing jobListing = job.findById(3)
                .orElseThrow(() -> new RuntimeException("JobListing not found with id 1"));
        JobSeeker jobSeeker = js.findById(1)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found with id 1"));

        a.setJobListing(jobListing);
        a.setJobSeeker(jobSeeker);

        app.save(a);

    }
*/
    
    
/*
    @Test
	void ApplicationremoveTest() {
		int applicationId=4;
		app.deleteById(applicationId);
	}
*/
    
    

    @Test
	void ApplicationupdateTest() {
		int applicationId=3;
		Application j=app.findById(applicationId).orElse(null);
		j.setStatus("Pending");
		
		app.save(j);
    }
    
    
    
    

}

package com.hexaware.app;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.hexaware.app.Dao.EmployerRepository;
import com.hexaware.app.Dao.JobListingRepository;
import com.hexaware.app.Entity.Employer;
import com.hexaware.app.Entity.JobListing;

@SpringBootTest
public class EmployerControllerTests {

    @Autowired
    private EmployerRepository emp ;

    @Autowired
    private JobListingRepository job ;
/*
    @Test
    void EmployersaveTest() {
		Employer e= new Employer (101,"google","Located in Chennai","google@gmail.com","9500405548","Chennai","google.com" );
		emp.save(e);
	}*/
/*    
    @Test
	void employerremoveTest() {
		int empid=8;
		emp.deleteById(empid);
	}*/
/*    
    @Test
	void EmployerupdateTest() {
		int empid=9;
		Employer e=emp.findById(empid).orElse(null);
		b.setCompanyName("TCS");
		
		emp.save(e);
		
	}*/
/*    
    @Test
    void getEmployerByIdTest() {
        int employerId = 9;
        Employer employerFromDb = emp.findById(employerId)
                .orElseThrow(() -> new RuntimeException("Employer not found with id " + employerId));

        Employer expectedEmployer = emp.findById(employerId)
                .orElseThrow(() -> new RuntimeException("Employer not found with id " + employerId));

        assertNotNull(employerFromDb);
        assertEquals(expectedEmployer.getEmpid(), employerFromDb.getEmpid());
        assertEquals(expectedEmployer.getCompanyName(), employerFromDb.getCompanyName());
        assertEquals(expectedEmployer.getCompanyDescription(), employerFromDb.getCompanyDescription());
        assertEquals(expectedEmployer.getEmail(), employerFromDb.getEmail());
        assertEquals(expectedEmployer.getContactNumber(), employerFromDb.getContactNumber());
        assertEquals(expectedEmployer.getAddress(), employerFromDb.getAddress());
        assertEquals(expectedEmployer.getWebsite(), employerFromDb.getWebsite());
    }
	private void assertEquals(int empid, int empid2) {
			// TODO Auto-generated method stub
			
		}
	private void assertEquals(String email, String email2) {
			// TODO Auto-generated method stub
			
		}
	private void assertNotNull(Employer employerFromDb) {
		// TODO Auto-generated method stub
		
	}*/
    
/*    
    @Test
    void getJobListingByIdTest() {
        int jobId = 4;
        JobListing jobListingFromDb = job.findById(jobId)
                .orElseThrow(() -> new RuntimeException("JobListing not found with id " + jobId));

        JobListing expectedJobListing = job.findById(jobId)
                .orElseThrow(() -> new RuntimeException("JobListing not found with id " + jobId));

        assertNotNull(jobListingFromDb);
        assertEquals(expectedJobListing.getJobId(), jobListingFromDb.getJobId());
        assertEquals(expectedJobListing.getJobTitle(), jobListingFromDb.getJobTitle());
        assertEquals(expectedJobListing.getCompany(), jobListingFromDb.getCompany());
        assertEquals(expectedJobListing.getJobDescription(), jobListingFromDb.getJobDescription());
        assertEquals(expectedJobListing.getLocation(), jobListingFromDb.getLocation());
        assertEquals(expectedJobListing.getRequirements(), jobListingFromDb.getRequirements());
        assertEquals(expectedJobListing.getSalaryRange(), jobListingFromDb.getSalaryRange());
        assertEquals(expectedJobListing.getPostedDate(), jobListingFromDb.getPostedDate());
    }
	private void assertEquals(LocalDate postedDate, LocalDate postedDate2) {
		// TODO Auto-generated method stub
		
	}
	private void assertEquals(Double salaryRange, Double salaryRange2) {
		// TODO Auto-generated method stub
		
	}
	private void assertNotNull(JobListing jobListingFromDb) {
		// TODO Auto-generated method stub
		
	}*/

/*
    @Test
	void saveJobListingTest() {
        JobListing jobListing = new JobListing();
        jobListing.setJobTitle("Software Engineer");
        jobListing.setCompany("Google");
        jobListing.setJobDescription("Develop and maintain software");
        jobListing.setLocation("Chennai");
        jobListing.setRequirements("3 years experience in Java");
        jobListing.setSalaryRange(120000.0);

        JobListing savedJobListing = job.save(jobListing);
	}   
*/    
/*    
    @Test
    void DeleteJobListingTest() {
    	int jobId = 47;
    	job.deleteById(jobId);
    }
}
*/ 
    /*
    @Test
	void JobListingupdateTest() {
		int jobId=9;
		JobListing j=job.findById(jobId).orElse(null);
		j.setJobDescription("Develop a Full Stack Java Software");
		job.save(j);
		
	}
	*/

    @Test
	void JobTitleupdateTest() {
		int jobId=9;
		JobListing j=job.findById(jobId).orElse(null);
		j.setJobTitle("Manager");
		job.save(j);
		
	}

    
    
}


    
    


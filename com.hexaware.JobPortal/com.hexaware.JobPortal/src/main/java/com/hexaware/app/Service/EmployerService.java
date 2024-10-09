package com.hexaware.app.Service;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.app.Dao.ApplicationRepository;
import com.hexaware.app.Dao.EmployerRepository;
import com.hexaware.app.Dao.JobListingRepository;
import com.hexaware.app.Dao.ResumeRepository;
import com.hexaware.app.Entity.Application;
import com.hexaware.app.Entity.Employer;
import com.hexaware.app.Entity.JobListing;

import com.hexaware.app.Exception.IDnotfoundException;


@Service
public class EmployerService {
	@Autowired
	EmployerRepository Repemp;
	@Autowired
	JobListingRepository jobemp;
	@Autowired
	ApplicationRepository Appemp;
	@Autowired
	ResumeRepository resemp;

    // Create or post a new job
    public JobListing postJob(JobListing job) {
    	if (jobemp.findById(job.getJobId()).isPresent()) {
			return null;
		}
    	JobListing job1=jobemp.save(job);
		return job1;
        
    }

	public List<JobListing> getJobs() {
		List<JobListing> l=(List)jobemp.findAll();
		return l;
	}

	public JobListing removeJob(int jobid) throws IDnotfoundException {
		JobListing s=	jobemp.findById(jobid).orElseThrow(()->new IDnotfoundException("Job with ID " + jobid + " not found."));
		
		jobemp.delete(s);
	
	return s;
	}
	
	public Employer removeEmployer(int employerId) throws IDnotfoundException {
	    Employer employer = Repemp.findById(employerId)
	        .orElseThrow(() -> new IDnotfoundException("Employer with ID " + employerId + " not found."));
	    
	    Repemp.delete(employer);
	    
	    return employer;
	}


	public String updatejob(int jobid, String jobtitle) throws IDnotfoundException {
		JobListing s = jobemp.findById(jobid)
	            .orElseThrow(() -> new IDnotfoundException("Job not found with ID: " + jobid));
		
			s.setJobTitle(jobtitle);
			jobemp.save(s);
			return "updated";
		
	}

	public JobListing updateInfoJob(int jobid, String jobTitle, String company, String jobDescription, String location, String requirements, Double salaryRange) throws IDnotfoundException {
	    JobListing job = jobemp.findById(jobid)
	        .orElseThrow(() -> new IDnotfoundException("Job not found with ID: " + jobid));
	    
	    job.setJobTitle(jobTitle);
	    job.setCompany(company);
	    job.setJobDescription(jobDescription);
	    job.setLocation(location);
	    job.setRequirements(requirements);
	    job.setSalaryRange(salaryRange); // Use the existing employer entity

	    return jobemp.save(job); // Save the updated job and return it
	}




	public JobListing getJobsbyid(int id) throws IDnotfoundException {
	    return jobemp.findById(id).orElseThrow(() -> new IDnotfoundException("Job not found with ID: " + id));
	}


	public Employer Register(Employer emp) {
		
			if (Repemp.findById(emp.getEmpid()).isPresent()) {
				return null;
			}
			Employer e1=Repemp.save(emp);
			return e1;
		}

	public String updateApplicationStatus(Integer id, String status) throws IDnotfoundException {
		Application app=Appemp.findById(id).orElseThrow(() -> new IDnotfoundException("Application not found with ID: " + id));
		app.setStatus(status);
		Appemp.save(app);
		return "updated";
	}

	

	    public List<JobListing> getJobListingsByEmployerId(int empid) {
	        return jobemp.findByEmployer_Empid(empid);
	    }

	    public JobListing UpdateInfoJOB(int jobid, JobListing jobDetails) throws IDnotfoundException {
	        // Retrieve the job from the database by ID
	        Optional<JobListing> jobOptional = jobemp.findById(jobid);
	        if (jobOptional.isPresent()) {
	            JobListing existingJob = jobOptional.get();

	            // Update the job details
	            existingJob.setJobTitle(jobDetails.getJobTitle());
	            existingJob.setCompany(jobDetails.getCompany());
	            existingJob.setJobDescription(jobDetails.getJobDescription());
	            existingJob.setLocation(jobDetails.getLocation());
	            existingJob.setRequirements(jobDetails.getRequirements());
	            existingJob.setSalaryRange(jobDetails.getSalaryRange());

	            // Save the updated job back to the database
	            return jobemp.save(existingJob); // Return the updated job entity
	        } else {
	            throw new IDnotfoundException("Job ID not found: " + jobid);
	        }
	    }

	    public Employer getEmployeeByEmail(String email) {
	        return Repemp.findByEmail(email);
	    }

	    public Employer updateEmployeeProfile(String email, String companyName, String companyDescription, String contactNumber, String address, String website, int empid) {
	        // Fetch the employer using the email
	        Employer employer = Repemp.findByEmail(email);
	        
	        if (employer != null) {
	            // Update employer details
	            employer.setCompanyName(companyName);
	            employer.setCompanyDescription(companyDescription);
	            employer.setContactNumber(contactNumber);
	            employer.setAddress(address);
	            employer.setWebsite(website);
	            employer.setEmpid(empid);
	            
	            // Save the updated employer back to the database
	            return Repemp.save(employer);
	        }
	        
	        return null; // Return null if not found
	    }


		public List<Employer> getemp() {
			return Repemp.findAll();
		}
		
		public byte[] getResumeByApplicationId(int applicationId) throws IDnotfoundException {
	        return Appemp.findById(applicationId)
	            .map(Application::getResume) // Get the resume directly
	            .orElseThrow(() -> new IDnotfoundException("Application not found with ID: " + applicationId));
	    }
		
		public List<Application> findByJobId(int jobId) throws Exception {
			List<Application> app1 = Appemp.findByJobListingJobId(jobId);
			if (app1 == null || app1.isEmpty()) {
				throw new Exception("No applications found for job  ID: " + jobId);
			}
			return app1;
			
		}
		
		public List<Employer> getempd() {
			List<Employer> l=(List)Repemp.findAll();
			return l;
		}
		
}
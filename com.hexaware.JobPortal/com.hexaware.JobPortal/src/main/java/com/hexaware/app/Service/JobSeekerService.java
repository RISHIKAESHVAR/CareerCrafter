package com.hexaware.app.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.app.Dao.ApplicationRepository;
import com.hexaware.app.Dao.JobListingRepository;
import com.hexaware.app.Dao.JobSeekerRepository;
import com.hexaware.app.Dao.ResumeRepository;
import com.hexaware.app.Entity.Application;
import com.hexaware.app.Entity.JobListing;
import com.hexaware.app.Entity.JobSeeker;
import com.hexaware.app.Entity.Resume;
import com.hexaware.app.Exception.IDnotfoundException;

@Service
public class JobSeekerService {
	@Autowired
	JobSeekerRepository Repjob;
	@Autowired
	JobListingRepository jobemp;
	@Autowired
	ApplicationRepository Appemp;
	@Autowired
	ResumeRepository Resjob;

	public JobSeeker createJob(JobSeeker jobSeeker) {
		if (Repjob.findById(jobSeeker.getJobSeekerId()).isPresent()) {
			return null;
		}
		JobSeeker job = Repjob.save(jobSeeker);
		return job;

	}

	public JobListing getJobsbyid(int id) throws IDnotfoundException {
		return jobemp.findById(id).orElseThrow(() -> new IDnotfoundException("Job not found with ID: " + id));

	}

	public List<JobListing> searchJobs(String location, String jobTitle, String company) {
		
		List<JobListing> jobs;

		if (location != null && jobTitle != null && company != null) {
			jobs = jobemp.findByLocationAndJobTitleAndCompany(location, jobTitle, company);
		} else if (location != null && jobTitle != null) {
			jobs = jobemp.findByLocationAndJobTitle(location, jobTitle);
		} else if (location != null && company != null) {
			jobs = jobemp.findByLocationAndCompany(location, company);
		} else if (jobTitle != null && company != null) {
			jobs = jobemp.findByJobTitleAndCompany(jobTitle, company);
		} else if (location != null) {
			jobs = jobemp.findByLocation(location);
		} else if (jobTitle != null) {
			jobs = jobemp.findByJobTitle(jobTitle);
		} else if (company != null) {
			jobs = jobemp.findByCompany(company);
		} else {
			jobs = new ArrayList<>();
		}

		return jobs;
	}

	public Application applyToJob(Application application) {
	    // Check if the job seeker has already applied to the specific job
	    Optional<Application> existingApplication = Appemp.findByJobListingAndJobSeeker(application.getJobListing(), application.getJobSeeker());
	    
	    if (existingApplication.isPresent()) {
	        return null; // Job seeker has already applied to this job
	    }
	    
	    return Appemp.save(application);
	}


	public List<Application> findByJobSeekerId(int jobSeekerId) throws Exception {
		List<Application> applications = Appemp.findByJobSeekerJobSeekerId(jobSeekerId);
		if (applications == null || applications.isEmpty()) {
			throw new Exception("No applications found for job seeker ID: " + jobSeekerId);
		}
		return applications;
	}

	public List<JobListing> searchJobsbycompany(String company) {
		List<JobListing> l = (List<JobListing>) jobemp.findByCompany(company);
		return l;

	}

	public List<Application> findByJobId(int jobId) throws Exception {
		List<Application> app1 = Appemp.findByJobListingJobId(jobId);
		if (app1 == null || app1.isEmpty()) {
			throw new Exception("No applications found for job  ID: " + jobId);
		}
		return app1;

	}

	public Application findByApplicationId(int applicationId) throws IDnotfoundException  {

		return Appemp.findById(applicationId).orElseThrow(() -> new IDnotfoundException("Application not found with ID: " + applicationId));
	}

	public List<Application> getallApplications() {
		List<Application> l = (List) Appemp.findAll();
		return l;
	}

	public JobSeeker updateProfileByEmail(String email, JobSeeker jobSeeker) throws IDnotfoundException {
	    // Fetch the JobSeeker using the email
	    JobSeeker existingJobSeeker = Repjob.findByEmail(email);
	    
	    // Check if the JobSeeker exists
	    if (existingJobSeeker == null) {
	        throw new IDnotfoundException("JobSeeker not found with email: " + email);
	    }

	    // Update the existing JobSeeker with the new information
	    existingJobSeeker.setFirstName(jobSeeker.getFirstName());
	    existingJobSeeker.setLastName(jobSeeker.getLastName());
	    existingJobSeeker.setEmail(email); // Ensure email remains the same
	    existingJobSeeker.setMobileNumber(jobSeeker.getMobileNumber());
	    existingJobSeeker.setAddress(jobSeeker.getAddress());
	    existingJobSeeker.setDob(jobSeeker.getDob());

	    // Save the updated JobSeeker entity
	    Repjob.save(existingJobSeeker);

	    return existingJobSeeker; // Return the updated JobSeeker
	}



	private final String uploadDir = "uploads/";
	public String saveResume(MultipartFile file, int jobSeekerId) throws IOException {
		
	

	  
	        // Generate a unique file name
	        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
	        String filePath = uploadDir + fileName;

	        // Ensure the upload directory exists
	        File directory = new File(uploadDir);
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }

	        // Save the file to the server
	        Files.copy(file.getInputStream(), Paths.get(filePath));

	        // Create a Resume entity and save it to the database
	        JobSeeker jobSeeker = Repjob.findById(jobSeekerId)
	                .orElseThrow(() -> new RuntimeException("JobSeeker not found"));

	        Resume resume = new Resume();
	        resume.setFilePath(filePath);
	        resume.setJobSeeker(jobSeeker);

	        return Resjob.save(resume).getFilePath();
	    }

	public List<JobListing> getJobs() {
		return jobemp.findAll();	}

	public JobSeeker getJobseeker(int jobseekerid) throws IDnotfoundException {
		
		return Repjob.findById(jobseekerid).orElseThrow(() -> new IDnotfoundException("Jobseeker not found with ID: " + jobseekerid));
	}


	public JobSeeker getJobseekerbyemail(String email) {
		return Repjob.findByEmail(email);
	}

	public List<JobSeeker> getjobseeker() {
		return Repjob.findAll();
	}
	
	public boolean hasApplied(int jobSeekerId, int jobId) {
	    JobSeeker jobSeeker = new JobSeeker();
	    jobSeeker.setJobSeekerId(jobSeekerId);

	    JobListing jobListing = new JobListing();
	    jobListing.setJobId(jobId);

	    // Check if an application already exists
	    return Appemp.findByJobListingAndJobSeeker(jobListing, jobSeeker).isPresent();
	}

	
	public Application uploadResume(MultipartFile resume, int jobSeekerId, int jobId) throws IOException {
	    Application application = new Application();

	    byte[] resumeBytes = resume.getBytes();
	    application.setResume(resumeBytes);
	    application.setAppliedDate(LocalDate.now());
	    application.setStatus("Applied");

	    // Set job seeker and job listing
	    JobSeeker jobSeeker = new JobSeeker();
	    jobSeeker.setJobSeekerId(jobSeekerId);
	    application.setJobSeeker(jobSeeker);

	    JobListing jobListing = new JobListing();
	    jobListing.setJobId(jobId);
	    application.setJobListing(jobListing);

	    // Save application to the repository
	    return Appemp.save(application);
	}
	
	



	

}
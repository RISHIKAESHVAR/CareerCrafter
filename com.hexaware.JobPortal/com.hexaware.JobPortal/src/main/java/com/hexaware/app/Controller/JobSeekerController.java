package com.hexaware.app.Controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.app.Entity.Application;
import com.hexaware.app.Entity.JobListing;
import com.hexaware.app.Entity.JobSeeker;
import com.hexaware.app.Exception.IDnotfoundException;
import com.hexaware.app.Service.JobSeekerService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/jobseeker")
public class JobSeekerController {
	@Autowired
	JobSeekerService jobSer;

	// create profile
	@PostMapping("/createProfile")
	public ResponseEntity<JobSeeker> createJobSeeker(@RequestBody JobSeeker jobSeeker) {
		JobSeeker createdJobSeeker = jobSer.createJob(jobSeeker);
		if (createdJobSeeker == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(createdJobSeeker, HttpStatus.CREATED);
		}
	}
	@PutMapping("/updateprofilebyemail/{email}")
	public ResponseEntity<JobSeeker> updateProfileByEmail(
	        @PathVariable("email") String email,
	        @RequestBody JobSeeker jobSeeker) throws IDnotfoundException {
	    
	    JobSeeker updatedJobseeker = jobSer.updateProfileByEmail(email, jobSeeker);
	    
	    return new ResponseEntity<>(updatedJobseeker, HttpStatus.OK);
	}



	// get jobseeker for jobseeker id
		@GetMapping("/getJobseeker/{jobseekerid}")
		public ResponseEntity<JobSeeker> getJobseeker(@PathVariable int jobseekerid) throws IDnotfoundException {
			JobSeeker job = jobSer.getJobseeker(jobseekerid);
			
			return new ResponseEntity<>(job, HttpStatus.OK);
		}
		
		// get jobseeker for vby email
				@GetMapping("/getJobseekerbyemail/{email}")
				public ResponseEntity<JobSeeker> getJobseekeremail(@PathVariable("email") String email) throws IDnotfoundException {
					JobSeeker job = jobSer.getJobseekerbyemail(email);
					
					return new ResponseEntity<>(job, HttpStatus.OK);
				}

	// search by job id
	@GetMapping("/getJobsbyid/{jobid}")
	public ResponseEntity<JobListing> getJobsbyid(@PathVariable("jobid") int jobid) throws IDnotfoundException {
		JobListing job = jobSer.getJobsbyid(jobid);
		
		return new ResponseEntity<>(job, HttpStatus.OK);
	}
	@GetMapping("/getJobs")
	public ResponseEntity<List<JobListing>> getJobs() throws IDnotfoundException {
		List<JobListing> job = jobSer.getJobs();
		
		return new ResponseEntity<>(job, HttpStatus.OK);
	}
	// Search Jobs by location and job title and company
	@GetMapping("/SearchJobs")
	public ResponseEntity<List<JobListing>> searchJobs(
	    @RequestParam(name = "location", required = false) String location,
	    @RequestParam(name = "jobTitle", required = false) String jobTitle,
	    @RequestParam(name = "company", required = false) String company) {

	    List<JobListing> jobs = jobSer.searchJobs(location, jobTitle, company);
	    if (jobs.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } else {
	        return new ResponseEntity<>(jobs, HttpStatus.CREATED);
	    }
	}

	// search jobs by company
	@GetMapping("/SearchJobs/{company}")
	public ResponseEntity<List<JobListing>> searchJobsbycompany(@PathVariable String company) {
		List<JobListing> jobs = jobSer.searchJobsbycompany(company);
		if (jobs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(jobs, HttpStatus.CREATED);
		}
	}
	@PostMapping("/applications")
	public ResponseEntity<?> applyToJob(@RequestBody Application application) {
	    Application createdApplication = jobSer.applyToJob(application);
	    if (createdApplication == null) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("You have already applied to this job.");
	    }
	    return ResponseEntity.status(HttpStatus.CREATED).body(createdApplication);
	}

	
	// get all application by application id
	@GetMapping("/applications/{ApplicationId}")
	public ResponseEntity<Application> getApplicationsByapplicationid(
			@PathVariable("ApplicationId") int Applicationid) throws IDnotfoundException {
		
			Application applications = jobSer.findByApplicationId(Applicationid);
			return new ResponseEntity<>(applications, HttpStatus.OK);
		
	}

	// get all applications-jobseeker id
	@GetMapping("/applications/jobseekers/{jobseekerid}")
	public ResponseEntity<List<Application>> getApplicationsByJobSeeker(@PathVariable("jobseekerid") int jobSeekerId) throws Exception {
		
			List<Application> applications = jobSer.findByJobSeekerId(jobSeekerId);
			return new ResponseEntity<>(applications, HttpStatus.OK);
		
	}

	// view application status
	@GetMapping("/viewapplications")
	public ResponseEntity<List<Application>> getallApplication() {
		List<Application> users = jobSer.getallApplications();
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
    @PostMapping("/uploadResume")
    public ResponseEntity<?> uploadResume(@RequestParam("file")  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Resume file", content = @Content(mediaType = "multipart/form-data", schema = @Schema(type = "string", format = "binary"))) MultipartFile file,
                                           @RequestParam("jobSeekerId") int jobSeekerId) {
        try {
            String filePath = jobSer.saveResume(file, jobSeekerId);
            return new ResponseEntity<>(filePath, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getalljobseeker")
	public ResponseEntity<List<JobSeeker>> getJobSeekerByEmail(@RequestParam("email") String email) {
		 List<JobSeeker> employees = jobSer.getjobseeker();
		    for (JobSeeker employee : employees) {
		        if (employee.getEmail().equals(email)) {
		            return new ResponseEntity<>(Collections.singletonList(employee), HttpStatus.OK); // Return the matched employee
		        }
		    }
		   
		    // If no employee found with the given email
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    
    
    @PostMapping("/applications/upload")
    public ResponseEntity<String> uploadResume(
            @RequestParam("resume") MultipartFile resume,
            @RequestParam("jobSeekerId") int jobSeekerId,
            @RequestParam("jobId") int jobId) {
        try {
            // Check if the job seeker has already applied to this job
            if (jobSer.hasApplied(jobSeekerId, jobId)) {
                return new ResponseEntity<>("You have already applied to this job.", HttpStatus.BAD_REQUEST);
            }

            // Proceed to upload the resume if no previous application exists
            jobSer.uploadResume(resume, jobSeekerId, jobId);
            return new ResponseEntity<>("Application submitted successfully!", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload resume: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    


    
    

}
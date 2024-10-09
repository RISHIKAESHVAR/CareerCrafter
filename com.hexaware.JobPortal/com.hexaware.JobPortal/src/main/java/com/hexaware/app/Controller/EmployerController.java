package com.hexaware.app.Controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.app.Entity.Application;
import com.hexaware.app.Entity.Employer;
import com.hexaware.app.Entity.JobListing;

import com.hexaware.app.Exception.IDnotfoundException;
import com.hexaware.app.Service.EmployerService;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/employer")
public class EmployerController {
	@Autowired
	EmployerService empSer;

	@PostMapping("/post")
	public ResponseEntity<JobListing> postJob(@RequestBody JobListing job) {
		JobListing createdJob = empSer.postJob(job);
		if (createdJob == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
		}

	}
	@PostMapping("/addEmployeeProfile")
	public ResponseEntity<Employer> Register(@RequestBody Employer emp) {
		Employer e= empSer.Register(emp);
		if (e == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(e, HttpStatus.CREATED);
		}

	}
	@GetMapping("/getallemployees")
	public ResponseEntity<List<Employer>> getAllEmployees(@RequestParam("email") String email) {
	    List<Employer> employees = empSer.getemp(); // Fetch all employees
	    
	    // Find the employee with the matching email
	    for (Employer employee : employees) {
	        if (employee.getEmail().equals(email)) {
	            return new ResponseEntity<>(Collections.singletonList(employee), HttpStatus.OK); // Return the matched employee
	        }
	    }
	    
	    // If no employee found with the given email
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
//	@GetMapping("/getallemployees")
//	public ResponseEntity<List<Employer>> getemp()  {
//		List<Employer> job = empSer.getemp();
//		
//		return new ResponseEntity<>(job, HttpStatus.OK);
//	}
	@GetMapping("/getEmployeebyemail/{email}")
    public ResponseEntity<Employer> getEmployeeByEmail(@PathVariable("email") String email) {
        Employer employee = empSer.getEmployeeByEmail(email);
      
            return ResponseEntity.ok(employee);
        
	}
	@PutMapping("/updateprofilebyemail/{email}")
	public ResponseEntity<Employer> updateEmployeeProfile(
	        @PathVariable("email") String email,
	        @RequestParam("companyName") String companyName,
	        @RequestParam("companyDescription") String companyDescription,
	        @RequestParam("contactNumber") String contactNumber,
	        @RequestParam("address") String address,
	        @RequestParam("website") String website,
	        @RequestParam("empid") int empid) {
	    
	    Employer updatedEmployer = empSer.updateEmployeeProfile(email, companyName, companyDescription, contactNumber, address, website, empid);
	    if (updatedEmployer == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<>(updatedEmployer, HttpStatus.OK);
	}

	@GetMapping("/getJobs")
	public ResponseEntity<List<JobListing>> getJobs() {
		List<JobListing> users = empSer.getJobs();
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/getJobsbyid/{jobid}")
	public ResponseEntity<JobListing> getJobsbyid(@PathVariable("jobid") int jobid) throws IDnotfoundException {
		JobListing job = empSer.getJobsbyid(jobid);
		
		return new ResponseEntity<>(job, HttpStatus.OK);
	}
	

    @GetMapping("/{empId}/jobListings")
    public ResponseEntity<List<JobListing>> getJobListingsByEmployerId(@PathVariable("empId") int empId) {
        List<JobListing> jobListings =empSer.getJobListingsByEmployerId(empId);
        return ResponseEntity.ok(jobListings);
    }
	@DeleteMapping("/removeJobs/{jobid}")
	public JobListing removeJobs(@PathVariable("jobid") int jobid) throws IDnotfoundException {
		JobListing j2 = empSer.removeJob(jobid);
		return j2;

	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployer(@PathVariable int id) {
        try {
            Employer deletedEmployer = empSer.removeEmployer(id);
            return ResponseEntity.ok("Deleted Employer: " + deletedEmployer.getCompanyName());
        } catch (IDnotfoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

	@PutMapping("/updatedatabyjobid/{jobid}")
    public ResponseEntity<JobListing> updateData(
            @PathVariable("jobid") int jobid,
            @RequestParam("jobTitle") String jobTitle,
            @RequestParam("company") String company,
            @RequestParam("jobDescription") String jobDescription,
            @RequestParam("location") String location,
            @RequestParam("requirements") String requirements,
            @RequestParam("salaryRange") Double salaryRange) throws IDnotfoundException {

        JobListing updatedJob = empSer.updateInfoJob(jobid, jobTitle, company, jobDescription, location, requirements, salaryRange);
        return new ResponseEntity<>(updatedJob, HttpStatus.OK); // Respond with the updated job and OK status
    }


	@PutMapping("/updateJOB/{jobid}/{jobtitle}")
	public ResponseEntity<String> updatejob(@PathVariable("jobid") int jobid, @PathVariable("jobtitle") String jobTitle) throws IDnotfoundException {
		String r = empSer.updatejob(jobid, jobTitle);

		
			return new ResponseEntity<>(r, HttpStatus.CREATED);
		

	}
	// manage application status
			@PutMapping("/status/{id}")
		    public ResponseEntity<String> updateApplicationStatus(@PathVariable("id") Integer id, @RequestParam("status") String status) throws IDnotfoundException {
		        String j= empSer.updateApplicationStatus(id, status);
		        
					return new ResponseEntity<>(j, HttpStatus.CREATED);
				
	}
			
			@GetMapping("applications/{applicationId}/resume")
		    public ResponseEntity<byte[]> getResume(@PathVariable("applicationId") int applicationId) throws IDnotfoundException {
		        byte[] resumeData = empSer.getResumeByApplicationId(applicationId);
		        if (resumeData == null) {
		            return ResponseEntity.notFound().build();
		        }
		        HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // Generic for binary data
		        headers.setContentDispositionFormData("attachment", "resume.pdf"); // Set the file name
		        return new ResponseEntity<>(resumeData, headers, HttpStatus.OK);
		    }
		    
		    @GetMapping("/applications/jobListing/{jobid}")
			public ResponseEntity<List<Application>> getApplicationsByJobId(@PathVariable("jobid") int jobId) throws Exception {
				
					List<Application> applications = empSer.findByJobId(jobId);
					return new ResponseEntity<>(applications, HttpStatus.OK);
				
			}
		    
		    
		    @GetMapping("/getemployees")
			public ResponseEntity<List<Employer>> getemp() {
				List<Employer> users = empSer.getempd();
				if (users.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(users, HttpStatus.OK);
			}
			

}
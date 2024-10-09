package com.hexaware.app.Entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
public class JobListing {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	int jobId;
	String jobTitle;
	String company;
	String jobDescription;
	String location;
	String requirements;
	Double salaryRange;
	
	LocalDate postedDate;
	
	@OneToMany(mappedBy = "jobListing", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Application> applications;
	
	 @ManyToOne
	 @JsonBackReference
	 @JoinColumn(name = "employer_empid", referencedColumnName = "empid")
	    private Employer employer;
	public Employer getEmployer() {
		return employer;
	}
	
	



	public String getCompany() {
		return company;
	}



	public void setCompany(String company) {
		this.company = company;
	}



	public void setEmployer(Employer employer) {
		this.employer = employer;	}



	
	@PrePersist
    protected void onCreate() {
        if (postedDate == null) {
            postedDate = LocalDate.now();
        }
    }
	

	public JobListing(int jobId, String jobTitle, String company, String jobDescription, String location,
			String requirements, Double salaryRange, LocalDate postedDate, Employer employer) {
		super();
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.company = company;
		this.jobDescription = jobDescription;
		this.location = location;
		this.requirements = requirements;
		this.salaryRange = salaryRange;
		this.postedDate = postedDate;
		this.employer = employer;
	}







	public JobListing(int jobId, String jobTitle, String company, String jobDescription, String location,
			String requirements, Double salaryRange, LocalDate postedDate) {
		super();
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.company = company;
		this.jobDescription = jobDescription;
		this.location = location;
		this.requirements = requirements;
		this.salaryRange = salaryRange;
		this.postedDate = postedDate;
	}



	public JobListing() {
		super();
	}



	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public Double getSalaryRange() {
		return salaryRange;
	}

	public void setSalaryRange(Double salaryRange) {
		this.salaryRange = salaryRange;
	}

	public LocalDate getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(LocalDate postedDate) {
		this.postedDate = postedDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(company, employer, jobDescription, jobId, jobTitle, location, postedDate, requirements,
				salaryRange);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobListing other = (JobListing) obj;
		return Objects.equals(company, other.company) && Objects.equals(employer, other.employer)
				&& Objects.equals(jobDescription, other.jobDescription) && jobId == other.jobId
				&& Objects.equals(jobTitle, other.jobTitle) && Objects.equals(location, other.location)
				&& Objects.equals(postedDate, other.postedDate) && Objects.equals(requirements, other.requirements)
				&& Objects.equals(salaryRange, other.salaryRange);
	}



	@Override
	public String toString() {
		return "JobListing [jobId=" + jobId + ", jobTitle=" + jobTitle + ", jobDescription=" + jobDescription
				+ ", location=" + location + ", requirements=" + requirements + ", salaryRange=" + salaryRange
				+ ", PostedDate=" + postedDate + "]";
	}

}

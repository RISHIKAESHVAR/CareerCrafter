package com.hexaware.app.Dto;

import java.time.LocalDate;

public class JobListingDTO {
    private int jobId;
    private String jobTitle;
    private String company;
    private String jobDescription;
    private String location;
    private String requirements;
    private Double salaryRange;
    private LocalDate postedDate;
    private int employerId;

    public JobListingDTO() {
    }

    public JobListingDTO(int jobId, String jobTitle, String company, String jobDescription, String location, String requirements, Double salaryRange, LocalDate postedDate, int employerId) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.company = company;
        this.jobDescription = jobDescription;
        this.location = location;
        this.requirements = requirements;
        this.salaryRange = salaryRange;
        this.postedDate = postedDate;
        this.employerId = employerId;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    @Override
    public String toString() {
        return "JobListingDTO [jobId=" + jobId + ", jobTitle=" + jobTitle + ", jobDescription=" + jobDescription + ", location=" + location + ", requirements=" + requirements + ", salaryRange=" + salaryRange + ", postedDate=" + postedDate + ", employerId=" + employerId + "]";
    }
}

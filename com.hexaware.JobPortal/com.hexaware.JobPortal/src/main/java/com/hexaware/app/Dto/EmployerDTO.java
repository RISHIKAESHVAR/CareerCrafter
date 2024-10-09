package com.hexaware.app.Dto;

import java.util.List;

import com.hexaware.app.Entity.JobListing;

public class EmployerDTO {
    private int empid;
    private String companyName;
    private String companyDescription;
    private String email;
    private String contactNumber;
    private String address;
    private String website;
    private List<JobListing> jobListings;

    public EmployerDTO() {
    }

    public EmployerDTO(int empid, String companyName, String companyDescription, String email, String contactNumber, String address, String website, List<JobListing> jobListings) {
        this.empid = empid;
        this.companyName = companyName;
        this.companyDescription = companyDescription;
        this.email = email;
        this.contactNumber = contactNumber;
        this.address = address;
        this.website = website;
        this.jobListings = jobListings;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<JobListing> getJobListings() {
        return jobListings;
    }

    public void setJobListings(List<JobListing> jobListings) {
        this.jobListings = jobListings;
    }

    @Override
    public String toString() {
        return "EmployerDTO [empid=" + empid + ", companyName=" + companyName + ", companyDescription=" + companyDescription + ", email=" + email + ", contactNumber=" + contactNumber + ", address=" + address + ", website=" + website + ", jobListings=" + jobListings + "]";
    }
}

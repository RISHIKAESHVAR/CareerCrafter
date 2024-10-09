package com.hexaware.app.Dto;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.app.Entity.Application;

public class JobSeekerDTO {
    private int jobSeekerId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dob;
    private String mobileNumber;
    private String address;
    private List<Application> applications;

    public JobSeekerDTO() {
    }

    public JobSeekerDTO(int jobSeekerId, String firstName, String lastName, String email, LocalDate dob, String mobileNumber, String address, List<Application> applications) {
        this.jobSeekerId = jobSeekerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.applications = applications;
    }

    public int getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "JobSeekerDTO [jobSeekerId=" + jobSeekerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", dob=" + dob + ", mobileNumber=" + mobileNumber + ", address=" + address + ", applications=" + applications + "]";
    }
}

package com.hexaware.app.Entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class JobSeeker {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	int jobSeekerId;
	String firstName;
	String lastName;
	@Column(unique = true)
	String email;
	LocalDate dob;
	String mobileNumber;
	String address;
   
	@OneToMany(mappedBy = "jobSeeker")
	@JsonIgnore
    private List<Application> applications;
	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public JobSeeker(int jobSeekerId, String firstName, String lastName, String email, LocalDate dob, String mobileNumber,
			String address) {
		super();
		this.jobSeekerId = jobSeekerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dob = dob;
		this.mobileNumber = mobileNumber;
		this.address = address;
	}

	public JobSeeker() {
		super();
	}

	

	public JobSeeker(String firstName, String lastName, String email, LocalDate dob, String mobileNumber,
			String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dob = dob;
		this.mobileNumber = mobileNumber;
		this.address = address;
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

	@Override
	public String toString() {
		return "JobSeeker [jobSeekerId=" + jobSeekerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", dob=" + dob + ", mobileNumber=" + mobileNumber + ", address=" + address + "]";
	}

}

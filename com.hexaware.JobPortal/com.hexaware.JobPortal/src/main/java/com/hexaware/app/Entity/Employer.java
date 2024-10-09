package com.hexaware.app.Entity;



import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Employer {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	int empid;
	String companyName;
	String companyDescription;
	@Column(unique = true)
	String email;
	String contactNumber;
	String address;
	String website;
	 @OneToMany(mappedBy = "employer")
	 @JsonManagedReference
	    private List<JobListing> jobListings;
	 

	public List<JobListing> getJobListings() {
		return jobListings;
	}

	public void setJobListings(List<JobListing> jobListings) {
		this.jobListings = jobListings;
	}

	public int getEmpid() {
		return empid;
	}
	
	public Employer(int empid) {
        this.empid = empid;
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

	public Employer() {
		super();
	}

	public Employer(int empid, String companyName, String companyDescription, String email, String contactNumber,
			String address, String website) {
		super();
		this.empid = empid;
		this.companyName = companyName;
		this.companyDescription = companyDescription;
		this.email = email;
		this.contactNumber = contactNumber;
		this.address = address;
		this.website = website;
	}
	public Employer( String companyName, String companyDescription, String email, String contactNumber,
			String address, String website) {
		super();
		
		this.companyName = companyName;
		this.companyDescription = companyDescription;
		this.email = email;
		this.contactNumber = contactNumber;
		this.address = address;
		this.website = website;
	}



	@Override
	public int hashCode() {
		return Objects.hash(address, companyDescription, companyName, contactNumber, email, empid, website);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employer other = (Employer) obj;
		return Objects.equals(address, other.address) && Objects.equals(companyDescription, other.companyDescription)
				&& Objects.equals(companyName, other.companyName) && Objects.equals(contactNumber, other.contactNumber)
				&& Objects.equals(email, other.email) && empid == other.empid && Objects.equals(website, other.website);
	}

	@Override
	public String toString() {
		return "Employer [empid=" + empid + ", companyName=" + companyName + ", companyDescription="
				+ companyDescription + ", email=" + email + ", contactNumber=" + contactNumber + ", address=" + address
				+ ", website=" + website + "]";
	}

}

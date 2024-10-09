package com.hexaware.app.Entity;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
@Entity
@Table(name = "application", uniqueConstraints = @UniqueConstraint(columnNames = {"job_seeker_job_seeker_id", "job_listing_job_id"}))
public class Application {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	int applicationId;
	@Column(name = "status")
	String status;
	@Column(name = "applied_date")
	LocalDate appliedDate;
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JsonIgnoreProperties({"applications"})
	 @JoinColumn(name = "job_seeker_job_seeker_id")
	    private JobSeeker jobSeeker;
	    @ManyToOne
	    @JoinColumn(name = "job_listing_job_id")
	    private JobListing jobListing;
	    	public JobSeeker getJobSeeker() {
			return jobSeeker;
		}
	    	 @Lob
	    	 @Column(name = "resume", nullable = false)
	    	    private byte[] resume;
		public byte[] getResume() {
				return resume;
			}
			public void setResume(byte[] resume) {
				this.resume = resume;
			}
		public void setJobSeeker(JobSeeker jobSeeker) {
			this.jobSeeker = jobSeeker;
		}
		public JobListing getJobListing() {
			return jobListing;
		}
		public void setJobListing(JobListing jobListing) {
			this.jobListing = jobListing;
		}
	public Application(int applicationId, String status, LocalDate appliedDate) {
		super();
		this.applicationId = applicationId;
		this.status = status;
		this.appliedDate = appliedDate;
	}
	public Application() {
		super();
	}
	@PrePersist
   protected void onCreate() {
       if (appliedDate == null) {
       	appliedDate = LocalDate.now();
       }
   }
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}
	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", status=" + status + ", appliedDate=" + appliedDate
				+ "]";
	}
}

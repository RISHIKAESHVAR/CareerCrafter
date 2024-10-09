package com.hexaware.app.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Resume {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	int resumeId;
	String filePath;
	LocalDate uploadDate;
	@ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false) // Specifies foreign key column
    private JobSeeker jobSeeker;
	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public Resume(int resumeId, String filePath, LocalDate uploadDate) {
		super();
		this.resumeId = resumeId;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
	}

	public Resume() {
		super();
	}

	public int getResumeId() {
		return resumeId;
	}

	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public LocalDate getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDate uploadDate) {
		this.uploadDate = uploadDate;
	}
	@PrePersist
    protected void onCreate() {
        if (uploadDate == null) {
        	uploadDate = LocalDate.now();
        }
    }
	@Override
	public String toString() {
		return "Resume [resumeId=" + resumeId + ", filePath=" + filePath + ", uploadDate=" + uploadDate + "]";
	}

}

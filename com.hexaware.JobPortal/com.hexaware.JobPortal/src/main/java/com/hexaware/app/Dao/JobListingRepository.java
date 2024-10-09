package com.hexaware.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.app.Entity.JobListing;
@Repository
public interface JobListingRepository extends JpaRepository<JobListing , Integer>{
	@Modifying
	@Query(value="UPDATE JobListing  SET jobTitle = :jt, jobDescription = :jD,"
			+ "location = :l, requirements = :req, salaryRange = :sR,"
			+ "   j.PostedDate = :pd WHERE j.jobId = :jid",nativeQuery=true)
    int updateJobListing(@Param("jid") Integer jobId, @Param("jt") String jobTitle,@Param("jD") String jobDescription,@Param("l") String location,@Param("req") String requirements, @Param("sR") Double salaryRange, @Param("pd") String postedDate);

	
    List<JobListing> findByLocation(String location);
    List<JobListing> findByJobTitle(String jobTitle);


	List<JobListing> findByCompany(String company);


	List<JobListing> findByLocationAndJobTitleAndCompany(String location, String jobTitle, String company);


	List<JobListing> findByLocationAndJobTitle(String location, String jobTitle );


	List<JobListing> findByLocationAndCompany(String location, String company);


	List<JobListing> findByJobTitleAndCompany(String jobTitle, String company);


	 List<JobListing> findByEmployer_Empid(int empid);

}

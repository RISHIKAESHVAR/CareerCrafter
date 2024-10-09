package com.hexaware.app.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.app.Entity.Employer;

import jakarta.transaction.Transactional;

@Repository
public interface EmployerRepository extends JpaRepository<Employer , Integer>{

	Employer findByEmail(String email);

	@Modifying
    @Transactional
    @Query("DELETE FROM Employer e WHERE e.id = :id")
	void deleteEmployer(Employer emp);

}

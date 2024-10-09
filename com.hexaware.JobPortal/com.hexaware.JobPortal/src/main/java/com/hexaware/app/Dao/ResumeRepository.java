package com.hexaware.app.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.app.Entity.Resume;
@Repository
public interface ResumeRepository extends JpaRepository<Resume , Integer>{

}

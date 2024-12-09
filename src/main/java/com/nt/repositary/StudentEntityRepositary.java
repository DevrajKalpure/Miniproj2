package com.nt.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.StudentEntity;

public interface StudentEntityRepositary extends JpaRepository<StudentEntity, Integer>{
	
	

}

package com.nt.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CoursesEntity;

public interface CoursesRepositary extends JpaRepository<CoursesEntity, Integer>{
	
	

}

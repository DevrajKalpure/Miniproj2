package com.nt.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.EnquiryStatusEntity;

public interface EnquiryStatusRepositary  extends JpaRepository<EnquiryStatusEntity, Integer>{

}

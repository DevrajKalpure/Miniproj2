package com.nt.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.UserDtlsEntity;
import java.util.List;



public interface UserDtlsRepositary extends JpaRepository<UserDtlsEntity, Integer>{
	
	
	public UserDtlsEntity findByEmail(String email);
	public UserDtlsEntity findByEmailAndPwd(String email, String pwd);

}

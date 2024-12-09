package com.nt.service;

import java.util.List;

import com.nt.binding.DashBoardResponse;
import com.nt.binding.EnquiryForm;
import com.nt.binding.EnquirySearchCriteria;
import com.nt.entity.StudentEntity;

public interface IEnquiryService {
	
	 DashBoardResponse getDashBoardResponse(Integer userId);

	 //dropdown in addEnquiry
	  List<String> getCourses();
		 //dropdown in addEnquiry
	 List<String> enquiryStatus();
	 
	 public boolean saveEnqForm(EnquiryForm form);
	 
	 public List<StudentEntity> getEnquries();
	 
	 public List<StudentEntity> getFilterData(EnquirySearchCriteria criteria,Integer userId);
}

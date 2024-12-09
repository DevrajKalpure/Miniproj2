package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.DashBoardResponse;
import com.nt.binding.EnquiryForm;
import com.nt.binding.EnquirySearchCriteria;
import com.nt.entity.CoursesEntity;
import com.nt.entity.EnquiryStatusEntity;
import com.nt.entity.StudentEntity;
import com.nt.entity.UserDtlsEntity;
import com.nt.repositary.CoursesRepositary;
import com.nt.repositary.EnquiryStatusRepositary;
import com.nt.repositary.StudentEntityRepositary;
import com.nt.repositary.UserDtlsRepositary;

import jakarta.servlet.http.HttpSession;

@Service
public class EnquiruServiceImpl implements IEnquiryService {

	@Autowired
	private UserDtlsRepositary userRepo;

	@Autowired
	private CoursesRepositary courseRepo;

	@Autowired
	private StudentEntityRepositary studentRepo;

	@Autowired
	private EnquiryStatusRepositary statusRepo;

	@Autowired
	private HttpSession session;

	DashBoardResponse dashBoardResponse = new DashBoardResponse();

	@Override
	public DashBoardResponse getDashBoardResponse(Integer userId) {

		Optional<UserDtlsEntity> findById = userRepo.findById(userId);

		if (findById.isPresent()) {
			UserDtlsEntity entity = findById.get();

			List<StudentEntity> enquires = entity.getStudentEntity();
			Integer totalCnt = enquires.size();

			Integer enrolledCnt = enquires.stream().filter(e -> e.getEnquiryStatus().equalsIgnoreCase("Enrolled"))
					.collect(Collectors.toList()).size();

			Integer lostCnt = enquires.stream().filter(e -> e.getEnquiryStatus().equalsIgnoreCase("lost"))
					.collect(Collectors.toList()).size();

			dashBoardResponse.setEnrollerdCount(enrolledCnt);
			dashBoardResponse.setLostCount(lostCnt);
			dashBoardResponse.setTotalEnquiresCount(totalCnt);
		}
		return dashBoardResponse;
	}

	@Override
	public List<String> getCourses() {

		List<CoursesEntity> findAll = courseRepo.findAll();

		List<String> name = new ArrayList<>();
		System.out.println(courseRepo.findAll());
		for (CoursesEntity entity : findAll) {
			name.add(entity.getCourseName());

		}
		return name;
	}

	@Override
	public List<String> enquiryStatus() {

		List<EnquiryStatusEntity> findAll = statusRepo.findAll();

		List<String> name = new ArrayList<>();

		System.out.println(findAll);

		for (EnquiryStatusEntity status : findAll) {
			name.add(status.getEnquiryStatus());

		}

		return name;
	}

	@Override
	public boolean saveEnqForm(EnquiryForm form) {

		StudentEntity entity = new StudentEntity();
		BeanUtils.copyProperties(form, entity);

		Integer userId = (Integer) session.getAttribute("userId");

		UserDtlsEntity userDtlsEntity = userRepo.findById(userId).get();
		entity.setUserDtlsEntity(userDtlsEntity);
		studentRepo.save(entity);

		return true;
	}

	@Override
	public List<StudentEntity> getEnquries() {
		return userRepo.findById((Integer) session.getAttribute("userId")).get().getStudentEntity();
	}

	@Override
	public List<StudentEntity> getFilterData(EnquirySearchCriteria criteria, Integer userId) {
		try {

			Optional<UserDtlsEntity> byId = userRepo.findById(userId);
			if (byId.isPresent()) {
				UserDtlsEntity user = byId.get();
				System.out.println(user);
				System.out.println();
				List<StudentEntity> enquries = user.getStudentEntity();
				System.out.println(enquries);
				System.out.println();
				if (criteria.getCourseName() != null && !"".equals(criteria.getCourseName())) {

					enquries = enquries.stream().filter(e -> e.getCourseName().equals(criteria.getCourseName()))
							.collect(Collectors.toList());
				}
				if (null != criteria.getClassMode() && !"".equals(criteria.getClassMode())) {
					enquries = enquries.stream().filter(e -> e.getClassStatus().equals(criteria.getClassMode()))
							.collect(Collectors.toList());
				}
				if (null != criteria.getEnquiryStatus() && !"".equals(criteria.getEnquiryStatus())) {

					enquries = enquries.stream().filter(e -> e.getEnquiryStatus().equals(criteria.getEnquiryStatus()))
							.collect(Collectors.toList());
				}
				System.out.println("returning " + enquries);
				return enquries;
			}
		} catch (Exception e) {
		}
		return null;
	}

}

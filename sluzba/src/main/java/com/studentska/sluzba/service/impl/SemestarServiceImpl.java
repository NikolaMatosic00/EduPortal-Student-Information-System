package com.studentska.sluzba.service.impl;

import com.studentska.sluzba.dto.administrator.OveriSemestarDTOReq;
import com.studentska.sluzba.model.Semestar;
import com.studentska.sluzba.model.Student;
import com.studentska.sluzba.model.StudentPohadja;
import com.studentska.sluzba.model.StudentPohadjaPK;
import com.studentska.sluzba.repository.SemestarRepository;
import com.studentska.sluzba.repository.StudentPohadjaRepository;
import com.studentska.sluzba.repository.StudentRepository;
import com.studentska.sluzba.security.TokenUtils;
import com.studentska.sluzba.service.SemestarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SemestarServiceImpl implements SemestarService {

	@Autowired
	SemestarRepository semestarRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	StudentPohadjaRepository studentPohadjaRepository;

	@Autowired
	TokenUtils tokenUtils;

	@Override
	public void overiSemestar(String token, int semestarId) {
		String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
		Student student = studentRepository.findOneByEmail(username);

		Semestar semestar = semestarRepository.getById(semestarId);
		StudentPohadja studentPohadja = studentPohadjaRepository.findOneByStudentAndSemestar(student, semestar);
		if (studentPohadja == null) {
			studentPohadja = new StudentPohadja();
		}

		StudentPohadjaPK id = new StudentPohadjaPK();
		id.setSemestarId(semestar.getId());
		id.setStudentId(student.getId());
		studentPohadja.setId(id);
		studentPohadja.setStudent(student);
		studentPohadja.setOveren((byte) 1);
		studentPohadja.setSemestar(semestar);
		studentPohadjaRepository.save(studentPohadja);
	}
}

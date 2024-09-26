package com.studentska.sluzba.repository;

import com.studentska.sluzba.model.Semestar;
import com.studentska.sluzba.model.Student;
import com.studentska.sluzba.model.StudentPohadja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPohadjaRepository extends JpaRepository<StudentPohadja, Integer> {
    StudentPohadja findOneByStudentAndSemestar(Student student, Semestar semestar);
}

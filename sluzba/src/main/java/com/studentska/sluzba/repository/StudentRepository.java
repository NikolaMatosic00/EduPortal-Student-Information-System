package com.studentska.sluzba.repository;

import com.studentska.sluzba.model.Predavac;
import com.studentska.sluzba.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findOneByEmail(String email);
}

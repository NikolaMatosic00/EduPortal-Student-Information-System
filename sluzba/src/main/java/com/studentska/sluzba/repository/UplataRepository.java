package com.studentska.sluzba.repository;

import com.studentska.sluzba.model.Student;
import com.studentska.sluzba.model.Uplata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UplataRepository extends JpaRepository<Uplata, Integer> {
    List<Uplata> findAllByStudent(Student student);
}

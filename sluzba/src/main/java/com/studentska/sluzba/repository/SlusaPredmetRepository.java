package com.studentska.sluzba.repository;

import com.studentska.sluzba.model.Predmet;
import com.studentska.sluzba.model.SlusaPredmet;
import com.studentska.sluzba.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlusaPredmetRepository extends JpaRepository<SlusaPredmet, Integer> {

    SlusaPredmet findOneByStudentAndPredmet(Student student, Predmet predmet);

    List<SlusaPredmet> findAllByStudent(Student student);

    List<SlusaPredmet> findAllByPredmet(Predmet predmet);
}

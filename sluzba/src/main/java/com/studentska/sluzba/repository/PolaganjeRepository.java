package com.studentska.sluzba.repository;

import com.studentska.sluzba.model.Polaganje;
import com.studentska.sluzba.model.Predmet;
import com.studentska.sluzba.model.Student;
import com.studentska.sluzba.model.TerminPolaganja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolaganjeRepository extends JpaRepository<Polaganje, Integer> {
    List<Polaganje> findAllBySlusaPredmetStudentOrderByIdDesc(Student student);
    List<Polaganje> findAllBySlusaPredmetStudentAndSlusaPredmetPredmetOrderByIdDesc(Student student, Predmet predmet);
    List<Polaganje> findAllByTerminPolaganjaOrderByIdDesc(TerminPolaganja terminPolaganja);
}

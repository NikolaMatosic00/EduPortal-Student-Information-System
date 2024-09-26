package com.studentska.sluzba.repository;

import com.studentska.sluzba.model.TerminPolaganja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerminPolaganjaRepository extends JpaRepository<TerminPolaganja, Integer> {
    List<TerminPolaganja> findAllByPravilaPolaganjaIdPredmetId(int predmetId);
}

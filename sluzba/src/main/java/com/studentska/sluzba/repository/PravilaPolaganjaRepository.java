package com.studentska.sluzba.repository;

import com.studentska.sluzba.model.PravilaPolaganja;
import com.studentska.sluzba.model.PravilaPolaganjaPK;
import com.studentska.sluzba.model.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PravilaPolaganjaRepository extends JpaRepository<PravilaPolaganja, PravilaPolaganjaPK> {
    List<PravilaPolaganja> findAllByIdPredmetId(int predmetId);
}

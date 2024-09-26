package com.studentska.sluzba.repository;

import com.studentska.sluzba.model.Obavestenje;
import com.studentska.sluzba.model.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObavestenjeRepository extends JpaRepository<Obavestenje, Integer> {
    List<Obavestenje> findAllByPredmet(Predmet predmet);
}

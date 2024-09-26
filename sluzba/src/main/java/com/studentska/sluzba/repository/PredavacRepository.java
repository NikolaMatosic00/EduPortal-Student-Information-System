package com.studentska.sluzba.repository;

import com.studentska.sluzba.model.Admin;
import com.studentska.sluzba.model.Predavac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredavacRepository extends JpaRepository<Predavac, Integer> {
    Predavac findOneByEmail(String email);
}

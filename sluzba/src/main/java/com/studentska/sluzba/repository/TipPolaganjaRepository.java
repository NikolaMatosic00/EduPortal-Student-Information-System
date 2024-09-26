package com.studentska.sluzba.repository;

import com.studentska.sluzba.model.TipPolaganja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipPolaganjaRepository extends JpaRepository<TipPolaganja, Integer> {
}

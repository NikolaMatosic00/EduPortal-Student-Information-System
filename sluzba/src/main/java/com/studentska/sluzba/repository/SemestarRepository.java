package com.studentska.sluzba.repository;

import com.studentska.sluzba.model.Semestar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemestarRepository extends JpaRepository<Semestar, Integer> {
}

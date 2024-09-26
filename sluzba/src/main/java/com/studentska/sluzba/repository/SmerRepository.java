package com.studentska.sluzba.repository;

import com.studentska.sluzba.model.Smer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmerRepository extends JpaRepository<Smer,Integer> {
}

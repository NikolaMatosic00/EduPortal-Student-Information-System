package com.studentska.sluzba.repository;

import com.studentska.sluzba.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findOneByEmail(String email);

}

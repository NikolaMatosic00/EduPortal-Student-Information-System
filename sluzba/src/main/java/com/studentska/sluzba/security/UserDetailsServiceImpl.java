package com.studentska.sluzba.security;


import com.studentska.sluzba.model.Admin;
import com.studentska.sluzba.model.Predavac;
import com.studentska.sluzba.model.Student;
import com.studentska.sluzba.repository.AdminRepository;
import com.studentska.sluzba.repository.PredavacRepository;
import com.studentska.sluzba.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PredavacRepository predavacRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findOneByEmail(username);
        if (admin != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
            return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPass(), grantedAuthorities);
        }
        Student student = studentRepository.findOneByEmail(username);
        if (student != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
            grantedAuthorities.add(new SimpleGrantedAuthority("STUDENT"));
            return new org.springframework.security.core.userdetails.User(student.getEmail(), student.getPass(), grantedAuthorities);
        }
        Predavac predavac = predavacRepository.findOneByEmail(username);
        if (predavac != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
            grantedAuthorities.add(new SimpleGrantedAuthority("PREDAVAC"));
            return new org.springframework.security.core.userdetails.User(predavac.getEmail(), predavac.getPass(), grantedAuthorities);
        }
        throw new UsernameNotFoundException(String.format("Nije nadjen korisnik sa username-om '%s'.", username));
    }
}



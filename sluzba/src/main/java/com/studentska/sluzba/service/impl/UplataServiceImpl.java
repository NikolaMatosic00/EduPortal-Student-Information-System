package com.studentska.sluzba.service.impl;

import com.studentska.sluzba.dto.administrator.EvidencijaUplataDTOReq;
import com.studentska.sluzba.dto.administrator.EvidencijaUplataDTORes;
import com.studentska.sluzba.model.Admin;
import com.studentska.sluzba.model.Student;
import com.studentska.sluzba.model.Uplata;
import com.studentska.sluzba.repository.AdminRepository;
import com.studentska.sluzba.repository.StudentRepository;
import com.studentska.sluzba.repository.UplataRepository;
import com.studentska.sluzba.security.TokenUtils;
import com.studentska.sluzba.service.UplataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UplataServiceImpl implements UplataService {

    @Autowired
    UplataRepository uplataRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TokenUtils tokenUtils;

    @Override
    public List<EvidencijaUplataDTORes> uplate() {
        List<Uplata> sveUplate = uplataRepository.findAll();
        List<EvidencijaUplataDTORes> res = new ArrayList<>();
        for(Uplata u:sveUplate){
            EvidencijaUplataDTORes tmp = new EvidencijaUplataDTORes();
            tmp.setImeStudenta(u.getStudent().getIme());
            tmp.setPrezimeStudenta(u.getStudent().getPrezime());
            tmp.setVremeUplate(u.getVremeUplate().toString());
            tmp.setSvrha(u.getSvrha());
            tmp.setIznos(u.getIznos());
            tmp.setIdStudent(u.getStudent().getId());
            res.add(tmp);
        }
        return res;
    }

    @Override
    public void evidentirajUplatu(String token, EvidencijaUplataDTOReq req) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Admin admin = adminRepository.findOneByEmail(username);
        Student student = studentRepository.getById(req.getIdStudent());
        Uplata u = new Uplata();
        u.setAdmin(admin);
        u.setStudent(student);
        u.setIznos(req.getIznos());
        u.setVremeUplate(new Date());
        u.setSvrha(req.getSvrha());
        uplataRepository.save(u);
    }

    @Override
    public List<EvidencijaUplataDTORes> uplateZaStudenta(String token) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Student student = studentRepository.findOneByEmail(username);

        List<Uplata> sveUplate = uplataRepository.findAllByStudent(student);
        List<EvidencijaUplataDTORes> res = new ArrayList<>();
        for(Uplata u:sveUplate){
            EvidencijaUplataDTORes tmp = new EvidencijaUplataDTORes();
            tmp.setImeStudenta(u.getStudent().getIme());
            tmp.setPrezimeStudenta(u.getStudent().getPrezime());
            tmp.setVremeUplate(u.getVremeUplate().toString());
            tmp.setSvrha(u.getSvrha());
            tmp.setIznos(u.getIznos());
            tmp.setIdStudent(u.getStudent().getId());
            res.add(tmp);
        }
        return res;
    }
}

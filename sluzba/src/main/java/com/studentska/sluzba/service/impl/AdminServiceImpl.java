package com.studentska.sluzba.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentska.sluzba.dto.additional.SemestarDTO;
import com.studentska.sluzba.dto.additional.SmerDTO;
import com.studentska.sluzba.dto.administrator.KreirajPredmetDTOReq;
import com.studentska.sluzba.dto.predavac.ObavestenjeDTOReq;
import com.studentska.sluzba.dto.predavac.TipPolaganjaDtoRes;
import com.studentska.sluzba.model.Obavestenje;
import com.studentska.sluzba.model.PravilaPolaganja;
import com.studentska.sluzba.model.PravilaPolaganjaPK;
import com.studentska.sluzba.model.Predmet;
import com.studentska.sluzba.model.Semestar;
import com.studentska.sluzba.model.Smer;
import com.studentska.sluzba.model.TipPolaganja;
import com.studentska.sluzba.repository.PredmetRepository;
import com.studentska.sluzba.repository.SemestarRepository;
import com.studentska.sluzba.repository.SmerRepository;
import com.studentska.sluzba.repository.TipPolaganjaRepository;

@Service
public class AdminServiceImpl {
	@Autowired
	PredmetRepository predmetRepository;

	@Autowired
	SmerRepository smerRepository;
	
	@Autowired
	SemestarRepository semestarRepository;
	
	@Autowired
	TipPolaganjaRepository tipPolaganjaRepository;

	public List<SmerDTO> getAllSmerovi() {
		List<SmerDTO> list = new ArrayList<>();
		for (Smer smer : smerRepository.findAll()) {
			list.add(new SmerDTO(smer.getId(), smer.getNaziv()));
		}
		return list;
	}

	public List<SemestarDTO> getAllSemestari() {
		List<SemestarDTO> list = new ArrayList<>();
		for (Semestar s : semestarRepository.findAll()) {
			list.add(new SemestarDTO(s.getId(), s.getBrojSemestra()));
		}
		return list;
	}
	
	public List<TipPolaganjaDtoRes> getAllTipoviPolaganja() {
		List<TipPolaganjaDtoRes> list = new ArrayList<>();
		for (TipPolaganja s : tipPolaganjaRepository.findAll()) {
			list.add(new TipPolaganjaDtoRes(s.getId(), s.getNaziv(), s.getMinimalnoZaProlaz(), s.getUkupno(), s.getMinimalnoZaUslov()));
		}
		return list;
	}
	
    public void dodajSmer(String naziv) {
       Smer smer = new Smer();
       System.out.println(naziv);
       smer.setNaziv(naziv);
       smerRepository.save(smer);
    }
	
}

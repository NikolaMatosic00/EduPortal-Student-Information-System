package com.studentska.sluzba.service;

import com.studentska.sluzba.dto.predavac.ProfileDtoRes;
import com.studentska.sluzba.dto.predavac.TerminPolaganjaDtoRes;
import com.studentska.sluzba.dto.student.AzuriranjeProfilaDTOReq;

import java.util.List;

import com.studentska.sluzba.dto.NovEmailDTOReq;
import com.studentska.sluzba.dto.NovaLozinkaDTOReq;
import com.studentska.sluzba.dto.student.DetaljiPredmetaDTORes;
import com.studentska.sluzba.dto.student.DodajStudentaNaPredmetDTO;
import com.studentska.sluzba.dto.student.DodavanjeStudentaDTO;
import com.studentska.sluzba.dto.student.StudentDTO;

public interface StudentService {
    void novEmail(String token, NovEmailDTOReq req);

    void novaLozinka(String token, NovaLozinkaDTOReq req);


    void azuriranjeProfila(String token, AzuriranjeProfilaDTOReq req);

    void uverenjeOStudiranju(String token);

    ProfileDtoRes getProfile(String token);

	void dodajStudenta(DodavanjeStudentaDTO student);

	void dodajStudentaNaPredmet(DodajStudentaNaPredmetDTO dto);

	List<StudentDTO> sviStudenti();

	int trenutnoStanje(String token);

	List<TerminPolaganjaDtoRes> dobaviTermine(String token);
}

package com.studentska.sluzba.controllers;

import com.studentska.sluzba.dto.ErrorDto;
import com.studentska.sluzba.dto.additional.SemestarDTO;
import com.studentska.sluzba.dto.additional.SmerDTO;
import com.studentska.sluzba.dto.administrator.*;
import com.studentska.sluzba.dto.predavac.DodavanjePredavacaDTO;
import com.studentska.sluzba.dto.predavac.PredavacDTO;
import com.studentska.sluzba.dto.predavac.TipPolaganjaDtoRes;
import com.studentska.sluzba.dto.student.DodajStudentaNaPredmetDTO;
import com.studentska.sluzba.dto.student.DodavanjeStudentaDTO;
import com.studentska.sluzba.dto.student.PredmetDTORes;
import com.studentska.sluzba.dto.student.StudentDTO;
import com.studentska.sluzba.model.Predavac;
import com.studentska.sluzba.service.PredavacService;
import com.studentska.sluzba.service.PredmetService;
import com.studentska.sluzba.service.SemestarService;
import com.studentska.sluzba.service.StudentService;
import com.studentska.sluzba.service.UplataService;
import com.studentska.sluzba.service.impl.AdminServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdministratorController {

    @Autowired
    UplataService uplataService;

    @Autowired
    PredmetService predmetService;

    @Autowired
    PredavacService predavacService;

    @Autowired
    StudentService studentService;
    
    @Autowired
    SemestarService semestarService;
    
    @Autowired
    AdminServiceImpl adminService;


    @GetMapping("/uplate")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> uplate() {
        List<EvidencijaUplataDTORes> response = null;
        try {
            response = uplataService.uplate();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/evidentirajUplatu")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> evidentirajUplatu(@RequestHeader("Authorization") String token, @RequestBody EvidencijaUplataDTOReq req) {
        try {
            uplataService.evidentirajUplatu(token, req);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/postaviUloguNaPredmet")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> postaviUloguNaPredmet(@RequestBody PostaviUloguNaPredmetDTOReq req) {
        try {
            predmetService.postaviUloguNaPredmet(req);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/dodajPredmet")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> dodajPredmet(@RequestBody KreirajPredmetDTOReq req) {
        try {
            predmetService.dodajPredmet(req);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    

    @PostMapping("/dodajUloguNaPredmet")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> dodajUloguNaPredmet(@RequestBody PostaviUloguNaPredmetDTOReq req) {
        try {
            predmetService.postaviUloguNaPredmet(req);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/dodajPredavaca")
    public ResponseEntity<PredavacDTO> dodajPredavaca(@RequestBody DodavanjePredavacaDTO predavac) {
        PredavacDTO savedPredavac = predavacService.dodajPredavaca(predavac);
        return ResponseEntity.ok(savedPredavac);
    }
    
    @PostMapping("/dodajStudenta")
    public ResponseEntity<String> dodajStudenta(@RequestBody DodavanjeStudentaDTO student) {
        try {
            studentService.dodajStudenta(student); // Call the service method to add the student
            return new ResponseEntity<>("Student added successfully!", HttpStatus.CREATED); // Return success message
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding student: " + e.getMessage(), HttpStatus.BAD_REQUEST); // Return error message
        }
    }
    
    @PostMapping("/dodajNaPredmet")
    public ResponseEntity<String> dodajStudentaNaPredmet(@RequestBody DodajStudentaNaPredmetDTO dto) {
        studentService.dodajStudentaNaPredmet(dto);
        return ResponseEntity.ok("Student successfully added to the course");
    }
    
    
    @PostMapping("/dodajSmer")
    public ResponseEntity<String> dodajSmer(@RequestParam String naziv) {
        adminService.dodajSmer(naziv);
        return ResponseEntity.ok("Smer successfully added");
    }

    @DeleteMapping("/obrisi")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> obrisi(@RequestParam int id) {
        try {
            predmetService.obrisi(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/overiSemestar")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ResponseEntity<?> overiSemestar(@RequestBody OveriSemestarDTOReq req) {
//        try {
//            semestarService.overiSemestar(req);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }
//    
    
    @GetMapping("/smerovi")
    public ResponseEntity<List<SmerDTO>> getAllSmerovi() {
        List<SmerDTO> smerovi = adminService.getAllSmerovi();
        return new ResponseEntity<>(smerovi, HttpStatus.OK);
    }
    
    @GetMapping("/semestari")
    public ResponseEntity<List<SemestarDTO>> getAllSemestari() {
        List<SemestarDTO> semestari = adminService.getAllSemestari();
        return new ResponseEntity<>(semestari, HttpStatus.OK);
    }
    
    @GetMapping("/tipoviPolaganja")
    public ResponseEntity<List<TipPolaganjaDtoRes>> getAllTipoviPolaganja() {
        List<TipPolaganjaDtoRes> tipoviPolaganja = adminService.getAllTipoviPolaganja();
        return new ResponseEntity<>(tipoviPolaganja, HttpStatus.OK);
    }
    
    @GetMapping("/predmeti")
    public ResponseEntity<List<PredmetDTORes>> getAllPredmeti() {
        List<PredmetDTORes> predmeti = predmetService.sviPredmeti();
        return new ResponseEntity<>(predmeti, HttpStatus.OK);
    }

    @GetMapping("/predavaci")
    public ResponseEntity<List<PredavacDTO>> getAllPredavaci() {
        List<PredavacDTO> predavaci = predavacService.sviPredavaci();
        return new ResponseEntity<>(predavaci, HttpStatus.OK);
    }

    @GetMapping("/studenti")
    public ResponseEntity<List<StudentDTO>> getAllStudenti() {
        List<StudentDTO> studenti = studentService.sviStudenti();
        return new ResponseEntity<>(studenti, HttpStatus.OK);
    }
}

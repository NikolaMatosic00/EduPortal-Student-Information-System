package com.studentska.sluzba.controllers;

import com.studentska.sluzba.dto.ErrorDto;
import com.studentska.sluzba.dto.NovEmailDTOReq;
import com.studentska.sluzba.dto.NovaLozinkaDTOReq;
import com.studentska.sluzba.dto.predavac.*;
import com.studentska.sluzba.dto.student.DetaljiPredmetaDTORes;
import com.studentska.sluzba.dto.student.IstorijatPolaganjaIspitaDTORes;
import com.studentska.sluzba.service.PredavacService;
import com.studentska.sluzba.service.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/predavac")
public class PredavacController {

    @Autowired
    PredavacService predavacService;

    @Autowired
    PredmetService predmetService;

    @PostMapping("/novEmail")
    @PreAuthorize("hasAuthority('PREDAVAC')")
    public ResponseEntity<?> novEmail(@RequestHeader("Authorization") String token, @RequestBody NovEmailDTOReq req) {
        try {
            predavacService.novEmail(token, req);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/novaLozinka")
    @PreAuthorize("hasAuthority('PREDAVAC')")
    public ResponseEntity<?> novaLozinka(@RequestHeader("Authorization") String token, @RequestBody NovaLozinkaDTOReq req) {
        try {
            predavacService.novaLozinka(token, req);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/postaviObavestenje")
    @PreAuthorize("hasAuthority('PREDAVAC')")
    public ResponseEntity<?> postaviObavestenje(@RequestHeader("Authorization") String token, @RequestBody ObavestenjeDTOReq req) {
        try {
            predavacService.postaviObavestenje(token, req);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/azuriranjeProfila")
    @PreAuthorize("hasAuthority('PREDAVAC')")
    public ResponseEntity<?> azuriranjeProfila(@RequestHeader("Authorization") String token, @RequestBody AzuriranjeProfilaDTOReq req) {
        try {
            predavacService.azuriranjeProfila(token, req);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/unesiBrojBodova")
    @PreAuthorize("hasAuthority('PREDAVAC')")
    public ResponseEntity<?> unesiBrojBodova(@RequestHeader("Authorization") String token, @RequestBody UnosBodovaDTOReq req) {
        try {
            predavacService.unesiBrojBodova(token, req);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/unesiOcenu")
    @PreAuthorize("hasAuthority('PREDAVAC')")
    public ResponseEntity<?> unesiOcenu(@RequestHeader("Authorization") String token, @RequestBody UnosOceneDTOReq req) {
        try {
            predavacService.unesiOcenu(token, req);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/elektronskiPotpis")
    @PreAuthorize("hasAuthority('PREDAVAC')")
    public ResponseEntity<?> elektronskiPotpis(@RequestHeader("Authorization") String token, @RequestBody ElektronskiPotpisDTOReq req) {
        try {
            predavacService.elektronskiPotpis(token, req);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/dodajTermin")
    @PreAuthorize("hasAuthority('PREDAVAC')")
    public ResponseEntity<?> dodajTermin(@RequestHeader("Authorization") String token, @RequestBody DodajTerminPolaganjaDTOReq req) {
        try {
            predavacService.dodajTermin(token, req);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/prijaveZaTermin")
    @PreAuthorize("hasAuthority('PREDAVAC')")
    public ResponseEntity<?> prijaveZaTermin(@RequestHeader("Authorization") String token, @RequestParam int id) {
        List<PrijavaDTORes> response = null;
        try {
            response = predavacService.prijaveZaTermin(token, id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/istorijaPrijavaStudenta")
    @PreAuthorize("hasAuthority('PREDAVAC')")
    public ResponseEntity<?> istorijaPrijavaStudenta(@RequestParam int idStudenta, @RequestParam int idPredmeta) {
        List<IstorijatPolaganjaIspitaDTORes> response = null;
        try {
            response = predmetService.istorijaPrijavaStudenta(idStudenta, idPredmeta);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getProfile")
    @PreAuthorize("hasAuthority('PREDAVAC')")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String token) {
        ProfileDtoRes response = null;
        try {
            response = predavacService.getProfile(token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/tipoviPolaganjaZaPredmet")
    @PreAuthorize("hasAuthority('PREDAVAC')")
    public ResponseEntity<?> tipoviPolaganjaZaPredmet(@RequestHeader("Authorization") String token, @RequestParam int idPredmeta) {
        List<TipPolaganjaDtoRes> response = null;
        try {
            response = predavacService.tipoviPolaganjaZaPredmet(token, idPredmeta);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/dobaviSlusanja")
    @PreAuthorize("hasAuthority('PREDAVAC')")
    public ResponseEntity<?> dobaviSlusanja(@RequestHeader("Authorization") String token, @RequestParam int potpis) {
        List<SlusaPredmetDtoRes> response = null;
        try {
            response = predavacService.dobaviSlusanja(token, potpis);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/dobaviTermine")
    @PreAuthorize("hasAuthority('PREDAVAC')")
    public ResponseEntity<?> dobaviTermine(@RequestHeader("Authorization") String token) {
        List<TerminPolaganjaDtoRes> response = null;
        try {
            response = predavacService.dobaviTermine(token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/dobaviPolaganjaZaTermin")
    @PreAuthorize("hasAuthority('PREDAVAC')")
    public ResponseEntity<?> dobaviPolaganjaZaTermin(@RequestHeader("Authorization") String token, @RequestParam int idTermina) {
        List<PolaganjeDtoRes> response = null;
        try {
            response = predavacService.dobaviPolaganjaZaTermin(token, idTermina);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

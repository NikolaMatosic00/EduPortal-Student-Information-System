package com.studentska.sluzba.controllers;

import com.studentska.sluzba.dto.ErrorDto;
import com.studentska.sluzba.dto.NovEmailDTOReq;
import com.studentska.sluzba.dto.NovaLozinkaDTOReq;
import com.studentska.sluzba.dto.administrator.EvidencijaUplataDTORes;
import com.studentska.sluzba.dto.administrator.OveriSemestarDTOReq;
import com.studentska.sluzba.dto.predavac.ProfileDtoRes;
import com.studentska.sluzba.dto.predavac.TerminPolaganjaDtoRes;
import com.studentska.sluzba.dto.student.*;
import com.studentska.sluzba.service.PredmetService;
import com.studentska.sluzba.service.SemestarService;
import com.studentska.sluzba.service.StudentService;
import com.studentska.sluzba.service.UplataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	SemestarService semestarService;

	@Autowired
	StudentService studentService;

	@Autowired
	PredmetService predmetService;

	@Autowired
	UplataService uplataService;

	@PostMapping("/novEmail")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity<?> novEmail(@RequestHeader("Authorization") String token, @RequestBody NovEmailDTOReq req) {
		try {
			studentService.novEmail(token, req);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/novaLozinka")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity<?> novaLozinka(@RequestHeader("Authorization") String token,
			@RequestBody NovaLozinkaDTOReq req) {
		try {
			studentService.novaLozinka(token, req);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/prijavaZaPolaganje")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity<?> prijavaZaPolaganje(@RequestHeader("Authorization") String token,
			@RequestBody PrijavaIspitaDTOReq req) {
		try {
			predmetService.prijavaZaPolaganje(token, req);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/uplateZaStudenta")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity<?> uplateZaStudenta(@RequestHeader("Authorization") String token) {
		List<EvidencijaUplataDTORes> response = null;
		try {
			response = uplataService.uplateZaStudenta(token);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/istorijatPolaganjaIspita")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity<?> istorijatPolaganjaIspita(@RequestHeader("Authorization") String token) {
		List<IstorijatPolaganjaIspitaDTORes> response = null;
		try {
			response = predmetService.istorijatPolaganjaIspita(token);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/azuriranjeProfila")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity<?> azuriranjeProfila(@RequestHeader("Authorization") String token,
			@RequestBody AzuriranjeProfilaDTOReq req) {
		try {
			studentService.azuriranjeProfila(token, req);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/pregledPredmeta")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity<?> pregledPredmeta(@RequestHeader("Authorization") String token,
			@RequestParam(required = false, defaultValue = "svi") String filter) {
		List<PredmetDTORes> response = null;
		try {
			response = predmetService.pregledPredmeta(token, filter);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/pregledRasporeda")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity<?> pregledRasporeda(@RequestHeader("Authorization") String token) {
		List<ObavestenjeDTORes> response = null;
		try {
			response = predmetService.pregledRasporeda(token);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/detaljiPredmeta")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity<?> detaljiPredmeta(@RequestHeader("Authorization") String token, @RequestParam int id) {
		DetaljiPredmetaDTORes response = null;
		try {
			response = predmetService.detaljiPredmeta(token, id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/uverenjeOStudiranju")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity<?> uverenjeOStudiranju(@RequestHeader("Authorization") String token) {
		try {
			studentService.uverenjeOStudiranju(token);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getProfile")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String token) {
		ProfileDtoRes response = null;
		try {
			response = studentService.getProfile(token);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/trenutnoStanje")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity<?> trenutnoStanje(@RequestHeader("Authorization") String token) {
		try {
			int response = studentService.trenutnoStanje(token);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska", HttpStatus.OK);
		}
	}

	@PostMapping("/overiSemestar")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity<?> overiSemestar(@RequestHeader("Authorization") String token, @RequestParam int semestarId) {
		try {
			semestarService.overiSemestar(token, semestarId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	

    @GetMapping("/dobaviTermine")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<?> dobaviTermine(@RequestHeader("Authorization") String token) {
        List<TerminPolaganjaDtoRes> response = null;
        try {
            response = studentService.dobaviTermine(token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}

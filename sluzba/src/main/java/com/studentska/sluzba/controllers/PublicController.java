package com.studentska.sluzba.controllers;

import com.studentska.sluzba.dto.ErrorDto;
import com.studentska.sluzba.dto.student.IstorijatPolaganjaIspitaDTORes;
import com.studentska.sluzba.dto.student.PredmetDTORes;
import com.studentska.sluzba.service.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    PredmetService predmetService;

    @GetMapping("/sviPredmeti")
    public ResponseEntity<?> sviPredmeti() {
        List<PredmetDTORes> response = null;
        try {
            response = predmetService.sviPredmeti();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }



}

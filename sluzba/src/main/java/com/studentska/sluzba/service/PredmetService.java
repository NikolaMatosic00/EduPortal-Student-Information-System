package com.studentska.sluzba.service;

import com.studentska.sluzba.dto.administrator.KreirajPredmetDTOReq;
import com.studentska.sluzba.dto.administrator.PostaviUloguNaPredmetDTOReq;
import com.studentska.sluzba.dto.student.*;

import java.util.List;

public interface PredmetService {
    void postaviUloguNaPredmet(PostaviUloguNaPredmetDTOReq req) throws Exception;

    void dodajPredmet(KreirajPredmetDTOReq req) throws Exception;

    void obrisi(int id);

    void prijavaZaPolaganje(String token, PrijavaIspitaDTOReq req) throws Exception;

    List<IstorijatPolaganjaIspitaDTORes> istorijatPolaganjaIspita(String token);

    List<PredmetDTORes> pregledPredmeta(String token, String filter);

    List<ObavestenjeDTORes> pregledRasporeda(String token);

    List<PredmetDTORes> sviPredmeti();

    DetaljiPredmetaDTORes detaljiPredmeta(String token, int id);

    List<IstorijatPolaganjaIspitaDTORes> istorijaPrijavaStudenta(int idStudenta, int idPredmeta);
}

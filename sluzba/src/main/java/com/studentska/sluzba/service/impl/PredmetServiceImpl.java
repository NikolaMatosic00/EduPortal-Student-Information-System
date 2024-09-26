package com.studentska.sluzba.service.impl;

import com.studentska.sluzba.dto.administrator.KreirajPredmetDTOReq;
import com.studentska.sluzba.dto.administrator.PostaviUloguNaPredmetDTOReq;
import com.studentska.sluzba.dto.student.*;
import com.studentska.sluzba.model.*;
import com.studentska.sluzba.repository.*;
import com.studentska.sluzba.security.TokenUtils;
import com.studentska.sluzba.service.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PredmetServiceImpl implements PredmetService {

    @Autowired
    PredavacRepository predavacRepository;

    @Autowired
    PredmetRepository predmetRepository;

    @Autowired
    PredavacPredmetRepository predavacPredmetRepository;

    @Autowired
    SmerRepository smerRepository;

    @Autowired
    SemestarRepository semestarRepository;

    @Autowired
    TipPolaganjaRepository tipPolaganjaRepository;


    @Autowired
    TerminPolaganjaRepository terminPolaganjaRepository;

    @Autowired
    SlusaPredmetRepository slusaPredmetRepository;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PolaganjeRepository polaganjeRepository;
    
    @Autowired
    PravilaPolaganjaRepository pravilaPolaganjaRepository;


    @Autowired
    ObavestenjeRepository obavestenjeRepository;

    @Override
    public void postaviUloguNaPredmet(PostaviUloguNaPredmetDTOReq req) throws Exception {
        List<String> validneUloge = Arrays.asList("PROFESOR", "ASISTENT");
        if (!validneUloge.contains(req.getUloga())) {
            throw new Exception("Nevalidna uloga");
        }
        Predmet predmet = predmetRepository.getById(req.getIdPredmet());
        Predavac predavac = predavacRepository.getById(req.getIdPredavac());

        PredavacPredmet predavacPredmet = predavacPredmetRepository.findOneByPredmetAndUloga(predmet, req.getUloga());
        if (predavacPredmet == null) {
            predavacPredmet = new PredavacPredmet();
        }
        predavacPredmet.setPredmet(predmet);
        predavacPredmet.setPredavac(predavac);
        predavacPredmet.setUloga(req.getUloga());
        predavacPredmetRepository.save(predavacPredmet);
    }

    @Override
    public void dodajPredmet(KreirajPredmetDTOReq req) throws Exception {
        List<String> validniTipovi = Arrays.asList("IZBORNI", "OBAVEZNI");
        if (!validniTipovi.contains(req.getTip())) {
            throw new Exception("Nevalidan tip");
        }
        Predmet predmet = new Predmet();
        predmet.setTip(req.getTip());
        predmet.setEspb(req.getEspb());
        predmet.setNaziv(req.getNaziv());
        predmet.setSilabus(req.getSilabus());
        predmet.setSmer(smerRepository.getById(req.getIdSmer()));
        predmet.setSemestar(semestarRepository.getById(req.getIdSemestar()));
        List<TipPolaganja> tipPolaganjas = new ArrayList<>();
        for (Integer idTip : req.getTipoviPolaganja()) {
            tipPolaganjas.add(tipPolaganjaRepository.getById(idTip)); 
        }
        predmet.setTipPolaganjas(tipPolaganjas);
        Predmet saved = predmetRepository.save(predmet);
        for (Integer idTip : req.getTipoviPolaganja()) {
        	PravilaPolaganja pp = new PravilaPolaganja();
        	pp.setId(new PravilaPolaganjaPK());
        	pp.getId().setPredmetId(saved.getId());
        	pp.getId().setTipPolaganjaId(idTip);
            pravilaPolaganjaRepository.save(pp); 
        }
    }

    @Override
    public void obrisi(int id) {
        Predmet predmet = predmetRepository.getById(id);
        predmet.setTip("OBRISAN");
        predmetRepository.save(predmet);
    }

    @Override
    public void prijavaZaPolaganje(String token, PrijavaIspitaDTOReq req) throws Exception {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Student student = studentRepository.findOneByEmail(username);

        TerminPolaganja terminPolaganja = terminPolaganjaRepository.getById(req.getIdTerminPolaganja());
        Predmet predmet = predmetRepository.getById(terminPolaganja.getPravilaPolaganja().getId().getPredmetId());

        SlusaPredmet slusaPredmet = slusaPredmetRepository.findOneByStudentAndPredmet(student, predmet);
        if (slusaPredmet == null) {
            throw new Exception("Nije pronadjen predmet za studenta");
        }

        Polaganje polaganje = new Polaganje();
        polaganje.setOstvarenBrojBodova(0);
        polaganje.setTerminPolaganja(terminPolaganja);
        polaganje.setVremePrijave(new Date());
        polaganje.setSlusaPredmet(slusaPredmet);

        polaganjeRepository.save(polaganje);
    }

    @Override
    public List<IstorijatPolaganjaIspitaDTORes> istorijatPolaganjaIspita(String token) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Student student = studentRepository.findOneByEmail(username);
        List<IstorijatPolaganjaIspitaDTORes> res = new ArrayList<>();

        List<Polaganje> polaganja = polaganjeRepository.findAllBySlusaPredmetStudentOrderByIdDesc(student);
        for (Polaganje p : polaganja) {
            IstorijatPolaganjaIspitaDTORes tmp = new IstorijatPolaganjaIspitaDTORes();
            tmp.setIdPolaganja(p.getId());
            tmp.setOstvarenBrojBodova(p.getOstvarenBrojBodova());
            tmp.setVremePrijave(p.getVremePrijave());
            tmp.setIdPredmet(p.getSlusaPredmet().getPredmet().getId());
            tmp.setNazivPredmeta(p.getSlusaPredmet().getPredmet().getNaziv());
            tmp.setFinalnaOcena(p.getSlusaPredmet().getOcena());
            tmp.setNazivRoka(p.getTerminPolaganja().getNazivRoka());
            res.add(tmp);
        }

        return res;
    }

    @Override
    public List<PredmetDTORes> pregledPredmeta(String token, String filter) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Student student = studentRepository.findOneByEmail(username);
        List<SlusaPredmet> slusaPredmete = slusaPredmetRepository.findAllByStudent(student);

        List<PredmetDTORes> res = new ArrayList<>();
        for (SlusaPredmet sp : slusaPredmete) {
            PredmetDTORes tmp = new PredmetDTORes();
            tmp.setEspb(sp.getPredmet().getEspb());
            tmp.setId(sp.getPredmet().getId());
            tmp.setNaziv(sp.getPredmet().getNaziv());
            tmp.setSilabus(sp.getPredmet().getSilabus());
            tmp.setTip(sp.getPredmet().getTip());
            tmp.setFinalnaOcena(sp.getOcena());
            tmp.setPotpisan(sp.getElektronskiPotpis() == (byte) 1 ? "POTPISAN" : "NIJE");
            if (filter.equals("sve")) {
                res.add(tmp);
            } else {
                if (tmp.getFinalnaOcena() < 6) {
                    res.add(tmp);
                }
            }
        }

        return res;
    }

    @Override
    public List<ObavestenjeDTORes> pregledRasporeda(String token) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Student student = studentRepository.findOneByEmail(username);
        List<SlusaPredmet> slusaPredmete = slusaPredmetRepository.findAllByStudent(student);
        List<ObavestenjeDTORes> res = new ArrayList<>();
        for (SlusaPredmet sp : slusaPredmete) {
            Predmet p = sp.getPredmet();
            List<Obavestenje> obavestenjaOPredmetu = obavestenjeRepository.findAllByPredmet(p);
            for (Obavestenje o : obavestenjaOPredmetu) {
                ObavestenjeDTORes tmp = new ObavestenjeDTORes();
                tmp.setIdPredmeta(p.getId());
                tmp.setIdObavestenja(o.getId());
                tmp.setTekst(o.getTekst());
                tmp.setNazivPredmeta(p.getNaziv());
                res.add(tmp);
            }
        }
        res.sort(Comparator.comparing(ObavestenjeDTORes::getIdObavestenja).reversed());
        return res;
    }

    @Override
    public List<PredmetDTORes> sviPredmeti() {
        List<PredmetDTORes> res = new ArrayList<>();
        List<Predmet> sviPredmeti = predmetRepository.findAll();
        for (Predmet p : sviPredmeti) {
            if (p.getTip().equals("OBRISAN")) continue;
            PredmetDTORes tmp = new PredmetDTORes();
            tmp.setEspb(p.getEspb());
            tmp.setId(p.getId());
            tmp.setNaziv(p.getNaziv());
            tmp.setSilabus(p.getSilabus());
            tmp.setTip(p.getTip());
            res.add(tmp);
        }
        return res;
    }

    @Override
    public DetaljiPredmetaDTORes detaljiPredmeta(String token, int id) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Student student = studentRepository.findOneByEmail(username);
        Predmet predmet = predmetRepository.getById(id);

        List<IstorijatPolaganjaIspitaDTORes> istorija = new ArrayList<>();

        List<Polaganje> polaganja = polaganjeRepository.findAllBySlusaPredmetStudentAndSlusaPredmetPredmetOrderByIdDesc(student, predmet);
        for (Polaganje p : polaganja) {
            IstorijatPolaganjaIspitaDTORes tmp = new IstorijatPolaganjaIspitaDTORes();
            tmp.setIdPolaganja(p.getId());
            tmp.setOstvarenBrojBodova(p.getOstvarenBrojBodova());
            tmp.setVremePrijave(p.getVremePrijave());
            tmp.setIdPredmet(p.getSlusaPredmet().getPredmet().getId());
            tmp.setNazivPredmeta(p.getSlusaPredmet().getPredmet().getNaziv());
            tmp.setFinalnaOcena(p.getSlusaPredmet().getOcena());
            tmp.setNazivRoka(p.getTerminPolaganja().getNazivRoka());
            istorija.add(tmp);
        }

        List<ObavestenjeDTORes> obavestenja = new ArrayList<>();
        List<Obavestenje> obavestenjaOPredmetu = obavestenjeRepository.findAllByPredmet(predmet);
        for (Obavestenje o : obavestenjaOPredmetu) {
            ObavestenjeDTORes tmp = new ObavestenjeDTORes();
            tmp.setIdPredmeta(predmet.getId());
            tmp.setIdObavestenja(o.getId());
            tmp.setTekst(o.getTekst());
            tmp.setNazivPredmeta(predmet.getNaziv());
            obavestenja.add(tmp);
        }
        obavestenja.sort(Comparator.comparing(ObavestenjeDTORes::getIdObavestenja).reversed());

        DetaljiPredmetaDTORes res = new DetaljiPredmetaDTORes();
        res.setEspb(predmet.getEspb());
        res.setId(predmet.getId());
        res.setNaziv(predmet.getNaziv());
        res.setSilabus(predmet.getSilabus());
        res.setTip(predmet.getTip());
        res.setIstorijaPolaganja(istorija);
        res.setObavestenja(obavestenja);
        return res;
    }

    @Override
    public List<IstorijatPolaganjaIspitaDTORes> istorijaPrijavaStudenta(int idStudenta, int idPredmeta) {
        Student student = studentRepository.getById(idStudenta);
        Predmet predmet = predmetRepository.getById(idPredmeta);
        List<IstorijatPolaganjaIspitaDTORes> res = new ArrayList<>();

        List<Polaganje> polaganja = polaganjeRepository.findAllBySlusaPredmetStudentAndSlusaPredmetPredmetOrderByIdDesc(student, predmet);
        for (Polaganje p : polaganja) {
            IstorijatPolaganjaIspitaDTORes tmp = new IstorijatPolaganjaIspitaDTORes();
            tmp.setIdPolaganja(p.getId());
            tmp.setOstvarenBrojBodova(p.getOstvarenBrojBodova());
            tmp.setVremePrijave(p.getVremePrijave());
            tmp.setIdPredmet(p.getSlusaPredmet().getPredmet().getId());
            tmp.setNazivPredmeta(p.getSlusaPredmet().getPredmet().getNaziv());
            tmp.setFinalnaOcena(p.getSlusaPredmet().getOcena());
            tmp.setNazivRoka(p.getTerminPolaganja().getNazivRoka());
            res.add(tmp);
        }

        return res;
    }
}

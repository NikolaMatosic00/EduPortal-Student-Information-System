package com.studentska.sluzba.service.impl;

import com.studentska.sluzba.dto.NovEmailDTOReq;
import com.studentska.sluzba.dto.NovaLozinkaDTOReq;
import com.studentska.sluzba.dto.predavac.*;
import com.studentska.sluzba.dto.student.PredmetDTORes;
import com.studentska.sluzba.model.*;
import com.studentska.sluzba.repository.*;
import com.studentska.sluzba.security.SecurityConfiguration;
import com.studentska.sluzba.security.TokenUtils;
import com.studentska.sluzba.service.PredavacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PredavacServiceImpl implements PredavacService {

    @Autowired
    PredavacRepository predavacRepository;

    @Autowired
    PredmetRepository predmetRepository;

    @Autowired
    ObavestenjeRepository obavestenjeRepository;

    @Autowired
    SlusaPredmetRepository slusaPredmetRepository;

    @Autowired
    PolaganjeRepository polaganjeRepository;

    @Autowired
    PravilaPolaganjaRepository pravilaPolaganjaRepository;

    @Autowired
    TerminPolaganjaRepository terminPolaganjaRepository;

    @Autowired
    TipPolaganjaRepository tipPolaganjaRepository;

    @Autowired
    PredavacPredmetRepository predavacPredmetRepository;

    @Autowired
    SecurityConfiguration configuration;
    
    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    TokenUtils tokenUtils;

    @Override
    public void novEmail(String token, NovEmailDTOReq req) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Predavac predavac = predavacRepository.findOneByEmail(username);
        predavac.setEmail(req.getEmail());
        predavacRepository.save(predavac);
    }

    @Override
    public void novaLozinka(String token, NovaLozinkaDTOReq req) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Predavac predavac = predavacRepository.findOneByEmail(username);
        predavac.setPass(configuration.passwordEncoder().encode(req.getNovaLozinka()));
        predavacRepository.save(predavac);
    }

    @Override
    public void postaviObavestenje(String token, ObavestenjeDTOReq req) {
        Obavestenje obavestenje = new Obavestenje();
        obavestenje.setPredmet(predmetRepository.getById(req.getIdPredmet()));
        obavestenje.setTekst(req.getTekst());
        obavestenje.setVremeObjave(new Date());
        obavestenjeRepository.save(obavestenje);
    }

    @Override
    public void azuriranjeProfila(String token, AzuriranjeProfilaDTOReq req) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Predavac predavac = predavacRepository.findOneByEmail(username);
        predavac.setIme(req.getIme());
        predavac.setPrezime(req.getPrezime());
        predavacRepository.save(predavac);
    }

    @Override
    public void unesiBrojBodova(String token, UnosBodovaDTOReq req) {
        Polaganje polaganje = polaganjeRepository.getById(req.getIdPolaganje());
        polaganje.setOstvarenBrojBodova(req.getOstvarenBrojBodova());
        polaganjeRepository.save(polaganje);
    }

    @Override
    public void unesiOcenu(String token, UnosOceneDTOReq req) {
        SlusaPredmet sp = slusaPredmetRepository.getById(req.getIdSlusaPredmet());
        sp.setOcena(req.getOcena());
        slusaPredmetRepository.save(sp);
    }

    @Override
    public void elektronskiPotpis(String token, ElektronskiPotpisDTOReq req) {
        SlusaPredmet sp = slusaPredmetRepository.getById(req.getIdSlusaPredmet());
        sp.setElektronskiPotpis((byte) 1);
        slusaPredmetRepository.save(sp);
    }

    @Override
    @Transactional
    public void dodajTermin(String token, DodajTerminPolaganjaDTOReq req) {
        PravilaPolaganjaPK pk = new PravilaPolaganjaPK();
        pk.setPredmetId(req.getPredmetId());
        pk.setTipPolaganjaId(req.getTipPolaganjaId());
        PravilaPolaganja pravilaPolaganja = new PravilaPolaganja();
        ;
        pravilaPolaganja.setId(pk);
        pravilaPolaganjaRepository.saveAndFlush(pravilaPolaganja);

        TerminPolaganja tp = new TerminPolaganja();
        tp.setNapomena(req.getNapomena());
        tp.setNazivRoka(req.getNazivRoka());
        tp.setPravilaPolaganja(pravilaPolaganja);
        terminPolaganjaRepository.save(tp);
    }

    @Override
    public List<PrijavaDTORes> prijaveZaTermin(String token, int id) {
        List<PrijavaDTORes> res = new ArrayList<>();
        TerminPolaganja tp = terminPolaganjaRepository.getById(id);
        for (Polaganje p : tp.getPolaganjes()) {
            PrijavaDTORes tmp = new PrijavaDTORes();
            tmp.setIdPredmet(p.getSlusaPredmet().getPredmet().getId());
            tmp.setFinalnaOcena(p.getSlusaPredmet().getOcena());
            tmp.setIdStudent(p.getSlusaPredmet().getStudent().getId());
            tmp.setId(p.getId());
            tmp.setVremePrijave(p.getVremePrijave());
            tmp.setImeStudenta(p.getSlusaPredmet().getStudent().getIme());
            tmp.setNazivPredmeta(p.getSlusaPredmet().getPredmet().getNaziv());
            tmp.setPrezimeStudenta(p.getSlusaPredmet().getStudent().getPrezime());
            tmp.setOstvarenBrojBodova(p.getOstvarenBrojBodova());
            res.add(tmp);
        }
        return res;
    }

    @Override
    public ProfileDtoRes getProfile(String token) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Predavac predavac = predavacRepository.findOneByEmail(username);
        ProfileDtoRes res = new ProfileDtoRes();
        res.setIme(predavac.getIme());
        res.setPrezime(predavac.getPrezime());
        return res;
    }

    @Override
    public List<TipPolaganjaDtoRes> tipoviPolaganjaZaPredmet(String token, int idPredmeta) {
        List<PravilaPolaganja> pravila = pravilaPolaganjaRepository.findAllByIdPredmetId(idPredmeta);
        List<TipPolaganjaDtoRes> res = new ArrayList<>();
        for (PravilaPolaganja p : pravila) {
            TipPolaganja tp = tipPolaganjaRepository.getById(p.getId().getTipPolaganjaId());
            TipPolaganjaDtoRes tmp = new TipPolaganjaDtoRes();
            tmp.setId(tp.getId());
            tmp.setMinimumZaProlaz(tp.getMinimalnoZaProlaz());
            tmp.setNaziv(tp.getNaziv());
            tmp.setUkupno(tp.getUkupno());
            tmp.setMinimumZaUslov(tp.getMinimalnoZaUslov());
            res.add(tmp);
        }
        return res;
    }

    @Override
    public List<SlusaPredmetDtoRes> dobaviSlusanja(String token, int potpis) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Predavac predavac = predavacRepository.findOneByEmail(username);
        List<PredavacPredmet> predavacPredmets = predavacPredmetRepository.findAllByPredavac(predavac);
        List<SlusaPredmetDtoRes> res = new ArrayList<>();
        for (PredavacPredmet pp : predavacPredmets) {
            List<SlusaPredmet> slusaPredmetList = slusaPredmetRepository.findAllByPredmet(pp.getPredmet());
            for (SlusaPredmet sp : slusaPredmetList) {
                if ((int) sp.getElektronskiPotpis() == potpis) {
                    SlusaPredmetDtoRes tmp = new SlusaPredmetDtoRes();
                    tmp.setId(sp.getId());
                    tmp.setImeStudent(sp.getStudent().getIme());
                    tmp.setIdStudent(sp.getStudent().getId());
                    tmp.setPrezimeStudent(sp.getStudent().getPrezime());
                    tmp.setElektronskiPotpis(sp.getElektronskiPotpis());
                    tmp.setIndexStudent(sp.getStudent().getBrojIndexa());
                    tmp.setOcena(sp.getOcena());
                    tmp.setNazivPredmet(sp.getPredmet().getNaziv());
                    tmp.setIdPredmet(sp.getPredmet().getId());
                    res.add(tmp);
                }
            }
        }
        return res;
    }

    @Override
    public List<TerminPolaganjaDtoRes> dobaviTermine(String token) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Predavac predavac = predavacRepository.findOneByEmail(username);
        List<PredavacPredmet> predavacPredmets = predavacPredmetRepository.findAllByPredavac(predavac);
        List<TerminPolaganjaDtoRes> res = new ArrayList<>();
        for (PredavacPredmet pp : predavacPredmets) {
            List<TerminPolaganja> termini = terminPolaganjaRepository.findAllByPravilaPolaganjaIdPredmetId(pp.getPredmet().getId());
            for (TerminPolaganja t : termini) {
                TerminPolaganjaDtoRes tmp = new TerminPolaganjaDtoRes();
                tmp.setId(t.getId());
                tmp.setPredmet(pp.getPredmet().getNaziv());
                tmp.setNazivRoka(t.getNazivRoka());
                tmp.setNapomena(t.getNapomena());
                res.add(tmp);
            }
        }
        return res;
    }

    @Override
    public List<PolaganjeDtoRes> dobaviPolaganjaZaTermin(String token, int idTermina) {
        TerminPolaganja terminPolaganja = terminPolaganjaRepository.getById(idTermina);

        List<PolaganjeDtoRes> res = new ArrayList<>();
        List<Polaganje> polaganjaZaTermin = polaganjeRepository.findAllByTerminPolaganjaOrderByIdDesc(terminPolaganja);
        for (Polaganje polaganje : polaganjaZaTermin) {
            PolaganjeDtoRes tmp = new PolaganjeDtoRes();
            tmp.setId(polaganje.getId());
            tmp.setOstvarenBrojBodova(polaganje.getOstvarenBrojBodova());
            tmp.setVremePrijave(polaganje.getVremePrijave().toString());
            SlusaPredmet sp = polaganje.getSlusaPredmet();

            SlusaPredmetDtoRes tmpSp = new SlusaPredmetDtoRes();
            tmpSp.setId(sp.getId());
            tmpSp.setImeStudent(sp.getStudent().getIme());
            tmpSp.setIdStudent(sp.getStudent().getId());
            tmpSp.setPrezimeStudent(sp.getStudent().getPrezime());
            tmpSp.setElektronskiPotpis(sp.getElektronskiPotpis());
            tmpSp.setIndexStudent(sp.getStudent().getBrojIndexa());
            tmpSp.setOcena(sp.getOcena());
            tmpSp.setNazivPredmet(sp.getPredmet().getNaziv());
            tmpSp.setIdPredmet(sp.getPredmet().getId());
            tmp.setSlusaPredmetDtoRes(tmpSp);

            res.add(tmp);
        }
        return res;
    }
    
    public List<PredavacDTO> sviPredavaci() {
        List<PredavacDTO> res = new ArrayList<>();
        List<Predavac> sviPredavaci = predavacRepository.findAll();
        for (Predavac p : sviPredavaci) {
         
            res.add(new PredavacDTO(p.getId(), p.getIme(), p.getPrezime()));
        }
        return res;
    }
    
    public PredavacDTO dodajPredavaca(DodavanjePredavacaDTO dtoReq) {
    	Predavac p = new Predavac();
    	p.setIme(dtoReq.getIme());
    	p.setPrezime(dtoReq.getPrezime());
    	p.setEmail(dtoReq.getEmail());
    	p.setPass(passwordEncoder.encode(dtoReq.getPass()));
        Predavac pp = predavacRepository.save(p);
        
        return new PredavacDTO(pp.getId(), pp.getIme(), pp.getPrezime());
    }

}

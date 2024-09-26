package com.studentska.sluzba.dto.student;

public class ObavestenjeDTORes {

    private int idObavestenja;
    private String tekst;
    private int idPredmeta;
    private String nazivPredmeta;

    public int getIdObavestenja() {
        return idObavestenja;
    }

    public void setIdObavestenja(int idObavestenja) {
        this.idObavestenja = idObavestenja;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public int getIdPredmeta() {
        return idPredmeta;
    }

    public void setIdPredmeta(int idPredmeta) {
        this.idPredmeta = idPredmeta;
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }
}

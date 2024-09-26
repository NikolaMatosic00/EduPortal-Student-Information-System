package com.studentska.sluzba.dto.predavac;

public class ObavestenjeDTOReq {
    private String tekst;
    private int idPredmet;

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public int getIdPredmet() {
        return idPredmet;
    }

    public void setIdPredmet(int idPredmet) {
        this.idPredmet = idPredmet;
    }
}

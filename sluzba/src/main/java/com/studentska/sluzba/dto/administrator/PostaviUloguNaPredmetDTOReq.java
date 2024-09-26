package com.studentska.sluzba.dto.administrator;

import java.util.List;

public class PostaviUloguNaPredmetDTOReq {
    private int idPredmet;
    private int idPredavac;
    private String uloga;

    public int getIdPredmet() {
        return idPredmet;
    }

    public void setIdPredmet(int idPredmet) {
        this.idPredmet = idPredmet;
    }

    public int getIdPredavac() {
        return idPredavac;
    }

    public void setIdPredavac(int idPredavac) {
        this.idPredavac = idPredavac;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }




}

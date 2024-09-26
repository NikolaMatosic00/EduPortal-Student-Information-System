package com.studentska.sluzba.dto.administrator;

import java.util.List;

public class KreirajPredmetDTOReq {
    private int espb;
    private String naziv;
    private String silabus;
    private String tip;

    private int idSmer;

    private int IdSemestar;

    private List<Integer> tipoviPolaganja;

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSilabus() {
        return silabus;
    }

    public void setSilabus(String silabus) {
        this.silabus = silabus;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getIdSmer() {
        return idSmer;
    }

    public void setIdSmer(int idSmer) {
        this.idSmer = idSmer;
    }

    public int getIdSemestar() {
        return IdSemestar;
    }

    public void setIdSemestar(int idSemestar) {
        IdSemestar = idSemestar;
    }

    public List<Integer> getTipoviPolaganja() {
        return tipoviPolaganja;
    }

    public void setTipoviPolaganja(List<Integer> tipoviPolaganja) {
        this.tipoviPolaganja = tipoviPolaganja;
    }
}

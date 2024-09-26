package com.studentska.sluzba.dto.student;

import java.util.Date;

public class IstorijatPolaganjaIspitaDTORes {
    private int idPolaganja;
    private int ostvarenBrojBodova;
    private Date vremePrijave;

    private int idPredmet;
    private String nazivPredmeta;
    private int finalnaOcena;
    private String nazivRoka;

    public String getNazivRoka() {
        return nazivRoka;
    }

    public void setNazivRoka(String nazivRoka) {
        this.nazivRoka = nazivRoka;
    }

    public int getIdPolaganja() {
        return idPolaganja;
    }

    public void setIdPolaganja(int idPolaganja) {
        this.idPolaganja = idPolaganja;
    }

    public int getOstvarenBrojBodova() {
        return ostvarenBrojBodova;
    }

    public void setOstvarenBrojBodova(int ostvarenBrojBodova) {
        this.ostvarenBrojBodova = ostvarenBrojBodova;
    }

    public Date getVremePrijave() {
        return vremePrijave;
    }

    public void setVremePrijave(Date vremePrijave) {
        this.vremePrijave = vremePrijave;
    }

    public int getIdPredmet() {
        return idPredmet;
    }

    public void setIdPredmet(int idPredmet) {
        this.idPredmet = idPredmet;
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }

    public int getFinalnaOcena() {
        return finalnaOcena;
    }

    public void setFinalnaOcena(int finalnaOcena) {
        this.finalnaOcena = finalnaOcena;
    }
}

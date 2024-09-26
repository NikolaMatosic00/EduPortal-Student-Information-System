package com.studentska.sluzba.dto.predavac;

import java.util.Date;

public class PrijavaDTORes {
    private int id;
    private Date vremePrijave;
    private int ostvarenBrojBodova;
    private int finalnaOcena;
    private int idPredmet;
    private String nazivPredmeta;
    private int idStudent;
    private String imeStudenta;
    private String prezimeStudenta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getVremePrijave() {
        return vremePrijave;
    }

    public void setVremePrijave(Date vremePrijave) {
        this.vremePrijave = vremePrijave;
    }

    public int getOstvarenBrojBodova() {
        return ostvarenBrojBodova;
    }

    public void setOstvarenBrojBodova(int ostvarenBrojBodova) {
        this.ostvarenBrojBodova = ostvarenBrojBodova;
    }

    public int getFinalnaOcena() {
        return finalnaOcena;
    }

    public void setFinalnaOcena(int finalnaOcena) {
        this.finalnaOcena = finalnaOcena;
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

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getImeStudenta() {
        return imeStudenta;
    }

    public void setImeStudenta(String imeStudenta) {
        this.imeStudenta = imeStudenta;
    }

    public String getPrezimeStudenta() {
        return prezimeStudenta;
    }

    public void setPrezimeStudenta(String prezimeStudenta) {
        this.prezimeStudenta = prezimeStudenta;
    }
}

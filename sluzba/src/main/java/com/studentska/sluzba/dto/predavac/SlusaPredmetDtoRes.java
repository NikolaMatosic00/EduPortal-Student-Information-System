package com.studentska.sluzba.dto.predavac;

public class SlusaPredmetDtoRes {
    private int id;
    private int ocena;
    private int idPredmet;
    private String nazivPredmet;
    private int idStudent;
    private String imeStudent;
    private String prezimeStudent;
    private String indexStudent;
    private int elektronskiPotpis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public int getIdPredmet() {
        return idPredmet;
    }

    public void setIdPredmet(int idPredmet) {
        this.idPredmet = idPredmet;
    }

    public String getNazivPredmet() {
        return nazivPredmet;
    }

    public void setNazivPredmet(String nazivPredmet) {
        this.nazivPredmet = nazivPredmet;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getImeStudent() {
        return imeStudent;
    }

    public void setImeStudent(String imeStudent) {
        this.imeStudent = imeStudent;
    }

    public String getPrezimeStudent() {
        return prezimeStudent;
    }

    public void setPrezimeStudent(String prezimeStudent) {
        this.prezimeStudent = prezimeStudent;
    }

    public String getIndexStudent() {
        return indexStudent;
    }

    public void setIndexStudent(String indexStudent) {
        this.indexStudent = indexStudent;
    }

    public int getElektronskiPotpis() {
        return elektronskiPotpis;
    }

    public void setElektronskiPotpis(int elektronskiPotpis) {
        this.elektronskiPotpis = elektronskiPotpis;
    }
}

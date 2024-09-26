package com.studentska.sluzba.dto.predavac;

public class TerminPolaganjaDtoRes {
    private int id;
    private String nazivRoka;
    private String predmet;
    private String napomena;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivRoka() {
        return nazivRoka;
    }

    public void setNazivRoka(String nazivRoka) {
        this.nazivRoka = nazivRoka;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }
}

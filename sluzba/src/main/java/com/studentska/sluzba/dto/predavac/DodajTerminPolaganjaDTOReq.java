package com.studentska.sluzba.dto.predavac;

public class DodajTerminPolaganjaDTOReq {
    private String napomena;
    private String nazivRoka;
    private int tipPolaganjaId;
    private int predmetId;

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public String getNazivRoka() {
        return nazivRoka;
    }

    public void setNazivRoka(String nazivRoka) {
        this.nazivRoka = nazivRoka;
    }

    public int getTipPolaganjaId() {
        return tipPolaganjaId;
    }

    public void setTipPolaganjaId(int tipPolaganjaId) {
        this.tipPolaganjaId = tipPolaganjaId;
    }

    public int getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(int predmetId) {
        this.predmetId = predmetId;
    }
}

package com.studentska.sluzba.dto.predavac;

public class UnosOceneDTOReq {
    private int idSlusaPredmet;
    private int ocena;

    public int getIdSlusaPredmet() {
        return idSlusaPredmet;
    }

    public void setIdSlusaPredmet(int idSlusaPredmet) {
        this.idSlusaPredmet = idSlusaPredmet;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}

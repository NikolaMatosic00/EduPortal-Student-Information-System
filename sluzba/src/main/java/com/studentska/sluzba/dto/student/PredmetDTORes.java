package com.studentska.sluzba.dto.student;

public class PredmetDTORes {

    private int id;

    private int espb;

    private String naziv;

    private String silabus;

    private String tip;

    private int finalnaOcena;

    private String potpisan;

    public String getPotpisan() {
        return potpisan;
    }

    public void setPotpisan(String potpisan) {
        this.potpisan = potpisan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getFinalnaOcena() {
        return finalnaOcena;
    }

    public void setFinalnaOcena(int finalnaOcena) {
        this.finalnaOcena = finalnaOcena;
    }
}

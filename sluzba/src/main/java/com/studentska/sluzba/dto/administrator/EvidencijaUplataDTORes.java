package com.studentska.sluzba.dto.administrator;

public class EvidencijaUplataDTORes {
    private double iznos;
    private String svrha;
    private String vremeUplate;
    private int idStudent;
    private String imeStudenta;
    private String prezimeStudenta;

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public String getSvrha() {
        return svrha;
    }

    public void setSvrha(String svrha) {
        this.svrha = svrha;
    }

    public String getVremeUplate() {
        return vremeUplate;
    }

    public void setVremeUplate(String vremeUplate) {
        this.vremeUplate = vremeUplate;
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

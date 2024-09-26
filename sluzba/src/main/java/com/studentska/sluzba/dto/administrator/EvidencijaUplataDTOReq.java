package com.studentska.sluzba.dto.administrator;

import java.util.Date;

public class EvidencijaUplataDTOReq {
    private double iznos;
    private String svrha;
    private int idStudent;

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

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }
}

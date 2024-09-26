package com.studentska.sluzba.dto.predavac;

public class PolaganjeDtoRes {
    private int id;

    private int ostvarenBrojBodova;
    private String vremePrijave;
    private SlusaPredmetDtoRes slusaPredmetDtoRes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVremePrijave() {
        return vremePrijave;
    }

    public void setVremePrijave(String vremePrijave) {
        this.vremePrijave = vremePrijave;
    }

    public SlusaPredmetDtoRes getSlusaPredmetDtoRes() {
        return slusaPredmetDtoRes;
    }

    public void setSlusaPredmetDtoRes(SlusaPredmetDtoRes slusaPredmetDtoRes) {
        this.slusaPredmetDtoRes = slusaPredmetDtoRes;
    }

    public int getOstvarenBrojBodova() {
        return ostvarenBrojBodova;
    }

    public void setOstvarenBrojBodova(int ostvarenBrojBodova) {
        this.ostvarenBrojBodova = ostvarenBrojBodova;
    }
}

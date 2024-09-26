package com.studentska.sluzba.dto.predavac;

public class TipPolaganjaDtoRes {
    private int id;
    private String naziv;
    private int minimumZaProlaz;
    private int ukupno;
    private int minimumZaUslov;

    public TipPolaganjaDtoRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipPolaganjaDtoRes(int id, String naziv, int minimumZaProlaz, int ukupno, int minimumZaUslov) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.minimumZaProlaz = minimumZaProlaz;
		this.ukupno = ukupno;
		this.minimumZaUslov = minimumZaUslov;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getMinimumZaProlaz() {
        return minimumZaProlaz;
    }

    public void setMinimumZaProlaz(int minimumZaProlaz) {
        this.minimumZaProlaz = minimumZaProlaz;
    }

    public int getUkupno() {
        return ukupno;
    }

    public void setUkupno(int ukupno) {
        this.ukupno = ukupno;
    }

    public int getMinimumZaUslov() {
        return minimumZaUslov;
    }

    public void setMinimumZaUslov(int minimumZaUslov) {
        this.minimumZaUslov = minimumZaUslov;
    }
}

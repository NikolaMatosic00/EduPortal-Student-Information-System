package com.studentska.sluzba.dto.additional;


public class SmerDTO {
    private int id;
    private String naziv;
    // getters and setters
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
	public SmerDTO(int id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}
}


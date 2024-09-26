package com.studentska.sluzba.dto.student;


public class StudentDTO {
	
	private int id;
	private String brojIndeksa;
	private String ime;
	private String prezime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrojIndeksa() {
		return brojIndeksa;
	}
	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public StudentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentDTO(int id, String brojIndeksa, String ime, String prezime) {
		super();
		this.id = id;
		this.brojIndeksa = brojIndeksa;
		this.ime = ime;
		this.prezime = prezime;
	}
}

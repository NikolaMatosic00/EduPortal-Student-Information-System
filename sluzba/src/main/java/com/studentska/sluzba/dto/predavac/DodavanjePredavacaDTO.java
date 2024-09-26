package com.studentska.sluzba.dto.predavac;

public class DodavanjePredavacaDTO {
    private String ime;
    private String prezime;
    private String email;
    private String pass;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public DodavanjePredavacaDTO(String ime, String prezime, String email, String pass) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.pass = pass;
	}
}

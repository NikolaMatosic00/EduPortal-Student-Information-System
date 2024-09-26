package com.studentska.sluzba.dto.student;

public class DodavanjeStudentaDTO {
    private String ime;
    private String prezime;
    private String email;
    private String pass;
    private String brojIndeksa;
    private String smerId;
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
	public String getBrojIndeksa() {
		return brojIndeksa;
	}
	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}
	public String getSmerId() {
		return smerId;
	}
	public void setSmerId(String smerId) {
		this.smerId = smerId;
	}
}

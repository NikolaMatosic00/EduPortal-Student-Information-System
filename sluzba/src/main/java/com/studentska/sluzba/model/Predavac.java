package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the predavac database table.
 * 
 */
@Entity
@Table(name="predavac")
@NamedQuery(name="Predavac.findAll", query="SELECT p FROM Predavac p")
public class Predavac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	private String ime;

	private String pass;

	private String prezime;

	//bi-directional many-to-one association to PredavacPredmet
	@OneToMany(mappedBy="predavac")
	private List<PredavacPredmet> predavacPredmets;

	public Predavac(int id, String email, String ime, String pass, String prezime,
			List<PredavacPredmet> predavacPredmets) {
		super();
		this.id = id;
		this.email = email;
		this.ime = ime;
		this.pass = pass;
		this.prezime = prezime;
		this.predavacPredmets = predavacPredmets;
	}

	public Predavac() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public List<PredavacPredmet> getPredavacPredmets() {
		return this.predavacPredmets;
	}

	public void setPredavacPredmets(List<PredavacPredmet> predavacPredmets) {
		this.predavacPredmets = predavacPredmets;
	}

	public PredavacPredmet addPredavacPredmet(PredavacPredmet predavacPredmet) {
		getPredavacPredmets().add(predavacPredmet);
		predavacPredmet.setPredavac(this);

		return predavacPredmet;
	}

	public PredavacPredmet removePredavacPredmet(PredavacPredmet predavacPredmet) {
		getPredavacPredmets().remove(predavacPredmet);
		predavacPredmet.setPredavac(null);

		return predavacPredmet;
	}

}
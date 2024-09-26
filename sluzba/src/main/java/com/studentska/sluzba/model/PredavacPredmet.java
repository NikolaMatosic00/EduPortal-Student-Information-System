package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the predavac_predmet database table.
 * 
 */
@Entity
@Table(name="predavac_predmet")
@NamedQuery(name="PredavacPredmet.findAll", query="SELECT p FROM PredavacPredmet p")
public class PredavacPredmet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String uloga;

	//bi-directional many-to-one association to Predavac
	@ManyToOne
	private Predavac predavac;

	//bi-directional many-to-one association to Predmet
	@ManyToOne
	private Predmet predmet;

	public PredavacPredmet() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUloga() {
		return this.uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public Predavac getPredavac() {
		return this.predavac;
	}

	public void setPredavac(Predavac predavac) {
		this.predavac = predavac;
	}

	public Predmet getPredmet() {
		return this.predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

}
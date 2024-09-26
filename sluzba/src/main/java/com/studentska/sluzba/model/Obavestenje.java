package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the obavestenje database table.
 * 
 */
@Entity
@Table(name="obavestenje")
@NamedQuery(name="Obavestenje.findAll", query="SELECT o FROM Obavestenje o")
public class Obavestenje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String tekst;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="vreme_objave")
	private Date vremeObjave;

	//bi-directional many-to-one association to Predmet
	@ManyToOne
	private Predmet predmet;

	public Obavestenje() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Date getVremeObjave() {
		return this.vremeObjave;
	}

	public void setVremeObjave(Date vremeObjave) {
		this.vremeObjave = vremeObjave;
	}

	public Predmet getPredmet() {
		return this.predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

}
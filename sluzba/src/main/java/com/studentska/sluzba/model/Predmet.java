package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the predmet database table.
 * 
 */
@Entity
@Table(name="predmet")
@NamedQuery(name="Predmet.findAll", query="SELECT p FROM Predmet p")
public class Predmet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int espb;

	private String naziv;

	private String silabus;

	private String tip;

	//bi-directional many-to-one association to Obavestenje
	@OneToMany(mappedBy="predmet")
	private List<Obavestenje> obavestenjes;

	//bi-directional many-to-many association to TipPolaganja
	@ManyToMany(mappedBy="predmets")
	private List<TipPolaganja> tipPolaganjas;

	//bi-directional many-to-one association to PredavacPredmet
	@OneToMany(mappedBy="predmet")
	private List<PredavacPredmet> predavacPredmets;

	//bi-directional many-to-one association to Semestar
	@ManyToOne
	private Semestar semestar;

	//bi-directional many-to-one association to Smer
	@ManyToOne
	private Smer smer;

	//bi-directional many-to-one association to SlusaPredmet
	@OneToMany(mappedBy="predmet")
	private List<SlusaPredmet> slusaPredmets;

	public Predmet() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEspb() {
		return this.espb;
	}

	public void setEspb(int espb) {
		this.espb = espb;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSilabus() {
		return this.silabus;
	}

	public void setSilabus(String silabus) {
		this.silabus = silabus;
	}

	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public List<Obavestenje> getObavestenjes() {
		return this.obavestenjes;
	}

	public void setObavestenjes(List<Obavestenje> obavestenjes) {
		this.obavestenjes = obavestenjes;
	}

	public Obavestenje addObavestenje(Obavestenje obavestenje) {
		getObavestenjes().add(obavestenje);
		obavestenje.setPredmet(this);

		return obavestenje;
	}

	public Obavestenje removeObavestenje(Obavestenje obavestenje) {
		getObavestenjes().remove(obavestenje);
		obavestenje.setPredmet(null);

		return obavestenje;
	}

	public List<TipPolaganja> getTipPolaganjas() {
		return this.tipPolaganjas;
	}

	public void setTipPolaganjas(List<TipPolaganja> tipPolaganjas) {
		this.tipPolaganjas = tipPolaganjas;
	}

	public List<PredavacPredmet> getPredavacPredmets() {
		return this.predavacPredmets;
	}

	public void setPredavacPredmets(List<PredavacPredmet> predavacPredmets) {
		this.predavacPredmets = predavacPredmets;
	}

	public PredavacPredmet addPredavacPredmet(PredavacPredmet predavacPredmet) {
		getPredavacPredmets().add(predavacPredmet);
		predavacPredmet.setPredmet(this);

		return predavacPredmet;
	}

	public PredavacPredmet removePredavacPredmet(PredavacPredmet predavacPredmet) {
		getPredavacPredmets().remove(predavacPredmet);
		predavacPredmet.setPredmet(null);

		return predavacPredmet;
	}

	public Semestar getSemestar() {
		return this.semestar;
	}

	public void setSemestar(Semestar semestar) {
		this.semestar = semestar;
	}

	public Smer getSmer() {
		return this.smer;
	}

	public void setSmer(Smer smer) {
		this.smer = smer;
	}

	public List<SlusaPredmet> getSlusaPredmets() {
		return this.slusaPredmets;
	}

	public void setSlusaPredmets(List<SlusaPredmet> slusaPredmets) {
		this.slusaPredmets = slusaPredmets;
	}

	public SlusaPredmet addSlusaPredmet(SlusaPredmet slusaPredmet) {
		getSlusaPredmets().add(slusaPredmet);
		slusaPredmet.setPredmet(this);

		return slusaPredmet;
	}

	public SlusaPredmet removeSlusaPredmet(SlusaPredmet slusaPredmet) {
		getSlusaPredmets().remove(slusaPredmet);
		slusaPredmet.setPredmet(null);

		return slusaPredmet;
	}

}
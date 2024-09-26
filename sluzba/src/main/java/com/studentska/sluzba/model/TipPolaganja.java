package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tip_polaganja database table.
 * 
 */
@Entity
@Table(name="tip_polaganja")
@NamedQuery(name="TipPolaganja.findAll", query="SELECT t FROM TipPolaganja t")
public class TipPolaganja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="minimalno_za_prolaz")
	private int minimalnoZaProlaz;

	@Column(name="minimalno_za_uslov")
	private int minimalnoZaUslov;

	private String naziv;

	private int ukupno;

	//bi-directional many-to-many association to Predmet
	@ManyToMany
	@JoinTable(
		name="pravila_polaganja"
		, joinColumns={
			@JoinColumn(name="tip_polaganja_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="predmet_id")
			}
		)
	private List<Predmet> predmets;

	public TipPolaganja() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMinimalnoZaProlaz() {
		return this.minimalnoZaProlaz;
	}

	public void setMinimalnoZaProlaz(int minimalnoZaProlaz) {
		this.minimalnoZaProlaz = minimalnoZaProlaz;
	}

	public int getMinimalnoZaUslov() {
		return this.minimalnoZaUslov;
	}

	public void setMinimalnoZaUslov(int minimalnoZaUslov) {
		this.minimalnoZaUslov = minimalnoZaUslov;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getUkupno() {
		return this.ukupno;
	}

	public void setUkupno(int ukupno) {
		this.ukupno = ukupno;
	}

	public List<Predmet> getPredmets() {
		return this.predmets;
	}

	public void setPredmets(List<Predmet> predmets) {
		this.predmets = predmets;
	}

}
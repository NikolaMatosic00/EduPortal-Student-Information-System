package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the slusa_predmet database table.
 * 
 */
@Entity
@Table(name="slusa_predmet")
@NamedQuery(name="SlusaPredmet.findAll", query="SELECT s FROM SlusaPredmet s")
public class SlusaPredmet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="elektronski_potpis")
	private byte elektronskiPotpis;

	private int ocena;

	//bi-directional many-to-one association to Polaganje
	@OneToMany(mappedBy="slusaPredmet")
	private List<Polaganje> polaganjes;

	//bi-directional many-to-one association to Predmet
	@ManyToOne
	private Predmet predmet;

	//bi-directional many-to-one association to Student
	@ManyToOne
	private Student student;

	public SlusaPredmet() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getElektronskiPotpis() {
		return this.elektronskiPotpis;
	}

	public void setElektronskiPotpis(byte elektronskiPotpis) {
		this.elektronskiPotpis = elektronskiPotpis;
	}

	public int getOcena() {
		return this.ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public List<Polaganje> getPolaganjes() {
		return this.polaganjes;
	}

	public void setPolaganjes(List<Polaganje> polaganjes) {
		this.polaganjes = polaganjes;
	}

	public Polaganje addPolaganje(Polaganje polaganje) {
		getPolaganjes().add(polaganje);
		polaganje.setSlusaPredmet(this);

		return polaganje;
	}

	public Polaganje removePolaganje(Polaganje polaganje) {
		getPolaganjes().remove(polaganje);
		polaganje.setSlusaPredmet(null);

		return polaganje;
	}

	public Predmet getPredmet() {
		return this.predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
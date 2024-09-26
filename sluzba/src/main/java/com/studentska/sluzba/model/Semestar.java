package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the semestar database table.
 * 
 */
@Entity
@Table(name="semestar")
@NamedQuery(name="Semestar.findAll", query="SELECT s FROM Semestar s")
public class Semestar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="broj_semestra")
	private int brojSemestra;

	//bi-directional many-to-one association to Predmet
	@OneToMany(mappedBy="semestar")
	private List<Predmet> predmets;

	//bi-directional many-to-one association to StudentPohadja
	@OneToMany(mappedBy="semestar")
	private List<StudentPohadja> studentPohadjas;

	public Semestar() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBrojSemestra() {
		return this.brojSemestra;
	}

	public void setBrojSemestra(int brojSemestra) {
		this.brojSemestra = brojSemestra;
	}

	public List<Predmet> getPredmets() {
		return this.predmets;
	}

	public void setPredmets(List<Predmet> predmets) {
		this.predmets = predmets;
	}

	public Predmet addPredmet(Predmet predmet) {
		getPredmets().add(predmet);
		predmet.setSemestar(this);

		return predmet;
	}

	public Predmet removePredmet(Predmet predmet) {
		getPredmets().remove(predmet);
		predmet.setSemestar(null);

		return predmet;
	}

	public List<StudentPohadja> getStudentPohadjas() {
		return this.studentPohadjas;
	}

	public void setStudentPohadjas(List<StudentPohadja> studentPohadjas) {
		this.studentPohadjas = studentPohadjas;
	}

	public StudentPohadja addStudentPohadja(StudentPohadja studentPohadja) {
		getStudentPohadjas().add(studentPohadja);
		studentPohadja.setSemestar(this);

		return studentPohadja;
	}

	public StudentPohadja removeStudentPohadja(StudentPohadja studentPohadja) {
		getStudentPohadjas().remove(studentPohadja);
		studentPohadja.setSemestar(null);

		return studentPohadja;
	}

}
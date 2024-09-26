package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the smer database table.
 * 
 */
@Entity
@Table(name="smer")
@NamedQuery(name="Smer.findAll", query="SELECT s FROM Smer s")
public class Smer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String naziv;

	//bi-directional many-to-one association to Predmet
	@OneToMany(mappedBy="smer")
	private List<Predmet> predmets;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="smer")
	private List<Student> students;

	public Smer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Predmet> getPredmets() {
		return this.predmets;
	}

	public void setPredmets(List<Predmet> predmets) {
		this.predmets = predmets;
	}

	public Predmet addPredmet(Predmet predmet) {
		getPredmets().add(predmet);
		predmet.setSmer(this);

		return predmet;
	}

	public Predmet removePredmet(Predmet predmet) {
		getPredmets().remove(predmet);
		predmet.setSmer(null);

		return predmet;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setSmer(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setSmer(null);

		return student;
	}

}
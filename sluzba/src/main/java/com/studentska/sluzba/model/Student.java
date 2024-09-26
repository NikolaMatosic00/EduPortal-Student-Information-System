package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@Table(name="student")
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="broj_indexa")
	private String brojIndexa;

	private String email;

	private String ime;

	private String pass;

	private String prezime;

	//bi-directional many-to-one association to SlusaPredmet
	@OneToMany(mappedBy="student")
	private List<SlusaPredmet> slusaPredmets;

	//bi-directional many-to-one association to Smer
	@ManyToOne
	private Smer smer;

	//bi-directional many-to-one association to StudentPohadja
	@OneToMany(mappedBy="student")
	private List<StudentPohadja> studentPohadjas;

	//bi-directional many-to-one association to Uplata
	@OneToMany(mappedBy="student")
	private List<Uplata> uplatas;

	public Student() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrojIndexa() {
		return this.brojIndexa;
	}

	public void setBrojIndexa(String brojIndexa) {
		this.brojIndexa = brojIndexa;
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

	public List<SlusaPredmet> getSlusaPredmets() {
		return this.slusaPredmets;
	}

	public void setSlusaPredmets(List<SlusaPredmet> slusaPredmets) {
		this.slusaPredmets = slusaPredmets;
	}

	public SlusaPredmet addSlusaPredmet(SlusaPredmet slusaPredmet) {
		getSlusaPredmets().add(slusaPredmet);
		slusaPredmet.setStudent(this);

		return slusaPredmet;
	}

	public SlusaPredmet removeSlusaPredmet(SlusaPredmet slusaPredmet) {
		getSlusaPredmets().remove(slusaPredmet);
		slusaPredmet.setStudent(null);

		return slusaPredmet;
	}

	public Smer getSmer() {
		return this.smer;
	}

	public void setSmer(Smer smer) {
		this.smer = smer;
	}

	public List<StudentPohadja> getStudentPohadjas() {
		return this.studentPohadjas;
	}

	public void setStudentPohadjas(List<StudentPohadja> studentPohadjas) {
		this.studentPohadjas = studentPohadjas;
	}

	public StudentPohadja addStudentPohadja(StudentPohadja studentPohadja) {
		getStudentPohadjas().add(studentPohadja);
		studentPohadja.setStudent(this);

		return studentPohadja;
	}

	public StudentPohadja removeStudentPohadja(StudentPohadja studentPohadja) {
		getStudentPohadjas().remove(studentPohadja);
		studentPohadja.setStudent(null);

		return studentPohadja;
	}

	public List<Uplata> getUplatas() {
		return this.uplatas;
	}

	public void setUplatas(List<Uplata> uplatas) {
		this.uplatas = uplatas;
	}

	public Uplata addUplata(Uplata uplata) {
		getUplatas().add(uplata);
		uplata.setStudent(this);

		return uplata;
	}

	public Uplata removeUplata(Uplata uplata) {
		getUplatas().remove(uplata);
		uplata.setStudent(null);

		return uplata;
	}

}
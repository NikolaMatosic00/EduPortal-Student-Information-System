package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the uplata database table.
 * 
 */
@Entity
@Table(name="uplata")
@NamedQuery(name="Uplata.findAll", query="SELECT u FROM Uplata u")
public class Uplata implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double iznos;

	private String svrha;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="vreme_uplate")
	private Date vremeUplate;

	//bi-directional many-to-one association to Admin
	@ManyToOne
	private Admin admin;

	//bi-directional many-to-one association to Student
	@ManyToOne
	private Student student;

	public Uplata() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getIznos() {
		return this.iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public String getSvrha() {
		return this.svrha;
	}

	public void setSvrha(String svrha) {
		this.svrha = svrha;
	}

	public Date getVremeUplate() {
		return this.vremeUplate;
	}

	public void setVremeUplate(Date vremeUplate) {
		this.vremeUplate = vremeUplate;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
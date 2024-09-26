package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the student_pohadja database table.
 * 
 */
@Entity
@Table(name="student_pohadja")
@NamedQuery(name="StudentPohadja.findAll", query="SELECT s FROM StudentPohadja s")
public class StudentPohadja implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StudentPohadjaPK id;

	private byte overen;

	//bi-directional many-to-one association to Semestar
	@ManyToOne
	@JoinColumn(insertable=false, updatable=false)
	private Semestar semestar;

	//bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(insertable=false, updatable=false)
	private Student student;

	public StudentPohadja() {
	}

	public StudentPohadjaPK getId() {
		return this.id;
	}

	public void setId(StudentPohadjaPK id) {
		this.id = id;
	}

	public byte getOveren() {
		return this.overen;
	}

	public void setOveren(byte overen) {
		this.overen = overen;
	}

	public Semestar getSemestar() {
		return this.semestar;
	}

	public void setSemestar(Semestar semestar) {
		this.semestar = semestar;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
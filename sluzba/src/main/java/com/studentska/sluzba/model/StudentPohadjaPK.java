package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the student_pohadja database table.
 * 
 */
@Embeddable
public class StudentPohadjaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="student_id", insertable=false, updatable=false)
	private int studentId;

	@Column(name="semestar_id", insertable=false, updatable=false)
	private int semestarId;

	public StudentPohadjaPK() {
	}
	public int getStudentId() {
		return this.studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getSemestarId() {
		return this.semestarId;
	}
	public void setSemestarId(int semestarId) {
		this.semestarId = semestarId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof StudentPohadjaPK)) {
			return false;
		}
		StudentPohadjaPK castOther = (StudentPohadjaPK)other;
		return 
			(this.studentId == castOther.studentId)
			&& (this.semestarId == castOther.semestarId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.studentId;
		hash = hash * prime + this.semestarId;
		
		return hash;
	}
}
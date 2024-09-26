package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the pravila_polaganja database table.
 * 
 */
@Embeddable
public class PravilaPolaganjaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="tip_polaganja_id", insertable=false, updatable=false)
	private int tipPolaganjaId;

	@Column(name="predmet_id", insertable=false, updatable=false)
	private int predmetId;

	public PravilaPolaganjaPK() {
	}
	public int getTipPolaganjaId() {
		return this.tipPolaganjaId;
	}
	public void setTipPolaganjaId(int tipPolaganjaId) {
		this.tipPolaganjaId = tipPolaganjaId;
	}
	public int getPredmetId() {
		return this.predmetId;
	}
	public void setPredmetId(int predmetId) {
		this.predmetId = predmetId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PravilaPolaganjaPK)) {
			return false;
		}
		PravilaPolaganjaPK castOther = (PravilaPolaganjaPK)other;
		return 
			(this.tipPolaganjaId == castOther.tipPolaganjaId)
			&& (this.predmetId == castOther.predmetId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tipPolaganjaId;
		hash = hash * prime + this.predmetId;
		
		return hash;
	}
}
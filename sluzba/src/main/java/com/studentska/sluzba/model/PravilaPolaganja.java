package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pravila_polaganja database table.
 * 
 */
@Entity
@Table(name="pravila_polaganja")
@NamedQuery(name="PravilaPolaganja.findAll", query="SELECT p FROM PravilaPolaganja p")
public class PravilaPolaganja implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PravilaPolaganjaPK id;

	//bi-directional many-to-one association to TerminPolaganja
	@OneToMany(mappedBy="pravilaPolaganja")
	private List<TerminPolaganja> terminPolaganjas;

	public PravilaPolaganja() {
	}

	public PravilaPolaganjaPK getId() {
		return this.id;
	}

	public void setId(PravilaPolaganjaPK id) {
		this.id = id;
	}

	public List<TerminPolaganja> getTerminPolaganjas() {
		return this.terminPolaganjas;
	}

	public void setTerminPolaganjas(List<TerminPolaganja> terminPolaganjas) {
		this.terminPolaganjas = terminPolaganjas;
	}

	public TerminPolaganja addTerminPolaganja(TerminPolaganja terminPolaganja) {
		getTerminPolaganjas().add(terminPolaganja);
		terminPolaganja.setPravilaPolaganja(this);

		return terminPolaganja;
	}

	public TerminPolaganja removeTerminPolaganja(TerminPolaganja terminPolaganja) {
		getTerminPolaganjas().remove(terminPolaganja);
		terminPolaganja.setPravilaPolaganja(null);

		return terminPolaganja;
	}

}
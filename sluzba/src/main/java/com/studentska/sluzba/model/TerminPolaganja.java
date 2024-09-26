package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the termin_polaganja database table.
 * 
 */
@Entity
@Table(name="termin_polaganja")
@NamedQuery(name="TerminPolaganja.findAll", query="SELECT t FROM TerminPolaganja t")
public class TerminPolaganja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String napomena;

	@Column(name="naziv_roka")
	private String nazivRoka;

	//bi-directional many-to-one association to Polaganje
	@OneToMany(mappedBy="terminPolaganja")
	private List<Polaganje> polaganjes;

	//bi-directional many-to-one association to PravilaPolaganja
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="tip_polaganja_has_predmet_predmet_id", referencedColumnName="predmet_id"),
		@JoinColumn(name="tip_polaganja_has_predmet_tip_polaganja_id", referencedColumnName="tip_polaganja_id")
		})
	private PravilaPolaganja pravilaPolaganja;

	public TerminPolaganja() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNapomena() {
		return this.napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public String getNazivRoka() {
		return this.nazivRoka;
	}

	public void setNazivRoka(String nazivRoka) {
		this.nazivRoka = nazivRoka;
	}

	public List<Polaganje> getPolaganjes() {
		return this.polaganjes;
	}

	public void setPolaganjes(List<Polaganje> polaganjes) {
		this.polaganjes = polaganjes;
	}

	public Polaganje addPolaganje(Polaganje polaganje) {
		getPolaganjes().add(polaganje);
		polaganje.setTerminPolaganja(this);

		return polaganje;
	}

	public Polaganje removePolaganje(Polaganje polaganje) {
		getPolaganjes().remove(polaganje);
		polaganje.setTerminPolaganja(null);

		return polaganje;
	}

	public PravilaPolaganja getPravilaPolaganja() {
		return this.pravilaPolaganja;
	}

	public void setPravilaPolaganja(PravilaPolaganja pravilaPolaganja) {
		this.pravilaPolaganja = pravilaPolaganja;
	}

}
package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the polaganje database table.
 * 
 */
@Entity
@Table(name="polaganje")
@NamedQuery(name="Polaganje.findAll", query="SELECT p FROM Polaganje p")
public class Polaganje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="ostvaren_broj_bodova")
	private int ostvarenBrojBodova;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="vreme_prijave")
	private Date vremePrijave;

	//bi-directional many-to-one association to SlusaPredmet
	@ManyToOne
	@JoinColumn(name="slusa_predmet_id")
	private SlusaPredmet slusaPredmet;

	//bi-directional many-to-one association to TerminPolaganja
	@ManyToOne
	@JoinColumn(name="termin_polaganja_id")
	private TerminPolaganja terminPolaganja;

	public Polaganje() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOstvarenBrojBodova() {
		return this.ostvarenBrojBodova;
	}

	public void setOstvarenBrojBodova(int ostvarenBrojBodova) {
		this.ostvarenBrojBodova = ostvarenBrojBodova;
	}

	public Date getVremePrijave() {
		return this.vremePrijave;
	}

	public void setVremePrijave(Date vremePrijave) {
		this.vremePrijave = vremePrijave;
	}

	public SlusaPredmet getSlusaPredmet() {
		return this.slusaPredmet;
	}

	public void setSlusaPredmet(SlusaPredmet slusaPredmet) {
		this.slusaPredmet = slusaPredmet;
	}

	public TerminPolaganja getTerminPolaganja() {
		return this.terminPolaganja;
	}

	public void setTerminPolaganja(TerminPolaganja terminPolaganja) {
		this.terminPolaganja = terminPolaganja;
	}

}
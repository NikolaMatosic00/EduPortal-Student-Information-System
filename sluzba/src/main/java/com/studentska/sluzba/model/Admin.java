package com.studentska.sluzba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the admin database table.
 * 
 */
@Entity
@Table(name="admin")
@NamedQuery(name="Admin.findAll", query="SELECT a FROM Admin a")
public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	private String pass;

	//bi-directional many-to-one association to Uplata
	@OneToMany(mappedBy="admin")
	private List<Uplata> uplatas;

	public Admin() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public List<Uplata> getUplatas() {
		return this.uplatas;
	}

	public void setUplatas(List<Uplata> uplatas) {
		this.uplatas = uplatas;
	}

	public Uplata addUplata(Uplata uplata) {
		getUplatas().add(uplata);
		uplata.setAdmin(this);

		return uplata;
	}

	public Uplata removeUplata(Uplata uplata) {
		getUplatas().remove(uplata);
		uplata.setAdmin(null);

		return uplata;
	}

}
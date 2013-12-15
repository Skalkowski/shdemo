package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "trumna.all", query = "Select t from Trumna t"),
		@NamedQuery(name = "trumna.dell", query = "Select t from Trumna t where t.nazwa = :nazwa")
})
public class Trumna {

	private int id;
	private String nazwa;
	private String gatunek_drewna;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getGatunek_drewna() {
		return gatunek_drewna;
	}
	
	public void setGatunek_drewna(String gatunek_drewna) {
		this.gatunek_drewna = gatunek_drewna;
	}
	
	public String getNazwa() {
		return nazwa;
	}
	
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	
	
}

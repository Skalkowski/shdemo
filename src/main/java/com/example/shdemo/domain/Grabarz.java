package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
		@NamedQuery(name = "grabarz.all", query = "Select g from Grabarz g"),
		@NamedQuery(name = "grabarz.byId", query = "Select g from Grabarz g where g.id = :id") })
public class Grabarz {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String imie;
	private String nazwisko;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Trumna> listaTrumien = new ArrayList<Trumna>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public List<Trumna> getListaTrumien() {
		return listaTrumien;
	}

	public void setListaTrumien(List<Trumna> listaTrumien) {
		this.listaTrumien = listaTrumien;
	}

}

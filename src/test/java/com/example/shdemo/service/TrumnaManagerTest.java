package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Trumna;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class TrumnaManagerTest {

	@Autowired
	TrumnaManager trumnaManager;

	private final String NAZWA_1 = "trumienka";
	private final String GATUNEK_1 = "sosna";

	private final String NAZWA_2 = "trumnos";
	private final String GATUNEK_2 = "dab";

	private final String NAZWA_3 = "trumieneczka";
	private final String GATUNEK_3 = "sosna";

	private final String NAZWA_DODANIE = "dodana";
	private final String GATUNEK_DODANIE = "sosna";

	private final String GATUNEK_UPDATE = "aktualizowany";

	@Before
	public void play() {
		Trumna trumna1 = new Trumna();
		trumna1.setNazwa(NAZWA_1);
		trumna1.setGatunek_drewna(GATUNEK_1);

		Trumna trumna2 = new Trumna();
		trumna2.setNazwa(NAZWA_2);
		trumna2.setGatunek_drewna(GATUNEK_2);

		Trumna trumna3 = new Trumna();
		trumna3.setNazwa(NAZWA_3);
		trumna3.setGatunek_drewna(GATUNEK_3);

		trumnaManager.addTrumna(trumna1);
		trumnaManager.addTrumna(trumna2);
		trumnaManager.addTrumna(trumna3);

	}

	@Test
	public void createTrumna() {

		Trumna trumna4 = new Trumna();
		trumna4.setNazwa(NAZWA_DODANIE);
		trumna4.setGatunek_drewna(GATUNEK_DODANIE);

		int id = trumnaManager.addTrumna(trumna4);

		Trumna trumnaTemp = trumnaManager.getTrumnaId(id);

		assertEquals(NAZWA_DODANIE, trumnaTemp.getNazwa());
		assertEquals(GATUNEK_DODANIE, trumnaTemp.getGatunek_drewna());

	}

	@Test
	public void readTrumna() {
		
		
		assert(0 < trumnaManager.getAllTrumna().size());

	}

	@Test
	public void deleteTrumna() {

		int liczbaPrzedDodaniem = trumnaManager.getAllTrumna().size();
		
		Trumna trumnaDoUsuniecia = new Trumna();
		trumnaDoUsuniecia.setNazwa(NAZWA_1);
		trumnaDoUsuniecia.setGatunek_drewna(GATUNEK_1);
		int id = trumnaManager.addTrumna(trumnaDoUsuniecia);
		
		int liczbaPoDodaniu = trumnaManager.getAllTrumna().size();

		assertEquals(liczbaPrzedDodaniem + 1, liczbaPoDodaniu);
		
		Trumna trumienka = trumnaManager.getTrumnaId(id);
		trumnaManager.deleteTrumna(trumienka);
		assertEquals(null, trumnaManager.getTrumnaId(id));

		int liczbaPoUsunieciu = trumnaManager.getAllTrumna().size();
		assertEquals(liczbaPrzedDodaniem, liczbaPoUsunieciu);
	}

	@Test
	public void getId() {
		Trumna trumna4 = new Trumna();
		trumna4.setNazwa(NAZWA_1);
		trumna4.setGatunek_drewna(GATUNEK_1);
		int id = trumnaManager.addTrumna(trumna4);

		Trumna trumnaPoId = trumnaManager.getTrumnaId(id);
		assertEquals(NAZWA_1, trumnaPoId.getNazwa());
		assertEquals(GATUNEK_1, trumnaPoId.getGatunek_drewna());
	}

	@Test
	public void updateTrumna() {
		//pobieram do sprawdzenia wszystkie trumny przed dodaniem i aktualizacją nowej
		List <Trumna> trumnyPrzed = trumnaManager.getAllTrumna();
		
		//dodanie trumny do aktualizacji
		Trumna trumna = new Trumna();
		trumna.setNazwa(NAZWA_1);
		trumna.setGatunek_drewna(GATUNEK_1);
		int id = trumnaManager.addTrumna(trumna);
		
		
		Trumna doZmiany = trumnaManager.getTrumnaId(id);
		doZmiany.setGatunek_drewna(GATUNEK_UPDATE);
		trumnaManager.updateTrumna(doZmiany);

		Trumna poZmianie = trumnaManager.getTrumnaId(id);
		
		assertEquals(GATUNEK_UPDATE, poZmianie.getGatunek_drewna());
		List <Trumna> trumnyPo = trumnaManager.getAllTrumna();
		
		for (int i = 1; i < trumnyPrzed.size(); i++){
			assertEquals(trumnyPrzed.get(i).getGatunek_drewna(), trumnyPo.get(i).getGatunek_drewna());
		}
	

	}

}

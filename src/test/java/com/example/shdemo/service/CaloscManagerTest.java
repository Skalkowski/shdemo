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

import com.example.shdemo.domain.Grabarz;
import com.example.shdemo.domain.Trumna;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class CaloscManagerTest {
	@Autowired
	GrabarzManager grabarzManager;

	@Autowired
	TrumnaManager trumnaManager;

	private final String IMIE_1 = "Piotr";
	private final String NAZWISKO_1 = "Ciekawski";
	private final String IMIE_2 = "Jan";
	private final String NAZWISKO_2 = "Nowak";
	private final String IMIE_3 = "Jarosław";
	private final String NAZWISKO_3 = "Bocianowski";

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
		Grabarz grabarz = new Grabarz();
		grabarz.setImie("Adam");
		grabarz.setNazwisko("Kowalski");
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

		Grabarz grabarz1 = new Grabarz();
		grabarz1.setImie(IMIE_1);
		grabarz1.setNazwisko(NAZWISKO_1);
		Grabarz grabarz2 = new Grabarz();
		grabarz2.setImie(IMIE_2);
		grabarz2.setNazwisko(NAZWISKO_2);
		Grabarz grabarz3 = new Grabarz();
		grabarz3.setImie(IMIE_3);
		grabarz3.setNazwisko(NAZWISKO_3);

		grabarzManager.addGrabarz(grabarz1);
		grabarzManager.addGrabarz(grabarz2);
		grabarzManager.addGrabarz(grabarz3);

	}

	@Test
	public void createGrabarz() {

		Grabarz grabarz = new Grabarz();
		grabarz.setImie("Adam");
		grabarz.setNazwisko("Kowalski");

		int id = grabarzManager.addGrabarz(grabarz);

		Grabarz grabarzTemp = grabarzManager.getGrabarzId(id);

		assertEquals("Adam", grabarzTemp.getImie());
		assertEquals("Kowalski", grabarzTemp.getNazwisko());

	}

	@Test
	public void readGrabarz() {

		assert (0 < grabarzManager.getAllGrabarz().size());

	}

	@Test
	public void deleteGrabarz() {

		int liczbaPrzedDodaniem = grabarzManager.getAllGrabarz().size();

		Grabarz grabarzDoUsuniecia = new Grabarz();
		grabarzDoUsuniecia.setImie(IMIE_1);
		grabarzDoUsuniecia.setNazwisko(NAZWISKO_1);
		int id = grabarzManager.addGrabarz(grabarzDoUsuniecia);

		int liczbaPoDodaniu = grabarzManager.getAllGrabarz().size();

		assertEquals(liczbaPrzedDodaniem + 1, liczbaPoDodaniu);

		Grabarz grabarzyk = grabarzManager.getGrabarzId(id);
		grabarzManager.deleteGrabarz(grabarzyk);
		assertEquals(null, grabarzManager.getGrabarzId(id));

		int liczbaPoUsunieciu = grabarzManager.getAllGrabarz().size();
		assertEquals(liczbaPrzedDodaniem, liczbaPoUsunieciu);
	}

	@Test
	public void getGrabarzId() {
		Grabarz grabarz4 = new Grabarz();
		grabarz4.setImie(IMIE_1);
		grabarz4.setNazwisko(NAZWISKO_1);
		int id = grabarzManager.addGrabarz(grabarz4);

		Grabarz grabarzPoId = grabarzManager.getGrabarzId(id);
		assertEquals(IMIE_1, grabarzPoId.getImie());
		assertEquals(NAZWISKO_1, grabarzPoId.getNazwisko());
	}

	@Test
	public void updateGrabarz() {
		// pobieram do sprawdzenia wszystkie trumny przed dodaniem i
		// aktualizacją nowej
		List<Grabarz> grabarzPrzed = grabarzManager.getAllGrabarz();

		// dodanie trumny do aktualizacji
		Grabarz grabarz = new Grabarz();
		grabarz.setImie(IMIE_1);
		grabarz.setNazwisko(NAZWISKO_1);
		int id = grabarzManager.addGrabarz(grabarz);

		Grabarz doZmiany = grabarzManager.getGrabarzId(id);
		doZmiany.setNazwisko(NAZWISKO_1);
		grabarzManager.updateGrabarz(doZmiany);

		Grabarz poZmianie = grabarzManager.getGrabarzId(id);

		assertEquals(NAZWISKO_1, poZmianie.getNazwisko());
		List<Grabarz> grabarzPo = grabarzManager.getAllGrabarz();

		for (int i = 0; i < grabarzPrzed.size(); i++) {
			assertEquals(grabarzPrzed.get(i).getImie(), grabarzPo.get(i)
					.getImie());
			assertEquals(grabarzPrzed.get(i).getNazwisko(), grabarzPo.get(i)
					.getNazwisko());
		}

	}

	// testowanie trumien
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

		assert (0 < trumnaManager.getAllTrumna().size());

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
		// pobieram do sprawdzenia wszystkie trumny przed dodaniem i
		// aktualizacją nowej
		List<Trumna> trumnyPrzed = trumnaManager.getAllTrumna();

		// dodanie trumny do aktualizacji
		Trumna trumna = new Trumna();
		trumna.setNazwa(NAZWA_1);
		trumna.setGatunek_drewna(GATUNEK_1);
		int id = trumnaManager.addTrumna(trumna);

		Trumna doZmiany = trumnaManager.getTrumnaId(id);
		doZmiany.setGatunek_drewna(GATUNEK_UPDATE);
		trumnaManager.updateTrumna(doZmiany);

		Trumna poZmianie = trumnaManager.getTrumnaId(id);

		assertEquals(GATUNEK_UPDATE, poZmianie.getGatunek_drewna());
		List<Trumna> trumnyPo = trumnaManager.getAllTrumna();

		for (int i = 0; i < trumnyPrzed.size(); i++) {
			assertEquals(trumnyPrzed.get(i).getGatunek_drewna(), trumnyPo
					.get(i).getGatunek_drewna());
		}

	}

}

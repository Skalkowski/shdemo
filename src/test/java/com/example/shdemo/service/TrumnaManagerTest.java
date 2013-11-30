package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

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


	@Test
	public void addTrumna() {
		
		Trumna trumna1 = new Trumna();
		trumna1.setNazwa(NAZWA_1);
		trumna1.setGatunek_drewna(GATUNEK_1);
		int trumnaId1 = trumnaManager.addTrumna(trumna1);
		
		Trumna trumnaTemp = trumnaManager.getTrumnaId(trumnaId1);

		assertEquals(NAZWA_1, trumnaTemp.getNazwa());
		assertEquals(GATUNEK_1, trumnaTemp.getGatunek_drewna());
		
	}


}

package com.example.shdemo.service;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Grabarz;
import com.example.shdemo.domain.Trumna;

@Component
@Transactional
public class GrabarzManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public int addGrabarz(Grabarz grabarz) {
		return (Integer) sessionFactory.getCurrentSession().save(grabarz);
	}

	@SuppressWarnings("unchecked")
	public List<Grabarz> getAllGrabarz() {

		return sessionFactory.getCurrentSession().getNamedQuery("grabarz.all")
				.list();
	}

	public void deleteGrabarz(Grabarz grabarz) {
		sessionFactory.getCurrentSession().delete(grabarz);
	}

	public Grabarz getGrabarzId(int id) {
		return (Grabarz) sessionFactory.getCurrentSession().get(Grabarz.class,
				id);

	}

	public void updateGrabarz(Grabarz grabarz) {
		sessionFactory.getCurrentSession().update(grabarz);
	}
	
	
	
	
	
	public void lacz(int grabarzId, int trumnaId) {
		Grabarz grabarz = (Grabarz) sessionFactory.getCurrentSession().get(
				Grabarz.class, grabarzId);
		Trumna trumna = (Trumna) sessionFactory.getCurrentSession()
				.get(Trumna.class, trumnaId);
		grabarz.getListaTrumien().add(trumna);
	}
	
	

	
	
	
}

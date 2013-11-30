package com.example.shdemo.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Trumna;

@Component
@Transactional
public class TrumnaManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	public int addTrumna(Trumna trumna) {
		return (Integer) sessionFactory.getCurrentSession().save(trumna);
	}
	
	public void deleteTrumna(Trumna trumna) {
		sessionFactory.getCurrentSession().delete(trumna);
	}
	
	
	public void updateTrumna(Trumna trumna){
		sessionFactory.getCurrentSession().update(trumna);
	}

	public Trumna getTrumnaId(int id){
		return (Trumna) sessionFactory.getCurrentSession().get(Trumna.class, id);	
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Trumna> getAllTrumna() {
	
		return sessionFactory.getCurrentSession().getNamedQuery("trumna.all").list();
	}

}

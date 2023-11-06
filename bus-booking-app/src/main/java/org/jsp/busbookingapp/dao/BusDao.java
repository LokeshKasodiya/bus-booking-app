package org.jsp.busbookingapp.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import org.jsp.busbookingapp.dto.Admin;
import org.jsp.busbookingapp.dto.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BusDao {
	@Autowired
	public EntityManager manager;

	public Bus addBus(Bus bus,int AdminId) {
		Admin a=manager.find(Admin.class, AdminId);
		if(a!=null) {
			bus.setAdmin(a);
			a.getBuses().add(bus);
		EntityTransaction transaction = manager.getTransaction();
		manager.persist(bus);
		transaction.begin();
		transaction.commit();
		return bus;
		}
		return null;
	}

	public Bus updateBus(Bus bus,int AdminId) {
		Admin a=manager.find(Admin.class, AdminId);
		if(a!=null) {
			bus.setAdmin(a);
			a.getBuses().add(bus);
		EntityTransaction transaction = manager.getTransaction();
		manager.merge(bus);
		transaction.begin();
		transaction.commit();
		return bus;
		}
		return null;
	}
	

	public List<Bus> findByAdminId(int id) {
		String jpql = "select a.buses from Admin a where a.id=?1";
		javax.persistence.Query q = manager.createQuery(jpql);
		q.setParameter(1, id);
		return q.getResultList();
	}

	public List<Bus> findByInfo(String from_loc, String to_loc, LocalDate date_of_departure) {
		String jpql = "select b from Bus b where b.from_loc=?1 and b.to_loc=?2 and b.date_of_departure=?3";
		javax.persistence.Query q = manager.createQuery(jpql);
		q.setParameter(1, from_loc);
		q.setParameter(2, to_loc);
		q.setParameter(3, date_of_departure);
		return q.getResultList();
	}
}

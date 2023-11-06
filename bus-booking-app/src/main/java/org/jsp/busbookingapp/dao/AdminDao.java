package org.jsp.busbookingapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import org.jsp.busbookingapp.dto.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {
	@Autowired
	public EntityManager manager;

	public Admin saveAdmin(Admin admin) {
		EntityTransaction transaction = manager.getTransaction();
		manager.persist(admin);
		transaction.begin();
		transaction.commit();
		return admin;
	}

	public Admin updateAdmin(Admin admin) {
		EntityTransaction transaction = manager.getTransaction();
		manager.merge(admin);
		transaction.begin();
		transaction.commit();
		return admin;
	}

	public Admin findById(int id) {
		return manager.find(Admin.class, id);
	}

	public Admin verifyAdmin(long phone, String password) {
		String jpql = "select a from Admin a where a.phone=?1 and a.password=?2";
		javax.persistence.Query q = manager.createQuery(jpql);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (Admin) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Admin verifyAdmin(String email, String password) {
		String jpql = "select a from Admin a where a.email=?1 and a.password=?2";
		javax.persistence.Query q = manager.createQuery(jpql);
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (Admin) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean deleteAdmin(int id) {
		Admin a = findById(id);
		if (a != null) {
			EntityTransaction transaction = manager.getTransaction();
			manager.remove(a);
			transaction.begin();
			transaction.commit();
			return true;
		}
		return false;
	}
}

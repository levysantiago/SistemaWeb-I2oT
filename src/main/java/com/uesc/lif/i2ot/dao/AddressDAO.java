package com.uesc.lif.i2ot.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.uesc.lif.i2ot.model.Address;
import com.uesc.lif.i2ot.util.HibernateUtil;


@SuppressWarnings("serial")
public class AddressDAO extends GenericDAO<Address> implements Serializable {
	// Saving a register and returning the id
	public Long saveGetId(Address address) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.getTransaction();
			transaction.begin();
			session.persist(address);
			transaction.commit();
			return address.getId();
		} catch (RuntimeException e) {
			if (transaction != null) {
				// Undoing the hole process
				transaction.rollback();
			}
			// Propagating the error
			throw e;

		} finally {
			session.close();
		}
	}
}

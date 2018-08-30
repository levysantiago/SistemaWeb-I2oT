package com.uesc.lif.i2ot.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.uesc.lif.i2ot.util.HibernateUtil;

//The Entity represents a generic class
public class GenericDAO<Entity> {

	private Class<Entity> classe;
	
	public Class<Entity> getClasse(){
		return classe;
	}

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		// Getting the specific type (class) from Entity
		this.classe = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		System.out.println(this.classe);
	}

	// Saving a register
	public Entity save(Entity entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(entity);
			transaction.commit();
			return entity;
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

	// Searching a register
	public Entity search(Long code) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			// Create CriteriaQuery
			CriteriaBuilder builder = session.getCriteriaBuilder();

			// Create CriteriaQuery
			CriteriaQuery<Entity> consulta = builder.createQuery(classe);

			// Specify criteria root
			Root<Entity> pRoot = consulta.from(classe);
			Character deleted = '0';
			consulta.select(pRoot).where(builder.equal(pRoot.get("deleted"), deleted), builder.equal(pRoot.get("id"), code));

			// Execute query
			Entity object = session.createQuery(consulta).getSingleResult();

			return object;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	// Listing every register from a table
	public List<Entity> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			// Create CriteriaQuery
			CriteriaBuilder builder = session.getCriteriaBuilder();

			// Create CriteriaQuery
			CriteriaQuery<Entity> consulta = builder.createQuery(classe);

			// Specify criteria root
			Root<Entity> pRoot = consulta.from(classe);
			Character deleted = '0';
			consulta.select(pRoot).where(builder.equal(pRoot.get("deleted"), deleted));

			// Execute query
			List<Entity> result = session.createQuery(consulta).getResultList();

			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	// Listing every register from a table by a second key
	public Entity searchBy(String query, String fieldName, Object fieldValue) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			TypedQuery<Entity> q = session.createNamedQuery(query, classe);
			q.setParameter(fieldName, fieldValue);
			Entity entity = q.getSingleResult();
			return entity;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	// Updating a register
	public Entity update(Entity entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(entity);
			transaction.commit();
			return entity;
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}
	
	public void merge(Entity entidade) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.merge(entidade);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}
}

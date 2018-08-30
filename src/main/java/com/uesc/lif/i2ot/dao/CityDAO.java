package com.uesc.lif.i2ot.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.uesc.lif.i2ot.model.City;
import com.uesc.lif.i2ot.util.HibernateUtil;

public class CityDAO extends GenericDAO<City> {
	/**
	 * <b>Method Name: </b> listByState <br>
	 * <br>
	 * 
	 * <b>Last Modification:</b> 19/03/2018<br>
	 * <br>
	 * 
	 * <b>Description: </b>This method gets all the cities from a specific state and
	 * order (ascending) them by name.
	 * 
	 * @param state_id
	 *            The state id
	 * @return An ordenade list (by name) of all cities registered in database that
	 *         is from a specific state
	 */
	public List<City> listByState(Long state_id) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			// Create CriteriaQuery
			CriteriaBuilder builder = session.getCriteriaBuilder();

			// Create CriteriaQuery
			CriteriaQuery<City> consulta = builder.createQuery(getClasse());

			// Specify criteria root
			Root<City> pRoot = consulta.from(City.class);
			consulta.select(pRoot).where(builder.equal(pRoot.get("state").get("id"), state_id));
			consulta.orderBy(builder.asc(pRoot.get("name")));

			// Execute query
			List<City> result = session.createQuery(consulta).getResultList();

			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
}

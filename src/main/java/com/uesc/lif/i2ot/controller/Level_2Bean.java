package com.uesc.lif.i2ot.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Level_2Bean {
	
	/*Level_2 level_2 = new Level_2();

	public Level_2 getLevel_2() {
		return level_2;
	}

	public void setLevel_2(Level_2 level_2) {
		this.level_2 = level_2;
	}
	
	public String FlameLevel_2Register() {
		return "Level_2Register";
	}
	
	public void saveLevel_2() {
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			trx.begin();
			em.persist(level_2);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
					"Usuário " +this.getLevel_2().getName() +" cadastrado com sucesso.");
			context.addMessage(null, message);
			level_2 = new Level_2();
			em.getTransaction().commit();
		}catch(Exception e){
			
		}finally {
			em.close();
		}
	}
	
	public void deleteLevel_2(Level_2 level_2){
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		level_2 = em.merge(level_2);
		em.remove(level_2);
		trx.commit();
		em.close();
	}*/
}


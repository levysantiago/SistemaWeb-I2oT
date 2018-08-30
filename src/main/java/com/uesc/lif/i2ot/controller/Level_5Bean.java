package com.uesc.lif.i2ot.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Level_5Bean {

	/*Level_5 level_5 = new Level_5();

	public Level_5 getLevel_5() {
		return level_5;
	}

	public void setLevel_5(Level_5 level_5) {
		this.level_5 = level_5;
	}
	
	public String FlameLevel_5Register() {
		return "Level_5Register";
	}
	
	public void saveLevel_5() {
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			trx.begin();
			em.persist(level_5);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
					"Usuário " +this.getLevel_5().getName() +" cadastrado com sucesso.");
			context.addMessage(null, message);
			level_5 = new Level_5();
			em.getTransaction().commit();
		}catch(Exception e){
			
		}finally {
			em.close();
		}
	}
	
	public void deleteLevel_5(Level_5 level_5){
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		level_5 = em.merge(level_5);
		em.remove(level_5);
		trx.commit();
		em.close();
	}*/
}

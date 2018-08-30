package com.uesc.lif.i2ot.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Level_4Bean {

	/*Level_4 level_4 = new Level_4();

	public Level_4 getLevel_4() {
		return level_4;
	}

	public void setLevel_4(Level_4 level_4) {
		this.level_4 = level_4;
	}
	
	public String FlameLevel_4Register() {
		return "Level_4Register";
	}
	
	public void saveLevel_4() {
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			trx.begin();
			em.persist(level_4);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
					"Usuário " +this.getLevel_4().getName() +" cadastrado com sucesso.");
			context.addMessage(null, message);
			level_4 = new Level_4();
			em.getTransaction().commit();
		}catch(Exception e){
			
		}finally {
			em.close();
		}
	}
	
	public void deleteLevel_4(Level_4 level_4){
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		level_4 = em.merge(level_4);
		em.remove(level_4);
		trx.commit();
		em.close();
	}*/
}

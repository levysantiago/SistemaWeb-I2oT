package com.uesc.lif.i2ot.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Level_1Bean {
	
	/*Level_1 level_1 = new Level_1();

	public Level_1 getLevel_1() {
		return level_1;
	}

	public void setLevel_1(Level_1 level_1) {
		this.level_1 = level_1;
	}
	
	public String FlameLevel_1Register() {
		return "Level_1Register";
	}
	
	public void saveLevel_1() {
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			trx.begin();
			em.persist(level_1);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
					"Usuário " +this.getLevel_1().getName() +" cadastrado com sucesso.");
			context.addMessage(null, message);
			level_1 = new Level_1();
			em.getTransaction().commit();
		}catch(Exception e){
			
		}finally {
			em.close();
		}
	}
	
	public void deleteLevel_1(Level_1 level_1){
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		level_1 = em.merge(level_1);
		em.remove(level_1);
		trx.commit();
		em.close();
	}*/
}

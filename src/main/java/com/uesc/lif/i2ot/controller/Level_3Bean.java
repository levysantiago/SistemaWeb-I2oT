package com.uesc.lif.i2ot.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Level_3Bean {

	/*Level_3 level_3 = new Level_3();

	public Level_3 getLevel_3() {
		return level_3;
	}

	public void setLevel_3(Level_3 level_3) {
		this.level_3 = level_3;
	}
	
	public String FlameLevel_3Register() {
		return "Level_3Register";
	}
	
	public void saveLevel_3() {
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			trx.begin();
			em.persist(level_3);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
					"Usuário " +this.getLevel_3().getName() +" cadastrado com sucesso.");
			context.addMessage(null, message);
			level_3 = new Level_3();
			em.getTransaction().commit();
		}catch(Exception e){
			
		}finally {
			em.close();
		}
	}
	
	public void deleteLevel_3(Level_3 level_3){
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		level_3 = em.merge(level_3);
		em.remove(level_3);
		trx.commit();
		em.close();
	}*/
}

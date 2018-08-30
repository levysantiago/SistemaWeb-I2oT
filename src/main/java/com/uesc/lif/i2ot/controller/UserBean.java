package com.uesc.lif.i2ot.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class UserBean implements Serializable {
	 
	/*private static final long serialVersionUID = 1L;
		
	private User user = new User();
	
	private User userLogged;
	
	private List<User> users;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public User getUserLogged() {
		return userLogged;
	}

	public void setUserLogged(User userLogged) {
		this.userLogged = userLogged;
	}

	/*public SelectItem[] getDepartmentType() {
	    SelectItem[] items = new SelectItem[DepartmentType.values().length];
	    int i = 0;
	    for(DepartmentType t: DepartmentType.values()) {
	        items[i++] = new SelectItem(t, t.getDepartment());
	    }
	    return items;
	}*/
	
	/*public String FlameRegister() {
		return "Register";
	}
	
	public String FlameHomepage() {
		return "Homepage";
	}
	
	public String FlameSearchUser() {
		return "SearchUser";
	}
	
	public String FlameTeste() {
		return "teste";
	}
	
	public String FlameRfidReaderPage() {
		return "RfidReaderPage";
	}
	
	public List<User> getUsers(){
		if(this.users==null) {
		EntityManager em = JpaUtil.getEntityManager();
		TypedQuery<User> q = em.createNamedQuery(User.findAll, User.class);
		this.users = q.getResultList();
		em.close();
		}
			return users;
	}
	
	public String Authentication() {
		EntityManager em = JpaUtil.getEntityManager();
		TypedQuery<User> q = em.createNamedQuery(User.findByLogin, User.class);
		q.setParameter("userName", user.getUserName());
		q.setParameter("password", user.getPassword());
		if(q.getSingleResult()!=null) {
			return "/RfidPage.xhtml?faces-redirect=true";
		}else {
			System.out.println("Usuário não encontrado");
		}
		return "/Homepage.xhtml?faces-redirect=true";
	}
	
	public void save() throws BusinessException {
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			trx.begin();
			em.persist(user);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
					"Usuário " +this.getUser().getName() +" cadastrado com sucesso.");
			context.addMessage(null, message);
			user = new User();
			em.getTransaction().commit();
		}finally {
			em.close();
		}
		
	}
	
	public String ConsultByName() {
		EntityManager em = JpaUtil.getEntityManager();
		TypedQuery<User> q = em.createNamedQuery(User.findByName, User.class);
		q.setParameter("name", user.getName());
		if(q.getSingleResult()!=null) {
	        System.out.println(user.getName());
	        return FlameTeste();
		}
		return "Usuário não encontrado";
		}
	
	public void deleteUser(User user){
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		user = em.merge(user);
		em.remove(user);
		trx.commit();
		em.close();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBean other = (UserBean) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}*/
	
	
}

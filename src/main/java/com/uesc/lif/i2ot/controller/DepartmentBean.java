package com.uesc.lif.i2ot.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import com.uesc.lif.i2ot.model.Department;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped

public class DepartmentBean implements Serializable{
	private Department department;
	private List<Department> departments;
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public List<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	
	@PostConstruct
	public void list() {
		try {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/department");
			String json = target.request().get(String.class);
			Gson gson = new Gson();
			Department[] departmentVector = gson.fromJson(json, Department[].class);
			departments = Arrays.asList(departmentVector);
		}catch(RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarDepartamentoErro"));
			e.printStackTrace();
		}
	}
	
	public void novo() {
		department = new Department();
	}
	
	public void save() {
		try {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/department");
			Gson gson = new Gson();
			String json = gson.toJson(department);
			
			if(department.getId() != null) {
				target.request().put(Entity.json(json));
			}else {
				target.request().post(Entity.json(json));
			}
			
			novo();
			
			json = target.request().get(String.class);
			Department[] departmentVector = gson.fromJson(json, Department[].class);
			departments = Arrays.asList(departmentVector);
			
			Messages.addGlobalInfo(Faces.getResourceBundle("msg").getString("salvoSucesso"));
		}catch(RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("salvoErro"));
			e.printStackTrace();
		}
	}
	
	public void delete(ActionEvent event) {
		try {
			department = (Department) event.getComponent().getAttributes().get("departmentSelected");
			
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/department");
			target.path("{id}").resolveTemplate("id", department.getId()).request().delete();
			
			Gson gson = new Gson();
			String json = target.request().get(String.class);
			Department[] departmentVector = gson.fromJson(json, Department[].class);
			departments = Arrays.asList(departmentVector);
			
			Messages.addGlobalInfo(Faces.getResourceBundle("msg").getString("deleteDepartamentoSucesso"));
		}catch(RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("deleteDepartamentoErro"));
			e.printStackTrace();
		}
	}
	
	public void update(ActionEvent event) {
		department = (Department) event.getComponent().getAttributes().get("departmentSelected");
	}
}

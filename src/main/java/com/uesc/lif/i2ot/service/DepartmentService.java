package com.uesc.lif.i2ot.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import com.uesc.lif.i2ot.dao.DepartmentDAO;
import com.uesc.lif.i2ot.model.Department;

@Path("department")
public class DepartmentService {
	// Link: //http://localhost:8080/I2oT/rest/department
	@GET
	public String list() {
		DepartmentDAO departmentDAO = new DepartmentDAO();
		List<Department> department = departmentDAO.list();
		Gson gson = new Gson();

		String gsonDepartment = gson.toJson(department);

		return gsonDepartment;
	}

	// Link: //http://localhost:8080/I2oT/rest/department/[code]
	@GET
	@Path("{id}")
	public String search(@PathParam("id") Long code) {
		DepartmentDAO departmentDAO = new DepartmentDAO();
		Department department = departmentDAO.search(code);
		Gson gson = new Gson();
		String json = gson.toJson(department);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/department
	@POST
	public String save(String json) {
		DepartmentDAO departmentDAO = new DepartmentDAO();
		Gson gson = new Gson();
		Department department = gson.fromJson(json, Department.class);
		departmentDAO.save(department);

		json = gson.toJson(department);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/department
	@PUT
	public String edit(String json) {
		DepartmentDAO departmentDAO = new DepartmentDAO();
		Gson gson = new Gson();
		Department department = gson.fromJson(json, Department.class);
		departmentDAO.update(department);
		json = gson.toJson(department);

		return json;

	}

	// Link: //http://localhost:8080/I2oT/rest/department/[code]
	@DELETE
	@Path("{id}")
	public String delete(@PathParam("id") Long code) {
		DepartmentDAO departmentDAO = new DepartmentDAO();
		Department department = departmentDAO.search(code);
		department.setDeleted('1');
		departmentDAO.update(department);
		Gson gson = new Gson();
		String json = gson.toJson(department);

		return json;
	}
}

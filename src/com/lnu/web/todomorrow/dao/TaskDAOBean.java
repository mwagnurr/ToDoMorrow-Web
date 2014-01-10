package com.lnu.web.todomorrow.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class TaskDAOBean {
	@PersistenceContext(name = "ToDoMorrow")
	EntityManager em;

	public TaskDAOBean() {
	}
}

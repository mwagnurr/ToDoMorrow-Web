package com.lnu.web.todomorrow.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.lnu.web.todomorrow.model.User;

/**
 * Session Bean implementation class UserDAOBean
 */
@Stateless
@LocalBean
public class UserDAOBean {

	@PersistenceContext(name = "ToDoMorrow")
	EntityManager em;

	public UserDAOBean() {
	}

	public List<User> getAllUsers() {
		TypedQuery<User> theQuery = em.createQuery("select p from Planets p", User.class);
		List<User> result = theQuery.getResultList();
		return result;
	}

}

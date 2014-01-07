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
		TypedQuery<User> theQuery = em.createQuery("select u from User u", User.class);
		List<User> result = theQuery.getResultList();
		return result;
	}

	public void persistUser(User user) {

		if (checkUserUniqueFieldsContained(user)) {
			System.out.println("user email or username already in database!");
			return;
		}

		em.persist(user);
	}

	private boolean checkUserUniqueFieldsContained(User user) {
		String query = "select u from User u where ";

		query += "u.username='" + user.getUsername() + "' ";
		query += "or u.email='" + user.getEmail() + "'";

		TypedQuery<User> theQuery = em.createQuery(query, User.class);
		List<User> result = theQuery.getResultList();

		if (result.size() >= 1) {
			return true;
		} else {
			return false;
		}

	}

}

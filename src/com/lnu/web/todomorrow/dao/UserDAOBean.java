package com.lnu.web.todomorrow.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.lnu.web.todomorrow.model.User;


@Stateless
@LocalBean
public class UserDAOBean {

	@PersistenceContext(name = "ToDoMorrow")
	EntityManager em;

	public UserDAOBean() {
	}

	public List<User> getAllUsers() {
		// TypedQuery<User> theQuery = em.createQuery("select u from User u", User.class);
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);

		List<User> result = query.getResultList();
		log("got all users, result size: " + result.size());
		return result;
	}

	public void persistUser(User user) {

		if (checkUserUniqueFieldsContained(user)) {
			System.out.println("user email or username already in database!");
			return;
		}

		em.persist(user);
		log("persisted user: " + user.toString());

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

	public boolean checkUserContained(int userId) {

		String query = "select u from User u where u.iduser='" + userId + "'";

		TypedQuery<User> theQuery = em.createQuery(query, User.class);
		List<User> result = theQuery.getResultList();
		if (result.size() >= 1) {
			log("checkUser - found " + result.size() + " results, with userId: " + userId);
			return true;
		} else {
			return false;
		}
	}

	private void log(String logMsg) {
		String TAG = UserDAOBean.class.getSimpleName() + ": ";
		System.out.println(TAG + logMsg);
	}

}

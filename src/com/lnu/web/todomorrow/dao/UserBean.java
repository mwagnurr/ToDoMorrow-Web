package com.lnu.web.todomorrow.dao;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lnu.web.todomorrow.model.User;

/**
 * Session Bean implementation class User
 */
@Singleton
@LocalBean
@Startup
public class UserBean {

	@PersistenceContext(name = "ToDoMorrow")
	// From persistence.xml
	EntityManager em;

	public UserBean() {
	}

	@PostConstruct
	// Defines a "main method"
	public void theStarter() {
		//persist user for test
		User user = new User();
		user.setEmail("test@test.com");
		user.setUsername("michi2");
		user.setPassword("12345");
		em.persist(user);
	}

}

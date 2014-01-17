package com.lnu.web.todomorrow.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.lnu.web.todomorrow.model.Goal;
import com.lnu.web.todomorrow.model.User;

@Stateless
@LocalBean
public class GoalDAOBean {
	@PersistenceContext(name = "ToDoMorrow")
	EntityManager em;

	public GoalDAOBean() {
	}
	
	public List<Goal> getAllGoals() {
		// TypedQuery<User> theQuery = em.createQuery("select u from User u", User.class);
		TypedQuery<Goal> query = em.createNamedQuery("Goal.findAll", Goal.class);

		List<Goal> result = query.getResultList();
		log("got all goals, result size: " + result.size());
		return result;
	}
	
	private void log(String logMsg) {
		String TAG = GoalDAOBean.class.getSimpleName() + ": ";
		System.out.println(TAG + logMsg);
	}
}

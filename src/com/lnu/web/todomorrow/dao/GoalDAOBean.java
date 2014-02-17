package com.lnu.web.todomorrow.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.lnu.web.todomorrow.model.Goal;

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

	public void persistGoal(Goal goal) {

		if (checkGoalUniqueFieldsContained(goal)) {
			System.out.println("Error, goal unique fields already contained in database");
			return;
		}

		if (goal.getName() == null || goal.getName().isEmpty()) {
			System.out.println("Error, goal name is empty or null");
			return;
		}

		em.persist(goal);
		log("persisted goal: " + goal.toString());

	}

	private boolean checkGoalUniqueFieldsContained(Goal goal) {
		String query = "select g from Goal g where ";

		query += "g.name='" + goal.getName() + "'";

		TypedQuery<Goal> theQuery = em.createQuery(query, Goal.class);
		List<Goal> result = theQuery.getResultList();

		if (result.size() >= 1) {
			return true;
		} else {
			return false;
		}
	}

	private void log(String logMsg) {
		String TAG = GoalDAOBean.class.getSimpleName() + ": ";
		System.out.println(TAG + logMsg);
	}
}

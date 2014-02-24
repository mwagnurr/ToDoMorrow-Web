package com.lnu.web.todomorrow.dao;

import java.sql.Timestamp;
import java.util.Date;
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
		TypedQuery<Goal> query = em.createNamedQuery("Goal.findAll", Goal.class);

		List<Goal> result = query.getResultList();
		log("got all goals, result size: " + result.size());
		return result;
	}

	public List<Goal> getAllGoals(User user) {

		//log("getting all goals, user is " + user);

		TypedQuery<Goal> query = em.createNamedQuery("Goal.findAllByUser", Goal.class)
				.setParameter("user", user);
		List<Goal> result = query.getResultList();
		log("got all goals for user_id " + user.getIduser() + " , result size: " + result.size());
		return result;
	}
	
	public Goal getGoal(int goalId) {

		String query = "select g from Goal g where g.idgoal=" + goalId;

		TypedQuery<Goal> theQuery = em.createQuery(query, Goal.class);
		List<Goal> result = theQuery.getResultList();

		if (result == null || result.isEmpty()) {
			log("Error: no goal found with goal id: " + goalId);
			return null;
		} else if (result.size() > 1) {
			log("Warning: More than one goal found with goal id: " + goalId);
		}

		Goal goal = result.get(0);
		return goal;
	}
	
	public void updateGoal(Goal goal){
		em.merge(goal);
		log("updated goal " + goal);
	}

	public void persistGoal(Goal goal) {

		if (checkGoalUniqueFieldsContained(goal)) {
			log("Error, goal unique fields already contained in database");
			return;
		}

		if (goal.getName() == null || goal.getName().isEmpty()) {
			log("Error, goal name is empty or null");
			return;
		}
		
		//save current time as created_at
		Date currTime = new Date();
		goal.setCreatedAt(new Timestamp(currTime.getTime()));

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

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
import com.lnu.web.todomorrow.model.Note;
import com.lnu.web.todomorrow.model.Task;
import com.lnu.web.todomorrow.model.User;

@Stateless
@LocalBean
public class TaskDAOBean {
	@PersistenceContext(name = "ToDoMorrow")
	EntityManager em;

	public TaskDAOBean() {
	}

	public List<Task> getAllTask(User user, Goal goal) {

		log("getting all tasks, user is " + user + ", goal is " + goal);

		TypedQuery<Task> query = em.createNamedQuery("Task.findAllByUserAndGoal", Task.class)
				.setParameter("user", user).setParameter("goal", goal);
		List<Task> result = query.getResultList();
		log("got all tasks for user_id " + user.getIduser() + " and goal_id " + goal.getIdgoal()
				+ ", result size: " + result.size());
		return result;
	}

	public void removeTask(Task task) {
		em.remove(task);
		log("removed task " + task);
	}
	
	public void updateTask(Task task){

		em.merge(task);
		log("updated task " + task);
	}
	
	public void persistTask(Task task) {

		if (checkTaskUniqueFieldsContained(task)) {
			log("Error, task unique fields already contained in database");
			return;
		}

		if (task.getName() == null || task.getName().isEmpty()) {
			log("Error, task name is empty or null");
			return;
		}

		// save current time as created_at
		Date currTime = new Date();
		task.setCreatedAt(new Timestamp(currTime.getTime()));

		em.persist(task);
		log("persisted task: " + task.toString());

	}

	private boolean checkTaskUniqueFieldsContained(Task task) {
		String query = "select t from Task t where ";

		query += "t.name='" + task.getName() + "'";

		TypedQuery<Task> theQuery = em.createQuery(query, Task.class);
		List<Task> result = theQuery.getResultList();

		if (result.size() >= 1) {
			return true;
		} else {
			return false;
		}
	}

	private void log(String logMsg) {
		String TAG = TaskDAOBean.class.getSimpleName() + ": ";
		System.out.println(TAG + logMsg);
	}
}

package com.lnu.web.todomorrow.controller;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;

import com.lnu.web.todomorrow.dao.GoalDAOBean;
import com.lnu.web.todomorrow.dao.TaskDAOBean;
import com.lnu.web.todomorrow.model.Goal;
import com.lnu.web.todomorrow.model.Task;
import com.lnu.web.todomorrow.model.User;

@ManagedBean
@SessionScoped
public class MainControl {

	@EJB
	private GoalDAOBean goalDAO;
	@EJB
	private TaskDAOBean taskDAO;

	private Date date;

	private String newGoalName;

	private User loggedInUser;

	private Goal selectedGoal;
	private Task selectedTask;

	public MainControl() {
	}

	public Goal getSelectedGoal() {
		return selectedGoal;
	}

	public void setSelectedGoal(Goal selectedGoal) {
		this.selectedGoal = selectedGoal;
	}

	public Task getSelectedTask() {
		return selectedTask;
	}

	public void setSelectedTask(Task selectedTask) {
		this.selectedTask = selectedTask;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public String getNewGoalName() {
		return newGoalName;
	}

	public void setNewGoalName(String newGoalName) {
		this.newGoalName = newGoalName;
	}

	public List<Goal> getGoalList() {
		log("getting goal list");
		List<Goal> goals = null;

		if (loggedInUser == null) {
			log("Error, no user logged in!");
			goals = goalDAO.getAllGoals();

		} else {
			// log("retrieving goals for user: " + loggedInUser.getUsername() + " userId: "
			// + loggedInUser.getIduser());
			goals = goalDAO.getAllGoals(loggedInUser);
		}

		return goals;
	}

	public List<Task> getTaskListForGoal(Goal goal) {
		log("getting task list for goal " + goal);
		List<Task> tasks = null;

		if (loggedInUser == null) {
			log("Error, no user logged in!");
			// tasks = taskDAO.getAllTask(loggedInUser, goal);

		} else if (goal == null) {
			log("Error, no goal for tasklist");
		} else {
			// log("retrieving goals for user: " + loggedInUser.getUsername() + " userId: "
			// + loggedInUser.getIduser());
			tasks = taskDAO.getAllTask(loggedInUser, goal);
		}

		return tasks;
	}

	public void addMessageForTaskChecked(Task task) {

		log("adding Message after checking for task " + task + ", completed: " + task.isCompleted());

		if (!task.isCompleted()) {
			log("task curr unchecked, set completed");
			task.setCompleted(true);

			Goal goal = task.getGoal();
			if (goal != null) {
				int score = goal.getScore() + task.getValue();
				log("updating score of goal to " + score);
				goal.setScore(score);
				goalDAO.updateGoal(goal);
			} else {
				log("errr, goal of task is null, cant update score");
			}

		} else {
			log("task curr checked, set uncompleted");
			task.setCompleted(false);

			Goal goal = task.getGoal();
			if (goal != null) {
				int score = goal.getScore() - task.getValue();
				log("updating score of goal to " + score);
				goal.setScore(score);
				goalDAO.updateGoal(goal);
			} else {
				log("errr, goal of task is null, cant update score");
			}
		}

		// log("currTaskChanged: " + currTaskChanged);
		// task.setCompleted(currTaskChanged);
		taskDAO.updateTask(task);

		String summary = task.isCompleted() ? "Checked Task " + task.getName() : "Unchecked Task "
				+ task.getName();

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
	}

	public void test1(ActionEvent actionEvent) {
		log("test1 called");
	}

	public void test2(ActionEvent actionEvent) {
		log("test2 called");
	}

	public void test3(ActionEvent actionEvent) {
		log("test3 called");
	}

	public void openAddGoalDialog() {
		log("opening add_goal_dialog.xhtml");

		// RequestContext.getCurrentInstance().openDialog("login");
		// TODO remove
		log("tried to open waaat");
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	private void log(String logMsg) {
		String TAG = MainControl.class.getSimpleName() + ": ";
		System.out.println(TAG + logMsg);
	}

}

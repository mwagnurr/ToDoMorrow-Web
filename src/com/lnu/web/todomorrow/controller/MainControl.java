package com.lnu.web.todomorrow.controller;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.lnu.web.todomorrow.dao.GoalDAOBean;
import com.lnu.web.todomorrow.dao.TaskDAOBean;
import com.lnu.web.todomorrow.model.Goal;
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

	public MainControl() {
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
			log("retrieving goals for user: " + loggedInUser.getUsername() + " userId: "
					+ loggedInUser.getIduser());
			goals = goalDAO.getAllGoals(loggedInUser.getIduser());
		}

		return goals;
	}

	public String blabla() {
		log("calling BLABALAAA");
		return "BLABLA";
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

		RequestContext.getCurrentInstance().openDialog("add_goal_dialog");

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

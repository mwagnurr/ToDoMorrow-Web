package com.lnu.web.todomorrow.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.lnu.web.todomorrow.dao.GoalDAOBean;
import com.lnu.web.todomorrow.model.Goal;

@ManagedBean
@RequestScoped
public class DialogControl {
	@EJB
	private GoalDAOBean goalDAO;

	private Goal newGoal;

	private Date deadline;

	public DialogControl() {

	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Goal getNewGoal() {
		return newGoal;
	}

	public void setNewGoal(Goal newGoal) {
		this.newGoal = newGoal;
	}

	public void setNewGoalDeadline(String date) {

		log("deadline string: " + date);
		this.newGoal.setDeadline(Timestamp.valueOf(date));
	}

	public void addGoal() {
		log("--addGoal()");
		if (newGoal == null) {
			log("Error, cant add Goal, null");
			return;
		}

		if (newGoal.getName() == null || newGoal.getName().isEmpty()) {
			log("Error, goal name is empty; do not add goal!");
			return;
		}

		if (deadline != null) {
			log("deadline not null; deadline: " + deadline + "; set deadline");
			newGoal.setDeadline(new Timestamp(deadline.getTime()));
		} else {
			log("deadline null; no deadline");
		}

		log("persisting goal: " + newGoal);

		goalDAO.persistGoal(newGoal);
	}

	@PostConstruct
	public void init() {
		newGoal = new Goal();
		log("started and created newGoal object for preparation");
	}

	private void log(String logMsg) {
		String TAG = DialogControl.class.getSimpleName() + ": ";
		System.out.println(TAG + logMsg);
	}
}

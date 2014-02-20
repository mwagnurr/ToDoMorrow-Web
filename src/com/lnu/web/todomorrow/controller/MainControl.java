package com.lnu.web.todomorrow.controller;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.lnu.web.todomorrow.dao.GoalDAOBean;
import com.lnu.web.todomorrow.dao.TaskDAOBean;
import com.lnu.web.todomorrow.model.Goal;

@ManagedBean
@SessionScoped
public class MainControl {

	@EJB
	private GoalDAOBean goalDAO;
	@EJB
	private TaskDAOBean taskDAO;

	private Date date;

	private String newGoalName;

	public MainControl() {
	}

	public String getNewGoalName() {
		return newGoalName;
	}

	public void setNewGoalName(String newGoalName) {
		this.newGoalName = newGoalName;
	}

	public List<Goal> getGoalList() {
		log("getting goal list");
		List<Goal> bla = goalDAO.getAllGoals();

		return bla;
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

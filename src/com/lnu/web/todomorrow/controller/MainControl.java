package com.lnu.web.todomorrow.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

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

	public MainControl() {
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

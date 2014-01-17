package com.lnu.web.todomorrow.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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

	public MainControl() {
	}

	public List<Goal> getGoalList() {
		log("getting goal list");
		List<Goal> bla = goalDAO.getAllGoals();

		return bla;
	}
	
	public String blabla(){
		log("calling BLABALAAA");
		return "BLABLA";
	}

	private void log(String logMsg) {
		String TAG = MainControl.class.getSimpleName() + ": ";
		System.out.println(TAG + logMsg);
	}

}

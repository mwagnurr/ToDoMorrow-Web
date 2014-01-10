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
		List<Goal> bla = new ArrayList<Goal>();
		
		bla.add(new Goal());
		bla.add(new Goal());
		bla.add(new Goal());
		return null;
	}

}

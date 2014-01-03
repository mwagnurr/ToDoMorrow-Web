package com.lnu.web.todomorrow.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import com.lnu.web.todomorrow.dao.UserDAOBean;
import com.lnu.web.todomorrow.model.User;


@ManagedBean(name = "rc")
@RequestScoped
public class RegisterControl {
	
	@EJB
	private UserDAOBean userDAO;
	
	//@ManagedProperty(value = "#{user}")
	//private User newUser;
	@ManagedProperty(value = "#{false}")
	private boolean registrationsuccess;

	public RegisterControl() {
	}
	
	public List<User> getUsers() {
		
		return userDAO.getAllUsers();
	}

	public boolean isRegistrationsuccess() {
		return registrationsuccess;
	}

	public void setRegistrationsuccess(boolean registrationsuccess) {
		this.registrationsuccess = registrationsuccess;
	}
	
	
	

}

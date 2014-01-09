package com.lnu.web.todomorrow.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.lnu.web.todomorrow.dao.UserDAOBean;
import com.lnu.web.todomorrow.model.User;

@ManagedBean(name = "rc")
@RequestScoped
public class RegisterControl {

	@EJB
	private UserDAOBean userDAO;

	@ManagedProperty(value = "#{user}")
	private User newUser;

	@ManagedProperty(value = "#{false}")
	private boolean registrationsuccess;

	public RegisterControl() {
	}

	@PostConstruct
	public void init() {
		newUser = new User();
		System.out.println("started and created User");
	}

	public void register(ActionEvent actionEvent) {

		System.out.println("TEST - registering called!");
		userDAO.persistUser(newUser);

		if (userDAO.checkUserContained(newUser.getIduser())) {
			registrationsuccess = true;
		}
		// return "register";
	}

	public void test(ActionEvent actionEvent) {
		
		System.out.println("TEST STETSETTSETSETESTSE");
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("Welcome " + newUser.getUsername() + " " + newUser.getPassword()
						+ "!"));
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

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

}

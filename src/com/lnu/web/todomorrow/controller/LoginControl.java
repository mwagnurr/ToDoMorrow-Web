package com.lnu.web.todomorrow.controller;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.lnu.web.todomorrow.dao.UserDAOBean;
import com.lnu.web.todomorrow.model.User;



// TODO 
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "lc")
@SessionScoped
public class LoginControl {

	@EJB
	private UserDAOBean userDAO;

	@ManagedProperty(value = "#{user}")
	private User user;

	@ManagedProperty(value = "#{false}")
	private boolean loginsuccess;



	private boolean showloginfailed;
	private String name;
	private String password;

	// Creates a new instance of LoginControl
	public LoginControl() {}

	public void login() {

		System.out.println("Test Login");
		if(userDAO.logIn(user) == true)
		{
			System.out.println(user.getUsername() + " " + user.getPassword());	
			loginsuccess = true;
		}
	}
	
	@PostConstruct
	public void init() {
		user = new User();
		System.out.println("started and created User");
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param player the player to be set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to be set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to be set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the value of showloginfailed
	 */
	public boolean isShowloginfailed() {
		return showloginfailed;
	}

	/**
	 * @param showloginfailed the value showloginfailed should be set to
	 */
	public void setShowloginfailed(boolean showloginfailed) {
		this.showloginfailed = showloginfailed;
	} 
	
    public boolean isLoginsuccess() {
		return loginsuccess;
	}

	public void setLoginsuccess(boolean loginsuccess) {
		this.loginsuccess = loginsuccess;
	}
}
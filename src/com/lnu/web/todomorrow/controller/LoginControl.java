package com.lnu.web.todomorrow.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.lnu.web.todomorrow.dao.UserDAOBean;
import com.lnu.web.todomorrow.model.User;

@ManagedBean(name = "lc")
@SessionScoped
public class LoginControl {

	@EJB
	private UserDAOBean userDAO;

	@ManagedProperty(value = "#{user}")
	private User user;

	@ManagedProperty(value = "#{false}")
	private boolean loginsuccess;

	@ManagedProperty(value = "#{mainControl}")
	private MainControl mainControl;

	private boolean showloginfailed;
	private String name;
	private String password;

	public LoginControl() {
	}

	public String login() {

		log("Trying to log in");

		if (userDAO.logIn(user) == true) {
			log(user.getUsername() + " successfully logged in");
			showloginfailed = false;
			loginsuccess = true;

			mainControl.setLoggedInUser(userDAO.getUser(user.getUsername()));

			return "main";
		} else {
			showloginfailed = true;
			loginsuccess = false;
			return "login";
		}
	}

	@PostConstruct
	public void init() {
		user = new User();
		log("started and created User");
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return the mainControl
	 */
	public MainControl getMainControl() {
		return mainControl;
	}

	/**
	 * @param mainControl
	 * the mainControl to set
	 */
	public void setMainControl(MainControl mainControl) {
		this.mainControl = mainControl;
	}

	/**
	 * @param player
	 * the player to be set
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
	 * @param name
	 * the name to be set
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
	 * @param password
	 * the password to be set
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
	 * @param showloginfailed
	 * the value showloginfailed should be set to
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

	private void log(String logMsg) {
		String TAG = LoginControl.class.getSimpleName() + ": ";
		System.out.println(TAG + logMsg);
	}
}
package com.lnu.web.todomorrow.controller;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;

import com.lnu.web.todomorrow.dao.GoalDAOBean;
import com.lnu.web.todomorrow.dao.NoteDAOBean;
import com.lnu.web.todomorrow.dao.TaskDAOBean;
import com.lnu.web.todomorrow.model.Goal;
import com.lnu.web.todomorrow.model.Note;
import com.lnu.web.todomorrow.model.Task;
import com.lnu.web.todomorrow.model.User;

@ManagedBean
@SessionScoped
public class MainControl {

	@EJB
	private GoalDAOBean goalDAO;
	@EJB
	private TaskDAOBean taskDAO;
	@EJB
	private NoteDAOBean noteDAO;

	private Date date;

	private String newGoalName;

	private User loggedInUser;

	private Goal selectedGoal;
	private Task selectedTask;

	private boolean renderedGoalList = true;
	private boolean renderedNotes = false;

	public MainControl() {
	}

	public Goal getSelectedGoal() {
		return selectedGoal;
	}

	public void setSelectedGoal(Goal selectedGoal) {
		this.selectedGoal = selectedGoal;
	}

	public Task getSelectedTask() {
		return selectedTask;
	}

	public void setSelectedTask(Task selectedTask) {
		this.selectedTask = selectedTask;
	}

	public boolean isRenderedGoalList() {
		return renderedGoalList;
	}

	public void setRenderedGoalList(boolean renderedGoalList) {
		this.renderedGoalList = renderedGoalList;
	}

	public boolean isRenderedNotes() {
		return renderedNotes;
	}

	public void setRenderedNotes(boolean renderedNotes) {
		this.renderedNotes = renderedNotes;
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

	public void switchToGoalList() {
		renderedGoalList = true;
		renderedNotes = false;
	}

	public void switchToNotes() {
		renderedGoalList = false;
		renderedNotes = true;
	}

	public List<Goal> getGoalList() {
		log("getting goal list");
		List<Goal> goals = null;

		if (loggedInUser == null) {
			log("Error, no user logged in!");
			goals = goalDAO.getAllGoals();

		} else {
			// log("retrieving goals for user: " + loggedInUser.getUsername() + " userId: "
			// + loggedInUser.getIduser());
			goals = goalDAO.getAllGoals(loggedInUser);
		}

		return goals;
	}

	public List<Note> getNoteList() {
		log("getting note list");
		List<Note> notes = null;

		if (loggedInUser == null) {
			log("Error, no user logged in!");

		} else {
			notes = noteDAO.getAllNotes(loggedInUser);
		}

		return notes;
	}
	
	//TODO remove
	public void removeSelectedGoal(){
//		if(selectedGoal==null){
//			log("Error, goal is null");
//			return;
//		}
//
//		log("removing from database: " + selectedGoal);
//		goalDAO.removeGoal(selectedGoal);
	}
	public void removeNote(Note note){
		log("removing note " + note);
		
		
	}

	public List<Task> getTaskListForGoal(Goal goal) {
		log("getting task list for goal " + goal);
		List<Task> tasks = null;

		if (loggedInUser == null) {
			log("Error, no user logged in!");
			// tasks = taskDAO.getAllTask(loggedInUser, goal);

		} else if (goal == null) {
			log("Error, no goal for tasklist");
		} else {
			// log("retrieving goals for user: " + loggedInUser.getUsername() + " userId: "
			// + loggedInUser.getIduser());
			tasks = taskDAO.getAllTask(loggedInUser, goal);
		}

		return tasks;
	}

	public void addMessageForTaskChecked(Task task) {

		log("adding Message after checking for task " + task + ", completed: " + task.isCompleted());

		if (!task.isCompleted()) {
			log("task curr unchecked, set completed");
			task.setCompleted(true);

			Goal goal = task.getGoal();
			if (goal != null) {
				int score = goal.getScore() + task.getValue();
				log("updating score of goal to " + score);
				goal.setScore(score);
				goalDAO.updateGoal(goal);
			} else {
				log("errr, goal of task is null, cant update score");
			}

		} else {
			log("task curr checked, set uncompleted");
			task.setCompleted(false);

			Goal goal = task.getGoal();
			if (goal != null) {
				int score = goal.getScore() - task.getValue();
				log("updating score of goal to " + score);
				goal.setScore(score);
				goalDAO.updateGoal(goal);
			} else {
				log("errr, goal of task is null, cant update score");
			}
		}

		// log("currTaskChanged: " + currTaskChanged);
		// task.setCompleted(currTaskChanged);
		taskDAO.updateTask(task);

		String summary = task.isCompleted() ? "Checked Task " + task.getName() : "Unchecked Task "
				+ task.getName();

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
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

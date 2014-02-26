package com.lnu.web.todomorrow.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.context.RequestContext;

import com.lnu.web.todomorrow.dao.GoalDAOBean;
import com.lnu.web.todomorrow.dao.NoteDAOBean;
import com.lnu.web.todomorrow.dao.TaskDAOBean;
import com.lnu.web.todomorrow.model.Goal;
import com.lnu.web.todomorrow.model.Note;
import com.lnu.web.todomorrow.model.Task;
import com.lnu.web.todomorrow.model.User;

@ManagedBean
@RequestScoped
public class DialogControl {
	@EJB
	private GoalDAOBean goalDAO;
	@EJB
	private TaskDAOBean taskDAO;
	@EJB
	private NoteDAOBean noteDAO;

	private Goal newGoal;
	private Task newTask;
	private Note newNote;

	private int taskGoalId;

	private Date deadline;

	public DialogControl() {

	}

	/**
	 * @return the taskGoalId
	 */
	public int getTaskGoalId() {
		return taskGoalId;
	}

	/**
	 * @param taskGoalId
	 * the taskGoalId to set
	 */
	public void setTaskGoalId(int taskGoalId) {
		this.taskGoalId = taskGoalId;
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

	/**
	 * @return the newNote
	 */
	public Note getNewNote() {
		return newNote;
	}

	/**
	 * @param newNote
	 * the newNote to set
	 */
	public void setNewNote(Note newNote) {
		this.newNote = newNote;
	}

	/**
	 * @return the newTask
	 */
	public Task getNewTask() {
		return newTask;
	}

	/**
	 * @param newTask
	 * the newTask to set
	 */
	public void setNewTask(Task newTask) {
		this.newTask = newTask;
	}

	public void setNewGoalDeadline(String date) {

		log("deadline string: " + date);
		this.newGoal.setDeadline(Timestamp.valueOf(date));
	}

	public String formatDate(Timestamp date) {

		String formattedDate = "";

		return formattedDate;
	}

	public String addGoal(User loggedInUser) {
		String nav = "main";
		log("--addGoal()");

		if (newGoal == null) {
			log("Error, cant add Goal, null");
			closeAddGoalDialog();
			return nav;
		} else {
			//log("trying to add goal: " + newGoal);
		}

		if (loggedInUser == null) {
			log("Error, no user is logged in!");
			closeAddGoalDialog();
			return nav;
		}
		log("loggedInUser: " + loggedInUser.getUsername() + ", userid: " + loggedInUser.getIduser());

		newGoal.setUser(loggedInUser);

		if (newGoal.getName() == null || newGoal.getName().isEmpty()) {
			log("Error, goal name is empty; do not add goal!");
			// TODO display message
			return nav;
		}

		if (deadline != null) {
			log("deadline not null; deadline: " + deadline + "; set deadline");
			newGoal.setDeadline(new Timestamp(deadline.getTime()));
		} else {
			log("deadline null; no deadline");
		}

		log("persisting goal: " + newGoal);

		goalDAO.persistGoal(newGoal);

		closeAddGoalDialog();

		return nav;
	}

	public void addTask(User loggedInUser) {

		log("addTask()");

		if (newTask == null) {
			log("Error, cant add Task, null");
			closeAddTaskDialog();
			return;
		} else {
			log("trying to add task: " + newTask);
		}

		if (loggedInUser == null) {
			log("Error, no user is logged in!");
			closeAddTaskDialog();
			return;
		} else {
			log("loggedInUser: " + loggedInUser.getUsername() + ", userid: "
					+ loggedInUser.getIduser());

			newTask.setUser(loggedInUser);
		}

		if (newTask.getName() == null || newTask.getName().isEmpty()) {
			log("Error, task name is empty; do not add task!");
			// TODO display message
			return;
		}

		if (deadline != null) {
			log("deadline not null; deadline: " + deadline + "; set deadline");
			newTask.setDeadline(new Timestamp(deadline.getTime()));
		} else {
			log("deadline null; no deadline");
		}

		if (taskGoalId > 0) {
			log("taskGoalId = " + taskGoalId);

			Goal goalForTask = goalDAO.getGoal(taskGoalId);
			newTask.setGoal(goalForTask);
		} else {
			log("Warning, goal id is 0 or lower, therefor probably invalid. taskGoalId: "
					+ taskGoalId);
		}

		log("persisting task: " + newTask);

		taskDAO.persistTask(newTask);

		closeAddTaskDialog();
	}

	public void addNote(User loggedInUser) {
		log("--addNote()");

		if (newNote == null) {
			log("Error, cant add Note, null");
			closeAddNoteDialog();
			return;
		} else {
			log("trying to add note: " + newNote);
		}

		if (loggedInUser == null) {
			log("Error, no user is logged in!");
			closeAddNoteDialog();
			return;
		}
		log("loggedInUser: " + loggedInUser.getUsername() + ", userid: " + loggedInUser.getIduser());

		newNote.setUser(loggedInUser);

		if (newNote.getTitle() == null || newNote.getTitle().isEmpty()) {
			log("Error, note name is empty; do not add note!");
			return;
		}

		log("persisting note: " + newNote);

		noteDAO.persistNote(newNote);

		closeAddNoteDialog();
	}

	// Dialog closing methods
	private void closeAddGoalDialog() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("add_dlg.hide()");
	}

	private void closeAddTaskDialog() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("add_task_dlg.hide()");
	}

	private void closeAddNoteDialog() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("add_note_dlg.hide()");
	}

	@PostConstruct
	public void init() {
		newGoal = new Goal();
		newTask = new Task();
		newNote = new Note();
		log("started and created newGoal/newTask/newNote objects for preparation");
	}

	private void log(String logMsg) {
		String TAG = DialogControl.class.getSimpleName() + ": ";
		System.out.println(TAG + logMsg);
	}
}

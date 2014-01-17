package com.lnu.web.todomorrow.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the goal database table.
 * 
 */
@Entity
@Table(name="goal")
@NamedQuery(name="Goal.findAll", query="SELECT g FROM Goal g")
public class Goal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idgoal;

	private boolean completed;

	@Column(name="created_at")
	private Timestamp createdAt;

	private Timestamp deadline;

	@Column(length=100)
	private String description;

	@Column(nullable=false, length=45)
	private String name;

	private int score;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="goal")
	private List<Task> tasks;

	public Goal() {
	}

	public int getIdgoal() {
		return this.idgoal;
	}

	public void setIdgoal(int idgoal) {
		this.idgoal = idgoal;
	}

	public boolean getCompleted() {
		return this.completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setGoal(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setGoal(null);

		return task;
	}

	@Override
	public String toString() {
		return "Goal [idgoal=" + idgoal + ", completed=" + completed + ", createdAt=" + createdAt
				+ ", deadline=" + deadline + ", description=" + description + ", name=" + name
				+ ", score=" + score + ", tasks=" + tasks + "]";
	}
	
	

}
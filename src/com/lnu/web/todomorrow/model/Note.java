package com.lnu.web.todomorrow.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;

/**
 * The persistent class for the note database table.
 * 
 */
@Entity
@Table(name = "note")
@NamedQueries({ @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n"),
		@NamedQuery(name = "Note.findAllByUser", query = "SELECT n FROM Note n WHERE n.user=:user") })
public class Note implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int idnote;

	@Column(length = 300)
	private String content;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(length = 45)
	private String title;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Note() {
	}

	public int getIdnote() {
		return this.idnote;
	}

	public void setIdnote(int idnote) {
		this.idnote = idnote;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Note [idnote=" + idnote + ", content=" + content + ", createdAt=" + createdAt
				+ ", title=" + title + ", user=" + user + "]";
	}

}
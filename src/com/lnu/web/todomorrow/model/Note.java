package com.lnu.web.todomorrow.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the note database table.
 * 
 */
@Entity
@Table(name="note")
@NamedQuery(name="Note.findAll", query="SELECT n FROM Note n")
public class Note implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idnote;

	@Column(length=300)
	private String content;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(length=45)
	private String title;

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

}
package com.lnu.web.todomorrow.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the forum_post database table.
 * 
 */
@Entity
@Table(name="forum_post")
@NamedQuery(name="ForumPost.findAll", query="SELECT f FROM ForumPost f")
public class ForumPost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idforum_post", unique=true, nullable=false)
	private int idforumPost;

	@Column(length=45)
	private String content;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(nullable=false, length=45)
	private String title;

	@Column(name="user_id", nullable=false)
	private int userId;

	//bi-directional many-to-one association to ForumPost
	@ManyToOne
	@JoinColumn(name="parent_post")
	private ForumPost forumPost;

	//bi-directional many-to-one association to ForumPost
	@OneToMany(mappedBy="forumPost")
	private List<ForumPost> forumPosts;

	public ForumPost() {
	}

	public int getIdforumPost() {
		return this.idforumPost;
	}

	public void setIdforumPost(int idforumPost) {
		this.idforumPost = idforumPost;
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

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ForumPost getForumPost() {
		return this.forumPost;
	}

	public void setForumPost(ForumPost forumPost) {
		this.forumPost = forumPost;
	}

	public List<ForumPost> getForumPosts() {
		return this.forumPosts;
	}

	public void setForumPosts(List<ForumPost> forumPosts) {
		this.forumPosts = forumPosts;
	}

	public ForumPost addForumPost(ForumPost forumPost) {
		getForumPosts().add(forumPost);
		forumPost.setForumPost(this);

		return forumPost;
	}

	public ForumPost removeForumPost(ForumPost forumPost) {
		getForumPosts().remove(forumPost);
		forumPost.setForumPost(null);

		return forumPost;
	}

}
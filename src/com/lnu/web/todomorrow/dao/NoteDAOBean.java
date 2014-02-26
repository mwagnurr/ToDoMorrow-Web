package com.lnu.web.todomorrow.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.lnu.web.todomorrow.model.Note;
import com.lnu.web.todomorrow.model.User;

@Stateless
@LocalBean
public class NoteDAOBean {
	@PersistenceContext(name = "ToDoMorrow")
	EntityManager em;

	public NoteDAOBean() {

	}

	public List<Note> getAllNotes(User user) {

		// log("getting all goals, user is " + user);

		TypedQuery<Note> query = em.createNamedQuery("Note.findAllByUser", Note.class)
				.setParameter("user", user);
		List<Note> result = query.getResultList();
		log("got all notes for user_id " + user.getIduser() + " , result size: " + result.size());
		return result;
	}

	public void persistNote(Note note) {
		// save current time as created_at
		Date currTime = new Date();
		note.setCreatedAt(new Timestamp(currTime.getTime()));

		em.persist(note);
		log("persisted note: " + note);

	}

	public void removeNote(Note note) {
		em.remove(note);
		log("removed note " + note);
	}

	public void updateNote(Note note) {
		em.merge(note);
		log("updated note " + note);
	}

	private void log(String logMsg) {
		String TAG = NoteDAOBean.class.getSimpleName() + ": ";
		System.out.println(TAG + logMsg);
	}
}

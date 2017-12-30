package org.rdemirkoparan.forum.helper;

import java.time.LocalDateTime;

import org.rdemirkoparan.forum.model.Answer;
import org.rdemirkoparan.forum.model.Topic;
import org.rdemirkoparan.forum.model.User;

/**
 * Instantiate new answer and fills its fields
 * 
 * @author recepd
 */
public class AnswerHelper {

	private Answer answer = new Answer ();

	public AnswerHelper start () {
		return new AnswerHelper ();
	}

	public AnswerHelper addContent (String content) {
		this.answer.setContent (content);
		return this;
	}

	public AnswerHelper addUser (User user) {
		this.answer.setUser (user);
		return this;
	}

	public AnswerHelper addTopic (Topic topic) {
		this.answer.setTopic (topic);
		return this;
	}

	public Answer end () {
		this.answer.setCreatedDate (LocalDateTime.now ());
		this.answer.setUseful (false);
		return this.answer;
	}
}

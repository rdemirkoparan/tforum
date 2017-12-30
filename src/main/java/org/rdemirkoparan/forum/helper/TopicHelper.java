package org.rdemirkoparan.forum.helper;

import java.time.LocalDateTime;

import org.rdemirkoparan.forum.model.Topic;
import org.rdemirkoparan.forum.model.User;

/**
 * Instantiate new topic and fills its fields
 *
 * @author recepd
 */
public class TopicHelper {

	private Topic topic = new Topic ();

	public TopicHelper start () {
		return new TopicHelper ();
	}

	public TopicHelper addTitle (String title) {
		this.topic.setTitle (title);
		return this;
	}

	public TopicHelper addContent (String content) {
		this.topic.setContent (content);
		return this;
	}

	public TopicHelper addUser (User user) {
		this.topic.setUser (user);
		return this;
	}

	public Topic end () {
		this.topic.setCreatedDate (LocalDateTime.now ());
		return this.topic;
	}
}

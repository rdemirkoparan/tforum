package org.rdemirkoparan.forum.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.rdemirkoparan.forum.util.DateTimeUtil;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author recepd
 *
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 32)
	private String username;

	@Column(nullable = false, length = 64)
	private String password;

	@Transient
	private String passwordConfirm;

	private LocalDateTime createdDate;

	@OneToMany(mappedBy = "user")
	private List<Topic> topics;

	@OneToMany(mappedBy = "user")
	private List<Answer> answers;

	public Long getId () {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
	}

	public String getUsername () {
		return username;
	}

	public void setUsername (String username) {
		this.username = username;
	}

	public String getPassword () {
		return password;
	}

	public void setPassword (String password) {
		this.password = password;
	}

	public String getPasswordConfirm () {
		return passwordConfirm;
	}

	public void setPasswordConfirm (String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public LocalDateTime getCreatedDate () {
		return createdDate;
	}

	public void setCreatedDate (LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public List<Topic> getTopics () {
		return topics;
	}

	public void setTopics (List<Topic> topics) {
		this.topics = topics;
	}

	public List<Answer> getAnswers () {
		return answers;
	}

	public void setAnswers (List<Answer> answers) {
		this.answers = answers;
	}

	public String displayParsedDate () {
		return DateTimeUtil.parseDate (this.createdDate);
	}
}
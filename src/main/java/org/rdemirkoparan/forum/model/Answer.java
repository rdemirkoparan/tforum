package org.rdemirkoparan.forum.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.rdemirkoparan.forum.util.DateTimeUtil;
import org.rdemirkoparan.forum.util.StringUtil;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author recepd
 *
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "answer")
public class Answer {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 1024)
    private String content;

    @Column(nullable = false)
    private boolean useful;

    private LocalDateTime createdDate;

    @ManyToOne
	@JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
	@JoinColumn(name = "topic_id")
    private Topic topic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isUseful() {
        return useful;
    }

    public void setUseful(boolean useful) {
        this.useful = useful;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String displayParsedCreatedDate() {
		return DateTimeUtil.parseDate (this.createdDate);
    }

    public String displayBeginning() {
		return StringUtil.displayBeginning (this.content);
    }
}

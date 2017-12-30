package org.rdemirkoparan.forum.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.rdemirkoparan.forum.util.DateTimeUtil;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author recepd
 *
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "topic")
public class Topic {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 32)
    private String title;

    @Column(nullable = false, length = 1024)
    private String content;

    private LocalDateTime createdDate;

    @ManyToOne
	@JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "topic")
    private List<Answer> answers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String displayParsedCreatedDate() {
		return DateTimeUtil.parseDate (this.createdDate);
    }
}

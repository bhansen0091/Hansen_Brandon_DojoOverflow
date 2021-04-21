package com.hansen.dojooverflow.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "questions")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotBlank(message = "Field cannot be empty")
	private String questionText;
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void updatedAt() {
		this.updatedAt = new Date();
	}
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Answer> answers;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "tags_questions",
			joinColumns = @JoinColumn(name = "question_id"),
			inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	private List<Tag> tags;
	
	@Transient
	@Pattern(regexp="^(([a-zA-Z\\\\s])+$|([a-zA-Z\\\\s]+,)[a-zA-Z\\\\s]+){1,2}$",message="Tags must be separated by commas, max 3")
	private String parsedTag;
	
	public Question() {}	

	public Question(String questionText, List<Tag> tags) {
		super();
		this.questionText = questionText;
		this.tags = tags;
	}
	
	public String[] splitTags() {
		return this.parsedTag.split("\\s*,\\s*");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getParsedTag() {
		return parsedTag;
	}

	public void setParsedTag(String parsedTag) {
		this.parsedTag = parsedTag;
	}
	
	
	
	
	

}

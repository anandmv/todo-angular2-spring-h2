package com.todo.jpa.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "TODO")
public class Todo {

	// status enum can be used if we have more than two status
	public enum Status{
		PENDING,COMPLETED
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "COMPLETED", nullable = false)
	private Boolean completed;

	@Column(name = "CREATED", nullable = false)
	private Date created;
	
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

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Todo{" +
				"id=" + id +
				", title='" + title + '\'' +
				", completed=" + completed +
				", created=" + created +
				'}';
	}
}

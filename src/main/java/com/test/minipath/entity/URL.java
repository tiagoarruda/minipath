package com.test.minipath.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class URL {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	@Column(unique=true)
	private String path;
	
	protected URL() {
		super();
	};
	
	public URL(String path) {
		this.path = path;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}

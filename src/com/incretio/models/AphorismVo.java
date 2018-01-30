package com.incretio.models;

import java.util.Date;

public class AphorismVo {

	private int id;
	private Date createdTime;
	private String text;
	private String author;
	private int likeCount;
	private boolean wasLiked;

	public AphorismVo() {
		super();
	}

	public AphorismVo(int id, Date createdTime, String text, String author) {
		super();
		this.id = id;
		this.createdTime = createdTime;
		this.text = text;
		this.author = author;
	}
	
	public int getLikeCount() {
		return likeCount;
	}
	
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}

	public String getText() {
		return text;
	}

	public String getAuthor() {
		return author;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public boolean isWasLiked() {
		return wasLiked;
	}

	public void setWasLiked(boolean wasLiked) {
		this.wasLiked = wasLiked;
	}

}

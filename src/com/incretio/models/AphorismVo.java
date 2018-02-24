package com.incretio.models;

import java.util.Date;

public class AphorismVo {

	private int id;
	private Date createdTime;
	private String text;
	private String author;
	private int likeCount;
	private boolean wasLiked;
	private String video;
	private String image;

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
	
	public AphorismVo(int id, Date createdTime, String text, String author, String video, String image) {
		this(id, createdTime, text, author);
		this.video = video;
		this.image = image;
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

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}

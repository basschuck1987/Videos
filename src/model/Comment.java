package model;

import java.util.Date;

public class Comment {

	private long id;
	private String content;
	private Date date;
	private User owner;
	private Video video;
	
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(long id, String content, Date date, User owner, Video video) {
		super();
		this.id = id;
		this.content = content;
		this.date = date;
		this.owner = owner;
		this.video = video;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
}

package model;

import java.util.Date;

public class LikeDislike {
	
	private long id;
	private boolean like;
	private Date date;
	private Video video;
	private Comment comment;
	
	
	public LikeDislike() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LikeDislike(long id, boolean like, Date date, Video video, Comment comment) {
		super();
		this.id = id;
		this.like = like;
		this.date = date;
		this.video = video;
		this.comment = comment;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public boolean isLike() {
		return like;
	}


	public void setLike(boolean like) {
		this.like = like;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Video getVideo() {
		return video;
	}


	public void setVideo(Video video) {
		this.video = video;
	}


	public Comment getComment() {
		return comment;
	}


	public void setComment(Comment comment) {
		this.comment = comment;
	}
	

}

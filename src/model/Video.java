package model;

import java.util.Date;

public class Video {

	public enum Visibility {
		
		PUBLIC,
		UNLISTED,
		PRIVATE
	};
	private long id;
	private String url;
	private String thumbnail;
	private String description;
	private Visibility visibility;
	private boolean blocked;
	private int previews;
	private Date date;
	private User owner;
	
	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Video(long id, String url, String thumbnail, String description, Visibility visibility, boolean blocked,
			int previews, Date date, User owner) {
		super();
		this.id = id;
		this.url = url;
		this.thumbnail = thumbnail;
		this.description = description;
		this.visibility = visibility;
		this.blocked = blocked;
		this.previews = previews;
		this.date = date;
		this.owner = owner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public int getPreviews() {
		return previews;
	}

	public void setPreviews(int previews) {
		this.previews = previews;
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
	
	
}
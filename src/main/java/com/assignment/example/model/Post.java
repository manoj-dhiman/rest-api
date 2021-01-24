package com.assignment.example.model;

/**
 * @author manoj
 *
 */
public class Post {

	private long id;

	private long userId;

	private String title;

	private String body;

	public Post() {
	}

	public Post(long id, long userId, String title, String body) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.body = body;
	}

	/**
	 * Gets id
	 * 
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets id
	 * 
	 * @param id the id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets userId
	 * 
	 * @return userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * Sets userId
	 * 
	 * @param userId the userId
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * Gets title
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets title
	 * 
	 * @param title the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets body
	 * 
	 * @return body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Sets body
	 * 
	 * @param body the body
	 */
	public void setBody(String body) {
		this.body = body;
	}
}

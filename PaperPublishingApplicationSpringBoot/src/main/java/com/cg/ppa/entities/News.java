package com.cg.ppa.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "news_master")
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int newsId;
	private String headline;
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User reporter;
	private String location;
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;
	private String newsDescription;
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public User getReporter() {
		return reporter;
	}
	public void setReporter(User reporter) {
		this.reporter = reporter;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getNewsDescription() {
		return newsDescription;
	}
	public void setNewsDescription(String newsDescription) {
		this.newsDescription = newsDescription;
	}
	public News() {
		// TODO Auto-generated constructor stub
	}
	public News(int newsId, String headline, User reporter, String location, Category category,
			String newsDescription) {
		super();
		this.newsId = newsId;
		this.headline = headline;
		this.reporter = reporter;
		this.location = location;
		this.category = category;
		this.newsDescription = newsDescription;
	}
}

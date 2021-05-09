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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "news_master")
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int newsId;
	@NotNull
	@Size(min=10, message="Headline should be minimum 10 characters long")
	private String headline;
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User reporter;
	@NotNull
	@Size(min=2, message="Location name should be greater than 2 characters")
	private String location;
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;
	@NotNull
	@Size(min=20, message = "News Description should be more than 20 characters")
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

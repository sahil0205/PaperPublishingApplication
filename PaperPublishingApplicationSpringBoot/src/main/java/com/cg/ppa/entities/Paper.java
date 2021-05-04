package com.cg.ppa.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "paper_master")
public class Paper {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paperId;
	private LocalDate publishDate;
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User editor;
	private int price;
	@OneToMany(targetEntity = News.class)
	private List<News> newsList;
	public int getPaperId() {
		return paperId;
	}
	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
	public LocalDate getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}
	public User getEditor() {
		return editor;
	}
	public void setEditor(User editor) {
		this.editor = editor;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public List<News> getNewsList() {
		return newsList;
	}
	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
	public Paper() {
		// TODO Auto-generated constructor stub
	}
	public Paper(int paperId, LocalDate publishDate, User editor, int price, List<News> newsList) {
		super();
		this.paperId = paperId;
		this.publishDate = publishDate;
		this.editor = editor;
		this.price = price;
		this.newsList = newsList;
	}
	@Override
	public String toString() {
		return "Paper [paperId=" + paperId + ", publishDate=" + publishDate + ", editor=" + editor + ", price=" + price
				+ ", newsList=" + newsList + "]";
	}
	
	
}

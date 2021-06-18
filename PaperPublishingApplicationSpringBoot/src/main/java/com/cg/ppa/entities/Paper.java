package com.cg.ppa.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "paper")
public class Paper {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paper_id")
	private int paperId;
	@FutureOrPresent(message = "Date should be either present date or future date")
	@Column(name = "publish_date")
	private LocalDate publishDate;
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "editor_id")
	private User editor;
	@Min(value = 2, message = "Min price is 2")
	@Column(name = "paper_price")
	private int price;
	@OneToMany(cascade = CascadeType.MERGE, targetEntity = News.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "paper_id", referencedColumnName = "paper_id")
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

	public Paper(int paperId, LocalDate publishDate, User editor, int price) {
		super();
		this.paperId = paperId;
		this.publishDate = publishDate;
		this.editor = editor;
		this.price = price;
	}

	public Paper(int paperId,
			@FutureOrPresent(message = "Date should be either present date or future date") LocalDate publishDate,
			User editor, @Min(value = 2, message = "Min price is 2") int price, List<News> newsList) {
		super();
		this.paperId = paperId;
		this.publishDate = publishDate;
		this.editor = editor;
		this.price = price;
		this.newsList = newsList;
	}

}

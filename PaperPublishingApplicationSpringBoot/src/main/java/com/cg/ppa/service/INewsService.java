package com.cg.ppa.service;

import java.util.List;

import com.cg.ppa.entities.News;
import com.cg.ppa.entities.User;
import com.cg.ppa.exception.NewsException;

public interface INewsService {

	public News addNews(News news) throws NewsException;

	public News viewNewsById(int newsId) throws NewsException;

	public void deleteNews(int newsId) throws NewsException;

	public News updateNews(News news) throws NewsException;

	public List<News> viewNewsByLocation(String location) throws NewsException;

	public List<News> viewAllNews() throws NewsException;
	
	public List<News> viewByReporter(int userid) throws NewsException;
}

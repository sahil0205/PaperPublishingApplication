package com.cg.ppa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ppa.entities.News;
import com.cg.ppa.exception.NewsException;
import com.cg.ppa.repository.INewsRepository;

@Service
@Transactional
public class NewsService implements INewsService{

	@Autowired
	INewsRepository repository;
	
	@Override
	public News addNews(News news) throws NewsException {
		if(repository.existsByNewsId(news.getNewsId()))
			throw new NewsException("News already exists");
		else
			return repository.save(news);
	}

	@Override
	public News viewNewsById(int newsId) throws NewsException {
		if(repository.existsByNewsId(newsId))
			return repository.findByNewsId(newsId);
		else
			throw new NewsException("News does not exist");
	}

	@Override
	public void deleteNews(int newsId) throws NewsException {
		if(repository.existsByNewsId(newsId))
			repository.deleteById(newsId);
		else
			throw new NewsException("News does not exist");
	}

	@Override
	public News updateNews(News news) throws NewsException {
		if(repository.existsByNewsId(news.getNewsId()))
			return repository.save(news);
		else
			throw new NewsException("News not found");
	}

	@Override
	public List<News> viewNewsByLocation(String location) throws NewsException {
		List<News> newsList = repository.findByLocation(location);
		if(newsList.isEmpty())
			throw new NewsException("No news for this location");
		else
			return newsList;
	}

	@Override
	public List<News> viewAllNews() throws NewsException {
		List<News> newsList = repository.findAll();
		if(newsList.isEmpty())
			throw new NewsException("No data found");
		else
			return newsList;
	}

}

package com.cg.ppa.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ppa.entities.News;
import com.cg.ppa.exception.NewsException;
import com.cg.ppa.service.INewsService;

@RestController
@RequestMapping("/ppa/news")
public class NewsController {

	@Autowired
	INewsService service;

	Logger logger = LoggerFactory.getLogger(NewsController.class);

	@PostMapping("/addnews")
	public ResponseEntity<Object> addNews(@Valid @RequestBody News news) {
		try {
			News newsData = service.addNews(news);
			logger.info("News with id: " + newsData.getNewsId() + " added");
			return new ResponseEntity<Object>(newsData, HttpStatus.OK);
		} catch (NewsException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/viewnewsbyid/{newsId}")
	public ResponseEntity<Object> viewNewsById(@PathVariable int newsId) {
		try {
			News newsData = service.viewNewsById(newsId);
			logger.info("Accessing news with id: " + newsId);
			return new ResponseEntity<Object>(newsData, HttpStatus.OK);
		} catch (NewsException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deletenews/{newsId}")
	public ResponseEntity<Object> deleteNews(@PathVariable int newsId) {
		try {
			service.deleteNews(newsId);
			logger.info("News with id: " + newsId + " deleted");
			return new ResponseEntity<Object>("News Deleted", HttpStatus.OK);
		} catch (NewsException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updatenews")
	public ResponseEntity<Object> updateNews(@Valid @RequestBody News news) {
		try {
			News newsData = service.updateNews(news);
			logger.info("News with id: " + news.getNewsId() + " updated");
			return new ResponseEntity<Object>(newsData, HttpStatus.OK);
		} catch (NewsException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("viewnewsbylocation/{location}")
	public ResponseEntity<Object> viewNewsByLocation(String location) {
		try {
			List<News> newsData = service.viewNewsByLocation(location);
			logger.info("Viewing news by location: " + location);
			return new ResponseEntity<Object>(newsData, HttpStatus.OK);
		} catch (NewsException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/viewallnews")
	public ResponseEntity<Object> viewAllNews() {
		try {
			List<News> newsList = service.viewAllNews();
			logger.info("Viewing all news");
			return new ResponseEntity<Object>(newsList, HttpStatus.OK);
		} catch (NewsException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}

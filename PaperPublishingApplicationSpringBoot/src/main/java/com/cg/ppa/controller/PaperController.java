package com.cg.ppa.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ppa.entities.Paper;
import com.cg.ppa.exception.PaperException;
import com.cg.ppa.service.IPaperService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ppa/paper")
public class PaperController {

	@Autowired
	IPaperService service;

	Logger logger = LoggerFactory.getLogger(PaperController.class);

	@PostMapping("/createpaper")
	public ResponseEntity<Object> createPaper(@Valid @RequestBody Paper paper) {
		try {
			Paper paperData = service.createPaper(paper);
			logger.info("Paper with id: " + paperData.getPaperId() + " created");
			return new ResponseEntity<Object>(paperData, HttpStatus.OK);
		} catch (PaperException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@PutMapping("/updatepaper")
	public ResponseEntity<Object> updatePaper(@Valid @RequestBody Paper paper) {
		try {
			Paper paperData = service.updatePaper(paper);
			logger.info("Paper with id: " + paperData.getPaperId() + " updated");
			return new ResponseEntity<Object>(paperData, HttpStatus.OK);
		} catch (PaperException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deletepaper/{paperId}")
	public ResponseEntity<Object> deletePaper(@PathVariable int paperId) {
		try {
			service.deletePaper(paperId);
			logger.info("Paper with id: " + paperId + " deleted");
			return new ResponseEntity<Object>("Paper Deleted", HttpStatus.OK);
		} catch (PaperException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/viewpaperbypublishdate/{publishDate}")
	public ResponseEntity<Object> viewPaperByPublishDate(@PathVariable LocalDate publishDate) {
		try {
			Paper paperData = service.viewPaperByPublishDate(publishDate);
			logger.info("Accessing paper for date: " + publishDate);
			return new ResponseEntity<Object>(paperData, HttpStatus.OK);
		} catch (PaperException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/viewallpaper")
	public ResponseEntity<Object> viewAllPaper() {
		try {
			List<Paper> paperList = service.viewAllPaper();
			logger.info("Viewing all paper");
			return new ResponseEntity<Object>(paperList, HttpStatus.OK);
		} catch (PaperException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/viewpaperbyid/{paperId}")
	public ResponseEntity<Object> viewPaperById(@PathVariable int paperId) {
		try {
			Paper paperData = service.viewPaperById(paperId);
			logger.info("Accessing paper by id: " + paperId);
			return new ResponseEntity<Object>(paperData, HttpStatus.OK);
		} catch (PaperException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/viewpaperbyeditor/{editorId}")
	public ResponseEntity<Object> viewPaperByEditor(@PathVariable int editorId){
		try {
			List<Paper> paperList = service.viewPaperByEditor(editorId);
			return new ResponseEntity<Object>(paperList, HttpStatus.OK);
		}catch (PaperException e) {
			// TODO: handle exception
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}
}

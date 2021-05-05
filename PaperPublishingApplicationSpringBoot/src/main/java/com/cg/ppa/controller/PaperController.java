package com.cg.ppa.controller;

import java.time.LocalDate;
import java.util.List;

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

import com.cg.ppa.entities.Paper;
import com.cg.ppa.exception.PaperException;
import com.cg.ppa.service.IPaperService;

@RestController
@RequestMapping("/ppa/paper")
public class PaperController {
	
	@Autowired
	IPaperService service;
	
	@PostMapping("/createpaper")
	public ResponseEntity<Object> createPaper(@RequestBody Paper paper){
		try {
			Paper paperData = service.createPaper(paper);
			return new ResponseEntity<Object>(paperData, HttpStatus.OK);
		}
		catch (PaperException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PutMapping("/updatepaper")
	public ResponseEntity<Object> updatePaper(@RequestBody Paper paper){
		try {
			Paper paperData = service.updatePaper(paper);
			return new ResponseEntity<Object>(paperData, HttpStatus.OK);
		}
		catch(PaperException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deletepaper/{paperId}")
	public ResponseEntity<Object> deletePaper(@PathVariable int paperId){
		try {
			service.deletePaper(paperId);
			return new ResponseEntity<Object>("Paper Deleted", HttpStatus.OK);
		}
		catch(PaperException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/viewpaperbypublishdate/{publishDate}")
	public ResponseEntity<Object> viewPaperByPublishDate(@PathVariable LocalDate publishDate){
		try {
			Paper paperData = service.viewPaperByPublishDate(publishDate);
			return new ResponseEntity<Object>(paperData, HttpStatus.OK);
		}
		catch(PaperException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/viewallpaper")
	public ResponseEntity<Object> viewAllPaper(){
		try {
			List<Paper> paperList = service.viewAllPaper();
			return new ResponseEntity<Object>(paperList, HttpStatus.OK);
		}
		catch(PaperException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/viewpaperbyid/{paperId}")
	public ResponseEntity<Object> viewPaperById(@PathVariable int paperId){
		try {
			Paper paperData = service.viewPaperById(paperId);
			return new ResponseEntity<Object>(paperData, HttpStatus.OK);
		}
		catch(PaperException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}

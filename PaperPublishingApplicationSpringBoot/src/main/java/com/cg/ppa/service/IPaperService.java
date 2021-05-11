package com.cg.ppa.service;

import java.time.LocalDate;
import java.util.List;
import com.cg.ppa.entities.Paper;
import com.cg.ppa.exception.PaperException;

public interface IPaperService {
	public Paper createPaper(Paper paper) throws PaperException;

	public Paper updatePaper(Paper paper) throws PaperException;

	public void deletePaper(int paperId) throws PaperException;

	public Paper viewPaperByPublishDate(LocalDate publishDate) throws PaperException;

	public List<Paper> viewAllPaper() throws PaperException;

	public Paper viewPaperById(int paperId) throws PaperException;
}

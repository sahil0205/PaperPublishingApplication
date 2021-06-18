package com.cg.ppa.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ppa.entities.Paper;
import com.cg.ppa.entities.User;
import com.cg.ppa.exception.PaperException;
import com.cg.ppa.repository.IPaperRepository;
import com.cg.ppa.repository.IUserRepository;

@Service
@Transactional
public class PaperService implements IPaperService {

	@Autowired
	IPaperRepository repository;
	
	@Autowired
	IUserRepository repo;

	@Override
	public Paper createPaper(Paper paper) throws PaperException {
		if (!(repository.existsById(paper.getPaperId())))
			return repository.save(paper);
		else
			throw new PaperException("Paper Id already exists cannot publish paper");
	}

	@Override
	public Paper updatePaper(Paper paper) throws PaperException {
		if (repository.existsById(paper.getPaperId()))
			return repository.save(paper);
		else
			throw new PaperException("Paper does not exist");
	}

	@Override
	public void deletePaper(int paperId) throws PaperException {
		if (repository.existsById(paperId))
			repository.deleteById(paperId);
		else
			throw new PaperException("Paper does not exist");
	}

	@Override
	public Paper viewPaperByPublishDate(LocalDate publishDate) throws PaperException {
		Paper paperData = repository.findByPublishDate(publishDate);
		if (paperData == null)
			throw new PaperException("Paper does not exist");
		else
			return paperData;
	}

	@Override
	public List<Paper> viewAllPaper() throws PaperException {
		List<Paper> paperList = repository.findAll();
		if (paperList.isEmpty())
			throw new PaperException("No data found");
		else
			return paperList;
	}

	@Override
	public Paper viewPaperById(int paperId) throws PaperException {
		if (repository.existsById(paperId))
			return repository.findByPaperId(paperId);
		else
			throw new PaperException("Paper does not exist");
	}

	@Override
	public List<Paper> viewPaperByEditor(int editorId) {
		User editor = repo.findByUserId(editorId);
		List<Paper> paperList = repository.findByEditor(editor);

		return paperList;
	}

}

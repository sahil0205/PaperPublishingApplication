package com.cg.ppa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ppa.entities.News;
import com.cg.ppa.entities.Paper;
import com.cg.ppa.entities.User;

@Repository
public interface IPaperRepository extends JpaRepository<Paper, Integer> {
	public Paper findByPublishDate(LocalDate publishDate);

	public Paper findByPaperId(int paperId);
	
	public List<Paper> findByEditor(User editor);

}

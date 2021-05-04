package com.cg.ppa.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ppa.entities.Paper;

@Repository
public interface IPaperRepository extends JpaRepository<Paper, Integer>{
	public Paper findByPublishDate(LocalDate publishDate);
}

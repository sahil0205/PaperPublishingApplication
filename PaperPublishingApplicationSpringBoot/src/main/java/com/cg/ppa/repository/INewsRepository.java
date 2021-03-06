package com.cg.ppa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ppa.entities.News;
import com.cg.ppa.entities.User;

@Repository
public interface INewsRepository extends JpaRepository<News, Integer> {
	public boolean existsByNewsId(int newsId);

	public News findByNewsId(int newsId);

	public List<News> findByLocation(String location);
	
	public List<News> findByReporter(User reporter);

}

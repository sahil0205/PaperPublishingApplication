package com.cg.ppa.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.ppa.entities.Category;
import com.cg.ppa.entities.News;
import com.cg.ppa.entities.Paper;
import com.cg.ppa.entities.User;
import com.cg.ppa.repository.IPaperRepository;
import com.cg.ppa.service.PaperService;

@SpringBootTest
public class PaperTest {

	@Autowired
	PaperService service;

	@MockBean
	IPaperRepository repository;

	public Category getCategory() {
		Category category = new Category(1, "Sports");
		return category;
	}

	public User getEditor() {
		User user = new User(1, "Sahil Palaskar", "Editor", "+91 7709161413", "sahil@gmail.com", "Sahil#213");
		return user;
	}

	public List<News> getNewsList() {
		List<News> newsList = Stream
				.of(new News(1, "Head Line 1", getEditor(), "Pune", getCategory(), "News Description 1"),
						new News(2, "Head Line 2", getEditor(), "Mumbai", getCategory(), "News Description 2"))
				.collect(Collectors.toList());
		return newsList;
	}

	@Test
	public void createPaper() {
		Paper paper = new Paper(1, LocalDate.now(), getEditor(), 2, getNewsList());
		when(repository.save(paper)).thenReturn(paper);
		assertEquals(1, service.createPaper(paper).getPaperId());
		assertEquals(LocalDate.now(), service.createPaper(paper).getPublishDate());
	}

	@Test
	public void updatePaper() {
		Paper paper = new Paper(1, LocalDate.now(), getEditor(), 2, getNewsList());
		when(repository.existsById(1)).thenReturn(true);
		when(repository.save(paper)).thenReturn(paper);
		assertEquals(1, service.updatePaper(paper).getPaperId());
		assertEquals(LocalDate.now(), service.updatePaper(paper).getPublishDate());
	}

	@Test
	public void deletePaper() {
		when(repository.existsById(1)).thenReturn(true);
		service.deletePaper(1);
		verify(repository, times(1)).deleteById(1);
	}

	@Test
	public void viewByPublishDate() {
		Paper paper = new Paper(1, LocalDate.now(), getEditor(), 2, getNewsList());
		when(repository.findByPublishDate(LocalDate.now())).thenReturn(paper);
		assertEquals(1, service.viewPaperByPublishDate(LocalDate.now()).getPaperId());
		assertEquals(LocalDate.now(), service.viewPaperByPublishDate(LocalDate.now()).getPublishDate());
	}

	@Test
	public void viewById() {
		Paper paper = new Paper(1, LocalDate.now(), getEditor(), 2, getNewsList());
		when(repository.existsById(1)).thenReturn(true);
		when(repository.findByPaperId(1)).thenReturn(paper);
		assertEquals(1, service.viewPaperById(1).getPaperId());
	}

	@Test
	public void viewAll() {
		when(repository.findAll())
				.thenReturn(Stream
						.of(new Paper(1, LocalDate.now(), getEditor(), 2, getNewsList()),
								new Paper(2, LocalDate.now(), getEditor(), 2, getNewsList()))
						.collect(Collectors.toList()));
		assertEquals(2, service.viewAllPaper().size());
	}
}

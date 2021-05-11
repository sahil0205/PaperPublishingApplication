package com.cg.ppa.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.ppa.entities.Category;
import com.cg.ppa.entities.News;
import com.cg.ppa.entities.User;
import com.cg.ppa.repository.INewsRepository;
import com.cg.ppa.service.NewsService;

@SpringBootTest
public class NewsTest {

	@Autowired
	NewsService service;

	@MockBean
	INewsRepository repository;

	public Category getCategory() {
		Category category = new Category(1, "Sports");
		return category;
	}

	public User getReporter() {
		User user = new User(1, "Sahil Palaskar", "Reporter", "+91 7709161413", "sahil@gmail.com", "Sahil#213");
		return user;
	}

	@Test
	public void addNews() {
		News news = new News(1, "HeadLine 1", getReporter(), "Pune", getCategory(), "News Description");
		when(repository.save(news)).thenReturn(news);
		assertEquals(1, service.addNews(news).getNewsId());
		assertEquals(getReporter().getUserName(), service.addNews(news).getReporter().getUserName());
	}

	@Test
	public void viewAllNews() {
		when(repository.findAll()).thenReturn(Stream
				.of(new News(1, "Head Line 1", getReporter(), "Pune", getCategory(), "News Description 1"),
						new News(2, "Head Line 2", getReporter(), "Mumbai", getCategory(), "News Description 2"))
				.collect(Collectors.toList()));
		assertEquals(2, service.viewAllNews().size());
	}

	@Test
	public void viewNewsById() {
		News news = new News(1, "HeadLine 1", getReporter(), "Pune", getCategory(), "News Description");
		when(repository.existsByNewsId(1)).thenReturn(true);
		when(repository.findByNewsId(1)).thenReturn(news);
		assertEquals(1, service.viewNewsById(1).getNewsId());
		assertEquals("Pune", service.viewNewsById(1).getLocation());
	}

	@Test
	public void deleteNews() {
		when(repository.existsByNewsId(1)).thenReturn(true);
		service.deleteNews(1);
		verify(repository, times(1)).deleteById(1);
	}

	@Test
	public void updateNews() {
		News news = new News(1, "HeadLine 1", getReporter(), "Pune", getCategory(), "News Description");
		when(repository.existsByNewsId(1)).thenReturn(true);
		when(repository.save(news)).thenReturn(news);
		assertEquals(1, service.updateNews(news).getNewsId());
		assertEquals("Pune", service.updateNews(news).getLocation());
	}

	@Test
	public void viewNewsByLocation() {
		when(repository.findByLocation("Pune")).thenReturn(Stream
				.of(new News(1, "Head Line 1", getReporter(), "Pune", getCategory(), "News Description 1"),
						new News(2, "Head Line 2", getReporter(), "Pune", getCategory(), "News Description 2"))
				.collect(Collectors.toList()));
		assertEquals(2, service.viewNewsByLocation("Pune").size());
	}
}

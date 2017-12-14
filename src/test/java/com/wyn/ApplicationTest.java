package com.wyn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wyn.dao.BookCategoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
	
	@Autowired
	private BookCategoryRepository bookCategoryRepository;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void cleanDB() {
		bookCategoryRepository.deleteAll();
	}
}

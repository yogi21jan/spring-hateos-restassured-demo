package com.wyn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wyn.dao.BookCategoryRepository;
import com.wyn.domain.Book;
import com.wyn.domain.BookCategory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class BookInitializer implements CommandLineRunner {

	private final BookCategoryRepository bookCategoryRepository;

	@Override
	@Transactional
	public void run(String... strings) throws Exception {
		// save a couple of categories
		BookCategory categoryA = new BookCategory();
		categoryA.setName("Category A");
		Set<Book> bookAs = new HashSet<Book>() {
			{
				add(new Book("Book A1", categoryA));
				add(new Book("Book A2", categoryA));
				add(new Book("Book A3", categoryA));
			}
		};
		categoryA.setBooks(bookAs);

		BookCategory categoryB = new BookCategory();
		categoryB.setName("Category B");
		Set<Book> bookBs = new HashSet<Book>() {
			{
				add(new Book("Book B1", categoryB));
				add(new Book("Book B2", categoryB));
				add(new Book("Book B3", categoryB));
			}
		};
		categoryB.setBooks(bookBs);

		bookCategoryRepository.save(new HashSet<BookCategory>() {
			{
				add(categoryA);
				add(categoryB);
			}
		});

		List<Integer> ids = new ArrayList<>();
		// fetch all categories
		for (BookCategory bookCategory : bookCategoryRepository.findAll()) {
			bookCategory.getBooks().forEach(book -> {
				log.info("Book: " + book.getId());
				ids.add(book.getId());
			});
			log.info(bookCategory.toString());
		}

		Set<BookCategory> bookCategories = bookCategoryRepository.findByBooksIn(categoryA.getBooks());
		bookCategories.forEach(bookCategory -> {
			log.info(bookCategory.toString());
		});

	}

}

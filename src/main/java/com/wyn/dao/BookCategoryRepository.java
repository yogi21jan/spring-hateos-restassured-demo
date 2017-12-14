package com.wyn.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wyn.domain.Book;
import com.wyn.domain.BookCategory;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer>{
	
	Set<BookCategory> findByBooksIn(Set<Book> books);
}

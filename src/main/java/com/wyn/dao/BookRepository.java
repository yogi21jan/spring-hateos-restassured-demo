package com.wyn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wyn.domain.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}

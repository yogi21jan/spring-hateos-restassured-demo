package com.wyn.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wyn.dao.BookCategoryRepository;
import com.wyn.dao.BookRepository;
import com.wyn.domain.Book;
import com.wyn.domain.BookCategory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Api(value="BookController REST API")
public class BookController {
	
	private final BookCategoryRepository bookCategoryRepository;
	private final BookRepository bookRepository;
	
	@GetMapping(value = "/bookCategories/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "List book category for given {id}")
	public Resource<BookCategory> getBookCategory(@PathVariable(value = "id") Integer id) {
		BookCategory bookCategory = bookCategoryRepository.findOne(Integer.valueOf(id));
		Resource<BookCategory> resource = new Resource<BookCategory>(bookCategory);
		resource.add(linkTo(methodOn(BookController.class).getBookCategory(id)).withSelfRel());
		return resource;
	}
	
	@GetMapping(value = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "List book for given {id}")
	public Resource<Book> getBook(@PathVariable(value = "id") Integer id) {
		Book book = bookRepository.findOne(Integer.valueOf(id));
		Resource<Book> resource = new Resource<Book>(book);
		resource.add(linkTo(methodOn(BookController.class).getBook(id)).withSelfRel());
		resource.add(linkTo(methodOn(BookController.class).getBookCategory(book.getBookCategory().getId())).withRel("book category"));
		return resource;
	}
	
	@GetMapping(value = "/bookCategories", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "List book category for given {id}")
	public Collection<Resource<BookCategory>> getAllBookCategory() {
		List<BookCategory> bookCategories = bookCategoryRepository.findAll();
		List<Resource<BookCategory>> resources = new ArrayList<Resource<BookCategory>>();
		bookCategories.forEach(bookCategory->{
			resources.add(getBookCategoryResource(bookCategory));
		});
		
		return resources;
	}

	private Resource<BookCategory> getBookCategoryResource(BookCategory bookCategory) {
		
		Resource<BookCategory> resource = new Resource<BookCategory>(bookCategory);
		resource.add(linkTo(methodOn(BookController.class).getBookCategory(bookCategory.getId())).withSelfRel());
		return resource;
	}
	

}

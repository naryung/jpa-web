package com.naryung.web.modules.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naryung.web.modules.store.model.Book;
import com.naryung.web.modules.store.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired 
	BookService bookService;
	
	@GetMapping("")
	public List<Book> getBooks(Pageable pageable) {
		return bookService.getAll(pageable);
	}
	
	@PostMapping("")
	public Book createBook(@RequestBody Book b) {
		return bookService.createBook(b);
	}
	
	@GetMapping("/{id}")
	public Book getBook(@PathVariable Long id) {
		return bookService.getOne(id);
	}
	
	@PutMapping("/{id}")
	public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
		return bookService.updateBook(id, book);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
	}
}
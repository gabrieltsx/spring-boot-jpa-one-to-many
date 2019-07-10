package br.gahlls.example.springbootrelationship.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gahlls.example.springbootrelationship.model.Book;
import br.gahlls.example.springbootrelationship.repository.BookRepository;
import br.gahlls.example.springbootrelationship.service.BookCategoryService;
import br.gahlls.example.springbootrelationship.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookCategoryService bookCategoryService;
	
	
	@Override
	public Book insert(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public Book findById(Integer id) {
		return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found with id: " +id));
	}

	@Override
	public Book update(Integer id, Book book) {
		Book bookCurrent = this.findById(id);
		bookCurrent.setBookCategory(bookCategoryService.findById(book.getBookCategory().getId()));
		bookCurrent.setPrice(book.getPrice());
		bookCurrent.setTitle(book.getTitle());
		return bookRepository.save(bookCurrent);
	}

	@Override
	public void delete(Integer id) {
		Book bookCurrent = this.findById(id);
		bookRepository.delete(bookCurrent);
	}
}

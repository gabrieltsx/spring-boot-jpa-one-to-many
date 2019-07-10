package br.gahlls.example.springbootrelationship.service;

import java.util.List;

import br.gahlls.example.springbootrelationship.model.Book;

public interface BookService {

	Book insert(Book book);
	
	List<Book> findAll();
	
	Book findById(Integer id);
	
	Book update(Integer id, Book book);
	
	void delete(Integer id);
}

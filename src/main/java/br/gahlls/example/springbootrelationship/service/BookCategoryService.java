package br.gahlls.example.springbootrelationship.service;

import java.util.List;

import br.gahlls.example.springbootrelationship.model.BookCategory;

public interface BookCategoryService {

	BookCategory insert(BookCategory bookCategory);
	
	List<BookCategory> findAll();
	
	BookCategory findById(Integer id);
	
	void delete(Integer id);
	
	BookCategory update(Integer id, BookCategory bookCategory);
}

package br.gahlls.example.springbootrelationship.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gahlls.example.springbootrelationship.model.BookCategory;
import br.gahlls.example.springbootrelationship.repository.BookCategoryRepository;
import br.gahlls.example.springbootrelationship.service.BookCategoryService;

@Service
public class BookCategoryServiceImpl implements BookCategoryService{

	@Autowired
	private BookCategoryRepository bookCategoryRepository;

	@Override
	public BookCategory insert(BookCategory bookCategory) {
		return bookCategoryRepository.save(bookCategory);
	}

	@Override
	public List<BookCategory> findAll() {
		return bookCategoryRepository.findAll();
	}

	@Override
	public BookCategory findById(Integer id) {
		return bookCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found with id: " + id));
	}

	@Override
	public void delete(Integer id) {
		BookCategory bookCategory = this.findById(id);
		bookCategoryRepository.delete(bookCategory);
	}

	@Override
	public BookCategory update(Integer id, BookCategory bookCategory) {
		BookCategory bookCategoryCurrent = this.findById(id);
		bookCategoryCurrent.setName(bookCategory.getName());
		return bookCategoryRepository.save(bookCategoryCurrent);
	}
}

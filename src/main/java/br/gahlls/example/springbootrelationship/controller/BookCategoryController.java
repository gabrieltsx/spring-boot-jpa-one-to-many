package br.gahlls.example.springbootrelationship.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gahlls.example.springbootrelationship.model.BookCategory;
import br.gahlls.example.springbootrelationship.request.BookCategoryRequest;
import br.gahlls.example.springbootrelationship.service.BookCategoryService;

@RestController
@RequestMapping("/v1/api/book-category")
public class BookCategoryController {

	private BookCategoryService bookCategoryService;
	private ModelMapper modelMapper;

	@Autowired
	public BookCategoryController(BookCategoryService bookCategoryService, ModelMapper modelMapper) {
		this.bookCategoryService = bookCategoryService;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping
	public ResponseEntity<BookCategory> insertBookCategory(@RequestBody BookCategoryRequest bookCategoryRequest) {
		BookCategory bookCategory = modelMapper.map(bookCategoryRequest, BookCategory.class);
		return ResponseEntity.ok(bookCategoryService.insert(bookCategory));
	}
	
	@GetMapping
	public ResponseEntity<List<BookCategory>> getAll() {
		return ResponseEntity.ok(bookCategoryService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookCategory> getOne(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(bookCategoryService.findById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBookCategory(@PathVariable("id") Integer id){
		bookCategoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<BookCategory> updateBookCategory(@PathVariable("id") Integer id, @RequestBody BookCategoryRequest bookCategoryRequest){
		BookCategory bookCategory = modelMapper.map(bookCategoryRequest, BookCategory.class);
		return ResponseEntity.ok(bookCategoryService.update(id, bookCategory));
	}
}

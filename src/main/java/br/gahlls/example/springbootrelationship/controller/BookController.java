package br.gahlls.example.springbootrelationship.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gahlls.example.springbootrelationship.model.Book;
import br.gahlls.example.springbootrelationship.request.AddBookRequest;
import br.gahlls.example.springbootrelationship.request.UpdateBookRequest;
import br.gahlls.example.springbootrelationship.service.BookService;

@RestController
@RequestMapping("/v1/api/book")
public class BookController {
	
	private BookService bookService;
	private ModelMapper modelMapper;
	
	@Autowired
	public BookController(BookService bookService, ModelMapper modelMapper) {
		this.bookService = bookService;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping
	public ResponseEntity<URI> insert(@RequestBody AddBookRequest addBookRequest){
		Book book = modelMapper.map(addBookRequest, Book.class);
		Book bookSaved = bookService.insert(book);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bookSaved.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<List<Book>> getAll() {
		return ResponseEntity.ok(bookService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getOne(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(bookService.findById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") Integer id, @RequestBody UpdateBookRequest updateBookRequest){
		Book book = modelMapper.map(updateBookRequest, Book.class);
		return ResponseEntity.ok(bookService.update(id, book));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable("id") Integer id){
		bookService.delete(id);
		return ResponseEntity.noContent().build();
	}
}

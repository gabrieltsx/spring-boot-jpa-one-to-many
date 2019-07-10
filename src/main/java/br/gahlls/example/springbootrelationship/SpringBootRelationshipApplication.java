package br.gahlls.example.springbootrelationship;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.gahlls.example.springbootrelationship.model.Book;
import br.gahlls.example.springbootrelationship.model.BookCategory;
import br.gahlls.example.springbootrelationship.repository.BookCategoryRepository;
import br.gahlls.example.springbootrelationship.repository.BookRepository;

@SpringBootApplication
public class SpringBootRelationshipApplication implements CommandLineRunner {

	@Autowired
	private BookCategoryRepository bookCategoryRepository;

	@Autowired
	private BookRepository bookRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRelationshipApplication.class, args);
	}
	
    @Override
    public void run(String... args) {
    	// Create a couple of Book and BookCategory
    	BookCategory scienceFiction = bookCategoryRepository.save(new BookCategory("Science fiction", new Book("Brave New World", 50.00), new Book("Blade Runner", 30.35)));    
    	
    	// Find a BookCategory
    	Optional<BookCategory> bookCategory = bookCategoryRepository.findById(scienceFiction.getId());
    	
    	Book newBook = new Book("Foundation", 45.00);
    	newBook.setBookCategory(bookCategory.get());

    	// Save a new book 
    	bookRepository.save(newBook);
    	
    	// Create a new BookCategory
    	BookCategory fantasy = bookCategoryRepository.save(new BookCategory("Fantasy"));
    	
    	Book book = new Book("The Lord of the Rings", 40.00);
    	book.setBookCategory(fantasy); // Set BookCategory to a new Book 
    	
    	// Save a new Book with a new BookCategory
    	bookRepository.save(book);
    }
}

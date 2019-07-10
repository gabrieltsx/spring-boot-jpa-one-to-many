package br.gahlls.example.springbootrelationship.model;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(exclude ="books")
@Entity
public class BookCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "bookCategory", cascade = CascadeType.ALL)
	private Set<Book> books;

	public BookCategory(String name, Book... books) {
		this.name = name;
		this.books = Stream.of(books).collect(Collectors.toSet());
		this.books.forEach(x -> x.setBookCategory(this));
	}
}

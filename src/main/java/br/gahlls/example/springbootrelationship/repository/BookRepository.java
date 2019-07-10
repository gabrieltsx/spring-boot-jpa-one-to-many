package br.gahlls.example.springbootrelationship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gahlls.example.springbootrelationship.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}

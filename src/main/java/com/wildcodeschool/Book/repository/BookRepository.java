package com.wildcodeschool.Book.repository;

import com.wildcodeschool.Book.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book,Long> {
    List<Book> findByTitleContainingOrDescriptionContaining(String searchTerm, String searchTerm1);
}

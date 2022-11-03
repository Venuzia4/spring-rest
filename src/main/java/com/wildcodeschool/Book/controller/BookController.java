package com.wildcodeschool.Book.controller;

import com.wildcodeschool.Book.entity.Book;
import com.wildcodeschool.Book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public List<Book> index(){
        return (List<Book>) bookRepository.findAll();
    }


    @GetMapping("/books/{id}")
    public Book show(@PathVariable int id) {
        return bookRepository.findById((long) id).get();
    }


    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
                String searchTerm = body.get("text");
                return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
            }


    @PostMapping("/books")
    public Book create(@RequestBody Book book){
                return bookRepository.save(book);
            }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book){

            Book bookToUpdate = bookRepository.findById((long) id).get();
                bookToUpdate.setTitle(book.getTitle());
                bookToUpdate.setDescription(book.getDescription());
                return bookRepository.save(bookToUpdate);
           }

    @DeleteMapping("books/{id}")
   public boolean delete(@PathVariable int id){
               bookRepository.deleteById((long) id);
        return true;
           }
}



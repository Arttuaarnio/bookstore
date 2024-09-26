package hh.sof3.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof3.bookstore.domain.Book;
import hh.sof3.bookstore.domain.BookRepository;

@CrossOrigin
@Controller

public class BookRestController {

    @Autowired
    private BookRepository repository;

    @GetMapping("/booklist")
    public @ResponseBody List<Book> bookListRest() {
        return (List<Book>) repository.findAll();
    }

    @GetMapping("/books/{id}")
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
        return repository.findById(bookId);
    }

    @PostMapping("/booklist")
    public @ResponseBody Book saveBookRest(@RequestBody Book book){
        return repository.save(book);
    }
}

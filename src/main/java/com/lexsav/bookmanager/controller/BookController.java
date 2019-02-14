package com.lexsav.bookmanager.controller;

import com.lexsav.bookmanager.model.Book;
import com.lexsav.bookmanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/books/add")
    public String createBook(Model model) {
        model.addAttribute("book", new Book());
        return "edit";
    }

    @PostMapping("/books")
    public String saveBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }

    @GetMapping("/books/search")
    public String searchBooks(Model model, @RequestParam(name = "id") String search) {
        List<Book> foundBooks = bookRepository.findByTitleIgnoreCase(search);
        model.addAttribute("books", foundBooks);
        return "books";
    }

    @GetMapping("/books/read/{id}")
    public String readBook(@PathVariable(name = "id") Integer id) {
        Book book = bookRepository.getOne(id);
        if (!book.getFinished()) {

            book.setFinished(true);
            bookRepository.save(book);
        }
        return "redirect:/books";
    }

    @GetMapping("books/edit/{id}")
    public String editBook(Model model, @PathVariable(name = "id") Integer id) {
        model.addAttribute("book", bookRepository.getOne(id));
        return "edit";
    }

    @GetMapping("books/delete/{id}")
    public String deleteBook(@PathVariable(name = "id") Integer id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}

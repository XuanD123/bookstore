package backend.bookstore.web;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/index")
    public String books(Model model) {
        
        Book book = new Book("Suomen luonto", "Leena Nieminen", 2025, "1234567890", 25.50);
        model.addAttribute("book", book);
        return "index"; 
    }
    // Kaikkien kirjojen näyttäminen taulukossa
    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist"; // viittaa booklist.html:ään templates-kansiossa
    }
}




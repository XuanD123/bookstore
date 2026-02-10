package backend.bookstore.web;

import backend.bookstore.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @GetMapping("/index")
    public String books(Model model) {
        
        Book book = new Book("Suomen luonto", "Leena Nieminen", 2025, "1234567890", 25.50);
        model.addAttribute("book", book);
        return "index"; 
    }
}

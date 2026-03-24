package backend.bookstore.web;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import backend.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
    private final BookRepository repository;

    private final CategoryRepository categoryRepository;

    public BookController(BookRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
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
    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll()); 
        return "addbook";
    }
    @PostMapping("/addbook")
    public String addBookSubmit(@ModelAttribute Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }
    /*@GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/booklist";
    }*/
     // Delete book
    @GetMapping("/books/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
        return "redirect:/booklist";
    }
   

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));

        model.addAttribute("book", book);
        model.addAttribute("categories", categoryRepository.findAll()); 
        return "editbook";
    }
    @PostMapping("/save")
    public String save(Book book) {
    repository.save(book);
    return "redirect:/booklist";
    }
        
}




package backend.bookstore.web;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;

import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/custombooks") //tehtävä 2, muutin api/books -> api/custombooks 
                                    //tai kommentoi pois koko bookrestcontroller
public class BookRestController {

    private final BookRepository repository;

    public BookRestController(BookRepository repository) {
        this.repository = repository;
    }

    // a) Palauta kaikki kirjat JSON-muodossa
    @GetMapping
    public Iterable<Book> getAllBooks() {
        return repository.findAll();
    }

    // b) Palauta kirja id:n perusteella JSON-muodossa
    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return repository.findById(id);
    }
}
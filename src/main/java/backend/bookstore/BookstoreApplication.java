package backend.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;
import backend.bookstore.domain.Category;
import backend.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
    

@Bean
public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository cRepository) {
    return (args) -> {
        log.info("Save some categories");

        // --- Lisää kategoriat ---
        Category scifi = cRepository.save(new Category("SciFi"));
        Category comic = cRepository.save(new Category("Comic"));
        Category fantasy = cRepository.save(new Category("Fantasy"));


        log.info("Save some sample books");

        // --- Lisää kirjat kategoriatiedolla ---
        brepository.save(new Book("Clean Code", "Robert C. Martin", 2008, "9780132350884", 45.0, fantasy));
        brepository.save(new Book("Effective Java", "Joshua Bloch", 2018, "9780134685991", 50.0, scifi));
        brepository.save(new Book("Spring in Action", "Craig Walls", 2022, "9781617297571", 55.0, comic));
        

        log.info("Fetch all the categories");
			for (Category category : cRepository.findAll()) {
				log.info(category.toString());
			}

			log.info("Fetch all the books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

        };
    }
}


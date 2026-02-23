package backend.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
    public CommandLineRunner demo(BookRepository repository) {
        return (args) -> {

            repository.save(new Book("Clean Code", "Robert C. Martin", 2008, "9780132350884", 45.0));
            repository.save(new Book("Effective Java", "Joshua Bloch", 2018, "9780134685991", 50.0));
            repository.save(new Book("Spring in Action", "Craig Walls", 2022, "9781617297571", 55.0));

            repository.findAll().forEach(book -> {
                System.out.println(book.toString());
            });
        };
    }
}


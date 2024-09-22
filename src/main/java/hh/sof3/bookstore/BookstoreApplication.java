package hh.sof3.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof3.bookstore.domain.Book;
import hh.sof3.bookstore.domain.BookRepository;
import hh.sof3.bookstore.domain.Category;
import hh.sof3.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CategoryRepository categoryRepository, BookRepository bookRepository) {
		return (args) -> {

			Category oppikirja = new Category("oppikirja");
			Category keittokirja = new Category("keittokirja");
			categoryRepository.save(oppikirja);
			categoryRepository.save(keittokirja);

			bookRepository.save(new Book("Robotiikka ja tekoäly", "Pasi Hänninen", 2022, "9789527394120", 42.95, oppikirja));
			bookRepository
					.save(new Book("Nordic Winter Cookbook", "Viola Minerva Virtamo", 2021, "9789527381465", 24.95, keittokirja));

			categoryRepository.findAll().forEach(category -> System.out.println(category.toString()));
			bookRepository.findAll().forEach(book -> System.out.println(book.toString()));
		};
	}
}
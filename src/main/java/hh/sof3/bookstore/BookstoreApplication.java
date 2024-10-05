package hh.sof3.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof3.bookstore.domain.AppUser;
import hh.sof3.bookstore.domain.AppUserRepository;
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
	public CommandLineRunner demo(CategoryRepository categoryRepository, BookRepository bookRepository, AppUserRepository appUserRepository) {
		return (args) -> {

			Category oppikirja = new Category("oppikirja");
			Category keittokirja = new Category("keittokirja");
			categoryRepository.save(oppikirja);
			categoryRepository.save(keittokirja);

			bookRepository
					.save(new Book("Robotiikka ja tekoäly", "Pasi Hänninen", 2022, "9789527394120", 42.95, oppikirja));
			bookRepository
					.save(new Book("Nordic Winter Cookbook", "Viola Minerva Virtamo", 2021, "9789527381465", 24.95,
							keittokirja));

			AppUser user1 = new AppUser("Arttu", "arttu.aarnio@myy.haaga-helia.fi",
					"$2a$10$EnZmwA1lEuDa51Dty1/YPOwp/cM3UV4DV91Zk2oh5j6HM95Cu/IlC", "ADMIN");
			AppUser user2 = new AppUser("user", "user@user.com",
					"$2a$10$sxcN.FT1CfuaoOX1sdrO7uyGEKJq1mmIxgPhFKX5Ovrvgg8kVBNAW",
					"USER");
			appUserRepository.save(user1);
			appUserRepository.save(user2);

			categoryRepository.findAll().forEach(category -> System.out.println(category.toString()));
			bookRepository.findAll().forEach(book -> System.out.println(book.toString()));

		};
	}
}
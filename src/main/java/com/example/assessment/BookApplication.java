package com.example.assessment;

import com.example.assessment.model.Author;
import com.example.assessment.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaAuditing
public class BookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

//	@Component
//	public class StartUpRunner implements CommandLineRunner {
//		@Autowired
//		private AuthorRepository authorRepository;
//		@Override
//		public void run(String... args) throws Exception {
//			authorRepository.saveAll(Arrays.asList(
//					Author.builder().name("Author Name A").build(),
//					Author.builder().name("Author Name B").build(),
//					Author.builder().name("Author Name C").build()
//			));
//			System.out.println(authorRepository.count());
//		}
//	}
}
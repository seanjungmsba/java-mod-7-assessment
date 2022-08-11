package com.example.assessment;

import com.example.assessment.model.jay.Activity;
import com.example.assessment.repository.jay.ActivityRepository;
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
//
//		@Autowired
//		private ActivityRepository activityRepository;
//		@Override
//		public void run(String... args) throws Exception {
//			System.out.println("Running");
//			activityRepository.saveAll(Arrays.asList(
//					Activity.builder().name("Archery").difficulty(3).build(),
//					Activity.builder().name("Hatchet Throwing").difficulty(5).build(),
//					Activity.builder().name("Firestarting").difficulty(2).build()
//			));
//			System.out.println(activityRepository.count());
//		}
//	}
}

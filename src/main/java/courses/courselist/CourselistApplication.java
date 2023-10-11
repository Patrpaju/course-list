package courses.courselist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import courses.courselist.model.AppUser;

import courses.courselist.model.AppUserRepository;
import courses.courselist.model.Category;
import courses.courselist.model.CategoryRepository;
import courses.courselist.model.Course;
import courses.courselist.model.CourseRepository;

@SpringBootApplication
public class CourselistApplication {
	private static final Logger log = LoggerFactory.getLogger(CourselistApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CourselistApplication.class, args);
	}

	@Bean
	public CommandLineRunner courseDemo(CourseRepository repository, CategoryRepository crepository,
			AppUserRepository urepository) {
		return (args) -> {
		log.info("Adding some categories");
		crepository.save(new Category("Ict and Business"));
		crepository.save(new Category("Programming"));
		repository.save(new Course("Programming 1", "SOF005AS2AE", 5, 5.0, crepository.findByName("Programming").get(0)));
		repository.save(new Course("Business Intelligence", "ICB005AS2A", 5, 5.0, crepository.findByName("Ict and Business").get(0)));
		
		// Create users: admin/admin user/user
					AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
					AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
							"ADMIN");
					urepository.save(user1);
					urepository.save(user2);
					
					log.info("Fetch all courses");
					for (Course course : repository.findAll()) {
						log.info(course.toString());
		}

	};
}
}
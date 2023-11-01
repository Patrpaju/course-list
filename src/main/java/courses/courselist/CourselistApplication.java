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
		crepository.save(new Category("Key Competencies"));
		repository.save(new Course("Programming 1", "SOF005AS2AE-3001", 5, 4, crepository.findByName("Programming").get(0)));
		repository.save(new Course("Programming 2", "SOF005AS2AE-3001", 5, 5, crepository.findByName("Programming").get(0)));
		repository.save(new Course("Data Management and Databases", "SOF001AS2AE-3001", 5, 4, crepository.findByName("Programming").get(0)));
		repository.save(new Course("Python programming", "Summer 2003", 5, 4, crepository.findByName("Programming").get(0)));
		repository.save(new Course("Business Intelligence", "ICB005AS2A-3003", 5, 5, crepository.findByName("Ict and Business").get(0)));
		repository.save(new Course("Business Process Management", "ICB005AS2A-3003", 5, 5, crepository.findByName("Ict and Business").get(0)));
		repository.save(new Course("Excel and BI", "ANA001AS3A-3012", 5, 5, crepository.findByName("Ict and Business").get(0)));
		repository.save(new Course("Advanced Course in Excel", "ANA002AS2A-3009", 5, 5, crepository.findByName("Ict and Business").get(0)));
		repository.save(new Course("Professional English", "ENG001HH1AE-3026", 5, 3,crepository.findByName("Key Competencies").get(0)));
		repository.save(new Course("Teamwork and Project Management", "HRL001HH1A-3040", 5, 4,crepository.findByName("Key Competencies").get(0)));
		repository.save(new Course("Entrepreneurship and Business Operations", "ENT001HH1A-3038", 5, 5,crepository.findByName("Key Competencies").get(0)));
		repository.save(new Course("Basics of Financial Management", "ECO001HH1A-3045", 5, 5,crepository.findByName("Key Competencies").get(0)));
		repository.save(new Course("Customer Insight and Marketing", "MAR001HH1A-3115", 5, 3,crepository.findByName("Key Competencies").get(0)));
		repository.save(new Course("Professional Communication", "OM001HH1A-3043", 5, 5,crepository.findByName("Key Competencies").get(0)));
		repository.save(new Course("ICT-competencies", "ICB001HH1A", 5, 5,crepository.findByName("Key Competencies").get(0)));
		
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
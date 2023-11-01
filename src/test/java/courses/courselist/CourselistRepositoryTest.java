package courses.courselist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import courses.courselist.model.AppUser;
import courses.courselist.model.AppUserRepository;
import courses.courselist.model.Category;
import courses.courselist.model.CategoryRepository;
import courses.courselist.model.Course;
import courses.courselist.model.CourseRepository;


//@ExtendWith(SpringExtension.class)
@DataJpaTest

// @ExtendWith(SpringExtension.class)
// @SpringBootTest(classes = BookstoreApplication.class)
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourselistRepositoryTest {

	@Autowired
	private CourseRepository repository;

	@Autowired
	private CategoryRepository crepository;
	
	@Autowired
	private AppUserRepository arepository;
	
	// test that you can find course by name
	@Test
	public void findByName() {
		List<Course> courses = repository.findByName("Programming 1");
		assertThat(courses).hasSize(1);
		assertThat(courses.get(0).getCode()).isEqualTo("SOF005AS2AE-3001");
	}
	
	// test that adding new course works
	@Test
	public void createNewCourse() {
		Course course = new Course("Java Programming", "123-A", 5, 3, new Category("Programming"));
		repository.save(course);
		assertThat(course.getId()).isNotNull();
	}
	
	// test that you can delete a course
	@Test
	public void deleteNewCourse() {
		List<Course> courses = repository.findByName("Programming 1");
		Course course = courses.get(0);
		repository.delete(course);
		List<Course> newCourses = repository.findByName("Programming 1");
		assertThat(newCourses).hasSize(0);
	}
	
	// test that you can find category by name
	@Test
	public void findByCategory() {
		List<Category> categories = crepository.findByName("Programming");
		assertThat(categories).hasSize(1);
	}
	
	// test that you can create new category
	@Test
	public void createNewCategory() {
		Category category = new Category("Keys to Studies and Career");
		crepository.save(category);
		assertThat(category.getCategoryid()).isNotNull();
	}

	// test that you can delete category
	@Test
	public void deleteNewCategory() {
		List<Category> categories = crepository.findByName("Programming");
		Category category = categories.get(0);
		crepository.delete(category);
		List<Category> newCategories = crepository.findByName("Programming");
		assertThat(newCategories).hasSize(0);
	}
	
	// test that you can find user by username
	@Test
	public void findByAppUser() {
		AppUser Appuser = arepository.findByUsername("user");
		assertThat(Appuser).isNotNull();
	}
	
	// test that you can create new user
	@Test
	public void createNewAppUser() {
		AppUser appUser = new AppUser("test", "test", "test");
		arepository.save(appUser);
		assertThat(appUser.getUsername()).isNotNull();
	}
	
	// test that you can delete user
	@Test 
	public void deleteNewAppUser() {
		AppUser appUser = arepository.findByUsername("user");
		arepository.delete(appUser);
		AppUser newappUser = arepository.findByUsername("user");
		assertThat(newappUser).isNull();
		
	}
}


package courses.courselist.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import courses.courselist.model.Category;
import courses.courselist.model.CategoryRepository;
import courses.courselist.model.Course;
import courses.courselist.model.CourseRepository;

@Controller
public class CourseController {
	@Autowired
	private CourseRepository crepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/courselist")
	public String courseList(Model model) {
		model.addAttribute("courses", crepository.findAll());
		return "courselist";
	}

	@RequestMapping(value = "/save")
	public String addBook(Model model) {
		model.addAttribute("course", new Course());
		model.addAttribute("categorys", categoryRepository.findAll());
		return "addcourse";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Course course) {
		crepository.save(course);
		return "redirect:save";
	}

	@RequestMapping(value = "/ict")
	public String getCoursesByCategory(Model model) {
		Long categoryId = 1L;
		Category ictCategory = categoryRepository.findByCategoryid(categoryId);
		List<Course> ictCourses = crepository.findByCategory(ictCategory);
		model.addAttribute("courses", ictCourses);
		return "ict";
	}

	@RequestMapping(value = "/programming")
	public String programmingCourses(Model model) {
		Long categoryId = 2L;
		Category ictCategory = categoryRepository.findByCategoryid(categoryId);
		List<Course> ictCourses = crepository.findByCategory(ictCategory);
		model.addAttribute("courses", ictCourses);
		return "programming";
	}
	@RequestMapping(value = "/key")
	public String keyCompetencyCourses(Model model) {
		Long categoryId = 3L;
		Category ictCategory = categoryRepository.findByCategoryid(categoryId);
		List<Course> ictCourses = crepository.findByCategory(ictCategory);
		model.addAttribute("courses", ictCourses);
		return "keyCompetencies";
	}
}

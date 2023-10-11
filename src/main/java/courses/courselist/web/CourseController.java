package courses.courselist.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import courses.courselist.model.CategoryRepository;
import courses.courselist.model.Course;
import courses.courselist.model.CourseRepository;

@Controller
public class CourseController {
	@Autowired
	private CourseRepository crepository;
	
	@Autowired 
	private CategoryRepository catrepository;
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/courselist")
	public String courseList(Model model) {
		model.addAttribute("courses", crepository.findAll());
		return "courselist";
	}
	
	@RequestMapping(value ="/programming")
	public String programmingCourses(Model model) {
		model.addAttribute("courses", catrepository.findAll());
		return "programming";
	}
	
	@RequestMapping(value = "/save")
	public String addBook(Model model) {
		model.addAttribute("course", new Course());
		model.addAttribute("categorys", catrepository.findAll());
		return "addcourse";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Course course) {
		crepository.save(course);
		return "redirect:save";
	}
	@RequestMapping(value = "/ict")
	public String ictCourses(Model model) {
		List<Course> ictCourses = crepository.findByName("Ict and Business");
		model.addAttribute("courses", ictCourses);
		return "ict";
	}
}

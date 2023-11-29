package courses.courselist.web;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

	//List of all courses
	@RequestMapping(value = "/courselist")
	public String courseList(Model model) {
		// find all courses
		model.addAttribute("courses", crepository.findAll());
		model.addAttribute("categorys", categoryRepository.findAll());
		List<Course> allCourses = (List<Course>) crepository.findAll();
		
		// Call the method to calculate the average
		double averageGrade = calculateAverageGrade(allCourses);
		model.addAttribute("averageGrade", averageGrade);
		
		// Call the method to calculate total credits
		int totalCredits = calculateTotalCredits(allCourses);
		model.addAttribute("totalCredits", totalCredits);
		
		// Calculate how many percent of 210 credits is currently complete
		double creditsPercentage = (totalCredits / 210.0) * 100;
		DecimalFormat df = new DecimalFormat("#.##");
		String formattedPercentage = df.format(creditsPercentage);
		model.addAttribute("percent", formattedPercentage);
		
		return "courselist";
	}
	
	// how to add new course
	@RequestMapping(value = "/save")
	public String addCourse(Model model) {
		model.addAttribute("course", new Course());
		model.addAttribute("categorys", categoryRepository.findAll());
		List<Course> allCourses = (List<Course>) crepository.findAll();
		
		// Call the method to calculate the average
		double averageGrade = calculateAverageGrade(allCourses);
		model.addAttribute("averageGrade", averageGrade);
		return "addcourse";
	}

	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Course course) {
		crepository.save(course);
		return "redirect:save";
	}
	
	// delete course for admins
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long CourseId, Model model) {
		crepository.deleteById(CourseId);
		return "redirect:../courselist";
	}
	
	// edit course for admins
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String saveBook(@PathVariable("id") Long CourseId, Model model) {
		model.addAttribute("course", crepository.findById(CourseId));
		model.addAttribute("categorys", categoryRepository.findAll());
		return "editcourse";
	}
	
	// show only ICT and business courses
	@RequestMapping(value = "/ict")
	public String getCoursesByCategory(Model model) {
		// find category by ID
		Long categoryId = 1L;
		Category ictCategory = categoryRepository.findByCategoryid(categoryId);
		List<Course> ictCourses = crepository.findByCategory(ictCategory);
		model.addAttribute("courses", ictCourses);
		int totalCredits = calculateTotalCredits(ictCourses);
		model.addAttribute("totalCredits", totalCredits);
		return "ict";
	}

	// Show only programming courses
	@RequestMapping(value = "/programming")
	public String programmingCourses(Model model) {
		// find category by ID
		Long categoryId = 2L;
		Category ictCategory = categoryRepository.findByCategoryid(categoryId);
		List<Course> ictCourses = crepository.findByCategory(ictCategory);
		model.addAttribute("courses", ictCourses);
		int totalCredits = calculateTotalCredits(ictCourses);
		model.addAttribute("totalCredits", totalCredits);
		return "programming";
	}

	// Show only key competency courses
	@RequestMapping(value = "/key")
	public String keyCompetencyCourses(Model model) {
		// find category by ID
		Long categoryId = 3L;
		Category ictCategory = categoryRepository.findByCategoryid(categoryId);
		List<Course> ictCourses = crepository.findByCategory(ictCategory);
		model.addAttribute("courses", ictCourses);
		int totalCredits = calculateTotalCredits(ictCourses);
		model.addAttribute("totalCredits", totalCredits);
		return "keyCompetencies";
	}

	// Show average grade
	@RequestMapping(value = "/averageGrade")
	public String calculateAveragGrade(Model model) {
		List<Course> allCourses = (List<Course>) crepository.findAll();
		
		// Call the method to calculate the average
		double averageGrade = calculateAverageGrade(allCourses);
		model.addAttribute("averageGrade", averageGrade);
		return "averageGrade";
	}

	// method to calculate average grade
	private double calculateAverageGrade(List<Course> courses) {
		// assign the totalGrade to 0.0 at start
		double totalGrade = 0.0;
		// calculate the average

		for (Course course : courses) {
			totalGrade += course.getGrade();
		}

		double average = totalGrade / courses.size();

		// Format the average to 2 decimal places
		DecimalFormat df = new DecimalFormat("#.00");
		String formattedAverage = df.format(average);

		return Double.parseDouble(formattedAverage.replace(",", "."));
	}
	
	//method to calculate total credits
	public int calculateTotalCredits(List<Course> courses) {
		// assign total to 0 at start
		int totalCredits = 0;
		
		// iterate the courses and add up all credits
		for (Course course : courses) {
	        totalCredits += course.getCredits();
	    }
		return totalCredits;
	}
}

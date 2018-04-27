package temakereso.restcontroller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import temakereso.entity.Category;
import temakereso.entity.Supervisor;
import temakereso.helper.TopicStatus;
import temakereso.helper.TopicType;
import temakereso.service.CategoryService;
import temakereso.service.SupervisorService;

@RestController
@RequestMapping(value = "/api/constants")
public class ConstantController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SupervisorService supervisorService;
	
	// ------------------------ GET -------------------------- //
	
	@GetMapping(path = "/types")
	public ResponseEntity<List<TopicType>> getTypes() {
		List<TopicType> types = Arrays.asList(TopicType.values());
		return new ResponseEntity<>(types, HttpStatus.OK);
	}
	
	@GetMapping(path = "/statuses")
	public ResponseEntity<List<TopicStatus>> getStatuses() {
		List<TopicStatus> statuses = Arrays.asList(TopicStatus.values());
		return new ResponseEntity<>(statuses, HttpStatus.OK);
	}
	
	@GetMapping(path = "/categories")
	public ResponseEntity<List<Category>> getCategories() {
		List<Category> categories = categoryService.getAll();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@GetMapping(path = "/supervisors")
	public ResponseEntity<List<Supervisor>> getSupervisors() {
		List<Supervisor> supervisors = supervisorService.getAll();
		return new ResponseEntity<>(supervisors, HttpStatus.OK);
	}


	// ------------------------ POST -------------------------- //
	
	@PostMapping(path = "/categories")
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		Category savedCategory = categoryService.createCategory(category);
		return new ResponseEntity<>(savedCategory, HttpStatus.OK);
	}
	
	// ------------------------ PUT -------------------------- //
	
	// TODO put all as a list
	
//	@PostMapping(path = "/categories")
//	public ResponseEntity<Category> modifiyCategories(@RequestBody Category category) {
//		Category savedCategory = categoryService.createCategory(category);
//		return new ResponseEntity<>(savedCategory, HttpStatus.OK);
//	}

}

package net.guides.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.guides.exception.ResourceNotFoundException;
import net.guides.bean.entity.Employee;
import net.guides.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EmployeeController {
		
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.findAll();
	}

//	@Cacheable(value="employees", key="#id")
//	@GetMapping("/employees/{id}")
//	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id)
//			throws ResourceNotFoundException {
//		Employee employee = employeeRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
//		return ResponseEntity.ok().body(employee);
//	}
	
	@Cacheable(value="employees", key="#id")
	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable(value = "id") String id)
			throws ResourceNotFoundException {
		Employee employee = employeeService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
		return employee;
	}

	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.save(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") String employeeId,
			@RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeService.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

//		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = employeeService.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@CacheEvict(value="employees", key="#id")
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") String id)
			throws ResourceNotFoundException {
		Employee employee = employeeService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

		employeeService.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	
	@GetMapping("/test")
	public List<Employee> searchByIdorName() {
		return employeeService.searchByIdorName("108", "zhang18");
	}
	
	@GetMapping("/demo")
	public void demo() {
		employeeService.demo();
	}
	
	@GetMapping("/in")
	public List<Employee> in() {
		List<String> ids=new ArrayList<String>();
		ids.add("400");
		ids.add("300");
		return employeeService.searchByIn(ids);
	}
	
	
	@GetMapping("/page")
	public Page<Employee> page() {
		return employeeService.spage();
	}
	
	
}

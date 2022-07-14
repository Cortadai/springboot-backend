package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;

// @RestController combines @Controller and @ResponseBody, which eliminates
// the need to annotate every request with @ResponseBody annotation
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Value("${my.message}")
	private String message;

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	// build - create employee REST API
	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployee(
			@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}

	// build - get all employees REST API
	@GetMapping("/all")
	public List<Employee> getAllEmployees(){
		System.out.println("Message stored in properties: " + message);
		return employeeService.getAllEmployees();
	}
	
	// build - get employee by id REST API
	@GetMapping("/single/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}
	
	// build - update employee REST API
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployee(
			@RequestBody Employee employee,
			@PathVariable("id") long employeeId){
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, employeeId), HttpStatus.OK);
	}
	
	// build - delete employee REST API
	@DeleteMapping("/del/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") long employeeId){
		String message = "Employee with id: " + employeeId + " deleted successfully";
		HttpStatus status = HttpStatus.OK;
		try {
			employeeService.deleteEmployee(employeeId);
		} catch(ResourceNotFoundException resException) {
			message = resException.getMessage();
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<String>(message, status);
	}
}

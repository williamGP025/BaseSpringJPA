package com.example.demo.crud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.crud.exceptions.ResourceNotFoundException;
import com.example.demo.crud.models.Employee;
import com.example.demo.crud.services.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAll();
	}

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.create(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		return ResponseEntity.ok(employeeService.modify(employeeId, employeeDetails));
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", employeeService.delete(employeeId));
		return response;
	}
}
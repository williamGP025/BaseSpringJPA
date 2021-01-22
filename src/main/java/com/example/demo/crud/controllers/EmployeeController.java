package com.example.demo.crud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.crud.entities.Employee;
import com.example.demo.crud.exceptions.BadRequestException;
import com.example.demo.crud.exceptions.ResourceNotFoundException;
import com.example.demo.crud.services.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping()
	public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee, BindingResult brBindingResult)
			throws BadRequestException {
		if (brBindingResult.hasErrors()) {
			throw BadRequestException.builder().message("No se puede crear la entidad")
					.errors(brBindingResult.getFieldErrors()).build();
		}
		return ResponseEntity.ok(employeeService.create(employee));
	}

	@GetMapping()
	public List<Employee> read() {
		return employeeService.getAll();
	}

	@PutMapping("{id}")
	public ResponseEntity<Employee> update(@Valid @PathVariable(value = "id") Long employeeId,
			@RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		return ResponseEntity.ok(employeeService.modify(employeeId, employeeDetails));
	}

	@DeleteMapping("{id}")
	public Map<String, Boolean> delete(@Valid @PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", employeeService.delete(employeeId));
		return response;
	}
}
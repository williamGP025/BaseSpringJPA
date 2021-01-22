package com.example.demo.crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.crud.entities.Employee;
import com.example.demo.crud.exceptions.ResourceNotFoundException;
import com.example.demo.crud.repository.IEmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private IEmployeeRepository employeeRepository;

	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	public Employee create(Employee data) {
		return employeeRepository.save(data);
	}

	public Employee modify(Long employeeId, Employee data) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmailId(data.getEmailId());
		employee.setLastName(data.getLastName());
		employee.setFirstName(data.getFirstName());
		return employeeRepository.save(employee);
	}

	public boolean delete(Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		employeeRepository.delete(employee);
		return true;
	}
}
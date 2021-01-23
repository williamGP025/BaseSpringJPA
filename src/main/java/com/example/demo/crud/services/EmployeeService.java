package com.example.demo.crud.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.crud.entities.Employee;
import com.example.demo.crud.entities.HistoryEmployee;
import com.example.demo.crud.exceptions.BadRequestException;
import com.example.demo.crud.exceptions.ResourceNotFoundException;
import com.example.demo.crud.repository.IEmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private IEmployeeRepository _repo;
	private final String MESSAGE = "No se encontraron registros para el id: %s";

	public List<Employee> getAll() {
		return _repo.findAll();
	}

	public Employee create(Employee data) throws BadRequestException {
		// #region [Aplicando principio de IDEMPOTENCIA]
		boolean find2 = _repo.findByEmailId(data.getEmailId()).size() > 0;
		if (find2) {
			throw BadRequestException.builder().message(
					String.format("El correo: %s ya se encuentra registrado en la base de datos", data.getEmailId()))
					.build();
		}
		// #endregion
		List<HistoryEmployee> historyEmployees = new ArrayList<>();
		historyEmployees.add(HistoryEmployee.builder().description("creacion de la entidad").build());
		data.setHistoryEmployees(historyEmployees);
		return _repo.save(data);
	}

	public Employee modify(Long employeeId, Employee data) throws ResourceNotFoundException {
		Employee employee = _repo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(MESSAGE, employeeId)));

		employee.setEmailId(data.getEmailId());
		employee.setLastName(data.getLastName());
		employee.setFirstName(data.getFirstName());
		return _repo.save(employee);
	}

	public boolean delete(Long employeeId) throws ResourceNotFoundException {
		Employee employee = _repo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(MESSAGE, employeeId)));
		_repo.delete(employee);
		return true;
	}
}
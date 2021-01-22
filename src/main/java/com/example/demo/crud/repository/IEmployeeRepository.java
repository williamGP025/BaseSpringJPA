package com.example.demo.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.crud.entities.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
}
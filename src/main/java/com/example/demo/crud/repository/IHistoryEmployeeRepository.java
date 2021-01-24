package com.example.demo.crud.repository;

import com.example.demo.crud.entities.HistoryEmployee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistoryEmployeeRepository extends JpaRepository<HistoryEmployee, Long> {

}
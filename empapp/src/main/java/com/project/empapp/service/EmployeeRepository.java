package com.project.empapp.service;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.empapp.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//	List<Employee> findAll();

	

}

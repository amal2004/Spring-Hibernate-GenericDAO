package com.generic.hibdao.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.generic.hibdao.model.Employee;

public interface EmployeeService {

	public abstract List<Employee> getAllEmployees();

	/**
	 * Add new employee if it in not already exists
	 * 
	 * @param employee: Employee to add
	 */
	public abstract void addNewEmployee(Employee employee);

	/**
	 * Return maximum salary given to any employee
	 * 
	 * @return max salary
	 */
	public abstract Integer getMaxSalary();

}
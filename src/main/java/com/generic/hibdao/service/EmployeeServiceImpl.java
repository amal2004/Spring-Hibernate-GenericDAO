package com.generic.hibdao.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.hibdao.dao.EmployeeDao;
import com.generic.hibdao.model.Employee;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService  {
	
	final static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);


	@Autowired
	EmployeeDao employeeDao;


	public List<Employee> getAllEmployees() {
		logger.debug("Getting all employees...");
		return employeeDao.findAll();
	}

	/**
	 * Add new employee if it in not already exists
	 * 
	 * @param employee: Employee to add
	 */
	@Transactional(readOnly = false)
	public void addNewEmployee(Employee employee) {
		Employee emp = new Employee();
		emp.setFirstname(employee.getFirstname());
		emp.setLastname(employee.getLastname());
		List<Employee> emplList = employeeDao.findAllByExample(emp);
		if (emplList == null || emplList.isEmpty()) {
			Long id = (Long) employeeDao.save(employee);
			logger.debug("Id of new Employee " + id);
		} else {
			logger.debug("Employee " + emp + " already exists");
		}
		
	}

	/**
	 * Return maximum salary given to any employee
	 * 
	 * @return max salary
	 */
	public Integer getMaxSalary() {
		return employeeDao.getMaxSalary();
	}
	
	
	
}

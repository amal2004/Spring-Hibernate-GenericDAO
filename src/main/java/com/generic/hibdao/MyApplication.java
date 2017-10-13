package com.generic.hibdao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.generic.hibdao.model.Employee;
import com.generic.hibdao.service.EmployeeService;

@Component
public class MyApplication {
	
	final static Logger logger = Logger.getLogger(MyApplication.class);

	@Autowired
	private EmployeeService employeeService;
	
	
	public void performDbTasks()
	{
		// Get all employees
		List<Employee> employeeList = employeeService.getAllEmployees();
		printEmployees(employeeList);
		Employee empNew = new Employee("Amal", "Tree", "Senior Developer", 42000);
		
		// Save new employee
		employeeService.addNewEmployee(empNew);

		// Get all employees - to check added employee
		employeeList = employeeService.getAllEmployees();
		printEmployees(employeeList);

		Integer maxSalary = employeeService.getMaxSalary();
		logger.info("Maximum salary given to employee is: " + maxSalary);
	}
	
	
	private void printEmployees(List<Employee> emplist) {
		if (emplist != null) {
			logger.debug("Found total " + emplist.size() + " records.");
			for (Employee employee : emplist) {
				logger.info(employee.toString());
			}
		}
	}
}

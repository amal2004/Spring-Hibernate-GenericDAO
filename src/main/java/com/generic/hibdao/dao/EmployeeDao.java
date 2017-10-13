package com.generic.hibdao.dao;

import com.generic.hibdao.model.Employee;

public interface EmployeeDao extends GenericDao<Employee> {
	Integer getMaxSalary();
}

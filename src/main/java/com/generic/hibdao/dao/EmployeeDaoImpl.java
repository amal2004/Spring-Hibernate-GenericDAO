package com.generic.hibdao.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.generic.hibdao.model.Employee;

@Repository
public class EmployeeDaoImpl extends AbstractGenericDao<Employee> implements EmployeeDao {

	public Integer getMaxSalary() {
		Criteria criteria = getSession().createCriteria(Employee.class).setProjection(Projections.max("salary"));
		Integer maxSalary = (Integer) criteria.uniqueResult();
		return maxSalary;
	}

}

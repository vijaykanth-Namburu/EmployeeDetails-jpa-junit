package com.employee.service;

import java.util.List;

import com.employee.Exception.NoDataException;
import com.employee.mode.Employee;

public interface EmployeeServiceMethods {
	
	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(long id) throws NoDataException;
	
	Employee updateEmployee(Employee employee,long id)throws Exception;
	
	String deleteEmployeeById(long id);
	
	Employee createEmployee(Employee employee);

	List<Employee> createListEmployees(List<Employee> employee);





	List<Employee> updateListEmployees(List<Employee> employee) throws Exception;

	


}

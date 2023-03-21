package com.employee.service;
import java.util.stream.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.Exception.NoDataException;
import com.employee.dao.EmployeeRepository;
import com.employee.mode.Employee;

@Service
public class EmployeeService implements EmployeeServiceMethods{
	
	@Autowired
	private EmployeeRepository employeerepository;

	
	@Override
	public List<Employee> getAllEmployees() {
		return employeerepository.findAll();
		
	}


	@Override
	public Employee createEmployee(Employee employee) {
			return employeerepository.save(employee);
	}




	@Override
	public Employee getEmployeeById(long id) throws NoDataException {
		Optional<Employee> employee =	employeerepository.findById(id);

		if(employee.isPresent()) {
			return employee.get();
		}else {
			return null;
		}
	}




	@Override
	public String deleteEmployeeById(long id) {

		Optional<?> del=employeerepository.findById(id);
		if(del.isPresent()) {
			employeerepository.deleteById(id);
			return "Deleted account" ;
			
		}else {
			return null;
		}
	}




	@Override
	public Employee updateEmployee(Employee employee, long id) throws Exception {
		Optional<Employee> emp=employeerepository.findById(employee.getId());
		if(emp.isPresent())
		{
		Employee newemp =emp.get();
		newemp.setId(employee.getId());
		newemp.setFirstname(employee.getFirstname()); 
		newemp.setLastname(employee.getLastname());
		newemp.setAge(employee.getAge());
		return employeerepository.save(newemp);
		}else {

			return null;
		}
	}


	@Override
	public List<Employee> createListEmployees(List<Employee> employee) {

		return employeerepository.saveAll(employee);
	}


	@Override
	public List<Employee> updateListEmployees(List<Employee> employee) throws Exception {
		
		return employee.stream().map(u ->{
			try {
				return updateEmployee(u,u.getId());
			} catch (Exception e) {

				e.printStackTrace();
			}
			return u;
		}).collect(Collectors.toList());
				//forEach(u ->updateEmployee(u,u.getId())).;
		
		
		
		
		
		
		
		/*Optional<Employee> emp1=employeerepository.findById(employee.getId());
		if(emp1.isPresent())
		{
		Employee newemp1 =emp1.get();
		newemp1.setId(employee.getId());
		newemp1.setFirstname(employee.getFirstname()); 
		newemp1.setLastname(employee.getLastname());
		newemp1.setAge(employee.getAge());
		return employeerepository.save(newemp1);
		}else {

			return null;
		}
		

	
		/*ArrayList<Employee> employees = new ArrayList<Employee>();
		
		for(Employee em :employees) {
			
			if(em.getId() ==  employee.getId())
				employees.set(employees.indexOf(em),employee);
		
		}
		return employees;
		
		}
		*
		*
		*/
		
		
	

	


	
		
	
	
}
	}	
	
	

	
	
	
	
	
	


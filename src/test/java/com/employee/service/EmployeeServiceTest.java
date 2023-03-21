package com.employee.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.employee.Exception.NoDataException;
import com.employee.dao.EmployeeRepository;
import com.employee.mode.Employee;



@SpringBootTest(classes= {EmployeeServiceTest.class})
class EmployeeServiceTest {
	
	
	@InjectMocks
	private EmployeeService employeeservice;
	
	@Mock
	private EmployeeRepository employeerepository;
	
	
	
	
	@Test
	void testGetAllEmployees() {
		
		List<Employee> emp = new ArrayList<Employee>();
		emp.add(new Employee((long) 1,"vijay","kanth","23"));
		

		when(employeerepository.findAll()).thenReturn(emp);
		assertEquals(1,employeeservice.getAllEmployees().size());

		}

	
	@Test
	void testGetEmployeeById() throws NoDataException {
		Employee emp =new Employee(1,"vijay","kanth","23");
		
		long id = 1;
		
		when(employeerepository.findById(Mockito.anyLong())).thenReturn(Optional.of(emp));
		assertEquals(emp,employeeservice.getEmployeeById(id));
		
		when(employeerepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		assertEquals(null,employeeservice.getEmployeeById(id));
	}
	
	
	
	@Test
	void testDeleteEmployeeById() {
		
		Employee emp =new Employee(1,"vijay","kanth","23");
		
		long id = 1;
		
		when(employeerepository.findById(Mockito.anyLong())).thenReturn(Optional.of(emp));
		doNothing().when(employeerepository).deleteById(Mockito.anyLong());
		assertEquals("Deleted account",employeeservice.deleteEmployeeById(id));

		when(employeerepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		assertEquals(null,employeeservice.deleteEmployeeById(id));
	}
	
		
	
	@Test
	void testCreateEmployee() {
		
		Employee emp =new Employee(1,"vijay","kanth","23");
		
		when(employeerepository.save(emp)).thenReturn(emp);
		assertEquals(emp,employeeservice.createEmployee(emp));
		

	}

	
	@Test
	void testUpdateEmployee() throws Exception {

		Employee emp =new Employee(1,"vijay","kanth","23");
		
		when(employeerepository.findById(Mockito.anyLong())).thenReturn(Optional.of(emp));
		when(employeerepository.save(emp)).thenReturn(emp);
		assertEquals(emp,employeeservice.updateEmployee(emp, 1));

		
		when(employeerepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		assertEquals(null,employeeservice.updateEmployee(emp,1));
	}

	
	

	

}

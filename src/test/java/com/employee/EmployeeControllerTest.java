package com.employee;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.employee.Exception.NoDataException;
import com.employee.controller.EmployeeController;
import com.employee.mode.Employee;
import com.employee.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@ComponentScan(basePackages = "com.employee")
@ContextConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes= {EmployeeControllerTest.class})
class EmployeeControllerTest {

	
	@Autowired
	MockMvc mockmvc;
	
	
	@Mock
	private EmployeeService service;
	
	@InjectMocks
	private EmployeeController controller;
	
	
	@Autowired
	private ObjectMapper mapper;
	
	
	@BeforeEach
	public void setup()
	{
		mockmvc =MockMvcBuilders.standaloneSetup(controller).build();
		
	}

	@Test
	void testGetAllEmployees() throws Exception {
		
		List<Employee> emp = new ArrayList<Employee>();
		emp.add(new Employee((long) 1,"vijay","kanth","23"));
		when(service.getAllEmployees()).thenReturn(emp);
		this.mockmvc.perform(get("/employees"))
					.andExpect(status().isOk())
					.andDo(print());
		
	}
	
	@Test()
	void testGetEmployeeById() throws Exception{
		Employee emp =new Employee(1,"vijay","kanth","23");
		
		when(service.getEmployeeById(Mockito.anyLong())).thenReturn(emp);
		this.mockmvc.perform(get("/employees/{id}",1))
					.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))

					.andExpect(MockMvcResultMatchers.jsonPath(".firstname").value("vijay"))
					.andExpect(MockMvcResultMatchers.jsonPath(".lastname").value("kanth"))
					.andExpect(MockMvcResultMatchers.jsonPath(".age").value("23"))
					.andDo(print());
	} 
	
	
	@Test
	void testCreateEmployee() throws JsonProcessingException, Exception {

		Employee emp =new Employee(1,"vijay","kanth","23");
		
		when(service.createEmployee(Mockito.any(Employee.class))).thenReturn(emp);
	   	this.mockmvc.perform(post("/employees/create")
	   			.contentType(MediaType.APPLICATION_JSON_UTF8)
	   			.content(mapper.writeValueAsString(emp)))
	   			.andExpect(status().isOk())
	   			.andDo(print());	 
	}
	
	@Test
	void testDeleteEmployeeById() throws Exception {
		Employee emp =new Employee(1,"vijay","kanth","23");
		
		when(service.deleteEmployeeById(Mockito.anyLong())).thenReturn(null);
		this.mockmvc.perform(delete("/employees/{id}",1))
		.andExpect(status().isGone());

	}
	
	/*void testUpdateEmployee() throws JsonProcessingException, Exception {
		Employee emp =new Employee(1,"vijay","kanth","23");

		when(service.updateEmployee(Mockito.any(Employee.class))).thenReturn(emp);
		this.mockmvc.perform(put("/employees/update")
		   		.contentType(MediaType.APPLICATION_JSON_UTF8)
		   		.content(mapper.writeValueAsString(emp)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstname", is(emp.getFirstname())))
   				.andExpect(jsonPath("$.lastname", is(emp.getLastname())))
   				.andExpect(jsonPath("$.age", is(emp.getAge())))
   				.andDo(print());*/
				
} 




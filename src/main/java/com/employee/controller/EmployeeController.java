package com.employee.controller;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.Exception.NoDataException;
import com.employee.mode.Employee;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController
{
    @Autowired
    EmployeeService service; 
 
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees()
    {
    	List<Employee> emplist=service.getAllEmployees();
		return new ResponseEntity<List<Employee>>(emplist, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id)
                                                    throws Exception {
        Employee empl = service.getEmployeeById(id);
 
        return new ResponseEntity<Employee>(empl, new HttpHeaders(), HttpStatus.OK);
    }
    
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> UpdateEmployee(@PathVariable("id") long id,@RequestBody Employee employee)
                                                    throws Exception {
        Employee update = service.updateEmployee(employee, id);
        return new ResponseEntity<Employee>(update, new HttpHeaders(), HttpStatus.OK);
    }
   
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable("id") Long id)
                                                    throws NoDataException {
      String delete = service.deleteEmployeeById(id);
      return new ResponseEntity<Employee>(new HttpHeaders(),HttpStatus.GONE);

    }
    
  
    
    
   /* @DeleteMapping("/deleteall")
    public HttpStatus deleteEmployeeall()
                                                    throws NoDataException {
        service.deleteEmployeeById(0)
        return HttpStatus.GONE;
    }*/
    
    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
                                                    throws NoDataException {
    		
    		Employee create=service.createEmployee(employee);
			return new ResponseEntity<Employee>(create, new HttpHeaders(), HttpStatus.OK);
    }
    
    
    @PostMapping("/createemployees")
    public ResponseEntity<List<Employee>> createListEmployee(@RequestBody List<Employee> employee)
                                                    throws NoDataException {
    		
    		List<Employee> create1=service.createListEmployees(employee);
    		return new ResponseEntity<List<Employee>>(create1, new HttpHeaders(), HttpStatus.OK);
    }
    
   @PutMapping("/updateemployee")
    public ResponseEntity<List<Employee>> UpdateEmployees(@RequestBody List<Employee> employee)
                                                    throws Exception {
	  List<Employee> update1 = service.updateListEmployees(employee);
       return new ResponseEntity<List<Employee>>(update1, new HttpHeaders(), HttpStatus.OK);
       
  
    	 }
     }

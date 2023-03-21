package com.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.employee.mode.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long> {
 
}

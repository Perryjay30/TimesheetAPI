package com.codingchallenge.timesheetapi.data.repository;

import com.codingchallenge.timesheetapi.data.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

}

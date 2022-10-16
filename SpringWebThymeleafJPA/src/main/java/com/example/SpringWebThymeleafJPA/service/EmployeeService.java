package com.example.SpringWebThymeleafJPA.service;

import com.example.SpringWebThymeleafJPA.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployee(long id);
}

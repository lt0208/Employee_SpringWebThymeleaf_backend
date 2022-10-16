package com.example.SpringWebThymeleafJPA.service;

import com.example.SpringWebThymeleafJPA.model.Employee;
import com.example.SpringWebThymeleafJPA.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepo.findById(id).orElseThrow(RuntimeException::new);
        //return employeeRepo.findById(id); //if configure findById(id) in Repo with annotation
       /* Optional<Employee> optional = employeeRepo.findById(id);
        Employee employee = null;
        if (optional.isPresent()){
            employee = optional.get();
        }else {
            throw new RuntimeException("Employee not found");
        }
        return employee;*/
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepo.deleteById(id);
    }
}

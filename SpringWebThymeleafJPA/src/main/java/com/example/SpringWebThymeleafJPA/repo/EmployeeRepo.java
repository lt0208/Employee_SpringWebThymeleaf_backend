package com.example.SpringWebThymeleafJPA.repo;

import com.example.SpringWebThymeleafJPA.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
   // @Query("select e from Employee e where e.id = ?1")
   // Employee findById(long id);
}

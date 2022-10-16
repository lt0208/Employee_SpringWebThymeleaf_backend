package com.example.SpringWebThymeleafJPA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "First name cannot be empty.")
    @Size(min=2, max=25)
    private String firstName;
    private String lastName;
    private String level;
    @NotEmpty(message = "Email cannot be empty.")
    private String email;

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Employee> employeeList = new ArrayList<>();

    // annotation "orphanRemoval = true..." allows child entity to be removed???
    // delete manager will cause exception, regardless of using any kind of CascadeType.

    public List<Employee> getEmployeeList() {
        return employeeList;
    }
    // So, before deleting manager, we need to update or delete the employee


    public Manager(String firstName, String lastName, String level, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.level = level;
        this.email = email;
    }
}

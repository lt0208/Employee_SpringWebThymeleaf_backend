package com.example.SpringWebThymeleafJPA.controller;

import com.example.SpringWebThymeleafJPA.model.Employee;
import com.example.SpringWebThymeleafJPA.model.Manager;
import com.example.SpringWebThymeleafJPA.service.EmployeeService;
import com.example.SpringWebThymeleafJPA.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ManagerService managerService;

    //display list of employees
    @GetMapping("/employee")
    public String viewHomePage(Model model) {
        model.addAttribute("listEmployees", employeeService.getAllEmployee());
        return "employee";//this is the view name. It will go to templates and pick up index.html
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm1(Model model) {
        //create model attribute to bind form data
        Employee employee = new Employee();
        //employee.setManager(new Manager()); it isn't necessary
        //Thymeleaf template will access this empty employee object for binding form data
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    @GetMapping("/showNewEmployeeForm2")
    public String showNewEmployeeForm2(Model model) {
        List<Manager> managers = managerService.getAllManagers();
        Employee employee = new Employee();
        model.addAttribute("managers", managers);
        model.addAttribute("employee", employee);

        return "new_employee2";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee1(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult, Model model) {
        boolean thereAreErrors = bindingResult.hasErrors();
        if (thereAreErrors) {
            //model.addAttribute("employee", employee); it appears to be optional
            return "new_employee";
        } // if removing this block of code, there will be validation exception and calling error.html
        //Manager manager = managerService.getManagerById(employee.getManager().getId()); it isn't necessary
        //employee.setManager(manager);
        employeeService.saveEmployee(employee);
        return "redirect:/employee";
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate1(@PathVariable(value = "id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employee";

    }
}

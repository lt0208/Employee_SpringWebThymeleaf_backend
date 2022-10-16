package com.example.SpringWebThymeleafJPA.controller;

import com.example.SpringWebThymeleafJPA.model.Employee;
import com.example.SpringWebThymeleafJPA.model.Manager;
import com.example.SpringWebThymeleafJPA.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping("/all")
    String getAllManagers(Model model) {
        //List<Manager> managerList = managerService.getAllManagers(); not necessary to use a variable to save managerList
        model.addAttribute("managerList", managerService.getAllManagers());
        return "manager";
    }

    @GetMapping("/newform")
    String showNewManager(Model model) {
        model.addAttribute("newManager", new Manager());
        return "new_manager";
    }

    @PostMapping("/save-new")
    String addNewManager(@Valid @ModelAttribute("newManager") Manager manager, BindingResult bindingResult, Model model) {
        boolean thereAreErrors = bindingResult.hasErrors();
        if (thereAreErrors){
            //model.addAttribute("newManager", manager);
            return "new_manager";
        }
        managerService.saveManager(manager);
        return "redirect:/manager/all";
    }

    @GetMapping("/update-form/{id}")
    String showUpdateForm(@PathVariable(value = "id") Long id, Model model) {
        Manager manager = managerService.getManagerById(id);
        model.addAttribute("managerToUpd", manager);
        return "update_manager";
    }

    @PostMapping("/save-update")
    String saveUpdateManager(@ModelAttribute("managerToUpd") Manager manager) {
        managerService.saveManager(manager);
        return "redirect:/manager/all";
    }

    //delete manager can cause integrity issues with employee table
    @GetMapping("/delete/{id}")
    String deleteManager(@PathVariable(value = "id") Long id, Model model) {
        int num = managerService.getEmpsNum(id);
        if (num == 0) {
            managerService.deleteManager(id);
            return "redirect:/manager/all";
        } else {
            model.addAttribute("integrityException", "DataIntegrityViolationException");
            return "error";
        }
    }
}

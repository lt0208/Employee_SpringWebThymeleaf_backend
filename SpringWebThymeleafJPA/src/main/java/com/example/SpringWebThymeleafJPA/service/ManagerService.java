package com.example.SpringWebThymeleafJPA.service;

import com.example.SpringWebThymeleafJPA.model.Manager;

import java.util.List;
import java.util.Optional;

public interface ManagerService {
    void saveManager(Manager manager);
    List<Manager> getAllManagers();
    Manager getManagerById(Long id);
    void deleteManager(Long id);
    int getEmpsNum(Long id);
}

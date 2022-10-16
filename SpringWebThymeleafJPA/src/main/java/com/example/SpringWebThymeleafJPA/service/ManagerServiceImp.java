package com.example.SpringWebThymeleafJPA.service;

import com.example.SpringWebThymeleafJPA.model.Manager;
import com.example.SpringWebThymeleafJPA.repo.ManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImp implements ManagerService {
    @Autowired
    private ManagerRepo managerRepo;

    @Override
    public void saveManager(Manager manager) {
        managerRepo.saveAndFlush(manager);
    }

    @Override
    public List<Manager> getAllManagers() {

        return managerRepo.findAll();
    }

    @Override
    public Manager getManagerById(Long id) {
        return managerRepo.findManagerById(id); // it appears work, coz I configured it in the manager repo with SQL annotation
    }


    @Override
    public void deleteManager(Long id) {
        managerRepo.deleteById(id);
    }

    @Override
    public int getEmpsNum(Long id) {
        Manager manager = managerRepo.findManagerById(id);
        return manager.getEmployeeList().size();
    }


}

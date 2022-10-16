package com.example.SpringWebThymeleafJPA.repo;

import com.example.SpringWebThymeleafJPA.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ManagerRepo extends JpaRepository<Manager, Long> {

    @Query("select m from Manager m where m.id = ?1")
    Manager findManagerById(Long id);

    @Transactional
    @Modifying
    @Query("delete from Manager m where m.id = ?1")
    void deleteById(Long id);
}

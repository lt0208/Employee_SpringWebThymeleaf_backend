package com.example.SpringWebThymeleafJPA;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.boot.Banner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionController {
    // For UI Pages
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String userNotFoundException(DataIntegrityViolationException ex) {
        return "error";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String userNotFoundException(ConstraintViolationException ex) {
        return "error";
    }

}

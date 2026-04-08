package com.RaceKatteKlubben.MaineCoonClub.controller;

import com.RaceKatteKlubben.MaineCoonClub.exception.DatabaseConnectionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DatabaseConnectionException.class)
    public String handleDatabaseConnection(DatabaseConnectionException dce, Model model){
        model.addAttribute("type", "Database fejl");
        model.addAttribute("errorMessage", dce.getMessage());
        return "error";
    }




    @ExceptionHandler(Exception.class)
    public String handleGeneric(Exception ex, Model model){
        model.addAttribute("type", "Ukendt Fejl");
        model.addAttribute("errorMessage", "Der skete en uventet fejl");
        return "error";
    }

}

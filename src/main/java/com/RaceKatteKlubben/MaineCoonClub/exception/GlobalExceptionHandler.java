package com.RaceKatteKlubben.MaineCoonClub.exception;

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

    @ExceptionHandler(RegisterValidationException.class)
    public String handleRegisterValidation(RegisterValidationException rve, Model model){
        model.addAttribute("responseMessage", rve.getMessage());
        return "authentication/register";
    }

    @ExceptionHandler(LoginValidationException.class)
    public String handleLoginValidation(LoginValidationException lve, Model model){
        model.addAttribute("responseMessage", lve.getMessage());
        return "authentication/login";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneric(Exception ex, Model model){
        model.addAttribute("type", "Ukendt Fejl");
        model.addAttribute("errorMessage", "Der skete en uventet fejl");
        return "error";
    }

}

package com.RaceKatteKlubben.MaineCoonClub.exception;

import com.RaceKatteKlubben.MaineCoonClub.service.ExceptionLoggerService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    final ExceptionLoggerService loggerService;

    public GlobalExceptionHandler(ExceptionLoggerService loggerService){
        this.loggerService = loggerService;
    }

    @ExceptionHandler(DatabaseConnectionException.class)
    public String handleDatabaseConnection(DatabaseConnectionException dce, Model model){
        loggerService.checkSaveExceptionMessage(dce, rootCauseMessage(dce));
        model.addAttribute("type", "Database fejl");
        model.addAttribute("errorMessage", dce.getMessage());
        return "error";
    }

    @ExceptionHandler(RegisterValidationException.class)
    public String handleRegisterValidation(RegisterValidationException rve, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("responseMessage", rve.getMessage());
        return "redirect:/authentication/register";
    }

    @ExceptionHandler(LoginValidationException.class)
    public String handleLoginValidation(LoginValidationException lve, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("responseMessage", lve.getMessage());
        return "redirect:/authentication/login";
    }

    @ExceptionHandler(CatValidationException.class)
    public String handleCatValidation(CatValidationException cve, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("responseMessage", cve.getMessage());
        return "redirect:/cat/add";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalValidation(IllegalArgumentException iae, Model model){
        loggerService.checkSaveExceptionMessage(iae, rootCauseMessage(iae));
        model.addAttribute("type", "Ugyldigt argument");
        model.addAttribute("errorMessage", iae.getMessage());
        return "error";
    }


    @ExceptionHandler(DataAccessException.class)
    public String handleDataAccess(DataAccessException dae, Model model){
        loggerService.checkSaveExceptionMessage(dae, rootCauseMessage(dae));
        model.addAttribute("type", "Databasefejl");
        model.addAttribute("errorMessage", dae.getMessage());
        return "error";
    }


    @ExceptionHandler(Exception.class)
    public String handleGeneric(Exception ex, Model model){
        loggerService.checkSaveExceptionMessage(ex, rootCauseMessage(ex));
        model.addAttribute("type", "Ukendt Fejl");
        model.addAttribute("errorMessage", "Det er ukendt hvad der er gået galt");
        return "error";
    }

    private String rootCauseMessage(Throwable t){
        Throwable root = t;
        while (root.getCause() != null){
            root = root.getCause();
        }
        return root.getClass().getSimpleName() + " : " + root.getMessage();
    }

}

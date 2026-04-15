package com.RaceKatteKlubben.MaineCoonClub.controller;

import com.RaceKatteKlubben.MaineCoonClub.domain.*;
import com.RaceKatteKlubben.MaineCoonClub.service.CatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CatController {

    private final CatService service;

    public CatController(CatService service){
        this.service = service;
    }

    @GetMapping("/cat/add")
    public String showWelcome(HttpSession session, Model model){
        AuthSessionMember currentMember = (AuthSessionMember) session.getAttribute("currentUser");
        if (currentMember == null){
            return "redirect:/authentication/login";
        }
        model.addAttribute("cat", new Cat());
        model.addAttribute("sex", Sex.values());
        model.addAttribute("emsCodes", EmsCode.values());
        return "cat/add";
    }

    @PostMapping("/cat/add")
    public String tryToAddCat(@ModelAttribute Cat cat, RedirectAttributes redirectAttributes, HttpSession session){
        AuthSessionMember currentMember = (AuthSessionMember) session.getAttribute("currentUser");
        if (currentMember == null){
            return "redirect:/authentication/login";
        }
        cat.setMemberId(currentMember.getMemberId());
        service.checkAddCat(cat);
        //Jeg ved ikke om man kan komme udenom at tildele boolean 'fertile' en værdi,
        // men i så fald ved jeg ikke hvordan man tjekker det
        redirectAttributes.addFlashAttribute("responseMessage", "Du har tilføjet " + cat.getName() + " til din profil");
        return "redirect:/cat/add";
    }


}

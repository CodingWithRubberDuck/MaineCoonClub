package com.RaceKatteKlubben.MaineCoonClub.controller;

import com.RaceKatteKlubben.MaineCoonClub.domain.*;
import com.RaceKatteKlubben.MaineCoonClub.service.CatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CatController {

    private final CatService service;

    public CatController(CatService service){
        this.service = service;
    }

    @GetMapping("/cat/add")
    public String showCatAdd(HttpSession session, Model model){
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

    @GetMapping("/cat/search")
    public String listCats(@RequestParam(required = false) String name, HttpSession session, Model model) {
        AuthSessionMember currentMember = (AuthSessionMember) session.getAttribute("currentUser");
        if (currentMember == null) {
            return "redirect:/authentication/login";
        }
        List<Cat> cats = new ArrayList<>();

        if (name == null || name.isBlank()) {
            cats = service.showListOfCats();
        } else {
            Optional<List<Cat>> result = service.showCatByName(name);
            if (result.isPresent()) {
                cats = result.get();
            }
        }
        model.addAttribute("cats", cats);

        return "cat/search";
    }
}

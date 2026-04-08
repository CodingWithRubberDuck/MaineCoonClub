package com.RaceKatteKlubben.MaineCoonClub.controller;

import com.RaceKatteKlubben.MaineCoonClub.domain.Member;
import com.RaceKatteKlubben.MaineCoonClub.service.MemberLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberLoginController {
    private final MemberLoginService service;

    public MemberLoginController(MemberLoginService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/authentication/register")
    public String showRegisterForm(Model model){
        model.addAttribute("member", new Member());
        return "authentication/register";
    }

    @PostMapping("/authentication/register")
    public String tryToRegister(@ModelAttribute Member member, Model model){

    }

    @GetMapping("/authentication/login")
    public String showLoginForm(Model model){
        model.addAttribute("member", new Member());
        return "authentication/login";
    }
}
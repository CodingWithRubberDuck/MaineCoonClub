package com.RaceKatteKlubben.MaineCoonClub.controller;

import com.RaceKatteKlubben.MaineCoonClub.domain.AuthSessionMember;
import com.RaceKatteKlubben.MaineCoonClub.domain.Member;
import com.RaceKatteKlubben.MaineCoonClub.service.MemberAuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberAuthController {
    private final MemberAuthService service;

    public MemberAuthController(MemberAuthService service) {
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
    public String tryToRegister(@ModelAttribute Member member){
        service.checkRegister(member);
        return "redirect:/authentication/login";
    }

    @GetMapping("/authentication/login")
    public String showLoginForm(Model model){
        model.addAttribute("member", new Member());
        return "authentication/login";
    }

    @PostMapping("/authentication/login")
    public String tryToLogin(@ModelAttribute Member member, HttpSession session){
        AuthSessionMember loggedIn = service.checkLogin(member.getEmail(), member.getPassword());
        session.setAttribute("currentUser", loggedIn);
        return "redirect:/welcome";
    }

    @GetMapping("/welcome")
    public String showWelcome(HttpSession session){
        AuthSessionMember currentMember = (AuthSessionMember) session.getAttribute("currentUser");
        if (currentMember == null){
            return "redirect:/authentication/login";
        }
        return "welcome";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }


}
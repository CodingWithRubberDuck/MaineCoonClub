package com.RaceKatteKlubben.MaineCoonClub.controller;

import com.RaceKatteKlubben.MaineCoonClub.domain.AuthSessionMember;
import com.RaceKatteKlubben.MaineCoonClub.domain.Member;
import com.RaceKatteKlubben.MaineCoonClub.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/search")
    public String listMembers(@RequestParam(required = false) String name, HttpSession session, Model model) {
        AuthSessionMember currentMember = (AuthSessionMember) session.getAttribute("currentUser");
        if (currentMember == null) {
            return "redirect:/authentication/login";
        }
        List<Member> members = new ArrayList<>();

        if (name == null || name.isBlank()) {
            members = memberService.showListOfMembers();
        } else {
            Optional<List<Member>> result = memberService.showMemberByName(name);
            if (result.isPresent()) {
                members = result.get();
            }
        }

        model.addAttribute("members", members);

        return "member/search";
    }
}

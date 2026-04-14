package com.RaceKatteKlubben.MaineCoonClub.controller;

import ch.qos.logback.core.model.Model;
import com.RaceKatteKlubben.MaineCoonClub.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /*

    @GetMapping("/welcome/members")
    public String getAllMembers(Model model) {

    }

     */
}

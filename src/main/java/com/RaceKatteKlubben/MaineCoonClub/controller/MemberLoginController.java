package com.RaceKatteKlubben.MaineCoonClub.controller;

import com.RaceKatteKlubben.MaineCoonClub.service.MemberLoginService;
import org.springframework.stereotype.Controller;

@Controller
public class MemberLoginController {
    private final MemberLoginService service;

    public MemberLoginController(MemberLoginService service) {
        this.service = service;
    }
}
package com.RaceKatteKlubben.MaineCoonClub.service;

import com.RaceKatteKlubben.MaineCoonClub.domain.IMemberLoginRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberLoginService {

    private final IMemberLoginRepository repository;

    public MemberLoginService(IMemberLoginRepository repository){
        this.repository = repository;
    }
}

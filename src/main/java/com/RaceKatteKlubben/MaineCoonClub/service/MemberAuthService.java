package com.RaceKatteKlubben.MaineCoonClub.service;

import com.RaceKatteKlubben.MaineCoonClub.domain.IMemberAuthRepository;
import com.RaceKatteKlubben.MaineCoonClub.domain.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberAuthService {

    private final IMemberAuthRepository repository;

    public MemberAuthService(IMemberAuthRepository repository){
        this.repository = repository;
    }

    public void checkRegister(Member member){

    }


    private void validate(){
        //something happens and it's cool
    }
}

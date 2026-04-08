package com.RaceKatteKlubben.MaineCoonClub.service;

import com.RaceKatteKlubben.MaineCoonClub.domain.IMemberLoginRepository;
import com.RaceKatteKlubben.MaineCoonClub.domain.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberLoginService {

    private final IMemberLoginRepository repository;

    public MemberLoginService(IMemberLoginRepository repository){
        this.repository = repository;
    }

    public void checkRegister(Member member){

    }


    private void validate(){
        //something happens and it's cool
    }
}

package com.RaceKatteKlubben.MaineCoonClub.service;

import com.RaceKatteKlubben.MaineCoonClub.domain.AuthSessionMember;
import com.RaceKatteKlubben.MaineCoonClub.domain.IMemberAuthRepository;
import com.RaceKatteKlubben.MaineCoonClub.domain.Member;
import com.RaceKatteKlubben.MaineCoonClub.exception.LoginValidationException;
import com.RaceKatteKlubben.MaineCoonClub.exception.RegisterValidationException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class MemberAuthService {

    private final IMemberAuthRepository repository;
    private final RegisterValidationService registerValidation;

    public MemberAuthService(IMemberAuthRepository repository, RegisterValidationService registerValidation){
        this.repository = repository;
        this.registerValidation = registerValidation;
    }

    public void checkRegister(Member member){
        registerValidation.validate(member);
        if (repository.findByEmail(member.getEmail()).isEmpty()){
            throw new RegisterValidationException("Den indtastede email findes allerede i systemet");
        }
        String hashed = BCrypt.hashpw(member.getPassword(), BCrypt.gensalt(10));
        member.setPassword(hashed);
        repository.saveNewMember(member);
    }

    public AuthSessionMember checkLogin(String email, String password){
        Member member = repository.findByEmail(email).orElse(null);

        if (member != null && BCrypt.checkpw(password, member.getPassword())){
            return new AuthSessionMember(member.getMemberId(), member.getName(), member.getEmail());
        }
        throw new LoginValidationException("Email eller kodeord er ikke korrekt");
    }

}

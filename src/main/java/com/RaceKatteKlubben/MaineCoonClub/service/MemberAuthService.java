package com.RaceKatteKlubben.MaineCoonClub.service;

import com.RaceKatteKlubben.MaineCoonClub.domain.IMemberAuthRepository;
import com.RaceKatteKlubben.MaineCoonClub.domain.Member;
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
        if (repository.emailAlreadyExists(member.getEmail()) != null){
            throw new RegisterValidationException("Den indtastede email findes allerede i systemet");
        }
        String hashed = BCrypt.hashpw(member.getPassword(), BCrypt.gensalt(10));
        member.setPassword(hashed);
        repository.saveNewMember(member);
    }

}

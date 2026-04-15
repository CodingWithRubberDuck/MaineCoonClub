package com.RaceKatteKlubben.MaineCoonClub.service;

import com.RaceKatteKlubben.MaineCoonClub.domain.IMemberRepository;
import com.RaceKatteKlubben.MaineCoonClub.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final IMemberRepository repository;

    public MemberService(IMemberRepository repository) {
        this.repository = repository;
    }

    public List<Member> showListOfMembers() {
        return repository.findAllMembers();
    }

    public Optional<List<Member>> showMemberByName(String name) {
        return repository.findMemberByName(name);
    }
}

package com.RaceKatteKlubben.MaineCoonClub.domain;

import java.util.Optional;

public interface IMemberAuthRepository {
    Optional<Member> findByEmail(String email);
    void saveNewMember(Member member);
}

package com.RaceKatteKlubben.MaineCoonClub.domain;

public interface IMemberAuthRepository {
    Member emailAlreadyExists(String email);
    void saveNewMember(Member member);
}

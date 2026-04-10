package com.RaceKatteKlubben.MaineCoonClub.domain;

public interface IMemberAuthRepository {
    boolean emailAlreadyExists(String email);
    void saveNewMember(Member member);
}

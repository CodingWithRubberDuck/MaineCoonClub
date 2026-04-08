package com.RaceKatteKlubben.MaineCoonClub.domain;

public interface IMemberLoginRepository {
    boolean emailAlreadyExists(String email);
    void saveNewMember(Member member);
}

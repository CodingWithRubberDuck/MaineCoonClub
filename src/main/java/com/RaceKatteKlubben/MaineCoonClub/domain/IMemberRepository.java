package com.RaceKatteKlubben.MaineCoonClub.domain;

import java.util.List;
import java.util.Optional;

public interface IMemberRepository {
    List<Member> findAllMembers();
    Optional<List<Member>> findMemberByName(String name);
    void deleteMemberById(int memberId);
}

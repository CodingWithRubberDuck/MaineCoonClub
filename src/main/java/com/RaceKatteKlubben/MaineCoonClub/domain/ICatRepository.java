package com.RaceKatteKlubben.MaineCoonClub.domain;

import java.util.List;
import java.util.Optional;

public interface ICatRepository {
    void addCatToMember(Cat cat);
    List<Cat> findAllCats();
    Optional<List<Cat>> findCatByName(String name);
}

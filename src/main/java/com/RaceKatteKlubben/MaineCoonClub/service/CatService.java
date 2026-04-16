package com.RaceKatteKlubben.MaineCoonClub.service;

import com.RaceKatteKlubben.MaineCoonClub.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CatService {

    private final CatValidationService validationService;
    private final ICatRepository repository;

    public CatService(CatValidationService validationService, ICatRepository repository){
        this.validationService = validationService;
        this.repository = repository;
    }

    public void checkAddCat(Cat cat){
        normalizeOptionalFields(cat);
        validationService.validate(cat);
        repository.addCatToMember(cat);
    }


    private void normalizeOptionalFields(Cat cat){
        if (cat.getBreeder() != null && cat.getBreeder().isBlank()){
            cat.setBreeder(null);
        }
        if (cat.getFatherCat() != null && cat.getFatherCat().isBlank()){
            cat.setFatherCat(null);
        }
        if (cat.getMotherCat() != null && cat.getMotherCat().isBlank()){
            cat.setMotherCat(null);
        }
    }

    public List<Cat> showListOfCats() {
        return repository.findAllCats();
    }

    public Optional<List<Cat>> showCatByName(String name) {
        return repository.findCatByName(name);
    }
}

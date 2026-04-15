package com.RaceKatteKlubben.MaineCoonClub.service;

import com.RaceKatteKlubben.MaineCoonClub.domain.Cat;
import com.RaceKatteKlubben.MaineCoonClub.domain.EmsCode;
import com.RaceKatteKlubben.MaineCoonClub.domain.ICatRepository;
import com.RaceKatteKlubben.MaineCoonClub.domain.Sex;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}

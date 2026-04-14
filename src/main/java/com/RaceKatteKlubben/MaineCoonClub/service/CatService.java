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
        validationService.validate(cat);
        repository.addCatToMember(cat);
    }



    public List<EmsCode> getEmsCodes(){
        return new ArrayList<>(Arrays.asList(EmsCode.values()));
    }

    public List<Sex> getSex(){
        return new ArrayList<>(Arrays.asList(Sex.values()));
    }
}

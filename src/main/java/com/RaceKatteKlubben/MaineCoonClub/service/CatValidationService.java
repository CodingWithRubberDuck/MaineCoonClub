package com.RaceKatteKlubben.MaineCoonClub.service;

import com.RaceKatteKlubben.MaineCoonClub.domain.Cat;
import com.RaceKatteKlubben.MaineCoonClub.exception.CatValidationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CatValidationService {
    public void validate(Cat cat){
        //Jeg ved ikke lige hvordan man ville tjekke om nogen er gået uden om brugergrænsefladen
        // og at fertilitet/fertile aldrig er blevet assigned/fastsat

        //Der er ikke lavet Unit-test for disse valideringer, endnu
        validateSex(cat);
        validateEmsCode(cat);
        validateName(cat.getName());
        validateBirthAndDeath(cat.getDateOfBirth(), cat.getDateOfDeath());
        validateBreeder(cat);
        validateFather(cat);
        validateMother(cat);
    }

    private void validateSex(Cat cat){
        if (cat.getSex() == null){
            throw new CatValidationException("Kattens køn kan ikke være tom");
        }
    }

    private void validateEmsCode(Cat cat){
        if (cat.getEmsCode() == null){
            throw new CatValidationException("Kattens ems-kode kan ikke være tom");
        }
    }


    private void validateName(String name){
        final int MIN_NAME = 2;
        final int MAX_NAME = 200;
        if (name == null || name.isBlank()){
            throw new CatValidationException("Kattens navn skal udfyldes");
        }
        if (checkStringLength(name.length(), MIN_NAME, MAX_NAME)){
            throw new CatValidationException("Kattens navn skal være mellem " + MIN_NAME + " og " + MAX_NAME + " tegn");
        }
    }

    private void validateBreeder(Cat cat){
        final int MIN_NAME = 2;
        final int MAX_NAME = 100;
        //Hvis string er tom/blank burde den ikke anses som andet end null
        if (cat.getBreeder().isBlank()){
            cat.setBreeder(null);
        }
        String breeder = cat.getBreeder();
        if (breeder != null ) {
            if (checkStringLength(breeder.length(), MIN_NAME, MAX_NAME)) {
                throw new CatValidationException("Hvis ud skal være mellem " + MIN_NAME + " og " + MAX_NAME + " tegn");
            }
        }
    }

    private void validateFather(Cat cat){
        final int MIN_NAME = 2;
        final int MAX_NAME = 200;
        //Hvis string er tom/blank burde den ikke anses som andet end null
        if (cat.getFatherCat().isBlank()){
            cat.setFatherCat(null);
        }
        String father = cat.getBreeder();
        if (father != null) {
            if (checkStringLength(father.length(), MIN_NAME, MAX_NAME)) {
                throw new CatValidationException("Kattens navn skal være mellem " + MIN_NAME + " og " + MAX_NAME + " tegn");
            }
        }
    }

    private void validateMother(Cat cat){
        final int MIN_NAME = 2;
        final int MAX_NAME = 200;
        //Hvis string er tom/blank burde den ikke anses som andet end null
        if (cat.getMotherCat().isBlank()){
            cat.setMotherCat(null);
        }
        String mother = cat.getBreeder();
        if (mother != null) {
            if (checkStringLength(mother.length(), MIN_NAME, MAX_NAME)) {
                throw new CatValidationException("Kattens navn skal være mellem " + MIN_NAME + " og " + MAX_NAME + " tegn");
            }
        }
    }

    private void validateBirthAndDeath(LocalDate birth, LocalDate death){
        if (birth == null){
            throw new CatValidationException("Kattens fødselsdato skal udfyldes");
        }
        if (birth.isAfter(LocalDate.now())){
            throw new CatValidationException("Kattens fødselsdato kan ikke være fastsat i fremtiden");
        }
        if (death != null){
            if (death.isAfter(LocalDate.now())){
                throw new CatValidationException("Kattens bortgang kan ikke være fastsat i fremtiden");
            }
            if (death.isBefore(birth)){
                throw new CatValidationException("Kattens fødselsdato kan ikke være efter datoen katten er gået bort");
            }
        }
    }

    private boolean checkStringLength(int test, int min, int max){
        return (min > test || max < test);
    }


}

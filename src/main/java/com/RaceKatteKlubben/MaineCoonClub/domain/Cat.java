package com.RaceKatteKlubben.MaineCoonClub.domain;

import java.time.LocalDate;

public class Cat {
    private int catId;
    private int memberId;
    private String name;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private Sex sex;
    private EmsCode emsCode;
    private boolean fertile;
    private String breeder;
    private String fatherCat;
    private String motherCat;

    public Cat() {}

    public Cat(int catId, int memberId, String name, LocalDate dateOfBirth, LocalDate dateOfDeath, Sex sex,
               EmsCode emsCode, boolean fertile, String breeder, String fatherCat, String motherCat) {
        if (name == null){
            throw new IllegalArgumentException("Navnet på katten mangler");
        }
        if (dateOfBirth == null){
            throw new IllegalArgumentException("Fødselsdato på katten mangler");
        }
        if (sex == null){
            throw new IllegalArgumentException("Køn på katten mangler");
        }
        if (emsCode == null){
            throw new IllegalArgumentException("Ems kode på katten mangler");
        }
        this.catId = catId;
        this.memberId = memberId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.sex = sex;
        this.emsCode = emsCode;
        this.fertile = fertile;
        this.breeder = breeder;
        this.fatherCat = fatherCat;
        this.motherCat = motherCat;
    }

    /// Getters
    public int getCatId() {
        return catId;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public Sex getSex() {
        return sex;
    }

    public EmsCode getEmsCode() {
        return emsCode;
    }

    public boolean isFertile() {
        return fertile;
    }

    public String getBreeder() {
        return breeder;
    }

    public String getFatherCat() {
        return fatherCat;
    }

    public String getMotherCat() {
        return motherCat;
    }

    /// Setters
    public void setCatId(int catId) {
        this.catId = catId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setEmsCode(EmsCode emsCode) {
        this.emsCode = emsCode;
    }

    public void setFertile(boolean fertile) {
        this.fertile = fertile;
    }

    public void setBreeder(String breeder) {
        this.breeder = breeder;
    }

    public void setFatherCat(String fatherCat) {
        this.fatherCat = fatherCat;
    }

    public void setMotherCat(String motherCat) {
        this.motherCat = motherCat;
    }

    /// toString
    @Override
    public String toString() {
        return getName();
    }
}

package com.RaceKatteKlubben.MaineCoonClub.domain;

public class Member {
    private int memberId;
    private String name;
    private String email;
    private String passwordHash;

    public Member() {}

    public Member(int memberId, String name, String email, String passwordHash) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    /// Getters
    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    /// Setters
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /// toString
    @Override
    public String toString() {
        return getName();
    }
}

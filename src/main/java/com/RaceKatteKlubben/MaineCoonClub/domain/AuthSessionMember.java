package com.RaceKatteKlubben.MaineCoonClub.domain;

public class AuthSessionMember {
    private final int memberId;
    private final String name;
    private final String email;

    public AuthSessionMember(int memberId, String name, String email){
        if (memberId <= 0){
            throw new IllegalArgumentException("Auth mangler et id");
        }
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Auth mangler et navn");
        }
        if (email == null || email.isBlank()){
            throw new IllegalArgumentException("Auth mangler en email");
        }
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    public int getMemberId() {
        return memberId;
    }
    public String getName(){
        return name;
    }

    public String getEmail() {
        return email;
    }
}

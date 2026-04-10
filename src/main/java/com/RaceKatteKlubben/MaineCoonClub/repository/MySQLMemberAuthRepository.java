package com.RaceKatteKlubben.MaineCoonClub.repository;

import com.RaceKatteKlubben.MaineCoonClub.domain.IMemberAuthRepository;
import com.RaceKatteKlubben.MaineCoonClub.config.DatabaseConfig;
import com.RaceKatteKlubben.MaineCoonClub.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MySQLMemberAuthRepository implements IMemberAuthRepository {

    private final DatabaseConfig databaseConfig;

    public MySQLMemberAuthRepository(DatabaseConfig databaseConfig){
        this.databaseConfig = databaseConfig;
    }


    boolean emailAlreadyExists(String email){
        String sql = "SELECT password;
    }

    void saveNewMember(Member member){

    }


}

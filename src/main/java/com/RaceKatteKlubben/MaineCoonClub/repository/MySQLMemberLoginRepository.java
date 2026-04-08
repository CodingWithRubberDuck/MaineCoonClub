package com.RaceKatteKlubben.MaineCoonClub.repository;

import com.RaceKatteKlubben.MaineCoonClub.domain.IMemberLoginRepository;
import com.RaceKatteKlubben.MaineCoonClub.config.DatabaseConfig;

public class MySQLMemberLoginRepository implements IMemberLoginRepository {

    private final DatabaseConfig databaseConfig;

    public MySQLMemberLoginRepository(DatabaseConfig databaseConfig){
        this.databaseConfig = databaseConfig;
    }
}

package com.RaceKatteKlubben.MaineCoonClub.domain;

public interface IExceptionLoggerRepository {
    void saveExceptionMessage(Exception e, String details);
}

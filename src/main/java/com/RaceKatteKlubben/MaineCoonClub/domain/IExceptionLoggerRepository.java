package com.RaceKatteKlubben.MaineCoonClub.domain;

public interface IExceptionLoggerRepository {
    void saveExceptionMessage(Exception e, Throwable root);
}

package com.RaceKatteKlubben.MaineCoonClub.service;

import com.RaceKatteKlubben.MaineCoonClub.domain.IExceptionLoggerRepository;
import org.springframework.stereotype.Service;



@Service
public class ExceptionLoggerService {

    private final IExceptionLoggerRepository repository;

    public ExceptionLoggerService(IExceptionLoggerRepository repository){
        this.repository = repository;
    }

    public void checkSaveExceptionMessage(Exception e, String details){
        repository.saveExceptionMessage(e, details);
    }
}

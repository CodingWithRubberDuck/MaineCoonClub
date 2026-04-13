package com.RaceKatteKlubben.MaineCoonClub.repository;

import com.RaceKatteKlubben.MaineCoonClub.domain.IExceptionLoggerRepository;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public class FileExceptionLoggerRepository implements IExceptionLoggerRepository {
    private String filePath;
    private String backupFilePath;


    //Dette har mange indbyggede design-fejl, men det fungerer for nu, konstruktiv kritik ville være fint
    @Override
    public void saveExceptionMessage(Exception e, Throwable root) {
        setFilePath();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            writer.write("\n" + LocalTime.now() + " : " + e.getMessage() +
                    "\n " + root.getClass().getName() + " : " + root.getMessage());
        } catch (IOException ioe) {
            setBackupFilePath();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(backupFilePath, true))){
                writer.write("\n" + LocalTime.now() + " : " + e.getMessage() +
                        "\n " + root.getClass().getName() + " : " + root.getMessage() +
                        "\n Indlæsningsfejl i forhold til fil : " + ioe.getMessage());
            } catch (IOException ioe2) {
                //Ingen idé hvis backup fejler
            }
        }
    }

    private void setFilePath(){
        this.filePath = "src\\exceptionlogs\\" + getLogName();
    }

    private void setBackupFilePath(){
        this.backupFilePath = Paths.get(System.getProperty("user.home")) + getLogName();
    }

    private String getLogName(){
        return LocalDate.now() + "-log.txt";
    }
}

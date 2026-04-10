package com.RaceKatteKlubben.MaineCoonClub;

import com.RaceKatteKlubben.MaineCoonClub.domain.Member;
import com.RaceKatteKlubben.MaineCoonClub.exception.RegisterValidationException;
import com.RaceKatteKlubben.MaineCoonClub.service.RegisterValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegisterValidationTest {
    private RegisterValidationService registerValidation;
    private Member minimumSuccessMember;

    @BeforeEach
    void setUp(){
        registerValidation = new RegisterValidationService();
        minimumSuccessMember = new Member(
                1,
                "aa",
                "1@1.aa",
                "a1234567");
    }

    //Baseline test
    @Test
    void registerValidation_minimumSuccessTest(){
        assertDoesNotThrow(()->registerValidation.validate(minimumSuccessMember));
    }


/**
 * Navn skal være mellem 2 og 100 tegn
 * Navn kan ikke være tomt eller kun bestå af tomme pladser
 * Navn kan ikke indeholde mere end 1 tom plads i streg (efter hinanden)
 *
 */

    //Navn
    @Test
    void registerValidation_shouldThrowWhenNull(){
        minimumSuccessMember.setName(null);
        assertThrows(RegisterValidationException.class, ()-> registerValidation.validate(minimumSuccessMember));
    }

    @Test
    void registerValidation_shouldThrowWhenOnlyWhiteSpace(){
        minimumSuccessMember.setName("                 ");
        assertThrows(RegisterValidationException.class, ()-> registerValidation.validate(minimumSuccessMember));
    }

    @Test
    void registerValidation_shouldThrowWhenMultipleWhiteSpacesInRow(){
        minimumSuccessMember.setName("b  o");
        assertThrows(RegisterValidationException.class, ()-> registerValidation.validate(minimumSuccessMember));
    }

    @Test
    void registerValidation_shouldThrowWhenNameTooShort(){
        minimumSuccessMember.setName("a");
        assertThrows(RegisterValidationException.class, ()-> registerValidation.validate(minimumSuccessMember));
    }

    @Test
    void registerValidation_shouldThrowWhenNameTooLong(){
        //Tester at 101 tegn fejler
        minimumSuccessMember.setName(
                                    "1234567890"+
                                    "1234567890"+
                                    "1234567890"+
                                    "1234567890"+
                                    "1234567890"+
                                    "1234567890"+
                                    "1234567890"+
                                    "1234567890"+
                                    "1234567890"+
                                    "1234567890"+
                                    "1");
        assertThrows(RegisterValidationException.class, () -> registerValidation.validate(minimumSuccessMember));
    }


    /**
     * Email skal ligne den generelle idé af en email (regex)
     * eksempel(.mere)@yahoo(.com).au
     *
     * "^[_A-Za-z0-9+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
     *
     * Email kan ikke være længere end 100 tegn
     *
     */

    //Email
    @Test
    void registerValidation_shouldAcceptOptionalEmailParts(){
        minimumSuccessMember.setEmail("1.1@1.1.aa");
        assertDoesNotThrow(()-> registerValidation.validate(minimumSuccessMember));
    }

    @Test
    void registerValidation_shouldThrowWhenEmailNull(){
        minimumSuccessMember.setEmail(null);
        assertThrows(RegisterValidationException.class, ()-> registerValidation.validate(minimumSuccessMember));
    }


    @Test
    void registerValidation_shouldThrowWhenEmailNoLocalPart(){
        minimumSuccessMember.setEmail("@1.aa");
        assertThrows(RegisterValidationException.class, ()-> registerValidation.validate(minimumSuccessMember));
    }

    @Test
    void registerValidation_shouldThrowWhenEmailNoAtSign(){
        minimumSuccessMember.setEmail("11.aa");
        assertThrows(RegisterValidationException.class, ()-> registerValidation.validate(minimumSuccessMember));
    }

    @Test
    void registerValidation_shouldThrowWhenEmailNoDomainName(){
        minimumSuccessMember.setEmail("1@.aa");
        assertThrows(RegisterValidationException.class, ()-> registerValidation.validate(minimumSuccessMember));
    }

    @Test
    void registerValidation_shouldThrowWhenEmailNoPeriod(){
        minimumSuccessMember.setEmail("1@1aa");
        assertThrows(RegisterValidationException.class, ()-> registerValidation.validate(minimumSuccessMember));
    }

    @Test
    void registerValidation_shouldThrowWhenEmailTopLevelDomainTooShort(){
        minimumSuccessMember.setEmail("1@1.a");
        assertThrows(RegisterValidationException.class, ()-> registerValidation.validate(minimumSuccessMember));
    }

    @Test
    void registerValidation_shouldThrowWhenEmailTooLong(){
        // Tester at 101 tegn fejler
        minimumSuccessMember.setEmail("1@1.aa" +
                "abcdefghij" +
                "abcdefghij" +
                "abcdefghij" +
                "abcdefghij" +
                "abcdefghij" +
                "abcdefghij" +
                "abcdefghij" +
                "abcdefghij" +
                "abcdefghij" +
                "abcde");
        assertThrows(RegisterValidationException.class, () -> registerValidation.validate(minimumSuccessMember));
    }


    //Kodeord
    /**
     * Kodeord skal være mellem 8 og 120 tegn
     * Kodeord kan ikke være tomt
     * Kodeord kan ikke indeholde tomme pladser (mellemrum)
     * Kodeord skal indeholde et tal
     * Kodeord skal indeholde et bogstav
     */

    @Test
    void registerValidation_shouldThrowWhenPasswordNull(){
        minimumSuccessMember.setPasswordHash(null);
        assertThrows(RegisterValidationException.class, ()-> registerValidation.validate(minimumSuccessMember));
    }

    @Test
    void registerValidation_shouldThrowWhenPasswordNotContainNumber(){
        minimumSuccessMember.setPasswordHash("abcdefgh");
        assertThrows(RegisterValidationException.class, ()-> registerValidation.validate(minimumSuccessMember));
    }

    @Test
    void registerValidation_shouldThrowWhenPasswordNotContainLetter(){
        minimumSuccessMember.setPasswordHash("12345678");
        assertThrows(RegisterValidationException.class, ()-> registerValidation.validate(minimumSuccessMember));
    }

    @Test
    void registerValidation_shouldThrowWhenPasswordContainWhitespace(){
        minimumSuccessMember.setPasswordHash("a123 4567");
        assertThrows(RegisterValidationException.class, ()-> registerValidation.validate(minimumSuccessMember));
    }




}

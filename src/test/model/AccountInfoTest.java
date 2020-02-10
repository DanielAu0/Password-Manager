package model;

import exceptions.InvalidAddressException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountInfoTest {
    Info youtube;
    AccountInfo canvas;

    @BeforeEach
    void runBefore() {
        youtube = new AccountInfo();
        canvas = new AccountInfo("www.canvas.com","Peter123");
    }

    @Test
    void testSetAddressSuccess() {
        try {
            youtube.setAddress("www.youtube.com");
            System.out.println("Success");
        } catch (InvalidAddressException e) {
            Assertions.fail("Should not get here");
        }
    }

    @Test
    void testSetAddressFail() {
        try {
            youtube.setAddress("fdsfsdfsdf");
            Assertions.fail("Should not get here");
        } catch (InvalidAddressException e) {
            System.out.println("Success");
        }
    }

    @Test
    void testSetAddressShort() {
        try {
            canvas.setAddress("d");
            Assertions.fail("Should not get here");
        } catch (InvalidAddressException e) {
            System.out.println("Success");
        }
    }
}

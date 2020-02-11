package exceptions;

import model.AccountInfo;
import model.Info;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InvalidAddressTest {
    Info daniel;

    @BeforeEach
    void runBefore() {
        daniel = new AccountInfo();
    }

    @Test
    void testNoException() {
        try {
            daniel.setAddress("www.youtube.com");
            System.out.println("Success");
        } catch (InvalidAddressException e) {
            Assertions.fail("Should not get here");
        }
    }

    @Test
    void testException() {
        try {
            daniel.setAddress("fdsfsdfsdf");
            Assertions.fail("Should not get here");
        } catch (InvalidAddressException e) {
            System.out.println("Success");
        }
    }

}

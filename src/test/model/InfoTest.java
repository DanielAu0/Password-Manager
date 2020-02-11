package model;

import exceptions.InvalidAddressException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;


public class InfoTest {
    Info canvas;
    AccountInfo peter;
    UserInfo john;

    @BeforeEach
    void runBefore() {
        peter = new AccountInfo("peter@ubc.ca","Peter123");
        john = new UserInfo();
    }

    @Test
    void testAddressMatch() {
        canvas = new AccountInfo("www.canvas.com","danau4");
        Assertions.assertTrue(canvas.addressMatch("www.canvas.com"));
    }

    @Test
    void testAddressDoNotMatch() {
        canvas = new AccountInfo("www.canvas.com","danau4");
        Assertions.assertFalse(canvas.addressMatch("www.youtube.com"));
    }

    @Test
    void invalidAddressTest() {
        canvas = new AccountInfo("www.canvasm","danau4");
        try {
            canvas.validAddress();
            Assertions.fail();
        } catch (InvalidAddressException e) {
            System.out.println("Success");
        }

    }

    @Test
    void validAddressTest() throws InvalidAddressException {
        Assertions.assertTrue(peter.validAddress());
    }

    @Test
    void toStringTest() {
        String formatted = "Address: peter@ubc.ca\nLogin: Peter123";
        Assertions.assertEquals(formatted, peter.toString());
    }

    @Test
    public void setAddressTest() throws InvalidAddressException {
        john.setAddress("john@alumni.ubc.ca");
        Assertions.assertEquals("john@alumni.ubc.ca",john.getAddress());
    }

    @Test
    public void setLoginTest() throws InvalidAddressException {
        john.setLogin("john123");
        Assertions.assertEquals("john123",john.getLogin());
    }



}

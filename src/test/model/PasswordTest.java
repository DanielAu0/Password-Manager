package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PasswordTest {
    Password p1;
    Password p2;

    @BeforeEach
    void runBefore() {
        p1 = new Password();
        p2 = new Password("au");
    }

    @Test
    void testEncode() {
        Assertions.assertEquals("ZGVmYXVsdA==",p1.encode());
    }

    @Test
    void testDecode() {
        Assertions.assertEquals("default",p1.decode("ZGVmYXVsdA=="));
    }

    @Test
    void testPassGenerator() {
        String generated = p1.passwordGenerator(10);
        Assertions.assertEquals(10,generated.length());
    }

    @Test
    void testStrongPasswordOnlyCap() {
        Assertions.assertFalse(p1.strongPassword("XXXXXXXX"));
    }

    @Test
    void testStrongPasswordOnlyLower() {
        Assertions.assertFalse(p1.strongPassword("xxxxx"));
    }

    @Test
    void testStrongPasswordOnlyNumber() {
        Assertions.assertFalse(p1.strongPassword("123"));
    }

    @Test
    void testStrongPasswordCapNumber() {
        Assertions.assertFalse(p1.strongPassword("D123"));
    }

    @Test
    void testStrongPasswordCapLower() {
        Assertions.assertFalse(p1.strongPassword("Dd"));
    }

    @Test
    void testStrongPasswordNumLower() {
        Assertions.assertFalse(p1.strongPassword("2d"));
    }

    @Test
    void testStrongPassword() {
        Assertions.assertTrue(p1.strongPassword("This2"));
    }



    @Test
    void testSetPassGenerator() {
        p1.setPass("custom".toCharArray());
        Assertions.assertEquals(15,p1.getPass().length);
    }

    @Test
    void testSet() {
        p1.setPass("normal".toCharArray());
        Assertions.assertEquals("normal",new String(p1.getPass()));
    }
}

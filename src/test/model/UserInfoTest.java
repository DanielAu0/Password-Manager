package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserInfoTest {
    Info daniel;
    UserInfo peter;

    @BeforeEach
    void runBefore() {
        daniel = new UserInfo();
        peter = new UserInfo("peter@gmail.com","Peter123");
    }

    @Test
    void testGetter() {
        Assertions.assertEquals("peter@gmail.com",peter.getAddress());
    }
}

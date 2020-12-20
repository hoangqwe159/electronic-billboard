package Commmunication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestMessageFactory {

    @org.junit.jupiter.api.Test
    void normalCase() {
        String expected = "LoginRequest";
        Message testMsg = MessageFactory.getMessage(expected);
        String actual = testMsg.getClass().getSimpleName();
        assertEquals(expected, actual);
    }

    @Test
    void notExistCase(){
        String none = "Non-exist";
        Message testMsg = MessageFactory.getMessage(none);
        assertEquals(null, testMsg);
    }
}
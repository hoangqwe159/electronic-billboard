package Commmunication;

import Commmunication.Requests.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestMessage {
    Message msg;
    @BeforeEach
    void constructMessage(){
        msg = new LoginRequest();
    }

    @Test
    void getType(){
        String expected = "LoginRequest";
        String actual = msg.messageType();
        assertEquals(expected, actual);
    }
}
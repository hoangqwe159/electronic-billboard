package Utils.Token;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class TestTokenSchema {
    String payload;
    String token;
    @BeforeEach
    void constructPayload(){
        payload = "username password";
        token = new String(Base64.getEncoder().encode(payload.getBytes()));
    }

    @Test
    void encodePayload(){
        String expected = token;
        String actual = Token.encodePayload(payload);
        assertEquals(expected, actual);
    }

    @Test
    void decodeTest(){
        String expected = payload;
        String actual = Token.decodeToken(Token.encodePayload(payload));
        assertEquals(expected, actual);
    }

}
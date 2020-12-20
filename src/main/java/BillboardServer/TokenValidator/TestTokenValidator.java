package BillboardServer.TokenValidator;

import BillboardServer.TokenValidator.StubCases.ExpiredSessionStub;
import BillboardServer.TokenValidator.StubCases.NoSessionStub;
import BillboardServer.TokenValidator.StubCases.ValidSessionStub;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TestTokenValidator {
    TokenValidator validator;

    @Test
    void retrieveToken_noToken(){
        validator = new TokenValidator(new NoSessionStub());
        String actual = validator.retrieveToken();
        assertEquals("No token found", actual);
    }
    @Test
    void validateToken_noToken(){
        validator = new TokenValidator(new NoSessionStub());
        String actual = validator.validateToken("randomtoken");
        assertEquals("Session expired", actual);
    }
    @Test
    void retrieveToken_expiredToken(){
        validator = new TokenValidator(new ExpiredSessionStub());
        String actual = validator.retrieveToken();
        assertEquals("Session expired", actual);
    }

    @Test
    void validateToken_expiredToken() {
        validator = new TokenValidator(new ExpiredSessionStub());
        String actual = validator.validateToken("abcd");
        assertEquals("Session expired", actual);
    }
    @Test
    void retrieveToken_validToken(){
        validator = new TokenValidator(new ValidSessionStub());
        String actual = validator.retrieveToken();
        assertEquals("abcd", actual);
    }

    @Test
    void validateToken_validToken() {
        validator = new TokenValidator(new ValidSessionStub());
        String actual = validator.validateToken("abcd");
        assertEquals("abcd", actual);
    }

    @Test
    void retrieveToken_deleteExpiredToken(){
        ExpiredSessionStub exp = new ExpiredSessionStub();
        validator = new TokenValidator(exp);
        validator.retrieveToken();
        assertEquals(exp.getToken(), null);
    }
    @Test
    void validateToken_deleteExpiredToken(){
        ExpiredSessionStub exp = new ExpiredSessionStub();
        validator = new TokenValidator(exp);
        validator.validateToken("abcd");
        assertEquals(exp.getToken(), null);
    }

    @Test
    void retrieveToken_updateExpiry(){
        ValidSessionStub valid = new ValidSessionStub();
        validator = new TokenValidator(valid);
        String actual = validator.retrieveToken();
        long oneDay = new Date(new Date().getTime() + 24 * 60 * 60 * 1000).getTime();
        assertEquals(oneDay, valid.getExpiry().getTime());
    }

    @Test
    void validateToken_updateExpiry(){
        ValidSessionStub valid = new ValidSessionStub();
        validator = new TokenValidator(valid);
        String actual = validator.validateToken("abcd");
        long oneDay = new Date(new Date().getTime() + 24 * 60 * 60 * 1000).getTime();
        assertEquals(oneDay, valid.getExpiry().getTime());
    }
}
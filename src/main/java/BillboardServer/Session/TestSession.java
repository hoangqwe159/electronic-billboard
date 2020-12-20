package BillboardServer.Session;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TestSession {
    Session session = new Session();
    @Test
    void setToken_nullCase(){
        session.setToken(null);
        assertEquals(null, session.getExpiry());
    }

    @Test
    void setToken_validCase() {
        session.setToken("validtoken");
        Date expired = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
        assertEquals(expired, session.getExpiry());
    }

    @Test
    void updateExpiry(){
        session.updateExpiry();
        Date expired = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
        assertEquals(expired, session.getExpiry());
    }
}
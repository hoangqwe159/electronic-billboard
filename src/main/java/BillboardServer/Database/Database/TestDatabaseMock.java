package BillboardServer.Database.Database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestDatabaseMock {
    @Test
    void initializeBeforeConnect(){
        assertThrows(Exception.class, () -> {
            DatabaseAbs db = new DatabaseMock();
            db.initialize();
        });
    }
}
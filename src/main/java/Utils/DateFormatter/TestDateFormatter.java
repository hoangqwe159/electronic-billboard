package Utils.DateFormatter;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TestDateFormatter {
    @Test
    void NullInput() {
        Date actual = DateFormatter.formatDate(null);
        assertEquals(null, actual);
    }

    @Test
    void IncorrectFormat() {
        Date actual = DateFormatter.formatDate("03/22/2020");
        assertEquals(null, actual);
    }

    @Test
    void normalCase() {
        Date actual = DateFormatter.formatDate("2020-03-14 11:20:22");
        assertEquals((Double) 1.584148822E12, actual.getTime());
    }


}
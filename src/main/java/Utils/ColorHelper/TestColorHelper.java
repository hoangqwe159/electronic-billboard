package Utils.ColorHelper;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TestColorHelper {
    @Test
    void NullInputHex2RGB() {
        Color actual = ColorHelper.hex2Rgb(null);
        assertEquals(null, actual);
    }

    @Test
    void IncorrectFormatHex2RGB() {
        Color actual = ColorHelper.hex2Rgb("abcdef");
        assertEquals(null, actual);
    }

    @Test
    void normalCaseHex2RGB() {
        Color actual = ColorHelper.hex2Rgb("#FF0000");
        assertEquals(new Color(255,0,0), actual);
    }



    @Test
    void notIsHexString() {
        boolean actual = ColorHelper.isHexString("abc");
        assertEquals(false, actual);
    }

    @Test
    void normalCaseIsHexString() {
        boolean actual = ColorHelper.isHexString("#FF0000");
        assertEquals(true, actual);
    }
}
package Utils.ColorHelper;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Providing methods to handle color hex string
 */
public class ColorHelper {

    /**
     * Convert hex string color to Color
     * @param colorStr - hex string of a color
     * @return Color
     */
    static public Color hex2Rgb(String colorStr) {
        try {
            Color color = new Color(
                    Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                    Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                    Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
            return color;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * Check if a string is a hex string
     * @param colorString - string of color
     * @return true if string is hex string, false if not
     */
    static public boolean isHexString(String colorString) {
        String hexPattern = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
        Pattern pattern = Pattern.compile(hexPattern);
        Matcher matcher = pattern.matcher(colorString);
        return matcher.matches();

    }


}

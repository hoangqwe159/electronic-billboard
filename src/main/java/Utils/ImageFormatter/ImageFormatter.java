package Utils.ImageFormatter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * Providing methods for encode and decode image
 */
public class ImageFormatter {

    /**
     * Encode image to base64
     * @param filePath - image pathname
     * @return encoded string
     */
    static public String pathToData(String filePath) {
        try {
            File file = new File(filePath);
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            String encodedFile = Base64.getEncoder().encodeToString(bytes);
            return encodedFile;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Check if string is a base64
     * @param s - input string
     * @return boolean value
     */
    static public boolean isBase64String(String s) {
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            decoder.decode(s);
            return true;
        } catch(IllegalArgumentException iae) {
            return false;
        }
    }
}

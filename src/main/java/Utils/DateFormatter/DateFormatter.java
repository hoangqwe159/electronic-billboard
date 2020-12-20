package Utils.DateFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Format the date string into Date
 */
public class DateFormatter {

    /**
     * Parsing the string in to Date
     * @param dateAsString - Date as string
     * @return Date
     */
    public static Date formatDate (String dateAsString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (dateAsString == null) {
            return null;
        }
        try{
            Date d = formatter.parse(dateAsString);
            return d;
        } catch (ParseException e) {
            return null;
        }
    }
}

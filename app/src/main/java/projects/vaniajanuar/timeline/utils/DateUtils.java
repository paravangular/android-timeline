package projects.vaniajanuar.timeline.utils;

import android.nfc.FormatException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by vania on 6/10/16.
 */
public class DateUtils {

    public static Long convertDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(dateString);
            long dateInt = date.getTime();
            return dateInt;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDateString(long dateInMillis) {
        Date date = new Date(dateInMillis);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);
    }
}

package projects.vaniajanuar.timeline.utils;

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
}

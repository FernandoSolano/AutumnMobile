package cr.ac.ucr.paraiso.autumnmobile.common;

import android.app.Activity;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cr.ac.ucr.paraiso.autumnmobile.ValuationsActivity;

public class CalendarUtils {

    public static Date toDateFormat(String stringDate, Activity activity){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(stringDate);
            return date;
        } catch (ParseException e) {
            Toast.makeText(activity, "Unable to format date: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    public static String toStringFormat(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
    }
}

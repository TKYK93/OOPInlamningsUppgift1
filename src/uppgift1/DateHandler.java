package uppgift1;

import java.time.Duration;
import java.time.LocalDate;

public class DateHandler {

    public static String[] removeHyphenFromDate(String date){
        return date.split("-");
    }

    public static boolean checkTimeDiff(String[] date1, String[] date2){
        LocalDate formattedDate1 = LocalDate.of(
                Integer.parseInt(date1[0]), Integer.parseInt(date1[1]), Integer.parseInt(date1[2]));
        LocalDate formattedDate2 = LocalDate.of(
                Integer.parseInt(date2[0]), Integer.parseInt(date2[1]), Integer.parseInt(date2[2]));
        Duration timeDiff = Duration.between(formattedDate1.atStartOfDay(), formattedDate2.atStartOfDay());

        if(Math.abs(timeDiff.toDays()) <= 365){
            return true;
        }else{
            return false;
        }
    }

}

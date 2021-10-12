package uppgift1;

import java.time.Duration;
import java.time.LocalDate;

public class DateHandler {

    public static Duration checkTimeDiff(String date1, String date2){
        LocalDate formattedDate1 = LocalDate.parse(date1);
        LocalDate formattedDate2 = LocalDate.parse(date2);
        return Duration.between(formattedDate1.atStartOfDay(), formattedDate2.atStartOfDay());
    }

    public static boolean isTimeDiffWithin365Days(Duration timeDiff) {
        if(Math.abs(timeDiff.toDays()) <= 365){
            return true;
        }else{
            return false;
        }
    }

}

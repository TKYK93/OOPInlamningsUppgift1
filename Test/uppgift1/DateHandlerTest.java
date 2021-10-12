package uppgift1;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateHandlerTest {

    @Test
    void checkTimeDiff(){
        String today = "2021-10-12";
        String date1 = "2021-10-07";
        String date2 = "2019-06-20";
        System.out.println(DateHandler.checkTimeDiff(date2, today));
        assertTrue(DateHandler.checkTimeDiff(date1, today)
                .equals(Duration.between(LocalDate.parse(date1).atStartOfDay(), LocalDate.parse(today).atStartOfDay())));
        assertTrue(DateHandler.checkTimeDiff(date2, today)
                .equals(Duration.between(LocalDate.parse(date2).atStartOfDay(), LocalDate.parse(today).atStartOfDay())));
    }

    @Test
    void isTimeDiffWithin365Days(){
        LocalDate date1 = LocalDate.parse("2021-10-12");
        LocalDate date2 = LocalDate.parse("2021-10-07");
        LocalDate date3 = LocalDate.parse("2019-06-20");
        Duration duration1 = Duration.between(date1.atStartOfDay(), date2.atStartOfDay());
        Duration duration2 = Duration.between(date1.atStartOfDay(), date3.atStartOfDay());
        assertTrue(DateHandler.isTimeDiffWithin365Days(duration1) == true);
        assertTrue(DateHandler.isTimeDiffWithin365Days(duration2) == false);
    }



}
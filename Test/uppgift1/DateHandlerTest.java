package uppgift1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DateHandlerTest {

    @Test
    void removeHyphenFromDate(){
        String date1 = "2021-10-07";
        String date2 = "2020-10-06";
        String[] result1 = {"2021", "10", "07"};
        String[] result2 = {"2020", "10", "06"};
        assertTrue(Arrays.equals(DateHandler.removeHyphenFromDate(date1), result1));
        assertTrue(Arrays.equals(DateHandler.removeHyphenFromDate(date2), result2));
    }

    @Test
    void checkTimeDiff(){
        String[] today = {"2021", "10", "08"};
        String[] date1 = {"2021", "10", "07"};
        String[] date2 = {"2020", "08", "06"};
        String[] date3 = {"2021", "06", "09"};
        assertTrue(DateHandler.checkTimeDiff(date1, today) == true);
        assertFalse(DateHandler.checkTimeDiff(date1, today) == false);
        assertTrue(DateHandler.checkTimeDiff(date2, today) == false);
        assertTrue(DateHandler.checkTimeDiff(date3, today) == true);
    }


}
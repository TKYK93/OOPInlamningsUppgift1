package uppgift1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecorderTest {

    Recorder recorder = new Recorder();

    @Test
    void checkRecordExistence(){
        String fileName1= "src/uppgift1/customer1.txt";
        String fileName2= "customer6";
        assertTrue(recorder.checkRecordExistence(fileName1) == true);
        assertTrue(recorder.checkRecordExistence(fileName2) == false);
    }

    @Test
    void createRecord() {
        String name1= "customer1";
        long id1 = 234321;
        String name2= "customer5";
        long id2 = 234325;
        assertTrue(recorder.createRecord(name1, id1) == true);
        assertTrue(recorder.createRecord(name2, id2) == true);
    }

    @Test
    void printRecord() {
        String name1= "customer1";
        String name2= "customer6";
        assertTrue(recorder.printRecord(name1) == true);
        assertTrue(recorder.printRecord(name2) == false);
    }


}
package uppgift1;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Recorder {

    public boolean checkRecordExistence(String fileName){
        File file = new File(fileName);
        boolean isFileExists = file.exists();
        if(file.exists()){
            return true;
        } else {
            return false;
        }
    }

    public boolean createRecord(String name, long id){
        String fileName = "src/uppgift1/" + name + ".txt";
        boolean isFileExist = checkRecordExistence(fileName);
        BufferedWriter bw;
        try{
            if(!isFileExist){
                bw = new BufferedWriter(new FileWriter(fileName));
                bw.write(name + ", " + id);
                bw.newLine();
            } else {
                bw = new BufferedWriter(new FileWriter(fileName, true));
            }
            LocalDate today = LocalDate.now();
            bw.write(today.toString());
            bw.newLine();
            bw.close();
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean printRecord(String name) {
        String recordName = "src/uppgift1/ " + name + ".txt";
        if(!checkRecordExistence(recordName)){
            return false;
        }

        try(Scanner scanner = new Scanner(new FileReader(recordName));){
            while(scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }
}

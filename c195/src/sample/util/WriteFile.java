package sample.util;

import sample.Model.User;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class WriteFile {


    private String path;
    private boolean shouldAppendToFile = false;

    public WriteFile(String filePath, boolean shouldAppendToFile){
        path = filePath;
        this.shouldAppendToFile = shouldAppendToFile;
    }

    public WriteFile(String filePath){
        path = filePath;
    }

    public void recordLogin(User user, Calendar calendar) throws IOException {

        String userName = user.getUserName();
        String time = calendar.getTime().toString();


        String total = "User: " + userName +" Logged in at: " + time;
        writeToFile(total);
    }


    public void writeToFile(String text) throws  IOException{
        FileWriter writer = new FileWriter(path, shouldAppendToFile);
        PrintWriter printer = new PrintWriter(writer);

        printer.printf("%s" + "%n", text);

        printer.close();
    }
}

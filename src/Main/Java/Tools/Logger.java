package Main.Java.Tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;  
import java.util.Date; 

public class Logger {
    public static void log(String message) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();
        String logMSG = String.format("[%s] <%s> %s",formatter.format(date), KDebug.getCallerCallerClassName(), message);
        
        System.out.println(logMSG);

        File file = new File("src/Resources/Log.txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(logMSG);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
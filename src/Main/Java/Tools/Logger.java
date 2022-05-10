package Main.Java.Tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;  
import java.util.Date; 

public class Logger {
    public static void log(String message) {
        print(message, false, null);
    }
    public static void log(String message, boolean error) {
        print(message, error, null);
    }
    public static void log(String message, String type) {
        print(message, false, type);
    }

    private static void print(String message, boolean error, String type) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();
        String logMSG;
        if (!error) {
            if (type != null) {
                logMSG = String.format("[%s] <%s> [%s] %s",formatter.format(date), KDebug.getCallerCallerClassName(), type, message);
            }
            else {
                logMSG = String.format("[%s] <%s> [INFO] %s",formatter.format(date), KDebug.getCallerCallerClassName(), message);
            }
        }
        else {
            logMSG = String.format("[%s] <%s> [ERROR] %s",formatter.format(date), KDebug.getCallerCallerClassName(), message);
        }

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
            System.out.println("Error al escribir en el log");
        }
    }
}
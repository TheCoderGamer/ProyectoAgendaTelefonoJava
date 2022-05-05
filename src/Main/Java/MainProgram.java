package Main.Java;

import Main.Java.GUI.MainGUI;
import Main.Java.Tools.Logger;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;

import Main.Java.BBDD.DatabaseManager;
import Main.Java.BBDD.SQLiteCon;


public class MainProgram {
    
    public static Connection con;
    
    public static void main(String[] args) {
        
        Init();

        GUI();

        Exit();

    }

    
    
    private static void Init() {
        con = SQLiteCon.getConnection();

        boolean tablesOK = false;
        try {
            tablesOK = DatabaseManager.CheckTables();
        } catch (SQLException e) {
            Logger.log("Error de SQL al comprobar las tablas");
            Logger.log(e.getMessage());
        }
        if (!tablesOK) {
            Logger.log("Tablas no correctas.. Creando nuevas tablas");
            try {
                DatabaseManager.DropAllTables();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                Logger.log(e.getMessage());
            }
            try {
                DatabaseManager.CreateNewTables();
            } catch (SQLException e) {
                Logger.log("Error de SQL al crear las tablas");
                Logger.log(e.getMessage());
            }
            Logger.log("Tablas creadas correctamente");
        }
        else{ Logger.log("Tablas correctas"); }
    }

    private static void GUI() {
    EventQueue.invokeLater(new Runnable() {
        public void run() {
                try {
                    new MainGUI();
                } catch (Exception e) {
                    Logger.log("Error al crear la GUI");
                    Logger.log(e.getMessage());
                }
            }
        });
    }

    private static void Exit() {
    }
    
}
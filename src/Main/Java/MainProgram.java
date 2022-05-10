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

        Logger.log("Programa iniciado");

    }

    
    
    private static void Init() {
        Logger.log("Iniciando programa...");

        con = SQLiteCon.getConnection();

        boolean tablesOK = false;
        try {
            tablesOK = DatabaseManager.CheckTables();
        } catch (SQLException e) {
            Logger.log("Error de SQL al comprobar las tablas", true);
            Logger.log(e.getMessage());
        }
        if (!tablesOK) {
            Logger.log("Tablas no correctas.. Creando nuevas tablas");
            try {
                DatabaseManager.DropAllTables();
            } catch (Exception e) {
                Logger.log("Error al borrar las tablas", true);
                Logger.log(e.getMessage(), true);
            }
            try {
                DatabaseManager.CreateNewTables();
            } catch (SQLException e) {
                Logger.log("Error de SQL al crear las tablas", true);
                Logger.log(e.getMessage(), true);
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
                    Logger.log("Error al crear la GUI", true);
                    Logger.log(e.getMessage());
                }
            }
        });
    }    
}
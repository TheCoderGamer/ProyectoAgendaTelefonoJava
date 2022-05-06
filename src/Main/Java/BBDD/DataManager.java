package Main.Java.BBDD;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;

import Main.Java.Tools.Logger;

public class DataManager {

    public static void insertAficion(String aficion) {
        Logger.log("Insertando aficion: " + aficion);
        DatabaseManager.updateSQL("INSERT INTO aficiones (aficion) VALUES ('" + aficion + "')");
    }
    public static void editAficion(String oldAficion, String newAficion) {
        Logger.log("Editando aficion: " + oldAficion + " por " + newAficion);
        DatabaseManager.updateSQL("UPDATE aficiones SET aficion = '" + newAficion + "' WHERE aficion = '" + oldAficion + "'");
    }
    public static void deleteAficion(String aficion) {
        Logger.log("Borrando aficion: " + aficion);
        DatabaseManager.updateSQL("DELETE FROM aficiones WHERE aficion = '" + aficion + "'");
    }
    
    public static DefaultListModel<String> getAficiones() {
        DefaultListModel<String> model = new DefaultListModel<String>();
        ResultSet rs = DatabaseManager.getSQL("SELECT * FROM aficiones");
        try {
            while (rs.next()) {
                model.addElement(rs.getString(2));
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
        return model;
    }   
    public static DefaultListModel<String> getContactos() {
        DefaultListModel<String> model = new DefaultListModel<String>();
        ResultSet rs = DatabaseManager.getSQL("SELECT * FROM contactos");
        try {
            while (rs.next()) {
                model.addElement(rs.getInt(1) + "| " + rs.getString(3) + " " + rs.getString(6));
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
        return model;
    }

    public static DefaultListModel<String> getCorreosContacto(Integer IDcontacto) {
        DefaultListModel<String> model = new DefaultListModel<String>();
        ResultSet rs = DatabaseManager.getSQL("select correo from correos b, contactoscorreos a where b.idcorreo = a.idcorreo AND a.idcontacto = " + IDcontacto + ";");
        try {
            while (rs.next()) {
                model.addElement(rs.getString(1));
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
        return model;
    }
    public static DefaultListModel<String> getTelefonosContacto(Integer IDcontacto) {
        DefaultListModel<String> model = new DefaultListModel<String>();
        ResultSet rs = DatabaseManager.getSQL("select telefono from telefonos b, contactostelefonos a where b.idtelefono = a.idtelefono AND a.idcontacto = " + IDcontacto + ";");
        try {
            while (rs.next()) {
                model.addElement(rs.getString(1));
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
        return model;
    }
    public static DefaultListModel<String> getAficionesContacto(Integer IDcontacto) {
        DefaultListModel<String> model = new DefaultListModel<String>();
        ResultSet rs = DatabaseManager.getSQL("select aficion from aficiones b, contactosaficiones a where b.idaficion = a.idaficion AND a.idcontacto = " + IDcontacto + ";");
        try {
            while (rs.next()) {
                model.addElement(rs.getString(1));
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
        return model;
    }
    public static String getNombreContacto(Integer iDcontacto) {
        String nombre = "";
        ResultSet rs = DatabaseManager.getSQL("SELECT nombre FROM contactos WHERE IDcontacto = " + iDcontacto);
        try {
            while (rs.next()) {
                nombre = rs.getString(1);
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
        return nombre;
    }
    public static String getApellidosContacto(Integer iDcontacto) {
        String apellidos = "";
        ResultSet rs = DatabaseManager.getSQL("SELECT apellidos FROM contactos WHERE IDcontacto = " + iDcontacto);
        try {
            while (rs.next()) {
                apellidos = rs.getString(1);
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
        return apellidos;
    }
    public static String getDireccionContacto(Integer iDcontacto) {
        String direccion = "";
        ResultSet rs = DatabaseManager.getSQL("SELECT direccion FROM contactos WHERE IDcontacto = " + iDcontacto);
        try {
            while (rs.next()) {
                direccion = rs.getString(1);
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
        return direccion;
    }
    public static String getFechaNacimientoContacto(Integer iDcontacto) {
        String fechaNacimiento = "";
        ResultSet rs = DatabaseManager.getSQL("SELECT fechaNac FROM contactos WHERE IDcontacto = " + iDcontacto);
        try {
            while (rs.next()) {
                fechaNacimiento = rs.getString(1);
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
        return fechaNacimiento;
    }
    public static String getGeneroContacto(Integer iDcontacto) {
        String genero = "";
        ResultSet rs = DatabaseManager.getSQL("SELECT genero FROM contactos WHERE IDcontacto = " + iDcontacto);
        try {
            while (rs.next()) {
                genero = rs.getString(1);
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
        return genero;
    }
    public static String getNotasContacto(Integer iDcontacto) {
        String notas = "";
        ResultSet rs = DatabaseManager.getSQL("SELECT notas FROM contactos WHERE IDcontacto = " + iDcontacto);
        try {
            while (rs.next()) {
                notas = rs.getString(1);
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
        return notas;
    }
    public static DefaultListModel<String> getCorreos() {
        DefaultListModel<String> model = new DefaultListModel<String>();
        ResultSet rs = DatabaseManager.getSQL("SELECT * FROM correos");
        try {
            while (rs.next()) {
                model.addElement(rs.getString(2));
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
        return model;
    }
    public static DefaultListModel<String> getTelefonos() {
        DefaultListModel<String> model = new DefaultListModel<String>();
        ResultSet rs = DatabaseManager.getSQL("SELECT * FROM telefonos");
        try {
            while (rs.next()) {
                model.addElement(rs.getString(2));
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
        return model;
    }

    public static void insertCorreo(String newData) {
        Logger.log("Insertando correo: " + newData);
        DatabaseManager.updateSQL("INSERT INTO correos (correo) VALUES ('" + newData + "')");
    }
    public static void editCorreo(String edicion, String newData) {
        Logger.log("Editando correo: " + edicion + " por " + newData);
        DatabaseManager.updateSQL("UPDATE correos SET correo = '" + newData + "' WHERE correo = '" + edicion + "'");
    }
    public static void deleteCorreo(String oldData) {
        Logger.log("Eliminando correo: " + oldData);
        DatabaseManager.updateSQL("DELETE FROM correos WHERE correo = '" + oldData + "'");
    }
}

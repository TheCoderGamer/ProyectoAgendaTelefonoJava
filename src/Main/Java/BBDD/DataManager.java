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
    public static void deleteAficion(String oldData) {
        Logger.log("Borrando aficion: " + oldData);
        int idAficion = getIdByString("aficiones", "idaficion", "aficion" ,oldData);
        DatabaseManager.updateSQL("DELETE FROM contactosaficiones WHERE idaficion = '" + idAficion + "'");
        DatabaseManager.updateSQL("DELETE FROM aficiones WHERE idaficion = '" + idAficion + "'");
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
        int idCorreo = getIdByString("correos", "idcorreo", "correo" ,oldData);
        DatabaseManager.updateSQL("DELETE FROM contactoscorreos WHERE idcorreo = '" + idCorreo + "'");
        DatabaseManager.updateSQL("DELETE FROM correos WHERE idcorreo = '" + idCorreo + "'");
    }
    public static void insertTelefono(String newData) {
        Logger.log("Insertando telefono: " + newData);
        DatabaseManager.updateSQL("INSERT INTO telefonos (telefono) VALUES ('" + newData + "')");
    }
    public static void editTelefono(String edicion, String newData) {
        Logger.log("Editando telefono: " + edicion + " por " + newData);
        DatabaseManager.updateSQL("UPDATE telefonos SET telefono = '" + newData + "' WHERE telefono = '" + edicion + "'");
    }
    public static void deleteTelefono(String oldData) {
        Logger.log("Eliminando telefono: " + oldData);
        int idTelefono = getIdByString("telefonos", "idtelefono", "telefono" ,oldData);
        DatabaseManager.updateSQL("DELETE FROM contactostelefonos WHERE idtelefono = '" + idTelefono + "'");
        DatabaseManager.updateSQL("DELETE FROM telefonos WHERE idtelefono = '" + idTelefono + "'");
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

    
    
    public static void insertarContactoAficion(Integer iDcontacto, String string) {
        ResultSet rs;
        Integer idaficion = getIdByString("Aficiones", "IDaficion", "aficion", string);
        
        // Comprueba si existe la aficion en la tabla contactosaficiones y lo incluye si no está
        rs = DatabaseManager.getSQL("SELECT * FROM contactosaficiones WHERE idcontacto = " + iDcontacto + " AND idaficion = " + idaficion);
        try {
            if (!rs.next()) {
                DatabaseManager.updateSQL("INSERT INTO contactosaficiones (idcontacto, idaficion) VALUES (" + iDcontacto + ", " + idaficion + ")");
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
        
        try {
            rs.close();
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
    }
    public static void borrarContactoAficion(Integer iDcontacto, String string) {
        ResultSet rs;
        Integer idaficion = getIdByString("Aficiones", "IDaficion", "aficion", string);

        // Comprueba si existe la aficion en la tabla contactosaficiones y lo elimina si está
        rs = DatabaseManager.getSQL("SELECT * FROM contactosaficiones WHERE idcontacto = " + iDcontacto + " AND idaficion = " + idaficion);
        try {
            if (rs.next()) {
                DatabaseManager.updateSQL("DELETE FROM contactosaficiones WHERE idcontacto = " + iDcontacto + " AND idaficion = " + idaficion);
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }

        try {
            rs.close();
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
    }
    public static void insertarContactoCorreo(Integer iDcontacto, String string) {
        ResultSet rs;
        Integer idcorreo = getIdByString("Correos", "IDcorreo", "correo", string);

        // Comprueba si existe el correo en la tabla contactoscorreos y lo incluye si no está
        rs = DatabaseManager.getSQL("SELECT * FROM contactoscorreos WHERE idcontacto = " + iDcontacto + " AND idcorreo = " + idcorreo);
        try {
            if (!rs.next()) {
                DatabaseManager.updateSQL("INSERT INTO contactoscorreos (idcontacto, idcorreo) VALUES (" + iDcontacto + ", " + idcorreo + ")");
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
        
        try {
            rs.close();
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
    }
    public static void borrarContactoCorreo(Integer iDcontacto, String string) {
        ResultSet rs;
        Integer idcorreo = getIdByString("Correos", "IDcorreo", "correo", string);
        
        // Comprueba si existe el correo en la tabla contactoscorreos y lo elimina si está
        rs = DatabaseManager.getSQL("SELECT * FROM contactoscorreos WHERE idcontacto = " + iDcontacto + " AND idcorreo = " + idcorreo);
        try {
            if (rs.next()) {
                DatabaseManager.updateSQL("DELETE FROM contactoscorreos WHERE idcontacto = " + iDcontacto + " AND idcorreo = " + idcorreo);
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }

        try {
            rs.close();
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
    }
    public static void insertarContactoTelefono(Integer iDcontacto, String string) {
        ResultSet rs;
        Integer idtelefono = getIdByString("Telefonos", "IDtelefono", "telefono", string);

        // Comprueba si existe el telefono en la tabla contactostelefonos y lo incluye si no está
        rs = DatabaseManager.getSQL("SELECT * FROM contactostelefonos WHERE idcontacto = " + iDcontacto + " AND idtelefono = " + idtelefono);
        try {
            if (!rs.next()) {
                DatabaseManager.updateSQL("INSERT INTO contactostelefonos (idcontacto, idtelefono) VALUES (" + iDcontacto + ", " + idtelefono + ")");
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }

        try {
            rs.close();
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
    }
    public static void borrarContactoTelefono(Integer iDcontacto, String string) {
        ResultSet rs;
        Integer idtelefono = getIdByString("Telefonos", "IDtelefono", "telefono",  string);

        // Comprueba si existe el telefono en la tabla contactostelefonos y lo elimina si está
        rs = DatabaseManager.getSQL("SELECT * FROM contactostelefonos WHERE idcontacto = " + iDcontacto + " AND idtelefono = " + idtelefono);
        try {
            if (rs.next()) {
                DatabaseManager.updateSQL("DELETE FROM contactostelefonos WHERE idcontacto = " + iDcontacto + " AND idtelefono = " + idtelefono);
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }

        try {
            rs.close();
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
    }
    
    private static Integer getIdByString(String tabla, String tablaNameID, String tablaNameData , String data) {
        Integer ID = 0;
        String sql = "SELECT " + tablaNameID + " FROM " + tabla + " WHERE " + tablaNameData + " = '" + data + "'";
        ResultSet rs = DatabaseManager.getSQL(sql);
        try {
            while (rs.next()) {
                ID = rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
        return ID;
    }
    public static void addContact(
        String nombre, 
        String apellidos, 
        String direccion,
        String fechanac, 
        String notas, 
        String genero,
        DefaultListModel<String> mdl_aficiones, 
        DefaultListModel<String> mdl_correos,
        DefaultListModel<String> mdl_telefonos) 
        {
            // Crear contacto con lo minimo
            DatabaseManager.updateSQL("INSERT INTO contactos (nombre) VALUES ('" + nombre + "')");

            // Obtener el ID del contacto
            Integer idcontacto = getIdByString("Contactos", "IDcontacto", "nombre", nombre);

            // Insertar los demas datos en el contacto
            DatabaseManager.updateSQL("UPDATE contactos SET apellidos = '" + apellidos + "' WHERE IDcontacto = " + idcontacto);
            DatabaseManager.updateSQL("UPDATE contactos SET direccion = '" + direccion + "' WHERE IDcontacto = " + idcontacto);
            DatabaseManager.updateSQL("UPDATE contactos SET fechanac = '" + fechanac + "' WHERE IDcontacto = " + idcontacto);
            DatabaseManager.updateSQL("UPDATE contactos SET notas = '" + notas + "' WHERE IDcontacto = " + idcontacto);
            DatabaseManager.updateSQL("UPDATE contactos SET genero = '" + genero + "' WHERE IDcontacto = " + idcontacto);
                       
            for (int i = 0; i < mdl_aficiones.getSize(); i++) {
                insertarContactoAficion(idcontacto, mdl_aficiones.get(i));
            }
            for (int i = 0; i < mdl_correos.getSize(); i++) {
                insertarContactoCorreo(idcontacto, mdl_correos.get(i));
            }
            for (int i = 0; i < mdl_telefonos.getSize(); i++) {
                insertarContactoTelefono(idcontacto, mdl_telefonos.get(i));
            }
            

    }
    public static void editContact(
        int idcontacto,
        String nombre, 
        String apellidos, 
        String direccion,
        String fechanac, 
        String notas, 
        String genero,
        DefaultListModel<String> mdl_aficiones, 
        DefaultListModel<String> mdl_correos,
        DefaultListModel<String> mdl_telefonos) 
    {                   
        // Actualizar los datos en el contacto
        DatabaseManager.updateSQL("UPDATE contactos SET nombre = '" + nombre + "' WHERE IDcontacto = " + idcontacto);
        DatabaseManager.updateSQL("UPDATE contactos SET apellidos = '" + apellidos + "' WHERE IDcontacto = " + idcontacto);
        DatabaseManager.updateSQL("UPDATE contactos SET direccion = '" + direccion + "' WHERE IDcontacto = " + idcontacto);
        DatabaseManager.updateSQL("UPDATE contactos SET fechanac = '" + fechanac + "' WHERE IDcontacto = " + idcontacto);
        DatabaseManager.updateSQL("UPDATE contactos SET notas = '" + notas + "' WHERE IDcontacto = " + idcontacto);
        DatabaseManager.updateSQL("UPDATE contactos SET genero = '" + genero + "' WHERE IDcontacto = " + idcontacto);
                    
        // Borrar todos las aficiones del contacto
        DatabaseManager.updateSQL("DELETE FROM contactosaficiones WHERE idcontacto = " + idcontacto);
        // Insertar las aficiones del contacto
        for (int i = 0; i < mdl_aficiones.getSize(); i++) {
            insertarContactoAficion(idcontacto, mdl_aficiones.get(i));
        }
        // Borrar todos los correos del contacto
        DatabaseManager.updateSQL("DELETE FROM contactoscorreos WHERE idcontacto = " + idcontacto);
        // Insertar los correos del contacto
        for (int i = 0; i < mdl_correos.getSize(); i++) {
            insertarContactoCorreo(idcontacto, mdl_correos.get(i));
        }
        // Borrar todos los telefonos del contacto
        DatabaseManager.updateSQL("DELETE FROM contactostelefonos WHERE idcontacto = " + idcontacto);
        // Insertar los telefonos del contacto
        for (int i = 0; i < mdl_telefonos.getSize(); i++) {
            insertarContactoTelefono(idcontacto, mdl_telefonos.get(i));
        }
    }
    public static void deleteContact(Integer iDcontacto) {
        DatabaseManager.updateSQL("DELETE FROM contactos WHERE IDcontacto = " + iDcontacto);
        DatabaseManager.updateSQL("DELETE FROM contactosaficiones WHERE idcontacto = " + iDcontacto);
        DatabaseManager.updateSQL("DELETE FROM contactoscorreos WHERE idcontacto = " + iDcontacto);
        DatabaseManager.updateSQL("DELETE FROM contactostelefonos WHERE idcontacto = " + iDcontacto);
    }
}

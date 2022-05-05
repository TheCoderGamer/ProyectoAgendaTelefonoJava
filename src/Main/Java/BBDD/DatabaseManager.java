package Main.Java.BBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Main.Java.Tools.Logger;

public class DatabaseManager {

    private static Connection con = SQLiteCon.getConnection();

    public static void updateSQL(String sql) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
    }
    public static ResultSet getSQL(String sql) {
        ResultSet rs = null;
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Logger.log(e.getMessage());
        }
        return rs;
    }


    public static boolean CheckTables() throws SQLException {
        String sql = "SELECT NAME FROM sqlite_schema WHERE type ='table' AND name NOT LIKE 'sqlite_%' ORDER BY NAME ASC;";
		Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        int j = 0;
        for (int i = 0; rs.next(); i++) {
            j = i;
            switch (i) {
                case 0:
                    if (!rs.getString(1).equals("AFICIONES")) {
                        System.err.println("Error: La tabla AFICIONES no existe");
                        st.close();
                        rs.close();
                        return false; }
                    break;
                case 1:
                    if (!rs.getString(1).equals("CONTACTOS")) {
                        System.err.println("Error: La tabla CONTACTOS no existe");
                        st.close();
                        rs.close();
                        return false; }
                    break;
                case 2:
                    if (!rs.getString(1).equals("CONTACTOSAFICIONES")) {
                        System.err.println("Error: La tabla CONTACTOSAFICIONES no existe");
                        st.close();
                        rs.close();
                        return false; }
                    break;
                case 3:
                    if (!rs.getString(1).equals("CONTACTOSCORREOS")) {
                        System.err.println("Error: La tabla CONTACTOSCORREOS no existe");
                        st.close();
                        rs.close();
                        return false; }
                    break;
                case 4:
                    if (!rs.getString(1).equals("CONTACTOSTELEFONOS")) {
                        System.err.println("Error: La tabla CONTACTOSTELEFONOS no existe");
                        st.close();
                        rs.close();
                        return false; }
                    break;
                case 5:
                    if (!rs.getString(1).equals("CORREOS")) {
                        System.err.println("Error: La tabla CORREOS no existe");
                        st.close();
                        rs.close();
                        return false; }
                    break;
                case 6:
                    if (!rs.getString(1).equals("TELEFONOS")) {
                        System.err.println("Error: La tabla TELEFONOS no existe");
                        st.close();
                        rs.close();
                        return false; }
                    break;
                default:
                    System.err.println("Error: existen otras tablas");
                    st.close();
                    rs.close();
                    return false;
            }
        }
        st.close();
        rs.close();
        if (j==6){return true;}
        else{return false;}
    }
    public static void DropAllTables() throws SQLException {
        String sql = "PRAGMA writable_schema = 1; delete from sqlite_master where type in ('table', 'index', 'trigger'); PRAGMA writable_schema = 0;";
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        sql = "VACUUM;";
        st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        System.out.println("Base de datos eliminada");
    }
    public static void CreateNewTables() throws SQLException {
        String sql = "CREATE TABLE CONTACTOS( IDCONTACTO INTEGER PRIMARY KEY AUTOINCREMENT, TIPOCONTACTO CHAR(1), NOMBRE VARCHAR (20), DIRECCION VARCHAR (40), NOTAS VARCHAR (50), APELLIDOS VARCHAR (40), GENERO CHAR(1), FECHANAC CHAR(10)); CREATE TABLE CORREOS( IDCORREO INTEGER PRIMARY KEY AUTOINCREMENT, CORREO VARCHAR(40)); CREATE TABLE TELEFONOS (IDTELEFONO INTEGER PRIMARY KEY AUTOINCREMENT, TELEFONO NUMBER(10), PREFIJO NUMBER(2), TIPOTEL CHAR(1)); CREATE TABLE AFICIONES (IDAFICION INTEGER PRIMARY KEY AUTOINCREMENT, AFICION VARCHAR(20)); CREATE TABLE CONTACTOSAFICIONES (IDCONTACTO NUMBER(4), IDAFICION NUMBER(4), FOREIGN KEY(IDCONTACTO) REFERENCES CONTACTOS(IDCONTACTO), FOREIGN KEY(IDAFICION) REFERENCES AFICION(IDAFICION)); CREATE TABLE CONTACTOSCORREOS (IDCONTACTO NUMBER(4), IDCORREO NUMBER(4), FOREIGN KEY(IDCONTACTO) REFERENCES CONTACTOS(IDCONTACTO), FOREIGN KEY(IDCORREO) REFERENCES CORREOS(IDCORREOS)); CREATE TABLE CONTACTOSTELEFONOS ( IDCONTACTO NUMBER(4), IDTELEFONO NUMBER(4), FOREIGN KEY(IDCONTACTO) REFERENCES CONTACTOS(IDCONTACTO), FOREIGN KEY(IDTELEFONO) REFERENCES TELEFONOS(IDTELEFONO));";
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
    }
}
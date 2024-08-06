package Pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteConexion {
    // URL de la base de datos SQLite
    private static final String URL = "jdbc:sqlite:C:/Users/jose_/DBprueba.db";

    // Método para establecer la conexión con la base de datos SQLite
    public static Connection connect() {
        try {
            // Cargar el controlador JDBC para SQLite, el cual está dando errores
            Class.forName("org.sqlite.JDBC");
            // Establecer la conexión con la base de datos
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Conexión a la base de datos SQLite establecida.");
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("Error: controlador JDBC no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return null;
    }

    // Método para crear las tablas necesarias en la base de datos
    public static void createTables() {
        // SQL para crear la tabla 'Candidato'
        String sqlCandidato = "CREATE TABLE IF NOT EXISTS Candidato ("
                + "ID_candidato INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Nombre TEXT,"
                + "ApellidoPaterno TEXT,"
                + "ApellidoMaterno TEXT,"
                + "Educacion TEXT,"
                + "Experiencia TEXT,"
                + "Municipio TEXT,"
                + "TelefonoCasa TEXT,"
                + "TelefonoCelular TEXT"
                + ");";

        // Intentar conectar y crear la tabla
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlCandidato);
            System.out.println("Tabla 'Candidato' creada o verificada.");
        } catch (SQLException e) {
            System.out.println("Error al crear tablas: " + e.getMessage());
        }
    }
}
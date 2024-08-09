package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// Clase encargada de gestionar la conexión con la base de datos SQLite
public class conexion {
	
	 // Método para establecer la conexión con la base de datos
    private static final String URL = "jdbc:sqlite:DBportatil.db";

    public static Connection conectarDB() {
        try {
            Class.forName("org.sqlite.JDBC");
         // Carga el controlador JDBC de SQLite
            Connection cx = DriverManager.getConnection(URL);
            System.out.println("Conexión a la base de datos SQLite establecida.");
            return cx;
        } catch (ClassNotFoundException e) {
            System.out.println("Error: controlador JDBC no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return null;
    }

 // Método para crear las tablas necesarias en la base de datos si no existen
    public static void createTables() {
    	// Comando SQL para crear la tabla de Candidatos
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

        try (Connection conn = conectarDB();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlCandidato);
            System.out.println("Tabla 'Candidato' creada o verificada.");
        } catch (SQLException e) {
            System.out.println("Error al crear tablas: " + e.getMessage());
        }
    }
}
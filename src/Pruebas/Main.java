package Pruebas;

public class Main {
    public static void main(String[] args) {
        // Crear tablas al iniciar la aplicación
        SQLiteConexion.createTables();

        // Mostrar la interfaz para agregar candidatos
        javax.swing.SwingUtilities.invokeLater(() -> new AgregarCandidato().setVisible(true));
    }
}
package Pruebas;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultarCandidatos extends JFrame {
    private JTextArea areaTexto;

    // Constructor para inicializar la interfaz de usuario
    public ConsultarCandidatos() {
        setTitle("Consultar Candidatos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear área de texto para mostrar los candidatos
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        add(new JScrollPane(areaTexto), BorderLayout.CENTER);

        // Cargar candidatos desde la base de datos
        cargarCandidatos();
    }

    // Método para cargar candidatos desde la base de datos
    private void cargarCandidatos() {
        String consulta = "SELECT * FROM Candidato";
        try (Connection conn = SQLiteConexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(consulta);
             ResultSet rs = pstmt.executeQuery()) {

            // Leer resultados de la consulta y mostrarlos en el área de texto
            while (rs.next()) {
                String candidato = String.format("ID: %d, Nombre: %s %s %s, Educación: %s, Experiencia: %s, Municipio: %s, Teléfono Casa: %s, Teléfono Celular: %s\n",
                        rs.getInt("ID_candidato"),
                        rs.getString("Nombre"),
                        rs.getString("ApellidoPaterno"),
                        rs.getString("ApellidoMaterno"),
                        rs.getString("Educacion"),
                        rs.getString("Experiencia"),
                        rs.getString("Municipio"),
                        rs.getString("TelefonoCasa"),
                        rs.getString("TelefonoCelular"));
                areaTexto.append(candidato);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al consultar candidatos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsultarCandidatos().setVisible(true));
    }
}
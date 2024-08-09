package vista;

import Conexion.conexion;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AgregarCandidato extends JFrame {
    private JTextField nombre;
    private JTextField apellidoPaterno;
    private JTextField apellidoMaterno;
    private JTextField educacion;
    private JTextField experiencia;
    private JTextField municipio;
    private JTextField telefonoCasa;
    private JTextField telefonoCelular;
    private JButton guardarBtn;
    private JButton volverBtn;

 // Clase que representa la ventana para agregar un nuevo candidato
    public AgregarCandidato() {
        setTitle("Agregar Candidato");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));

        // Campos de texto para capturar los datos del candidato
        panel.add(new JLabel("Nombre:"));
        nombre = new JTextField();
        panel.add(nombre);

        panel.add(new JLabel("Apellido Paterno:"));
        apellidoPaterno = new JTextField();
        panel.add(apellidoPaterno);

        panel.add(new JLabel("Apellido Materno:"));
        apellidoMaterno = new JTextField();
        panel.add(apellidoMaterno);

        panel.add(new JLabel("Educación:"));
        educacion = new JTextField();
        panel.add(educacion);

        panel.add(new JLabel("Experiencia:"));
        experiencia = new JTextField();
        panel.add(experiencia);

        panel.add(new JLabel("Municipio:"));
        municipio = new JTextField();
        panel.add(municipio);

        panel.add(new JLabel("Teléfono de Casa:"));
        telefonoCasa = new JTextField();
        panel.add(telefonoCasa);

        panel.add(new JLabel("Teléfono Celular:"));
        telefonoCelular = new JTextField();
        panel.add(telefonoCelular);

        // Botón para guardar el candidato en la base de datos
        guardarBtn = new JButton("Guardar");
        guardarBtn.addActionListener(e -> {
        	 // Captura de datos ingresados por el usuario
            String nombreText = nombre.getText();
            String apellidoPaternoText = apellidoPaterno.getText();
            String apellidoMaternoText = apellidoMaterno.getText();
            String educacionText = educacion.getText();
            String experienciaText = experiencia.getText();
            String municipioText = municipio.getText();
            String telefonoCasaText = telefonoCasa.getText();
            String telefonoCelularText = telefonoCelular.getSelectedText();

            // Inserción de los datos en la base de datos
            try (Connection conn = new conexion().conectarDB();
                 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Candidato (Nombre, ApellidoPaterno, ApellidoMaterno, Educacion, Experiencia, Municipio, TelefonoCasa, TelefonoCelular) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
                pstmt.setString(1, nombreText);
                pstmt.setString(2, apellidoPaternoText);
                pstmt.setString(3, apellidoMaternoText);
                pstmt.setString(4, educacionText);
                pstmt.setString(5, experienciaText);
                pstmt.setString(6, municipioText);
                pstmt.setString(7, telefonoCasaText);
                pstmt.setString(8, telefonoCelularText);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Candidato agregado exitosamente");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al agregar candidato: " + ex.getMessage());
            }
        });
        panel.add(guardarBtn);
        // Botón para volver al menú principal
        volverBtn = new JButton("Volver");
        volverBtn.addActionListener(e -> dispose());
        panel.add(volverBtn);

        add(panel);
    }
}
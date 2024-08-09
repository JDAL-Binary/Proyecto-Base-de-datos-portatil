package vista;

import Conexion.conexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultarCandidatos extends JFrame {
    private JTextField txtIdCandidato, txtNombre, txtApellidoPaterno, txtApellidoMaterno, txtEducacion, txtExperiencia, txtMunicipio, txtTelefonoCasa, txtTelefonoCelular;
    private JButton btnBuscar, btnVolver;
    private JTextArea areaResultado;

    public ConsultarCandidatos() {
        setTitle("Consulta de Candidatos");
        setSize(500, 700);
        setLayout(new BorderLayout(10, 10));

        // Panel superior con campos de texto y etiquetas
        JPanel panelCampos = new JPanel(new GridLayout(9, 2, 5, 5));

        panelCampos.add(new JLabel("ID Candidato"));
        txtIdCandidato = new JTextField();
        panelCampos.add(txtIdCandidato);

        panelCampos.add(new JLabel("Nombre"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Apellido Paterno"));
        txtApellidoPaterno = new JTextField();
        panelCampos.add(txtApellidoPaterno);

        panelCampos.add(new JLabel("Apellido Materno"));
        txtApellidoMaterno = new JTextField();
        panelCampos.add(txtApellidoMaterno);

        panelCampos.add(new JLabel("Educación"));
        txtEducacion = new JTextField();
        panelCampos.add(txtEducacion);

        panelCampos.add(new JLabel("Experiencia"));
        txtExperiencia = new JTextField();
        panelCampos.add(txtExperiencia);

        panelCampos.add(new JLabel("Municipio"));
        txtMunicipio = new JTextField();
        panelCampos.add(txtMunicipio);

        panelCampos.add(new JLabel("Teléfono de Casa"));
        txtTelefonoCasa = new JTextField();
        panelCampos.add(txtTelefonoCasa);

        panelCampos.add(new JLabel("Teléfono Celular"));
        txtTelefonoCelular = new JTextField();
        panelCampos.add(txtTelefonoCelular);

        // Añadir el panel superior al JFrame
        add(panelCampos, BorderLayout.NORTH);

        // Panel central para el área de resultados con scroll
        areaResultado = new JTextArea(10, 30);
        areaResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaResultado);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior para los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnBuscar = new JButton("Buscar");
        btnVolver = new JButton("Volver");
        panelBotones.add(btnBuscar);
        panelBotones.add(btnVolver);

        // Añadir el panel inferior al JFrame
        add(panelBotones, BorderLayout.SOUTH);

        // Evento para el botón Buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCandidato();
            }
        });

        // Evento para el botón Volver
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });

        setVisible(true);
    }

    private void buscarCandidato() {
        String url = "jdbc:sqlite:DBportatil.db";
        StringBuilder query = new StringBuilder("SELECT * FROM Candidato WHERE 1=1");

        if (!txtIdCandidato.getText().isEmpty()) {
            query.append(" AND id_candidato = ").append(txtIdCandidato.getText());
        }
        if (!txtNombre.getText().isEmpty()) {
            query.append(" AND nombre LIKE '%").append(txtNombre.getText()).append("%'");
        }
        if (!txtApellidoPaterno.getText().isEmpty()) {
            query.append(" AND apellido_paterno LIKE '%").append(txtApellidoPaterno.getText()).append("%'");
        }
        if (!txtApellidoMaterno.getText().isEmpty()) {
            query.append(" AND apellido_materno LIKE '%").append(txtApellidoMaterno.getText()).append("%'");
        }
        if (!txtEducacion.getText().isEmpty()) {
            query.append(" AND educacion LIKE '%").append(txtEducacion.getText()).append("%'");
        }
        if (!txtExperiencia.getText().isEmpty()) {
            query.append(" AND experiencia LIKE '%").append(txtExperiencia.getText()).append("%'");
        }
        if (!txtMunicipio.getText().isEmpty()) {
            query.append(" AND municipio LIKE '%").append(txtMunicipio.getText()).append("%'");
        }
        if (!txtTelefonoCasa.getText().isEmpty()) {
            query.append(" AND telefono_casa LIKE '%").append(txtTelefonoCasa.getText()).append("%'");
        }
        if (!txtTelefonoCelular.getText().isEmpty()) {
            query.append(" AND telefono_celular LIKE '%").append(txtTelefonoCelular.getText()).append("%'");
        }

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(query.toString());
             ResultSet rs = pstmt.executeQuery()) {

            StringBuilder resultados = new StringBuilder("Resultados de búsqueda:\n\n");

            while (rs.next()) {
                resultados.append("ID Candidato: ").append(rs.getInt("ID_Candidato")).append("\n")
                          .append("Nombre: ").append(rs.getString("Nombre")).append("\n")
                          .append("Apellido Paterno: ").append(rs.getString("ApellidoPaterno")).append("\n")
                          .append("Apellido Materno: ").append(rs.getString("ApellidoMaterno")).append("\n")
                          .append("Educación: ").append(rs.getString("Educacion")).append("\n")
                          .append("Experiencia: ").append(rs.getString("Experiencia")).append("\n")
                          .append("Municipio: ").append(rs.getString("Municipio")).append("\n")
                          .append("Teléfono de Casa: ").append(rs.getString("TelefonoCasa")).append("\n")
                          .append("Teléfono Celular: ").append(rs.getString("TelefonoCelular")).append("\n")
                          .append("------------------------------------------------------\n");
            }

            areaResultado.setText(resultados.toString());
            // Castch para en caso de error, manda un mensaje de error
        } catch (SQLException e) {
            areaResultado.setText("Error al realizar la consulta: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ConsultarCandidatos();
    }
}
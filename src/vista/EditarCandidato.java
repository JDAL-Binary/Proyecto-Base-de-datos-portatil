package vista;

import Conexion.conexion;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditarCandidato extends JFrame {
    private JTextField idCandidato;
    private JTextField nombre;
    private JTextField apellidoPaterno;
    private JTextField apellidoMaterno;
    private JTextField educacion;
    private JTextField experiencia;
    private JTextField municipio;
    private JTextField telefonoCasa;
    private JTextField telefonoCelular;
    private JButton buscarBtn;
    private JButton volverBtn;
    private JPanel panelCampos;

    public EditarCandidato() {
        setTitle("Editar Candidato");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel para búsqueda por ID
        JPanel panelBusqueda = new JPanel(new GridLayout(2, 2, 5, 5));
        panelBusqueda.add(new JLabel("ID Candidato:"));
        idCandidato = new JTextField();
        panelBusqueda.add(idCandidato);

        buscarBtn = new JButton("Buscar");
        buscarBtn.addActionListener(e -> buscarCandidato());
        panelBusqueda.add(buscarBtn);

        volverBtn = new JButton("Volver");
        volverBtn.addActionListener(e -> dispose());
        panelBusqueda.add(volverBtn);

        add(panelBusqueda, BorderLayout.NORTH);

        // Panel para los campos editables
        panelCampos = new JPanel(new GridLayout(9, 2, 5, 5));

        panelCampos.add(new JLabel("Nombre:"));
        nombre = new JTextField();
        panelCampos.add(nombre);

        panelCampos.add(new JLabel("Apellido Paterno:"));
        apellidoPaterno = new JTextField();
        panelCampos.add(apellidoPaterno);

        panelCampos.add(new JLabel("Apellido Materno:"));
        apellidoMaterno = new JTextField();
        panelCampos.add(apellidoMaterno);

        panelCampos.add(new JLabel("Educación:"));
        educacion = new JTextField();
        panelCampos.add(educacion);

        panelCampos.add(new JLabel("Experiencia:"));
        experiencia = new JTextField();
        panelCampos.add(experiencia);

        panelCampos.add(new JLabel("Municipio:"));
        municipio = new JTextField();
        panelCampos.add(municipio);

        panelCampos.add(new JLabel("Teléfono de Casa:"));
        telefonoCasa = new JTextField();
        panelCampos.add(telefonoCasa);

        panelCampos.add(new JLabel("Teléfono Celular:"));
        telefonoCelular = new JTextField();
        panelCampos.add(telefonoCelular);

        // Botón para actualizar después de encontrar un candidato
        JButton actualizarBtn = new JButton("Actualizar");
        actualizarBtn.addActionListener(e -> actualizarCandidato());
        panelCampos.add(actualizarBtn);

        panelCampos.setVisible(false);
     // Ocultar campos inicialmente
        add(panelCampos, BorderLayout.CENTER);
    }

    private void buscarCandidato() {
        String consulta = "SELECT * FROM Candidato WHERE ID_candidato = ?";
        try (Connection conn = new conexion().conectarDB();
             PreparedStatement pstmt = conn.prepareStatement(consulta)) {
            pstmt.setInt(1, Integer.parseInt(idCandidato.getText()));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                nombre.setText(rs.getString("Nombre"));
                apellidoPaterno.setText(rs.getString("ApellidoPaterno"));
                apellidoMaterno.setText(rs.getString("ApellidoMaterno"));
                educacion.setText(rs.getString("Educacion"));
                experiencia.setText(rs.getString("Experiencia"));
                municipio.setText(rs.getString("Municipio"));
                telefonoCasa.setText(rs.getString("TelefonoCasa"));
                telefonoCelular.setText(rs.getString("TelefonoCelular"));

             // Mostrar campos al encontrar candidato
                panelCampos.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Candidato no encontrado");
             // Ocultar campos si no se encuentra candidato
                panelCampos.setVisible(false);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar candidato: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.");
        }
    }

    private void actualizarCandidato() {
        String actualizacion = "UPDATE Candidato SET Nombre = ?, ApellidoPaterno = ?, ApellidoMaterno = ?, Educacion = ?, Experiencia = ?, Municipio = ?, TelefonoCasa = ?, TelefonoCelular = ? WHERE ID_candidato = ?";
        try (Connection conn = new conexion().conectarDB();
             PreparedStatement pstmt = conn.prepareStatement(actualizacion)) {
            pstmt.setString(1, nombre.getText());
            pstmt.setString(2, apellidoPaterno.getText());
            pstmt.setString(3, apellidoMaterno.getText());
            pstmt.setString(4, educacion.getText());
            pstmt.setString(5, experiencia.getText());
            pstmt.setString(6, municipio.getText());
            pstmt.setString(7, telefonoCasa.getText());
            pstmt.setString(8, telefonoCelular.getText());
            pstmt.setInt(9, Integer.parseInt(idCandidato.getText()));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Candidato actualizado exitosamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar candidato: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditarCandidato().setVisible(true));
    }
}
package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Clase que representa la ventana principal de la aplicación
public class Main extends JFrame {
    public Main() {
    // Configuraciones de la ventana principal
        setTitle("Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        
     // Botones para las opciones del menú principal de la aplicación
        JButton agregarCandidatoBtn = new JButton("Capturar datos de candidato");
        JButton consultarCandidatoBtn = new JButton("Consulta de candidato");
        JButton editarCandidatoBtn = new JButton("Editar datos de candidato");
        JButton salirBtn = new JButton("Salir");
        
        agregarCandidatoBtn.addActionListener(e -> new AgregarCandidato().setVisible(true));
        consultarCandidatoBtn.addActionListener(e -> new ConsultarCandidatos().setVisible(true));
        editarCandidatoBtn.addActionListener(e -> new EditarCandidato().setVisible(true));
        salirBtn.addActionListener(e -> System.exit(0));
        
        panel.add(agregarCandidatoBtn);
        panel.add(consultarCandidatoBtn);
        panel.add(editarCandidatoBtn);
        panel.add(salirBtn);
        
        add(panel);
    }
    
    // Método principal que corre la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
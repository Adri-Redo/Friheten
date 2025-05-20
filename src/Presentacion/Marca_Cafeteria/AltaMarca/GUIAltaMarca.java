package Presentacion.Marca_Cafeteria.AltaMarca;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Marca_Cafeteria.TMarca;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIAltaMarca extends JFrame implements Observer {

    public GUIAltaMarca() {
        super("Alta Marca");
        initGUI();
    }

    public void initGUI() {
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Panel de título
        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Dar de alta una nueva marca"));
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Panel de campos de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        
        // Campo Nombre
        JPanel nombrePanel = new JPanel();
        JLabel nombreLabel = new JLabel("Nombre: ");
        JTextField nombreField = new JTextField(20);
        nombrePanel.add(nombreLabel);
        nombrePanel.add(nombreField);
        
        // Campo Categoría
        JPanel categoriaPanel = new JPanel();
        JLabel categoriaLabel = new JLabel("Categoría: ");
        JTextField categoriaField = new JTextField(20);
        categoriaPanel.add(categoriaLabel);
        categoriaPanel.add(categoriaField);
        
        // Espaciadores
        JPanel spacerTop = new JPanel();
        spacerTop.setPreferredSize(new Dimension(50, 40));
        JPanel spacerBottom = new JPanel();
        spacerBottom.setPreferredSize(new Dimension(50, 50));
        
        // Agregar componentes al panel de entrada
        inputPanel.add(spacerTop);
        inputPanel.add(nombrePanel);
        inputPanel.add(categoriaPanel);
        inputPanel.add(spacerBottom);
        
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Configuración del cierre de ventana
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Controller.getInstance().handle(new Context(Events.MARCA_VIEW, null));
                dispose();
            }
        });     
        
        // Panel de botones
        JPanel buttonPanel = new JPanel();
        
        // Botón Cancelar
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener((e) -> {
            Controller.getInstance().handle(new Context(Events.ALTA_MARCA_KO_VIEW, null));
            dispose();
        });

        // Botón Aceptar
        JButton acceptButton = new JButton("Aceptar");
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nombreField.getText().isEmpty() || categoriaField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, 
                        "Error: Todos los campos son obligatorios", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    Controller.getInstance().handle(new Context(Events.MARCA_ERROR_DATOS_NO_VALIDOS, null));
                } else {
                    TMarca tMarca = new TMarca();
                    tMarca.setNombre(nombreField.getText());
                    tMarca.setCategoria(categoriaField.getText());
                    tMarca.setActivo(true); // Por defecto activo al crear
                    
                    Controller.getInstance().handle(new Context(Events.ALTA_MARCA_OK_VIEW, tMarca));
                    dispose();
                }
            }
        });
        
        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Configuración final de la ventana
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(400, 250)); // Tamaño más adecuado
        pack();
        setLocationRelativeTo(null); // Centrar en pantalla
        setVisible(true);
    }

    @Override
    public void actualizar(Context context) {
        // Implementación del observer si es necesario
    }
}
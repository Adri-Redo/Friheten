package Presentacion.Marca_Cafeteria.ModificarMarca;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import Negocio.Marca_Cafeteria.TMarca;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIModificarMarca extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private Map<Integer, TMarca> marcas;
    private TMarca marcaActual;
    
    // Componentes de la interfaz
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtCategoria;
    
    public GUIModificarMarca() {
        super("Modificar Marca");
        marcaActual = new TMarca();
        marcas = new HashMap<>();
        initGUI();
    }
    
    private void initGUI() {
        // Configuración principal
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de título
        JLabel titleLabel = new JLabel("Modificar Marca Existente", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel de campos de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        
        // Panel para ID
        JPanel idPanel = createInputPanel("ID de la marca a modificar:", txtId = new JTextField(20));
        txtId.setToolTipText("Introduzca el ID numérico de la marca");
        
        // Panel para Nombre
        JPanel namePanel = createInputPanel("Nuevo nombre:", txtNombre = new JTextField(20));
        txtNombre.setToolTipText("Introduzca el nuevo nombre de la marca");
        
        // Panel para Categoría
        JPanel categoryPanel = createInputPanel("Nueva categoría:", txtCategoria = new JTextField(20));
        txtCategoria.setToolTipText("Introduzca la nueva categoría de la marca");
        
        inputPanel.add(idPanel);
        inputPanel.add(namePanel);
        inputPanel.add(categoryPanel);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        
        JButton btnAccept = new JButton("Aceptar");
        btnAccept.addActionListener(e -> handleAcceptButton());
        
        JButton btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(e -> closeWindow());
        
        buttonPanel.add(btnAccept);
        buttonPanel.add(btnCancel);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private JPanel createInputPanel(String labelText, JTextField textField) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel.add(new JLabel(labelText));
        panel.add(textField);
        return panel;
    }
    
    private void handleAcceptButton() {
        // Validar ID
        int id;
        try {
            id = Integer.parseInt(txtId.getText().trim());
            if (id <= 0) {
                showError("El ID debe ser un número positivo");
                return;
            }
        } catch (NumberFormatException ex) {
            showError("El ID debe ser un número válido");
            return;
        }
        
        // Validar nombre
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            showError("El nombre no puede estar vacío");
            return;
        }
        
        // Validar categoría
        String categoria = txtCategoria.getText().trim();
        if (categoria.isEmpty()) {
            showError("La categoría no puede estar vacía");
            return;
        }
        
        // Crear TMarca con los datos
        TMarca marca = new TMarca();
        marca.setId(id);
        marca.setNombre(nombre);
        marca.setCategoria(categoria);
        marca.setActivo(true); // Por defecto se mantiene activa
        
        // Enviar al controlador
        Controller.getInstance().handle(new Context(Events.MODIFICAR_MARCA_OK_VIEW, marca));
        dispose();
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void closeWindow() {
        Controller.getInstance().handle(new Context(Events.MARCA_VIEW, null));
        dispose();
    }
    
    @Override
    public void actualizar(Context context) {
        SwingUtilities.invokeLater(() -> {
            switch (context.getEvent()) {
                case Events.MODIFICAR_MARCA_OK_VIEW:
                    JOptionPane.showMessageDialog(this, 
                        "Marca modificada correctamente", 
                        "Éxito", 
                        JOptionPane.INFORMATION_MESSAGE);
                    break;
                    
                case Events.MODIFICAR_MARCA_KO_VIEW:
                    showError("No se pudo modificar la marca");
                    break;
                    
                case Events.MARCA_ERROR_NO_EXISTE:
                    showError("No existe ninguna marca con ese ID");
                    break;
            }
        });
    }
}
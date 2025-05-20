package Presentacion.Marca_Cafeteria.BajaMarca;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIBajaMarca extends JFrame implements Observer{

    private static final String TITLE = "Baja de Marca";
    private static final String INSTRUCTION = "Introduzca el ID de la marca a dar de baja:";
    private static final String INVALID_ID_MSG = "ID debe ser un número positivo";
    private static final String INVALID_FORMAT_MSG = "ID debe contener solo números";

    public GUIBajaMarca() {
        super(TITLE);
        initGUI();
    }

    private void initGUI() {
        // Panel principal con borde y espaciado
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de instrucción
        JPanel instructionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        instructionPanel.add(new JLabel(INSTRUCTION));
        mainPanel.add(instructionPanel, BorderLayout.NORTH);

        // Panel central con campo de texto
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JTextField idField = new JTextField(15);
        idField.setToolTipText("ID numérico de la marca (solo números positivos)");
        centerPanel.add(idField);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        
        JButton acceptButton = new JButton("Aceptar");
        acceptButton.addActionListener(e -> handleAcceptButton(idField.getText().trim()));
        
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> closeWindow());
        
        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Configuración de la ventana
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null); // Centrar en pantalla
        setVisible(true);
    }

    private void handleAcceptButton(String input) {
        try {
            int id = Integer.parseInt(input);
            if (id <= 0) {
                showError(INVALID_ID_MSG);
                return;
            }
            Controller.getInstance().handle(new Context(Events.BAJA_MARCA_OK_VIEW, id));
            dispose();
        } catch (NumberFormatException ex) {
            showError(INVALID_FORMAT_MSG);
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void closeWindow() {
        Controller.getInstance().handle(new Context(Events.MARCA_VIEW, null));
        dispose();
    }

    // Método actualizar del Observer (si es necesario mantenerlo)
    public void actualizar(Context context) {
        // Implementación si es necesaria
    }
}
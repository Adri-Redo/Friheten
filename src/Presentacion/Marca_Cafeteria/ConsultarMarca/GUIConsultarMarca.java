package Presentacion.Marca_Cafeteria.ConsultarMarca;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Negocio.Marca_Cafeteria.TMarca;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIConsultarMarca extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    
    // Componentes para mostrar datos
    private JLabel lblId;
    private JLabel lblNombre;
    private JLabel lblCategoria;
    private JLabel lblEstado;
    
    // Constantes para textos
    private static final String TITLE = "Consultar Marca";
    private static final String INSTRUCTION = "Introduzca el ID de la marca:";
    private static final String INVALID_ID_MSG = "ID debe ser un número positivo";
    private static final String INVALID_FORMAT_MSG = "ID debe contener solo números";
    
    public GUIConsultarMarca() {
        super(TITLE);
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
        
        // Panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel de entrada (arriba)
        JPanel inputPanel = createInputPanel();
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        
        // Panel de visualización (centro)
        JPanel dataPanel = createDataPanel();
        mainPanel.add(new JScrollPane(dataPanel), BorderLayout.CENTER);
        
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null); // Centrar ventana
        setVisible(true);
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        
        JLabel lblInstruction = new JLabel(INSTRUCTION);
        JTextField txtId = new JTextField(15);
        txtId.setToolTipText("ID numérico de la marca a consultar");
        
        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(e -> handleConsultar(txtId.getText().trim()));
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> closeWindow());
        
        panel.add(lblInstruction);
        panel.add(txtId);
        panel.add(btnConsultar);
        panel.add(btnCancelar);
        
        return panel;
    }
    
    private JPanel createDataPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Datos de la Marca"));
        
        // Inicializar componentes
        lblId = new JLabel("");
        lblNombre = new JLabel("");
        lblCategoria = new JLabel("");
        lblEstado = new JLabel("");
        
        // Configurar fuente para los datos
        Font dataFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);
        lblId.setFont(dataFont);
        lblNombre.setFont(dataFont);
        lblCategoria.setFont(dataFont);
        lblEstado.setFont(dataFont);
        
        // Añadir componentes al panel
        panel.add(createDataRow("ID Marca:", lblId));
        panel.add(createDataRow("Nombre:", lblNombre));
        panel.add(createDataRow("Categoría:", lblCategoria));
        panel.add(createDataRow("Estado:", lblEstado));
        
        return panel;
    }
    
    private JPanel createDataRow(String label, JComponent valueComponent) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row.add(new JLabel(label));
        row.add(valueComponent);
        return row;
    }
    
    private void handleConsultar(String input) {
        try {
            int id = Integer.parseInt(input);
            if (id <= 0) {
                showError(INVALID_ID_MSG);
                return;
            }
            Controller.getInstance().handle(new Context(Events.CONSULTAR_MARCA_OK_VIEW, id));
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
    
    @Override
    public void actualizar(Context context) {
        if (context.getData() instanceof TMarca) {
            TMarca marca = (TMarca) context.getData();
            
            if (marca.getActivo() == null || !marca.getActivo()) {
                JOptionPane.showMessageDialog(this, 
                    "La marca no está activa en la base de datos", 
                    "Advertencia", 
                    JOptionPane.WARNING_MESSAGE);
                clearData();
            } else {
                displayMarcaData(marca);
            }
        }
    }
    
    private void displayMarcaData(TMarca marca) {
        lblId.setText(String.valueOf(marca.getId()));
        lblNombre.setText(marca.getNombre());
        lblCategoria.setText(marca.getCategoria());
        lblEstado.setText(marca.getActivo() ? "Activa" : "Inactiva");
    }
    
    private void clearData() {
        lblId.setText("");
        lblNombre.setText("");
        lblCategoria.setText("");
        lblEstado.setText("");
    }
}
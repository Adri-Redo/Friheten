package Presentacion.Marca_Cafeteria.Marca;

import java.awt.*;
import javax.swing.*;
import Negocio.Marca_Cafeteria.TMarca;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.FactoryPresentacion.FactoryPresentacion;

public class GUIMarca extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    
    // Botones de la interfaz
    private JButton btnAlta;
    private JButton btnBaja;
    private JButton btnConsultar;
    private JButton btnListar;
    private JButton btnModificar;
    private JButton btnBack;

    public GUIMarca() {
        super("Gestión de Marcas");
        initGUI();
    }
    
    private void initGUI() {
        // Configuración principal
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel de título
        JLabel titleLabel = new JLabel("GESTIÓN DE MARCAS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Panel central con botones
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        
        // Creación de botones
        btnAlta = createButton("Alta Marca", Events.ALTA_MARCA_VIEW);
        btnBaja = createButton("Baja Marca", Events.BAJA_MARCA_VIEW);
        btnConsultar = createButton("Consultar Marca", Events.CONSULTAR_MARCA_VIEW);
        btnListar = createButton("Listar Marcas", Events.LISTAR_MARCA_VIEW);
        btnModificar = createButton("Modificar Marca", Events.MODIFICAR_MARCA_VIEW);
        
        // Añadir botones al panel central
        centerPanel.add(btnAlta);
        centerPanel.add(btnBaja);
        centerPanel.add(btnConsultar);
        centerPanel.add(btnListar);
        centerPanel.add(btnModificar);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Panel inferior con botón de volver
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnBack = createBackButton();
        bottomPanel.add(btnBack);
        
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null); // Centrar ventana
        setVisible(true);
    }
    
    private JButton createButton(String text, int event) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(180, 50));
        button.addActionListener(e -> {
            Controller.getInstance().handle(new Context(event, null));
            dispose();
        });
        return button;
    }
    
    private JButton createBackButton() {
        JButton button = new JButton("Volver");
        button.setToolTipText("Volver al menú principal");
        button.setPreferredSize(new Dimension(100, 40));
        button.addActionListener(e -> dispose());
        return button;
    }
    
    @Override
    public void actualizar(Context context) {
        SwingUtilities.invokeLater(() -> {
            switch(context.getEvent()) {
                case Events.RES_ALTA_MARCA_OK:
                    TMarca m = (TMarca) context.getData();
                    showMessage("Marca creada", "Se ha dado de alta la marca " + m.getNombre() + " correctamente.");
                    break;
                    
                case Events.RES_BAJA_MARCA_OK:
                    int id = (Integer) context.getData();
                    showMessage("Marca eliminada", "Se ha dado de baja la marca con ID: " + id);
                    break;
                    
                case Events.RES_CONSULTAR_MARCA_OK:
                    FactoryPresentacion.getInstance().generateGUI(Events.CONSULTAR_MARCA_VIEW).actualizar(context);
                    break;
                    
                case Events.RES_MODIFICAR_MARCA_OK:
                    TMarca m1 = (TMarca) context.getData();
                    showMessage("Marca modificada", 
                        "Se ha modificado la marca con ID " + m1.getId() + 
                        "\nNuevo nombre: " + m1.getNombre());
                    break;
                    
                case Events.MARCA_ERROR_YA_EXISTE:
                    TMarca m2 = (TMarca) context.getData();
                    showError("Error al crear marca", "Ya existe una marca con el nombre " + m2.getNombre());
                    break;
                    
                case Events.MARCA_ERROR_NO_EXISTE:
                    int idNoExiste = (Integer) context.getData();
                    showError("Error", "No existe ninguna marca con el ID " + idNoExiste);
                    break;
                    
                case Events.MARCA_ERROR_DATOS_NO_VALIDOS:
                    showError("Error", "Los datos introducidos no son válidos");
                    break;
                    
                case Events.MARCA_ERROR_PRODUCTOS_VINCULADOS_ACTIVOS:
                    showError("Error al eliminar", 
                        "No se puede eliminar la marca porque tiene productos asociados activos");
                    break;
            }
        });
    }
    
    private void showMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showError(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
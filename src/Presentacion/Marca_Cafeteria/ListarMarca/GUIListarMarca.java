package Presentacion.Marca_Cafeteria.ListarMarca;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

import Negocio.Marca_Cafeteria.TMarca;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIListarMarca extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    
    private DefaultTableModel tableModel;
    private List<TMarca> marcas;
    private JTable table;
    
    public GUIListarMarca() {
        super("Listado de Marcas");
        this.marcas = new ArrayList<>();
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

        // Título
        JLabel titleLabel = new JLabel("Listado de Marcas Registradas", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Configuración de la tabla
        String[] columnNames = {"ID", "Nombre", "Categoría", "Estado"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) return Boolean.class;
                return super.getColumnClass(columnIndex);
            }
        };

        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);
        
        // Mejoras visuales para la tabla
        table.setRowHeight(25);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Centrar contenido de las celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void closeWindow() {
        Controller.getInstance().handle(new Context(Events.MARCA_VIEW, null));
        dispose();
    }
    
    @Override
    public void actualizar(Context context) {
        SwingUtilities.invokeLater(() -> {
            tableModel.setRowCount(0);
            
            if (context.getData() instanceof List) {
                marcas = new ArrayList<>((List<TMarca>) context.getData());
                
                if (!marcas.isEmpty()) {
                    for (TMarca marca : marcas) {
                        if (marca.getActivo()) {
                            Object[] rowData = {
                                marca.getId(),
                                marca.getNombre(),
                                marca.getCategoria(),
                                marca.getActivo() ? "Activa" : "Inactiva"
                            };
                            tableModel.addRow(rowData);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "No hay marcas registradas en la base de datos", 
                        "Información", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
}
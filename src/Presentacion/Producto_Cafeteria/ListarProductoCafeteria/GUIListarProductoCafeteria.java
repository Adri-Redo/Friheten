package Presentacion.Producto_Cafeteria.ListarProductoCafeteria;

import javax.swing.JFrame;
import Presentacion.Observer;
import Presentacion.Context.Context;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Negocio.Producto_Cafeteria.TProductoCafeteria;
import Negocio.Producto_Cafeteria.TComida;
import Negocio.Producto_Cafeteria.TBebida;


public class GUIListarProductoCafeteria extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel _tableModel;
    private Set<TProductoCafeteria> _listaProductos;

    public GUIListarProductoCafeteria() {
        super("LISTAR PRODUCTOS ACTIVOS");
        this._listaProductos = new HashSet<>();
        this.initGUI();
    }

    public void initGUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        
        JPanel textPanel = new JPanel();
        textPanel.setBackground(Color.WHITE);
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Listado de todos los productos activos");
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        textPanel.add(label);
        mainPanel.add(textPanel);

        this._tableModel = new DefaultTableModel(
                new String[] { "ID", "Nombre", "Precio (€)", "Stock", "Nutri.", 
                              "Tipo", "Azúcar", "Tipo Comida" }, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        JTable table = new JTable(this._tableModel);
        
        table.setBackground(Color.WHITE);
        table.setOpaque(true);
        table.setSelectionBackground(new Color(184, 207, 229));
        table.setSelectionForeground(Color.BLACK);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(70, 130, 180));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setRowHeight(25);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i != 1 && i != 7) { 
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);   // ID
        columnModel.getColumn(1).setPreferredWidth(250);  // Nombre
        columnModel.getColumn(2).setPreferredWidth(80);   // Precio
        columnModel.getColumn(3).setPreferredWidth(60);   // Stock
        columnModel.getColumn(4).setPreferredWidth(50);   // Nutri.
        columnModel.getColumn(5).setPreferredWidth(80);   // Tipo
        columnModel.getColumn(6).setPreferredWidth(60);   // Azúcar
        columnModel.getColumn(7).setPreferredWidth(150);  // Tipo Comida

        table.setPreferredScrollableViewportSize(new Dimension(800, 400));

        JScrollPane scroll = new JScrollPane(table);
        scroll.getViewport().setBackground(Color.WHITE);
        infoPanel.add(scroll);
        mainPanel.add(infoPanel);

        setContentPane(mainPanel);
        setPreferredSize(new Dimension(850, 500));
        pack();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void actualizar(Context context) {
        this._tableModel.setRowCount(0);
        this.setTitle("LISTAR PRODUCTOS ACTIVOS");

        try {
            if (context.getData() == null) {
                 JOptionPane.showMessageDialog(this,
                    "No se recibieron datos para mostrar.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
                 return;
            }

            this._listaProductos = new HashSet<>((Set<TProductoCafeteria>) context.getData());

            if (!_listaProductos.isEmpty()) {
                int contadorActivos = 0;

                for (TProductoCafeteria producto : this._listaProductos) {
                    if (producto.getActivo() != null && producto.getActivo()) {

                        String tipoProducto = "Desconocido";
                        String nivelAzucarStr = "N/A";
                        String tipoComidaStr = "N/A";

                        if (producto.getTipoComida() != null) {
                            tipoProducto = "Comida";
                            tipoComidaStr = producto.getTipoComida();
                        } else if (producto.getNivelAzucar() != null) {
                            tipoProducto = "Bebida";
                            nivelAzucarStr = producto.getNivelAzucar().toString();
                        } else {
                             System.err.println("Advertencia: Producto DTO ID " + producto.getId() + " no tiene tipoComida ni nivelAzucar.");
                        }

                        Object[] rowData = {
                            producto.getId(),
                            producto.getNombre(),
                            String.format("%.2f", producto.getPrecio()),
                            producto.getStock(),
                            producto.getNivelNutricion(),
                            tipoProducto,
                            nivelAzucarStr,
                            tipoComidaStr
                        };

                        this._tableModel.addRow(rowData);
                        contadorActivos++;
                    } else if (producto.getActivo() == null) {
                         System.err.println("Advertencia: Producto ID " + producto.getId() + " tiene estado 'activo' nulo.");
                    }
                }

                if (contadorActivos == 0 && !_listaProductos.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                        "Se encontraron " + _listaProductos.size() + " productos, pero ninguno está activo.",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
                    this.setTitle("LISTAR PRODUCTOS ACTIVOS (Ninguno activo)");
                } else if (contadorActivos > 0) {
                    this.setTitle("LISTAR PRODUCTOS ACTIVOS (" + contadorActivos + " encontrados)");
                }

            } else {
                JOptionPane.showMessageDialog(this,
                    "No hay productos registrados en la base de datos",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
                 this.setTitle("LISTAR PRODUCTOS ACTIVOS (Vacío)");
            }
        } catch (ClassCastException cce) {
             System.err.println("Error de casteo al obtener la lista de productos: " + cce.getMessage());
             cce.printStackTrace();
             JOptionPane.showMessageDialog(this,
                "Error interno: Los datos recibidos no son una lista de productos.",
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            System.err.println("Error inesperado al procesar la lista de productos: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error inesperado al procesar la lista: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}

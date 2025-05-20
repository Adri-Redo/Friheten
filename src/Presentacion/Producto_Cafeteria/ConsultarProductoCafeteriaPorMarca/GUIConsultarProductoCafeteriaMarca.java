package Presentacion.Producto_Cafeteria.ConsultarProductoCafeteriaPorMarca;

import Negocio.Producto_Cafeteria.TProductoCafeteria;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Observer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class GUIConsultarProductoCafeteriaMarca extends JFrame
    implements Observer {

    private static final long serialVersionUID = 1L;
    private static final Color PRIMARY_COLOR = new Color(70, 130, 180);
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color FOREGROUND_COLOR = Color.BLACK;
    private static final Color BUTTON_BORDER_COLOR = new Color(60, 60, 60);
    private static final Color BUTTON_HOVER_COLOR = new Color(50, 100, 150);
    private static final Color TABLE_SELECTION_BACKGROUND = new Color(
        184,
        207,
        229
    );

    private DefaultTableModel tableModel;
    private JTable resultsTable;
    private JTextField txtMarcaId;
    private JLabel statusLabel;

    public GUIConsultarProductoCafeteriaMarca() {
        super("CONSULTAR PRODUCTOS POR MARCA");
        initGUI();
    }

    private void initGUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(900, 600));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);

        JLabel titulo = new JLabel(
            "Consultar Productos por Marca",
            SwingConstants.CENTER
        );
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        titulo.setOpaque(true);
        titulo.setBackground(PRIMARY_COLOR);
        titulo.setPreferredSize(new Dimension(0, 40));
        mainPanel.add(titulo, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        searchPanel.setBackground(BACKGROUND_COLOR);

        JLabel idLabel = new JLabel("ID de la Marca:");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMarcaId = new JTextField(10);
        txtMarcaId.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton consultarButton = createStyledButton("Consultar");
        JButton cancelButton = createStyledButton("Cancelar");

        searchPanel.add(idLabel);
        searchPanel.add(txtMarcaId);
        searchPanel.add(consultarButton);
        searchPanel.add(cancelButton);

        mainPanel.add(searchPanel, BorderLayout.CENTER);

        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsPanel.setBackground(BACKGROUND_COLOR);

        statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        resultsPanel.add(statusLabel, BorderLayout.NORTH);

        configureTable();
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.getViewport().setBackground(BACKGROUND_COLOR);
        resultsPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(resultsPanel, BorderLayout.SOUTH);

        cancelButton.addActionListener(e -> {
            dispose();
        });

        consultarButton.addActionListener(e -> {
            String idText = txtMarcaId.getText().trim();
            if (idText.isEmpty()) {
                showError("El ID de la marca no puede estar vacío.");
            } else {
                if (isValidPositiveInteger(idText, "El ID de la marca")) {
                    try {
                        Integer idMarca = Integer.parseInt(idText);
                        tableModel.setRowCount(0);
                        statusLabel.setText("Consultando...");
                        Controller
                            .getInstance()
                            .handle(
                                new Context(
                                    Events.CONSULTAR_PRODUCTO_POR_ID_MARCA_OK_VIEW,
                                    idMarca
                                )
                            );
                    } catch (NumberFormatException ex) {
                        showError("Error interno al convertir el ID a número.");
                    }
                }
            }
        });

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void configureTable() {
        tableModel =
            new DefaultTableModel(
                new String[] {
                    "ID",
                    "Nombre",
                    "Precio (€)",
                    "Stock",
                    "Estado",
                    "Nutrición",
                    "Nivel Azúcar",
                    "Tipo Comida",
                },
                0
            ) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };

        resultsTable = new JTable(tableModel);
        resultsTable.setBackground(BACKGROUND_COLOR);
        resultsTable.setOpaque(true);
        resultsTable.setSelectionBackground(TABLE_SELECTION_BACKGROUND);
        resultsTable.setSelectionForeground(FOREGROUND_COLOR);
        resultsTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        resultsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        resultsTable.getTableHeader().setOpaque(false);
        resultsTable.getTableHeader().setBackground(PRIMARY_COLOR);
        resultsTable.getTableHeader().setForeground(Color.WHITE);
        resultsTable.setRowHeight(25);
        resultsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

        TableColumnModel columnModel = resultsTable.getColumnModel();
        for (int i = 0; i < resultsTable.getColumnCount(); i++) {
            if (i == 1 || i == 7) {
                columnModel.getColumn(i).setCellRenderer(leftRenderer);
            } else {
                columnModel.getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        columnModel.getColumn(0).setPreferredWidth(60);
        columnModel.getColumn(1).setPreferredWidth(250);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(60);
        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(5).setPreferredWidth(70);
        columnModel.getColumn(6).setPreferredWidth(90);
        columnModel.getColumn(7).setPreferredWidth(150);

        resultsTable.setPreferredScrollableViewportSize(new Dimension(850, 300));
        resultsTable.setFillsViewportHeight(true);

        resultsTable.setDefaultRenderer(
            Object.class,
            new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column
                ) {
                    Component cell = super.getTableCellRendererComponent(
                        table,
                        value,
                        isSelected,
                        hasFocus,
                        row,
                        column
                    );
                    if (!isSelected) {
                        cell.setBackground(BACKGROUND_COLOR);
                        cell.setForeground(FOREGROUND_COLOR);
                    } else {
                        cell.setBackground(TABLE_SELECTION_BACKGROUND);
                        cell.setForeground(FOREGROUND_COLOR);
                    }
                    return cell;
                }
            }
        );
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(PRIMARY_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(BUTTON_BORDER_COLOR, 1));
        button.setPreferredSize(new Dimension(120, 35));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(BUTTON_HOVER_COLOR);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(PRIMARY_COLOR);
                }
            }
        );

        return button;
    }

    private boolean isValidPositiveInteger(String inputText, String fieldName) {
        if (inputText == null || inputText.isEmpty()) {
            showError(fieldName + " no puede estar vacío.");
            return false;
        }
        try {
            int value = Integer.parseInt(inputText);
            if (value <= 0) {
                showError(fieldName + " debe ser un número positivo.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            showError(fieldName + " debe ser un número entero válido.");
            return false;
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
            this,
            message,
            "Error de Validación",
            JOptionPane.ERROR_MESSAGE
        );
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(
            this,
            message,
            "Información",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public void actualizar(Context context) {
        if (
            context != null &&
            context.getEvent() == Events.CONSULTAR_PRODUCTO_POR_ID_MARCA_VIEW
        ) {
            tableModel.setRowCount(0);

            try {
                Object data = context.getData();
                if (data instanceof Set) {
                    Set<TProductoCafeteria> productList = (Set<TProductoCafeteria>) data;

                    if (productList.isEmpty()) {
                        statusLabel.setText("No se encontraron productos para esa marca.");
                        showInfo("No se encontraron productos activos para la marca especificada.");
                    } else {
                        for (TProductoCafeteria producto : productList) {
                            if (producto != null) {
                                tableModel.addRow(
                                    new Object[] {
                                        producto.getId(),
                                        producto.getNombre(),
                                        String.format("%.2f", producto.getPrecio()),
                                        producto.getStock(),
                                        producto.getActivo() ? "Activo" : "Inactivo",
                                        producto.getNivelNutricion(),
                                        producto.getNivelAzucar() != null
                                            ? producto.getNivelAzucar().toString()
                                            : "N/A",
                                        producto.getTipoComida() != null
                                            ? producto.getTipoComida()
                                            : "N/A",
                                    }
                                );
                            }
                        }
                        statusLabel.setText(
                            productList.size() + " producto(s) encontrado(s)."
                        );
                        setTitle(
                            "CONSULTAR POR MARCA (" + productList.size() + " encontrados)"
                        );
                    }
                } else if (data == null) {
                    statusLabel.setText("La consulta no devolvió resultados.");
                    showInfo("No se recibieron datos para la marca especificada.");
                } else {
                    statusLabel.setText("Error: Datos recibidos no válidos.");
                    showError(
                        "Error interno: Se esperaba una lista de productos pero se recibió: " +
                        data.getClass().getName()
                    );
                    System.err.println(
                        "Error en actualizar: Context data no es Set<TProductoCafeteria>"
                    );
                }
            } catch (Exception e) {
                statusLabel.setText("Error al procesar los resultados.");
                showError(
                    "Ocurrió un error inesperado al mostrar los productos: " +
                    e.getMessage()
                );
                System.err.println(
                    "Error inesperado en GUIConsultarProductoCafeteriaMarca.actualizar: " +
                    e.getMessage()
                );
                e.printStackTrace();
            }
        } else if (
            context != null && context.getEvent() == Events.CONSULTAR_PRODUCTO_POR_ID_MARCA_KO_VIEW
        ) {
            String errorMsg = (context.getData() instanceof String)
                ? (String) context.getData()
                : "Error desconocido al consultar la marca.";
            statusLabel.setText("Error en la consulta.");
            showError(errorMsg);
        }
    }
}

package Presentacion.Producto_Cafeteria.AltaProductoCafeteria;

import Negocio.Producto_Cafeteria.TProductoCafeteria;
import Negocio.Producto_Cafeteria.TComida;
import Negocio.Producto_Cafeteria.TBebida;

import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Observer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUIAltaProductoCafeteria extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    private JTextField idMarcaField;
    private JTextField nombreField;
    private JTextField precioField;
    private JTextField stockField;
    private JTextField nivelNutricionField;
    private JComboBox<String> tipoComboBox;
    private JTextField tipoComidaField;
    private JTextField nivelAzucarField;

    public GUIAltaProductoCafeteria() {
        super("ALTA PRODUCTO CAFETERIA");
        this.initGUI();
    }

    private void initGUI() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );
        panelPrincipal.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("ALTA PRODUCTO", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        titulo.setOpaque(true);
        titulo.setBackground(new Color(70, 130, 180));
        titulo.setPreferredSize(new Dimension(0, 50));
        panelPrincipal.add(titulo, BorderLayout.NORTH);
        
        JPanel panelBotones = new JPanel(new GridLayout(4, 2, 20, 20));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelBotones.setBackground(Color.WHITE);
        
        // Marca
        JLabel idMarcaLabel = new JLabel("ID Marca: ");
        idMarcaField = new JTextField(10);
        idMarcaField.setToolTipText("Introduce el ID numérico de la marca");
        JPanel panelIdMarca = new JPanel(new BorderLayout(5,0));
        panelIdMarca.add(idMarcaLabel, BorderLayout.WEST);
        panelIdMarca.add(idMarcaField, BorderLayout.CENTER);
        panelIdMarca.setBackground(Color.WHITE);
        
        // Nombre
        JLabel nombreLabel = new JLabel("Nombre: ");
        nombreField = new JTextField(15);
        nombreField.setToolTipText("Nombre Producto");
        JPanel panelNombre = new JPanel(new BorderLayout(5,0));
        panelNombre.add(nombreLabel, BorderLayout.WEST);
        panelNombre.add(nombreField, BorderLayout.CENTER);
        panelNombre.setBackground(Color.WHITE);


        // Precio
        JLabel precioLabel = new JLabel("Precio (€): ");
        precioField = new JTextField(15);
        precioField.setToolTipText("Precio (ej: 2.50)");
        JPanel panelPrecio = new JPanel(new BorderLayout(5,0));
        panelPrecio.add(precioLabel, BorderLayout.WEST);
        panelPrecio.add(precioField, BorderLayout.CENTER);
        panelPrecio.setBackground(Color.WHITE);

        // Stock
        JLabel stockLabel = new JLabel("Stock: ");
        stockField = new JTextField(15);
        stockField.setToolTipText("Stock inicial (número entero)");
        JPanel panelStock = new JPanel(new BorderLayout(5,0));
        panelStock.add(stockLabel, BorderLayout.WEST);
        panelStock.add(stockField, BorderLayout.CENTER);
        panelStock.setBackground(Color.WHITE);

        // Nivel Nutrición
        JLabel nivelNutricionLabel = new JLabel("Nivel Nutrición (A-E): ");
        nivelNutricionField = new JTextField(1);
        nivelNutricionField.setToolTipText("Nivel Nutrición (una letra de A a E)");
        JPanel PanelNutricionLabel = new JPanel(new BorderLayout(5,0));
        PanelNutricionLabel.add(nivelNutricionLabel, BorderLayout.WEST);
        PanelNutricionLabel.add(nivelNutricionField, BorderLayout.CENTER);
        PanelNutricionLabel.setBackground(Color.WHITE);

        // Tipo Comida/Bebida
        tipoComboBox = new JComboBox<>(new String[] { "Comida", "Bebida" });
        tipoComboBox.setToolTipText("Selecciona si es Comida o Bebida");
        tipoComboBox.setBackground(Color.WHITE);
        JPanel PanelComboComidaOBebida = new JPanel(new BorderLayout(5,0));
        PanelComboComidaOBebida.add(new JLabel("Tipo General: "), BorderLayout.WEST);
        PanelComboComidaOBebida.add(tipoComboBox, BorderLayout.CENTER);
        PanelComboComidaOBebida.setBackground(Color.WHITE);

        JPanel PanelEspecifico = new JPanel(new GridLayout(1, 2, 10, 0));
        PanelEspecifico.setBackground(Color.WHITE);

        JPanel PanelComida = new JPanel(new BorderLayout(5,0));
        JLabel tipoComidaLabel = new JLabel("Tipo Comida: ");
        tipoComidaField = new JTextField(10);
        tipoComidaField.setToolTipText("Ej: Bocadillo, Bollería, Plato");
        PanelComida.add(tipoComidaLabel, BorderLayout.WEST);
        PanelComida.add(tipoComidaField, BorderLayout.CENTER);
        PanelComida.setBackground(Color.WHITE);

        JPanel PanelAzucar = new JPanel(new BorderLayout(5,0));
        JLabel nivelAzucarLabel = new JLabel("Nivel Azúcar: ");
        nivelAzucarField = new JTextField(5);
        nivelAzucarField.setToolTipText("Nivel de azúcar como número entero");
        PanelAzucar.add(nivelAzucarLabel, BorderLayout.WEST);
        PanelAzucar.add(nivelAzucarField, BorderLayout.CENTER);
        PanelAzucar.setBackground(Color.WHITE);

        PanelEspecifico.add(PanelComida);
        PanelEspecifico.add(PanelAzucar);


        // Añadir paneles al grid
        panelBotones.add(panelNombre);
        panelBotones.add(panelPrecio);
        panelBotones.add(panelStock);
        panelBotones.add(PanelNutricionLabel);
        panelBotones.add(panelIdMarca);
        panelBotones.add(PanelComboComidaOBebida);
        panelBotones.add(PanelEspecifico);

        tipoComboBox.addActionListener(e -> {
            boolean esComida = tipoComboBox.getSelectedItem().equals("Comida");
            PanelComida.setVisible(esComida); 
            PanelAzucar.setVisible(!esComida);
        });

        tipoComboBox.setSelectedItem("Comida");
        PanelComida.setVisible(true);
        PanelAzucar.setVisible(false);

        JButton cancelButton = createStyledButton("CANCELAR");
        cancelButton.setToolTipText("Cancelar operación y volver");
        cancelButton.addActionListener(e -> {
            dispose();
        });

        JButton okButton = createStyledButton("ACEPTAR");
        okButton.setToolTipText("Validar datos y dar de alta el producto");
        okButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean datosCorrectos = true;
                    List<String> errores = new ArrayList<>();

                    String nombre = nombreField.getText().trim();
                    if (nombre.isEmpty()) {
                        datosCorrectos = false;
                        errores.add("Debe introducir un nombre.");
                    }
                    Double precio = 0.0;
                    try {
                        precio = Double.parseDouble(precioField.getText());
                        if (precio <= 0) {
                            datosCorrectos = false;
                            errores.add("El precio debe ser positivo.");
                        }
                    } catch (NumberFormatException ex) {
                        datosCorrectos = false;
                        errores.add("El precio debe ser un número válido.");
                    }
                    Integer stock = 0;
                    try {
                        stock = Integer.parseInt(stockField.getText());
                        if (stock < 0) {
                            datosCorrectos = false;
                            errores.add("El stock no puede ser negativo.");
                        }
                    } catch (NumberFormatException ex) {
                        datosCorrectos = false;
                        errores.add("El stock debe ser un número entero.");
                    }
                    String nivelNutricionStr = nivelNutricionField
                        .getText()
                        .toUpperCase()
                        .trim();
                    char nivelNutricion = ' ';
                    if (nivelNutricionStr.length() == 1 && nivelNutricionStr.matches("[A-E]")) {
                        nivelNutricion = nivelNutricionStr.charAt(0);
                    } else {
                        datosCorrectos = false;
                        errores.add(
                            "El nivel de nutrición debe ser una letra entre A y E."
                        );
                    }

                    Integer idMarca = null;
                    try {
                        idMarca = Integer.parseInt(idMarcaField.getText().trim());
                        if (idMarca <= 0) {
                            datosCorrectos = false;
                            errores.add("El ID de Marca debe ser un número positivo.");
                        }
                    } catch (NumberFormatException ex) {
                        datosCorrectos = false;
                        errores.add("El ID de Marca debe ser un número entero válido.");
                    }

                    boolean esComida = tipoComboBox.getSelectedItem().equals("Comida");
                    String tipoComida = null;
                    Integer nivelAzucar = null;
                    if (esComida) {
                        tipoComida = tipoComidaField.getText().trim();
                        if (tipoComida.isEmpty()) {
                            datosCorrectos = false;
                            errores.add("Debe especificar el tipo de comida.");
                        }
                    } else {
                        try {
                            nivelAzucar = Integer.parseInt(nivelAzucarField.getText().trim());
                            if (nivelAzucar < 0) {
                                datosCorrectos = false;
                                errores.add("El nivel de azúcar no puede ser negativo.");
                            }
                        } catch (NumberFormatException ex) {
                            datosCorrectos = false;
                            errores.add("El nivel de azúcar debe ser un número entero.");
                        }
                    }

                    if (datosCorrectos) {
                        TProductoCafeteria productoTransfer = null;

                        if (esComida) {
                            productoTransfer = new TComida(
                                null,
                                nombre,
                                precio,
                                stock,
                                nivelNutricion,
                                true,
                                tipoComida,
                                idMarca
                            );
                        } else {
                            productoTransfer = new TBebida(
                                null,
                                nombre,
                                precio,
                                stock,
                                nivelNutricion,
                                true,
                                nivelAzucar,
                                idMarca
                            );
                        }

                        Controller.getInstance()
                            .handle(new Context(Events.ALTA_PRODUCTO_OK_VIEW, productoTransfer));

                        dispose();

                    } else {
                        StringBuilder mensajeError = new StringBuilder("Corrija los siguientes errores:\n");
                        for (String error : errores) {
                            mensajeError.append("- ").append(error).append("\n");
                        }
                        JOptionPane.showMessageDialog(
                            GUIAltaProductoCafeteria.this,
                            mensajeError.toString(),
                            "ERROR DE VALIDACIÓN",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        );


        // Panel para botones de acción
        JPanel botonAccion = new JPanel();
        botonAccion.setBackground(Color.WHITE);
        botonAccion.add(okButton);
        botonAccion.add(cancelButton);


        // Añadir paneles al principal
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        panelPrincipal.add(botonAccion, BorderLayout.SOUTH);

        // Configuración final de la ventana
        this.setContentPane(panelPrincipal);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actualizar(Context context) {
        if (context == null) {
             return;
        }
        
        String message="";
        
        if (context.getData() instanceof Integer) {
            int ret = (Integer) context.getData();

            switch (ret) {
                case -1: message = "Datos de entrada nulos o incompletos."; break;
                case -2: message = "Error general de la base de datos durante la operación."; break;
                case -3: message = "El producto debe ser clasificado como Comida o Bebida."; break;
                case -4: message = "Ya existe un producto activo con el nombre introducido."; break;
                case -5: message = "El ID de Marca es inválido o no fue proporcionado."; break;
                case -6: message = "La Marca con el ID introducido no existe en la base de datos."; break;
                case -7: message = "La Marca con el ID introducido no está activa."; break;
                default: message = "Producto dado de alta con ID: " + ret + " ¡Alta Exitosa!"; break;
            }
            
            if (ret >= 0) {
                JOptionPane.showMessageDialog(
                        GUIAltaProductoCafeteria.this,
                        message.toString(),
                        "ÉXITO",
                        JOptionPane.INFORMATION_MESSAGE
                    );
            } else {
                JOptionPane.showMessageDialog(
                        GUIAltaProductoCafeteria.this,
                        message.toString(),
                        "ERROR AL DAR DE ALTA",
                        JOptionPane.ERROR_MESSAGE
                    );
            }

        } else if (context.getData() != null) {
        	message = context.getData().toString();
        }
        


    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 130, 180));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 2));
        button.setPreferredSize(new Dimension(150, 35));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(
            new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(new Color(50, 100, 150));
                }
                public void mouseExited(MouseEvent e) {
                    button.setBackground(new Color(70, 130, 180));
                }
            }
        );
        return button;
    }
}
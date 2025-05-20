package Presentacion.Producto_Cafeteria.ModificarProductoCafeteria;

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

public class GUIModificarProductoCafeteria extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    // Campos para buscar
    private JTextField idBuscarField;
    private JButton buscarButton;

    // Campos para modificar (inicialmente deshabilitados)
    private JTextField idMarcaField;
    private JTextField nombreField;
    private JTextField precioField;
    private JTextField stockField;
    private JTextField nivelNutricionField;
    private JComboBox<String> tipoComboBox;
    private JTextField tipoComidaField;
    private JTextField nivelAzucarField;
    private JButton okButton;
    private JButton cancelButton;

    // Paneles dinámicos
    private JPanel panelComida;
    private JPanel panelAzucar;
    private JPanel panelCamposEditables;

    // Datos del producto encontrado
    private Integer idProductoAModificar = null;

    public GUIModificarProductoCafeteria() {
        super("MODIFICAR PRODUCTO CAFETERIA");
        this.initGUI();
    }

    private void initGUI() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );
        panelPrincipal.setBackground(Color.WHITE);

        // --- Título ---
        JLabel titulo = new JLabel(
            "MODIFICAR PRODUCTO",
            SwingConstants.CENTER
        );
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        titulo.setOpaque(true);
        titulo.setBackground(new Color(70, 130, 180));
        titulo.setPreferredSize(new Dimension(0, 50));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        // --- Panel de Búsqueda ---
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setBackground(Color.WHITE);
        panelBusqueda.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        JLabel idBuscarLabel = new JLabel("ID Producto a Modificar: ");
        idBuscarField = new JTextField(10);
        idBuscarField.setToolTipText(
            "Introduce el ID numérico del producto y pulsa Buscar"
        );
        buscarButton = createStyledButton("BUSCAR");
        buscarButton.setPreferredSize(new Dimension(100, 35));
        buscarButton.setToolTipText("Buscar producto por ID");

        buscarButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int id = Integer.parseInt(idBuscarField.getText().trim());
                        if (id <= 0) {
                            JOptionPane.showMessageDialog(
                                GUIModificarProductoCafeteria.this,
                                "El ID debe ser un número positivo.",
                                "Error de Búsqueda",
                                JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }
                        setCamposEditablesEnabled(false);
                        idProductoAModificar = null;
                        // okButton aún no está inicializado o se deshabilita aquí
                        // okButton.setEnabled(false); // No es necesario si setCamposEditablesEnabled lo maneja

                        Controller.getInstance()
                            .handle(
                                new Context(Events.MODIFICAR_PRODUCTO_OK_VIEW, id)
                            );
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                            GUIModificarProductoCafeteria.this,
                            "El ID debe ser un número entero válido.",
                            "Error de Búsqueda",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        );

        panelBusqueda.add(idBuscarLabel);
        panelBusqueda.add(idBuscarField);
        panelBusqueda.add(buscarButton);

        // --- Panel Central (contendrá búsqueda y campos editables) ---
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(Color.WHITE);
        panelCentral.add(panelBusqueda, BorderLayout.NORTH);

        // --- Panel de Campos Editables ---
        panelCamposEditables = new JPanel(new GridLayout(0, 2, 20, 15));
        panelCamposEditables.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );
        panelCamposEditables.setBackground(Color.WHITE);

        // ID Marca
        JLabel idMarcaLabel = new JLabel("ID Marca: ");
        idMarcaField = new JTextField(10);
        idMarcaField.setToolTipText("Introduce el ID numérico de la marca");
        JPanel panelIdMarca = createLabelTextPair(idMarcaLabel, idMarcaField);

        // Nombre
        JLabel nombreLabel = new JLabel("Nombre: ");
        nombreField = new JTextField(15);
        nombreField.setToolTipText("Nombre Producto");
        JPanel panelNombre = createLabelTextPair(nombreLabel, nombreField);

        // Precio
        JLabel precioLabel = new JLabel("Precio (€): ");
        precioField = new JTextField(15);
        precioField.setToolTipText("Precio (ej: 2.50)");
        JPanel panelPrecio = createLabelTextPair(precioLabel, precioField);

        // Stock
        JLabel stockLabel = new JLabel("Stock: ");
        stockField = new JTextField(15);
        stockField.setToolTipText("Stock actual (número entero)");
        JPanel panelStock = createLabelTextPair(stockLabel, stockField);

        // Nivel Nutrición
        JLabel nivelNutricionLabel = new JLabel("Nivel Nutrición (A-E): ");
        nivelNutricionField = new JTextField(1);
        nivelNutricionField.setToolTipText(
            "Nivel Nutrición (una letra de A a E)"
        );
        JPanel panelNutricion = createLabelTextPair(
            nivelNutricionLabel,
            nivelNutricionField
        );

        // Tipo Comida/Bebida
        tipoComboBox = new JComboBox<>(new String[] { "Comida", "Bebida" });
        tipoComboBox.setToolTipText("Selecciona si es Comida o Bebida");
        tipoComboBox.setBackground(Color.WHITE);
        tipoComboBox.setEnabled(false);
        JPanel panelComboComidaOBebida = createLabelComboPair(
            new JLabel("Tipo General: "),
            tipoComboBox
        );

        // Panel Específico (Contenedor para Comida/Azúcar)
        JPanel panelEspecifico = new JPanel(new GridLayout(1, 2, 10, 0));
        panelEspecifico.setBackground(Color.WHITE);

        // Panel Comida
        panelComida = new JPanel(new BorderLayout(5, 0));
        JLabel tipoComidaLabel = new JLabel("Tipo Comida: ");
        tipoComidaField = new JTextField(10);
        tipoComidaField.setToolTipText("Ej: Bocadillo, Bollería, Plato");
        panelComida.add(tipoComidaLabel, BorderLayout.WEST);
        panelComida.add(tipoComidaField, BorderLayout.CENTER);
        panelComida.setBackground(Color.WHITE);

        // Panel Azúcar
        panelAzucar = new JPanel(new BorderLayout(5, 0));
        JLabel nivelAzucarLabel = new JLabel("Nivel Azúcar: ");
        nivelAzucarField = new JTextField(5);
        nivelAzucarField.setToolTipText(
            "Nivel de azúcar como número entero"
        );
        panelAzucar.add(nivelAzucarLabel, BorderLayout.WEST);
        panelAzucar.add(nivelAzucarField, BorderLayout.CENTER);
        panelAzucar.setBackground(Color.WHITE);

        panelEspecifico.add(panelComida);
        panelEspecifico.add(panelAzucar);

        panelCamposEditables.add(panelNombre);
        panelCamposEditables.add(panelPrecio);
        panelCamposEditables.add(panelStock);
        panelCamposEditables.add(panelNutricion);
        panelCamposEditables.add(panelIdMarca);
        panelCamposEditables.add(panelComboComidaOBebida);
        panelCamposEditables.add(panelEspecifico);

        tipoComboBox.addActionListener(e -> {
            actualizarVisibilidadCamposEspecificos();
        });

        setCamposEditablesEnabled(false);
        actualizarVisibilidadCamposEspecificos();

        panelCentral.add(panelCamposEditables, BorderLayout.CENTER);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        JPanel botonAccion = new JPanel();
        botonAccion.setBackground(Color.WHITE);
        botonAccion.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        cancelButton = createStyledButton("CANCELAR");
        cancelButton.setToolTipText("Cancelar operación y cerrar");
        cancelButton.addActionListener(e -> {
            dispose();
        });

        okButton = createStyledButton("ACEPTAR");
        okButton.setToolTipText("Validar datos y modificar el producto");
        okButton.setEnabled(false);
        okButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (idProductoAModificar == null) {
                        JOptionPane.showMessageDialog(
                            GUIModificarProductoCafeteria.this,
                            "Primero debe buscar un producto válido.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    boolean datosCorrectos = true;
                    List<String> errores = new ArrayList<>();

                    String nombre = nombreField.getText().trim();
                    if (nombre.isEmpty()) {
                        datosCorrectos = false;
                        errores.add("Debe introducir un nombre.");
                    }
                    Double precio = 0.0;
                    try {
                        precio = Double.parseDouble(precioField.getText().trim());
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
                        stock = Integer.parseInt(stockField.getText().trim());
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
                    if (
                        nivelNutricionStr.length() == 1 &&
                        nivelNutricionStr.matches("[A-E]")
                    ) {
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
                            errores.add(
                                "El ID de Marca debe ser un número positivo."
                            );
                        }
                    } catch (NumberFormatException ex) {
                        datosCorrectos = false;
                        errores.add(
                            "El ID de Marca debe ser un número entero válido."
                        );
                    }

                    boolean esComida = tipoComboBox
                        .getSelectedItem()
                        .equals("Comida");
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
                            nivelAzucar = Integer.parseInt(
                                nivelAzucarField.getText().trim()
                            );
                            if (nivelAzucar < 0) {
                                datosCorrectos = false;
                                errores.add(
                                    "El nivel de azúcar no puede ser negativo."
                                );
                            }
                        } catch (NumberFormatException ex) {
                            datosCorrectos = false;
                            errores.add(
                                "El nivel de azúcar debe ser un número entero."
                            );
                        }
                    }

                    if (datosCorrectos) {
                        TProductoCafeteria productoTransfer = null;

                        if (esComida) {
                            productoTransfer = new TComida(
                                idProductoAModificar,
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
                                idProductoAModificar,
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
                            .handle(
                                new Context(
                                    Events.MODIFICAR_PRODUCTO_OK_VIEW,
                                    productoTransfer
                                )
                            );

                    } else {
                        StringBuilder mensajeError = new StringBuilder(
                            "Corrija los siguientes errores:\n"
                        );
                        for (String error : errores) {
                            mensajeError.append("- ").append(error).append("\n");
                        }
                        JOptionPane.showMessageDialog(
                            GUIModificarProductoCafeteria.this,
                            mensajeError.toString(),
                            "ERROR DE VALIDACIÓN",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        );

        botonAccion.add(okButton);
        botonAccion.add(cancelButton);

        panelPrincipal.add(botonAccion, BorderLayout.SOUTH);

        // --- Configuración final de la ventana ---
        this.setContentPane(panelPrincipal);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void setCamposEditablesEnabled(boolean enabled) {
        idMarcaField.setEnabled(enabled);
        nombreField.setEnabled(enabled);
        precioField.setEnabled(enabled);
        stockField.setEnabled(enabled);
        nivelNutricionField.setEnabled(enabled);
        //tipoComboBox.setEnabled(enabled);
    }

    private void actualizarVisibilidadCamposEspecificos() {
        boolean esComidaSeleccionado = tipoComboBox
            .getSelectedItem()
            .equals("Comida");

        boolean camposHabilitados = nombreField != null && nombreField.isEnabled();

        panelComida.setVisible(esComidaSeleccionado);
        tipoComidaField.setEnabled(esComidaSeleccionado && camposHabilitados);
        if (!esComidaSeleccionado) tipoComidaField.setText("");

        panelAzucar.setVisible(!esComidaSeleccionado);
        nivelAzucarField.setEnabled(!esComidaSeleccionado && camposHabilitados);
        if (esComidaSeleccionado) nivelAzucarField.setText("");

        if (this.isShowing()) {
             this.pack();
        }
    }

    private JPanel createLabelTextPair(JLabel label, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout(5, 0));
        panel.setBackground(Color.WHITE);
        label.setBackground(Color.WHITE);
        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createLabelComboPair(JLabel label, JComboBox<String> comboBox) {
        JPanel panel = new JPanel(new BorderLayout(5, 0));
        panel.setBackground(Color.WHITE);
        label.setBackground(Color.WHITE);
        panel.add(label, BorderLayout.WEST);
        panel.add(comboBox, BorderLayout.CENTER);
        return panel;
    }

    @Override
    public void actualizar(Context context) {
        if (context == null) {
            return;
        }

        if (context.getData() instanceof TProductoCafeteria) {
            TProductoCafeteria producto = (TProductoCafeteria) context.getData();

            idProductoAModificar = producto.getId();

            idBuscarField.setEnabled(false);
            buscarButton.setEnabled(false);

            idMarcaField.setText(String.valueOf(producto.getIdMarca()));
            nombreField.setText(producto.getNombre());
            precioField.setText(String.valueOf(producto.getPrecio()));
            stockField.setText(String.valueOf(producto.getStock()));
            nivelNutricionField.setText(
                String.valueOf(producto.getNivelNutricion())
            );

            if (producto instanceof TComida) {
                tipoComboBox.setSelectedItem("Comida");
                tipoComidaField.setText((producto.getTipoComida()));
                nivelAzucarField.setText("");
            } else if (producto instanceof TBebida) {
                tipoComboBox.setSelectedItem("Bebida");
                nivelAzucarField.setText(String.valueOf((producto.getNivelAzucar())));
                tipoComidaField.setText(""); 
            }

            setCamposEditablesEnabled(true);
            okButton.setEnabled(true);

            actualizarVisibilidadCamposEspecificos();

            this.pack();

        } else if (context.getData() instanceof Integer) {
            int ret = (Integer) context.getData();
            String message = "";
            String title = "Resultado Operación";
            int messageType = JOptionPane.PLAIN_MESSAGE;

            switch (ret) {
                case -1: message = "Datos de entrada nulos o incompletos."; break;
                case -2: message = "Error general de la base de datos."; break;
                case -3: message = "El producto no existe o está inactivo."; break;
                case -4: message = "Ya existe un producto activo con el nombre introducido."; break;
                case -5: message = "El ID de Marca es inválido o no fue proporcionado."; break;
                case -6: message = "La Marca con el ID introducido no existe."; break;
                case -7: message = "La Marca con el ID introducido no está activa."; break;
                case -8: message = "ID de producto no válido para la operación."; break;
                default:
                    if (ret > 0) {
                        message = "Operación realizada con éxito. ID afectado: " + ret;
                        title = "Éxito";
                        JOptionPane.showMessageDialog(this, message, title, messageType);
                        dispose();
                        return;
                    } else {
                        message = "Error desconocido. Código: " + ret;
                    }
                    break;
            }

            messageType = JOptionPane.ERROR_MESSAGE;
            title = "Error";
            JOptionPane.showMessageDialog(this, message, title, messageType);

            if (ret <= 0 && idProductoAModificar == null) {
                idBuscarField.setEnabled(true);
                buscarButton.setEnabled(true);
                setCamposEditablesEnabled(false);
                if (okButton != null) {
                    okButton.setEnabled(false);
                }
            }

        } else if (context.getData() != null) {
            JOptionPane.showMessageDialog(
                this,
                "Respuesta inesperada: " + context.getData().toString(),
                "Error Inesperado",
                JOptionPane.ERROR_MESSAGE
            );
            if (idProductoAModificar == null) {
                 idBuscarField.setEnabled(true);
                 buscarButton.setEnabled(true);
            }
        }
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 130, 180));
        button.setFocusPainted(false);
        button.setBorder(
            BorderFactory.createLineBorder(new Color(60, 60, 60), 1)
        );
        button.setPreferredSize(new Dimension(150, 35));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(new Color(50, 100, 150));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(new Color(70, 130, 180));
                }
            }
        );
        return button;
    }
}

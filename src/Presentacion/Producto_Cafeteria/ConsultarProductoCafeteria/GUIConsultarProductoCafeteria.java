package Presentacion.Producto_Cafeteria.ConsultarProductoCafeteria;

import Negocio.Producto_Cafeteria.TProductoCafeteria;
import Negocio.Producto_Cafeteria.TComida;
import Negocio.Producto_Cafeteria.TBebida;

import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIConsultarProductoCafeteria extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    private JTextField idField;
    private JButton consultarButton;

    private JLabel idDisplayLabel;
    private JLabel nombreDisplayLabel;
    private JLabel precioDisplayLabel;
    private JLabel stockDisplayLabel;
    private JLabel nivelNutricionDisplayLabel;
    private JLabel idMarcaDisplayLabel;
    private JLabel tipoEspecificoLabel;

    private JPanel panelDatosProducto;

    public GUIConsultarProductoCafeteria() {
        super("CONSULTAR PRODUCTO CAFETERIA");
        this.initGUI();
    }

    private void initGUI() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );
        panelPrincipal.setBackground(Color.WHITE);

        JLabel titulo = new JLabel(
            "CONSULTAR PRODUCTO",
            SwingConstants.CENTER
        );
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        titulo.setOpaque(true);
        titulo.setBackground(new Color(70, 130, 180));
        titulo.setPreferredSize(new Dimension(0, 50));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(Color.WHITE);

        JPanel panelConsulta = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelConsulta.setBackground(Color.WHITE);
        panelConsulta.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel consultaLabel = new JLabel("Introduzca el ID del producto: ");
        idField = new JTextField(10);
        idField.setToolTipText("ID numérico del producto a consultar");
        consultarButton = createStyledButton("CONSULTAR");
        consultarButton.setPreferredSize(new Dimension(120, 35));

        panelConsulta.add(consultaLabel);
        panelConsulta.add(idField);
        panelConsulta.add(consultarButton);

        panelCentral.add(panelConsulta);

        panelDatosProducto = new JPanel(new GridLayout(0, 1, 5, 10));
        panelDatosProducto.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Datos del Producto"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            )
        );
        panelDatosProducto.setBackground(Color.WHITE);
        panelDatosProducto.setVisible(false);

        idDisplayLabel = createDataLabel("ID: ---");
        nombreDisplayLabel = createDataLabel("Nombre: ---");
        precioDisplayLabel = createDataLabel("Precio: ---");
        stockDisplayLabel = createDataLabel("Stock: ---");
        nivelNutricionDisplayLabel = createDataLabel("Nivel Nutrición: ---");
        idMarcaDisplayLabel = createDataLabel("ID Marca: ---");
        tipoEspecificoLabel = createDataLabel("Tipo/Nivel Azúcar: ---");

        panelDatosProducto.add(idDisplayLabel);
        panelDatosProducto.add(nombreDisplayLabel);
        panelDatosProducto.add(precioDisplayLabel);
        panelDatosProducto.add(stockDisplayLabel);
        panelDatosProducto.add(nivelNutricionDisplayLabel);
        panelDatosProducto.add(idMarcaDisplayLabel);
        panelDatosProducto.add(tipoEspecificoLabel);

        panelCentral.add(panelDatosProducto);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        JPanel panelAccion = new JPanel();
        panelAccion.setBackground(Color.WHITE);
        panelAccion.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JButton cancelButton = createStyledButton("CERRAR");
        cancelButton.setToolTipText("Cerrar esta ventana");
        cancelButton.addActionListener(e -> {
            dispose();
        });

        panelAccion.add(cancelButton);
        panelPrincipal.add(panelAccion, BorderLayout.SOUTH);

        consultarButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Integer id = Integer.parseInt(idField.getText().trim());
                        if (id <= 0) {
                            JOptionPane.showMessageDialog(
                                GUIConsultarProductoCafeteria.this,
                                "El ID debe ser un número positivo.",
                                "Error de Entrada",
                                JOptionPane.ERROR_MESSAGE
                            );
                        } else {
                            limpiarDatosDisplay();
                            panelDatosProducto.setVisible(false);
                            pack();

                            Controller.getInstance()
                                .handle(
                                    new Context(
                                        Events.CONSULTAR_PRODUCTO_OK_VIEW,
                                        id
                                    )
                                );
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                            GUIConsultarProductoCafeteria.this,
                            "El ID debe ser un número entero válido.",
                            "Error de Entrada",
                            JOptionPane.ERROR_MESSAGE
                        );
                        limpiarDatosDisplay();
                        panelDatosProducto.setVisible(false);
                        pack();
                    }
                }
            }
        );

        this.setContentPane(panelPrincipal);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    // helper para crear labels de datos con estilo uniforme
    private JLabel createDataLabel(String initialText) {
        JLabel label = new JLabel(initialText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        return label;
    }

    // para limpiar los labels de display
    private void limpiarDatosDisplay() {
        idDisplayLabel.setText("ID: ---");
        nombreDisplayLabel.setText("Nombre: ---");
        precioDisplayLabel.setText("Precio: ---");
        stockDisplayLabel.setText("Stock: ---");
        nivelNutricionDisplayLabel.setText("Nivel Nutrición: ---");
        idMarcaDisplayLabel.setText("ID Marca: ---");
        tipoEspecificoLabel.setText("Tipo/Nivel Azúcar: ---");
    }

    @Override
    public void actualizar(Context context) {
        if (context == null) {
            return;
        }

        if (context.getEvent() == Events.ERROR_PRODUCTO_NO_EXISTE) {
            JOptionPane.showMessageDialog(
                this,
                "El producto consultado no existe o no está activo.",
                "Producto No Encontrado",
                JOptionPane.ERROR_MESSAGE
            );
            limpiarDatosDisplay();
            panelDatosProducto.setVisible(false);
            pack();
            return;
        }

        if (context.getData() instanceof TProductoCafeteria) {
            TProductoCafeteria producto = (TProductoCafeteria) context.getData();

            idDisplayLabel.setText("ID: " + producto.getId());
            nombreDisplayLabel.setText("Nombre: " + producto.getNombre());
            precioDisplayLabel.setText(
                String.format("Precio: %.2f €", producto.getPrecio())
            );
            stockDisplayLabel.setText("Stock: " + producto.getStock());
            nivelNutricionDisplayLabel.setText(
                "Nivel Nutrición: " + producto.getNivelNutricion()
            );
            idMarcaDisplayLabel.setText("ID Marca: " + producto.getIdMarca());

            if (producto instanceof TComida) {
                tipoEspecificoLabel.setText(
                    "Tipo Comida: " + ((TComida) producto).getTipoComida()
                );
            } else if (producto instanceof TBebida) {
                tipoEspecificoLabel.setText(
                    "Nivel Azúcar: " + ((TBebida) producto).getNivelAzucar()
                );
            } else {
                tipoEspecificoLabel.setText("Tipo Específico: Desconocido");
            }

            panelDatosProducto.setVisible(true);
            this.pack();

        } else if (context.getData() instanceof Integer) {
            int ret = (Integer) context.getData();
            String message = "Error desconocido al consultar. Código: " + ret;
            JOptionPane.showMessageDialog(
                this,
                message,
                "Error en Consulta",
                JOptionPane.ERROR_MESSAGE
            );
            limpiarDatosDisplay();
            panelDatosProducto.setVisible(false);
            pack();

        } else if (context.getData() != null) {
            JOptionPane.showMessageDialog(
                this,
                "Respuesta inesperada: " + context.getData().toString(),
                "Error Inesperado",
                JOptionPane.ERROR_MESSAGE
            );
            limpiarDatosDisplay();
            panelDatosProducto.setVisible(false);
            pack();
        }
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 130, 180));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 1));
        button.setPreferredSize(new Dimension(150, 35)); // Tamaño estándar
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
package Presentacion.Compra_Cafeteria.DevolverProducto;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIDevolverProducto extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    public GUIDevolverProducto() {
        super("DEVOLUCION PRODUCTO");
        initGUI();
    }


    private void initGUI() {
        this.setMinimumSize(new Dimension(450, 200));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Controller.getInstance().handle(new Context(Events.COMPRA_CAF_VIEW, null));

                } catch (Exception f) {
                    f.printStackTrace();
                } finally {
                    dispose();
                }
            }
        });


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Labels and Text Fields in separate panels
        JPanel idCompraPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel idCompra = new JLabel("Introduzca ID compra: ");
        JTextField textoIdCompra = new JTextField();
        textoIdCompra.setPreferredSize(new Dimension(200, 40));
        idCompraPanel.add(idCompra);
        idCompraPanel.add(textoIdCompra);

        JPanel idProductoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel product = new JLabel("Introduzca ID de producto a devolver: ");
        JTextField textoIdProducto = new JTextField();
        textoIdProducto.setPreferredSize(new Dimension(200, 40));
        idProductoPanel.add(product);
        idProductoPanel.add(textoIdProducto);

        JPanel cantidadPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel cantidad = new JLabel("Introduzca la cantidad a devolver: ");
        JTextField textoCantidad = new JTextField();
        textoCantidad.setPreferredSize(new Dimension(200, 40));
        cantidadPanel.add(cantidad);
        cantidadPanel.add(textoCantidad);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton accept = new JButton("Aceptar");
        JButton cancel = new JButton("Cancelar");

        accept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] o = new Object[3];
                o[0] = Integer.valueOf(textoIdCompra.getText());
                o[1] = Integer.valueOf(textoIdProducto.getText());
                o[2] = Integer.valueOf(textoCantidad.getText());
                Context contexto = new Context(Events.RES_DEVOLUCION_PRODUCTO_CAF_OK_VIEW, o);
                Controller.getInstance().handle(contexto);
                dispose();
            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });

        buttonPanel.add(accept);
        buttonPanel.add(cancel);

        // Add panels to main panel
        mainPanel.add(idCompraPanel);
        mainPanel.add(idProductoPanel);
        mainPanel.add(idCompraPanel);
        mainPanel.add(idProductoPanel);
        mainPanel.add(cantidadPanel);
        mainPanel.add(buttonPanel);

        setContentPane(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actualizar(Context context) {
        
    }
}
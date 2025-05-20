package Presentacion.Alergeno_Cafeteria.VincularAlergeno;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Alergeno_Cafeteria.TAlergeno;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIVincularAlergeno extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    public GUIVincularAlergeno() {
        super("VINCULAR ALÉRGENO A PRODUCTO");
        this.initGUI();
    }

    private void initGUI() {

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        JPanel panelParaProducto = new JPanel();
        JPanel panelParaAlergeno = new JPanel();
        JPanel panelParaBotones = new JPanel();

        JLabel vincularProducto = new JLabel("Introduzca el ID del Producto: ");
        JTextField cuadroTextoProducto = new JTextField(20);
        cuadroTextoProducto.setEditable(true);
        cuadroTextoProducto.setToolTipText("ID del producto al que quieres vincular un alérgeno.");

        JLabel vincularAlergeno = new JLabel("Introduzca el ID del Alérgeno: ");
        JTextField cuadroTextoAlergeno = new JTextField(20);
        cuadroTextoAlergeno.setEditable(true);
        cuadroTextoAlergeno.setToolTipText("ID del alérgeno que quieres vincular a un producto.");

        JButton ok = new JButton("OK");
        ok.setToolTipText("Aceptar");
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                boolean validar = true;

                // Validar ID del producto
                String idProducto = cuadroTextoProducto.getText().trim();
                int idProd = 0;
                try {
                    idProd = Integer.parseInt(idProducto);
                    if (idProd <= 0) {
                        validar = false;
                        JOptionPane.showMessageDialog(null, "El ID del producto debe ser mayor que 0.", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    validar = false;
                    JOptionPane.showMessageDialog(null, 
                        "El ID del producto debe ser un número entero válido y no contener letras o espacios.",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                // Validar ID del alérgeno
                String idAlergeno = cuadroTextoAlergeno.getText().trim();
                int idAler = 0;
                try {
                    idAler = Integer.parseInt(idAlergeno);
                    if (idAler <= 0) {
                        validar = false;
                        JOptionPane.showMessageDialog(null, "El ID del alérgeno debe ser mayor que 0.", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    validar = false;
                    JOptionPane.showMessageDialog(null, 
                        "El ID del alérgeno debe ser un número entero válido y no contener letras o espacios.",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                if (validar) {
                	TAlergeno a = new TAlergeno();
                	a.setIdAlergeno(idAler);
                	a.setIdProducto(idProd);
                    Controller.getInstance().handle(new Context(Events.VINCULAR_ALERGENO_OK_VIEW,
                            a));
                    dispose();
                }
            }
        });

        JButton ko = new JButton("CANCEL");
        ko.setToolTipText("Cancelar");
        ko.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().handle(new Context(Events.ALERGENO_CANCEL_VIEW, null));
                dispose();
            }
        });

        panelParaProducto.add(vincularProducto);
        panelParaProducto.add(cuadroTextoProducto);
        panelParaAlergeno.add(vincularAlergeno);
        panelParaAlergeno.add(cuadroTextoAlergeno);
        panelParaBotones.add(ok);
        panelParaBotones.add(ko);

        panelPrincipal.add(panelParaProducto);
        panelPrincipal.add(panelParaAlergeno);
        panelPrincipal.add(panelParaBotones);

        this.setContentPane(panelPrincipal);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLocation(200, 200);
    }

    @Override
    public void actualizar(Context context) {
        // No se necesita actualizar esta vista
    }
}

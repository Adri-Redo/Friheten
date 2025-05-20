package Presentacion.Alergeno_Cafeteria.DesvincularAlergeno;



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

public class GUIDesvincularAlergeno extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    public GUIDesvincularAlergeno() {
        super("DESVINCULAR ALÉRGENO");
        this.initGUI();
    }

    private void initGUI() {

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        JPanel panelParaProducto = new JPanel();
        JPanel panelParaAlergeno = new JPanel();
        JPanel panelParaBotones = new JPanel();

        JLabel desvincularProducto = new JLabel("Introduzca el ID del Producto: ");
        JTextField cuadroTextoProducto = new JTextField(20);
        cuadroTextoProducto.setEditable(true);
        cuadroTextoProducto.setToolTipText("ID del producto del cual deseas desvincular un alérgeno.");

        JLabel desvincularAlergeno = new JLabel("Introduzca el ID del Alérgeno: ");
        JTextField cuadroTextoAlergeno = new JTextField(20);
        cuadroTextoAlergeno.setEditable(true);
        cuadroTextoAlergeno.setToolTipText("ID del alérgeno que deseas desvincular del producto.");

        JButton ok = new JButton("OK");
        ok.setToolTipText("Aceptar");
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                boolean validar = true;

                String idProducto = cuadroTextoProducto.getText().trim();
                int idProd = 0;
                try {
                    idProd = Integer.parseInt(idProducto);
                    if (idProd <= 0) {
                        validar = false;
                        JOptionPane.showMessageDialog(null,
                                "El ID del producto introducido debe ser mayor que 0.",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException m) {
                    validar = false;
                    JOptionPane.showMessageDialog(null,
                            "Ha introducido el ID " + idProducto
                                    + "; el ID no debe contener letras ni caracteres especiales, tampoco puede estar vacío.",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                String idAlergeno = cuadroTextoAlergeno.getText().trim();
                int idAlerg = 0;
                try {
                    idAlerg = Integer.parseInt(idAlergeno);
                    if (idAlerg <= 0) {
                        validar = false;
                        JOptionPane.showMessageDialog(null,
                                "El ID del alérgeno introducido debe ser mayor que 0.",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException m) {
                    validar = false;
                    JOptionPane.showMessageDialog(null,
                            "Ha introducido el ID " + idAlergeno
                                    + "; el ID no debe contener letras ni caracteres especiales, tampoco puede estar vacío.",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                if (validar) {
                	TAlergeno a = new TAlergeno();
                	a.setIdAlergeno(idAlerg);
                	a.setIdProducto(idProd);
                    Controller.getInstance().handle(new Context(Events.DESVINCULAR_ALERGENO_OK_VIEW,
                            a));
                    dispose();
                }

                dispose();
            }
        });

        JButton ko = new JButton("CANCEL");
        ko.setToolTipText("Cancelar");
        ko.addActionListener((e) -> {
            Controller.getInstance().handle(new Context(Events.ALERGENO_CANCEL_VIEW, null));
            dispose();
        });

        panelParaProducto.add(desvincularProducto);
        panelParaProducto.add(cuadroTextoProducto);
        panelParaAlergeno.add(desvincularAlergeno);
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
        // No se debe actualizar esta vista.
    }

}

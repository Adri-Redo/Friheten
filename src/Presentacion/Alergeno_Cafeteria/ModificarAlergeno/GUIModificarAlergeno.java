package Presentacion.Alergeno_Cafeteria.ModificarAlergeno;



import java.awt.FlowLayout;
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

public class GUIModificarAlergeno extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    public GUIModificarAlergeno() {
        super("MODIFICAR ALÉRGENO");
        this.initGUI();
    }

    private void initGUI() {

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        JPanel panelID = new JPanel();
        panelID.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JPanel panelNuevoNombre = new JPanel();
        panelNuevoNombre.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JPanel panelNuevoRiesgo = new JPanel();
        panelNuevoRiesgo.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JPanel panelNuevaFuente = new JPanel();
        panelNuevaFuente.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JPanel panelBotones = new JPanel();

        JLabel modificarAlergeno = new JLabel("Introduzca el ID del alérgeno a modificar: ");
        JTextField cuadroTextoID = new JTextField(20);
        cuadroTextoID.setEditable(true);
        cuadroTextoID.setToolTipText("ID del alérgeno que quieres modificar.");

        JLabel nuevoNombre = new JLabel("Nuevo nombre: ");
        JTextField cuadroTextoNombre = new JTextField(20);
        cuadroTextoNombre.setEditable(true);
        cuadroTextoNombre.setToolTipText("Nuevo nombre del alérgeno.");

        JLabel nuevoRiesgo = new JLabel("Nuevo riesgo (número entero): ");
        JTextField cuadroTextoRiesgo = new JTextField(20);
        cuadroTextoRiesgo.setEditable(true);
        cuadroTextoRiesgo.setToolTipText("Nuevo nivel de riesgo (número entero).");

        JLabel nuevaFuente = new JLabel("Nueva fuente: ");
        JTextField cuadroTextoFuente = new JTextField(20);
        cuadroTextoFuente.setEditable(true);
        cuadroTextoFuente.setToolTipText("Nueva fuente del alérgeno.");

        JButton ok = new JButton("OK");
        ok.setToolTipText("Aceptar");
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean validar = true;

                // Validar ID
                String idAlergeno = cuadroTextoID.getText().trim();
                int id = 0;
                try {
                    id = Integer.parseInt(idAlergeno);
                    if (id <= 0) {
                        validar = false;
                        JOptionPane.showMessageDialog(null, "El ID debe ser un número entero mayor que 0.", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    validar = false;
                    JOptionPane.showMessageDialog(null,
                            "El ID debe ser un número entero sin letras ni caracteres especiales.", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }

                // Validar nombre
                String nombreAlergeno = cuadroTextoNombre.getText().trim();
                if (validar && nombreAlergeno.isEmpty()) {
                    validar = false;
                    JOptionPane.showMessageDialog(null, "El nombre del alérgeno no puede estar vacío.", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }

                // Validar riesgo
                String riesgoAlergenoStr = cuadroTextoRiesgo.getText().trim();
                int riesgo = 0;
                try {
                    riesgo = Integer.parseInt(riesgoAlergenoStr);
                    if (riesgo < 0) {
                        validar = false;
                        JOptionPane.showMessageDialog(null, "El riesgo debe ser un número entero no negativo.", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    validar = false;
                    JOptionPane.showMessageDialog(null, "El riesgo debe ser un número entero válido.", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }

                // Validar fuente
                String fuenteAlergeno = cuadroTextoFuente.getText().trim();
                if (validar && fuenteAlergeno.isEmpty()) {
                    validar = false;
                    JOptionPane.showMessageDialog(null, "La fuente del alérgeno no puede estar vacía.", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }

                // Si todo es válido, enviar el contexto
                if (validar) {
                    TAlergeno alergeno = new TAlergeno();
                    alergeno.setIdAlergeno(id);
                    alergeno.setFuente(fuenteAlergeno);
                    alergeno.setRiesgo(riesgo);
                    alergeno.setNombre(nombreAlergeno);
                    Controller.getInstance().handle(new Context(Events.MODIFICAR_ALERGENO_OK_VIEW, alergeno));
                    dispose();
                }
            }
        });

        JButton ko = new JButton("CANCEL");
        ko.setToolTipText("Cancelar");
        ko.addActionListener(e -> {
            Controller.getInstance().handle(new Context(Events.ALERGENO_CANCEL_VIEW, null));
            dispose();
        });

        panelID.add(modificarAlergeno);
        panelID.add(cuadroTextoID);

        panelNuevoNombre.add(nuevoNombre);
        panelNuevoNombre.add(cuadroTextoNombre);

        panelNuevoRiesgo.add(nuevoRiesgo);
        panelNuevoRiesgo.add(cuadroTextoRiesgo);

        panelNuevaFuente.add(nuevaFuente);
        panelNuevaFuente.add(cuadroTextoFuente);

        panelBotones.add(ok);
        panelBotones.add(ko);

        panelPrincipal.add(panelID);
        panelPrincipal.add(panelNuevoNombre);
        panelPrincipal.add(panelNuevoRiesgo);
        panelPrincipal.add(panelNuevaFuente);
        panelPrincipal.add(panelBotones);

        this.setContentPane(panelPrincipal);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLocation(200, 200);
    }

    @Override
    public void actualizar(Context context) {
        // No debe actualizarse esta ventana.
    }
}

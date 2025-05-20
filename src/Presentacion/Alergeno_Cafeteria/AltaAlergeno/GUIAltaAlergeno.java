package Presentacion.Alergeno_Cafeteria.AltaAlergeno;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class GUIAltaAlergeno extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    public GUIAltaAlergeno() {
        super("ALTA ALERGENO");
        this.initGUI();
    }

    private void initGUI() {
        setTitle("Alta Alergeno");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel(new GridLayout(4, 2, 10, 10)); // Usamos GridLayout para organizar los componentes

        // Nombre del alérgeno
        JLabel nombreLabel = new JLabel("Introduzca el nombre del alergeno: ");
        JTextField nombreTexto = new JTextField(20);
        nombreTexto.setEditable(true);
        nombreTexto.setToolTipText("Nombre que dar de alta. SOLO ADMITE LETRAS.");

        // Riesgo del alérgeno
        JLabel riesgoLabel = new JLabel("Introduzca el riesgo del alergeno: ");
        JTextField riesgoTexto = new JTextField(20);
        riesgoTexto.setEditable(true);
        riesgoTexto.setToolTipText("Riesgo del alergeno, solo admite números.");

        // Fuente del alérgeno
        JLabel fuenteLabel = new JLabel("Introduzca la fuente del alergeno: ");
        JTextField fuenteTexto = new JTextField(20);
        fuenteTexto.setEditable(true);
        fuenteTexto.setToolTipText("Fuente del alergeno.");

        // Botones
        JButton ok = new JButton("OK");
        ok.setToolTipText("Aceptar");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean validar = true;
                String nombreAlergeno = nombreTexto.getText().trim();
                String riesgoAlergeno = riesgoTexto.getText().trim();
                String fuenteAlergeno = fuenteTexto.getText().trim();

                if (!nombreAlergeno.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$") || !isNumeric(riesgoAlergeno) || fuenteAlergeno.isEmpty()) {
                    validar = false;
                }

                if (validar) {
                    String nombreFormateado = nombreAlergeno.substring(0, 1).toUpperCase()
                            + nombreAlergeno.substring(1).toLowerCase();
                    TAlergeno al = new TAlergeno();
                    al.setFuente(fuenteAlergeno);
                    al.setNombre(nombreFormateado);
                    al.setRiesgo(Integer.parseInt(riesgoAlergeno));
                    Context contexto = new Context(Events.ALTA_ALERGENO_OK_VIEW, al);
                    Controller.getInstance().handle(contexto);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "El nombre del Alergeno solo puede tener letras, el riesgo debe ser numérico y ningún campo debe estar vacío.",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
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

        // Añadir componentes al panel principal
        panelPrincipal.add(nombreLabel);
        panelPrincipal.add(nombreTexto);
        panelPrincipal.add(riesgoLabel);
        panelPrincipal.add(riesgoTexto);
        panelPrincipal.add(fuenteLabel);
        panelPrincipal.add(fuenteTexto);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(ok);
        panelBotones.add(ko);

        // Añadir paneles a la ventana
        add(panelPrincipal, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        this.pack();
        this.setLocation(200, 200);
        this.setVisible(true);
    }

    public void actualizar(Context context) {

    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

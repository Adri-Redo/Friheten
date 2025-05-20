package Presentacion.Alergeno_Cafeteria.BajaAlergeno;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIBajaAlergeno extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    public GUIBajaAlergeno() {
        super("BAJA ALERGENO");
        this.initGUI();
    }

    private void initGUI() {
        setTitle("Baja de Alérgenos");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(200, 200);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new GridLayout(3, 1, 10, 10));

        // Etiqueta e input para ID del alérgeno
        JLabel idLabel = new JLabel("Introduzca el ID del alérgeno a eliminar:");
        JTextField idTextField = new JTextField(20);

        // Botones
        JButton okButton = new JButton("Eliminar");
        JButton cancelButton = new JButton("Cancelar");

        // Configuración de acciones
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = idTextField.getText().trim();

                if (isNumeric(idText)) {
                    int id = Integer.parseInt(idText);
                    Context context = new Context(Events.BAJA_ALERGENO_OK_VIEW, id);
                    Controller.getInstance().handle(context);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().handle(new Context(Events.ALERGENO_CANCEL_VIEW, null));
                dispose();
            }
        });

        // Añadir componentes al panel principal
        panelPrincipal.add(idLabel);
        panelPrincipal.add(idTextField);

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        panelPrincipal.add(buttonPanel);

        // Añadir panel principal a la ventana
        this.setContentPane(panelPrincipal);
        this.setVisible(true);
    }

    public void actualizar(Context context) {
        // Este método puede ser usado para actualizar la interfaz según el contexto
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

package Presentacion.Personal.PersonalPorRangoSueldo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

public class GUIPersonalPorRangoSueldo extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panelPrincipal;
    private JTextField txtMinimo;
    private JTextField txtMaximo;
    private JButton btnAceptar;
    private JButton btnCancelar;

	public GUIPersonalPorRangoSueldo() {
		super("PERSONAL POR RANGO SUELDO");
		this.initGUI();
	}

	private void initGUI() {
        // Inicializamos el panel principal
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label y TextField para el sueldo mínimo
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPrincipal.add(new JLabel("Mínimo"), gbc);

        txtMinimo = new JTextField(10);
        gbc.gridx = 1;
        panelPrincipal.add(txtMinimo, gbc);

        // Label y TextField para el sueldo máximo
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPrincipal.add(new JLabel("Máximo"), gbc);

        txtMaximo = new JTextField(10);
        gbc.gridx = 1;
        panelPrincipal.add(txtMaximo, gbc);

        // Botón de Aceptar
        btnAceptar = new JButton("Aceptar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPrincipal.add(btnAceptar, gbc);

        // Botón de Cancelar
        btnCancelar = new JButton("Cancelar");
        gbc.gridx = 1;
        panelPrincipal.add(btnCancelar, gbc);

        // Configuración del botón de cancelar
        btnCancelar.addActionListener(e -> dispose());

        // Configuración del botón de aceptar
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String minimoStr = txtMinimo.getText();
                String maximoStr = txtMaximo.getText();
                try {
                    int minimo = Integer.parseInt(minimoStr);
                    int maximo = Integer.parseInt(maximoStr);
                    
                    if(minimo > maximo) throw new NumberFormatException();
                    Object[] data = new Object[2];
                    Object[] data2 = new Object[2];
                    data[0] = "PersonalPorRangoSueldo";
                    data2[0] = minimo;
                    data2[1] = maximo;
                    data[1] = data2;
                    // Lógica de procesamiento con minimo y maximo
                    Controller.getInstance().handle(new Context(Events.MOSTRAR_PERSONAL_PORRANGOSUELDO_OK_VIEW, data));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(GUIPersonalPorRangoSueldo.this, 
                        "Por favor ingrese valores numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Configuración de la ventana
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(200, 200);
        this.setVisible(true);
    }

	@Override
	public void actualizar(Context context) {

		

		
	}

}

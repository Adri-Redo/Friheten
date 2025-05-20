package Presentacion.Habilidad.BajaHabilidad;

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

public class GUIBajaHabilidad extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIBajaHabilidad() {
		super("BAJA HABILIDAD");
		this.initGUI();

	}

	private void initGUI() {

		JPanel panelPrincipal = new JPanel();
		JPanel panelParaUsuario = new JPanel();

		JLabel bajaHabilidad = new JLabel("Introduzca el ID de la habilidad: ");
		JTextField cuadroTexto = new JTextField(20);
		cuadroTexto.setEditable(true);
		cuadroTexto.setToolTipText("ID de la habilidad que dar de baja (solo numeros positivos)");

		JButton ok = new JButton("OK");
		ok.setToolTipText("Aceptar");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean validar = true;

				String idHabilidad = cuadroTexto.getText().trim();
				int id = 0;
				try {
					id = Integer.parseInt(idHabilidad);
					if (id <= 0) {
						validar = false;
						JOptionPane.showMessageDialog(null, "El id introducido debe ser mayor que 0.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException m) {

					validar = false;
					JOptionPane.showMessageDialog(null, " Ha introducido el id " + idHabilidad
							+ "; el id introducido no debe contener letras ni caracteres especiales. Tampoco espacios ni puede estar vacio.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				}

				if (validar)
					Controller.getInstance().handle(new Context(Events.BAJA_HABILIDAD_OK_VIEW, id));

				dispose();
			}

		});

		JButton ko = new JButton("CANCEL");
		ko.setToolTipText("Cancelar");
		ko.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().handle(new Context(Events.HABILIDAD_CANCEL_VIEW, null));
				dispose();
			}

		});

		panelParaUsuario.add(bajaHabilidad);
		panelParaUsuario.add(cuadroTexto);
		panelParaUsuario.add(ok);
		panelParaUsuario.add(ko);

		panelPrincipal.add(panelParaUsuario);

		this.setContentPane(panelPrincipal);
		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);

	}

	@Override
	public void actualizar(Context context) {
		// Tampoco debe actualizarse. Se deben actualizar, por ejemplo, listar o
		// consultar.
	}

}

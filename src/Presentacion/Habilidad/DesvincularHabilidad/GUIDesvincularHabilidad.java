package Presentacion.Habilidad.DesvincularHabilidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Habilidad.THabilidadPersonal;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIDesvincularHabilidad extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIDesvincularHabilidad() {

		super("DESVINCULAR HABILIDAD");
		this.initGUI();
	}

	private void initGUI() {

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		JPanel panelParaUsuario = new JPanel();
		JPanel panelParaHabilidad = new JPanel();
		JPanel panelParaBotones = new JPanel();

		JLabel desvincularPersonal = new JLabel("Introduzca el ID de la Persona: ");
		JTextField cuadroTextoPersonal = new JTextField(20);
		cuadroTextoPersonal.setEditable(true);
		cuadroTextoPersonal.setToolTipText("ID de la persona que quieres desvincular de una habilidad.");
		JLabel desvincularHabilidad = new JLabel("Introduzca el ID de la Habilidad: ");
		JTextField cuadroTextoHabilidad = new JTextField(20);
		cuadroTextoHabilidad.setEditable(true);
		cuadroTextoHabilidad.setToolTipText("ID de la habilidad que quieres desvincular de una persona.");

		JButton ok = new JButton("OK");
		ok.setToolTipText("Aceptar");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean validar = true;

				String idPersonal = cuadroTextoPersonal.getText().trim();
				int idPer = 0;
				try {
					idPer = Integer.parseInt(idPersonal);
					if (idPer <= 0) {
						validar = false;
						JOptionPane.showMessageDialog(null, "El id de la persona introducido debe ser mayor que 0.",
								"ERROR", JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException m) {

					validar = false;
					JOptionPane.showMessageDialog(null, " Ha introducido el id " + idPersonal
							+ "; el id introducido no debe contener letras ni caracteres especiales. Tampoco espacios ni puede ser vacio.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				}

				String idHabilidad = cuadroTextoHabilidad.getText().trim();
				int idHab = 0;
				try {
					idHab = Integer.parseInt(idHabilidad);
					if (idHab <= 0) {
						validar = false;
						JOptionPane.showMessageDialog(null, "El id de habilidad introducido debe ser mayor que 0.",
								"ERROR", JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException m) {

					validar = false;
					JOptionPane.showMessageDialog(null, " Ha introducido el id " + idHabilidad
							+ "; el id introducido no debe contener letras ni caracteres especiales. Tampoco espacios.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				}

				if (validar) {
					Controller.getInstance().handle(new Context(Events.DESVINCULAR_HABILIDAD_OK_VIEW,
							new THabilidadPersonal(idPer, idHab, false)));
				}

				dispose();
			}

		});
		JButton ko = new JButton("CANCEL");
		ko.setToolTipText("Cancelar");
		ko.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.HABILIDAD_CANCEL_VIEW, null));
			dispose();
		});

		panelParaUsuario.add(desvincularPersonal);
		panelParaUsuario.add(cuadroTextoPersonal);
		panelParaBotones.add(ok);
		panelParaBotones.add(ko);
		panelParaHabilidad.add(desvincularHabilidad);
		panelParaHabilidad.add(cuadroTextoHabilidad);

		panelPrincipal.add(panelParaUsuario);
		panelPrincipal.add(panelParaHabilidad);
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

package Presentacion.Habilidad.AltaHabilidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Habilidad.THabilidad;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIAltaHabilidad extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIAltaHabilidad() {

		super("ALTA HABILIDAD");
		this.initGUI();
	}

	private void initGUI() {

		JPanel panelPrincipal = new JPanel();
		JPanel panelParaUsuario = new JPanel();

		JLabel altaHabilidad = new JLabel("Introduzca el nombre de la habilidad: ");
		JTextField nombreTexto = new JTextField(20);
		nombreTexto.setEditable(true);
		nombreTexto.setToolTipText("Nombre de la habilidad que dar de alta. SOLO ADMITE LETRAS.");

		JButton ok = new JButton("OK");
		ok.setToolTipText("Aceptar");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean validar = true;

				String nombreHabilidad = nombreTexto.getText().trim();
				if (!nombreHabilidad.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$")) {
					validar = false;
				}

				if (validar) {
					String nombreFormateado = nombreHabilidad.substring(0, 1).toUpperCase()
							+ nombreHabilidad.substring(1).toLowerCase();
					Context contexto = new Context(Events.ALTA_HABILIDAD_OK_VIEW,new THabilidad(0, nombreFormateado, true));
					Controller.getInstance().handle(contexto);
				} else
					JOptionPane.showMessageDialog(null,
							"El nombre de la habilidad solo puede tener caracteres no númericos.\n Recuerda no dejar vacío el campo para escribir",
							"ERROR", JOptionPane.ERROR_MESSAGE);

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

		panelParaUsuario.add(altaHabilidad);
		panelParaUsuario.add(nombreTexto);
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

		// Esta vista NO debe actualizarse.
	}

}

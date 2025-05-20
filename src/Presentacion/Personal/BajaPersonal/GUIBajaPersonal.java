package Presentacion.Personal.BajaPersonal;

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

public class GUIBajaPersonal extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIBajaPersonal() {
		super("BAJA PERSONAL");
		this.initGUI();
	}

	private void initGUI() {
		JPanel panelPrincipal = new JPanel();
		JPanel panelParaUsuario = new JPanel();

		JLabel bajaPersonal = new JLabel("Introduzca el ID de la persona: ");
		JTextField cuadroTexto = new JTextField(20);
		cuadroTexto.setEditable(true);
		cuadroTexto.setToolTipText("ID de la persona a dar de baja");

		JButton ok = new JButton("OK");
		ok.setToolTipText("Aceptar");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean validar = true;

				String idPersonal = cuadroTexto.getText().trim();
				int id = 0;
				try {
					id = Integer.parseInt(idPersonal);
					if (id <= 0) {

						validar = false;
						JOptionPane.showMessageDialog(null, "El identificador de la persona debe ser mayor que 0.",
								"ERROR", JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException m) {

					JOptionPane.showMessageDialog(null,
							"El identificador debe ser un numero mayor que 0, sin letras. Tampoco debe ser vacio",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					validar = false;
				}

				if (validar) {
					Controller.getInstance().handle(new Context(Events.BAJA_PERSONAL_OK_VIEW, id));

				}

				dispose();
			}

		});

		JButton ko = new JButton("CANCEL");
		ko.setToolTipText("Cancelar");
		ko.addActionListener((e) -> {

			Controller.getInstance().handle(new Context(Events.BAJA_PERSONAL_KO_VIEW, null));
			dispose();
		});

		panelParaUsuario.add(bajaPersonal);
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
		// NO actualizar, solo listar o consultar
	}

}

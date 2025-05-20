package Presentacion.Habilidad.ModificarHabilidad;

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

import Negocio.Habilidad.THabilidad;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIModificarHabilidad extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIModificarHabilidad() {
		super("MODIFICAR HABILIDAD");
		this.initGUI();
	}

	private void initGUI() {

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		JPanel panelNuevoNombre = new JPanel();
		panelNuevoNombre.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		JPanel panelNuevoActivo = new JPanel();
		panelNuevoActivo.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		JPanel panelBotones = new JPanel();
		JPanel panelParaUsuario = new JPanel();
		panelParaUsuario.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel modificarHabilidad = new JLabel("Introduzca el ID de la habilidad a modificar: ");
		JTextField cuadroTexto = new JTextField(20);
		cuadroTexto.setEditable(true);
		cuadroTexto.setToolTipText("ID de la habilidad que quieres modificar.");

		JLabel nuevoNombre = new JLabel("Nuevo nombre: ");
		JTextField nuevoNombreTexto = new JTextField(20);
		nuevoNombreTexto.setEditable(true);
		nuevoNombreTexto.setToolTipText("Nuevo nombre de la habilidad");

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
						JOptionPane.showMessageDialog(null, "El id debe ser un número entero mayor que 0.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException m) {

					validar = false;
					JOptionPane.showMessageDialog(null,
							"El id debe ser un número entero sin letras. Tampoco debe ser vacio", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

				String nombreHabilidad = nuevoNombreTexto.getText().trim();
				if (validar && (!nombreHabilidad.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$"))) {

					validar = false;
					JOptionPane.showMessageDialog(null,
							"El nombre de la habilidad no puede tener numeros ni simbolos. Tampoco debe ser vacio",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				}

				if (validar) {
					String nombreFormateado = nombreHabilidad.substring(0, 1).toUpperCase()
							+ nombreHabilidad.substring(1).toLowerCase();
					Controller.getInstance().handle(new Context(Events.MODIFICAR_HABILIDAD_OK_VIEW,
							new THabilidad(id, nombreFormateado, true)));
				}

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

		panelParaUsuario.add(modificarHabilidad);
		panelParaUsuario.add(cuadroTexto);

		panelNuevoNombre.add(nuevoNombre);
		panelNuevoNombre.add(nuevoNombreTexto);

		panelBotones.add(ok);
		panelBotones.add(ko);

		panelPrincipal.add(panelParaUsuario);
		panelPrincipal.add(panelNuevoNombre);
		panelPrincipal.add(panelNuevoActivo);
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

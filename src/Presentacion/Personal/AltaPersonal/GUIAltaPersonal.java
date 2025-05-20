package Presentacion.Personal.AltaPersonal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Personal.TEmpleado;
import Negocio.Personal.TJefe;
import Negocio.Personal.TPersonal;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIAltaPersonal extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private record InfoPanel(JPanel p, JLabel l, JTextField tf) {
	};

	private List<InfoPanel> atributos;

	private JComboBox<String> tipos_combo_box;
	private JButton ok;
	private JButton ko;

	public GUIAltaPersonal() {
		super("ALTA PERSONAL");
		atributos = new ArrayList<>();
		this.initGUI();
	}

	private void initGUI() {

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		JPanel panelParaBotones = new JPanel();

		DefaultComboBoxModel<String> tipos_model = new DefaultComboBoxModel<>();
		String[] tipos = TPersonal.getTipos();
		for (int i = 0; i < tipos.length; i++) {
			tipos_model.addElement(tipos[i]);
		}
		tipos_combo_box = new JComboBox<>(tipos_model);
		tipos_combo_box.addActionListener((e) -> {
			updateAtributos(panelPrincipal, panelParaBotones);
		});
		panelPrincipal.add(tipos_combo_box);

		ok = new JButton("OK");
		ok.setToolTipText("Aceptar");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean validar = true;

				List<Object> atributosValues = new ArrayList<>();
				for (InfoPanel ip : atributos) {
					String text = ip.l.getText();
					switch (text) {
					case "Nif":
						String nif = ip.tf.getText().trim();
						if (!nif.matches("\\d{8}[A-Z]{1}")) {

							validar = false;
							JOptionPane.showMessageDialog(null, "El nif debe contener 8 numeros y 1 letra mayuscula.",
									"ERROR", JOptionPane.ERROR_MESSAGE);
						}
						atributosValues.add(nif);
						break;
					case "Nombre":
						String nombre = ip.tf.getText().trim();
						if (validar && !nombre.matches("^[a-zA-Z]+$")) {
							JOptionPane.showMessageDialog(null,
									"El nombre debe de contener unicamente letras y debe ser una única palabra",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							validar = false;
						}
						if (validar) {
							String nombreFormateado = nombre.substring(0, 1).toUpperCase()
									+ nombre.substring(1).toLowerCase();
							atributosValues.add(nombreFormateado);
						}

						break;
					case "Apellido":
						String apellido = ip.tf().getText().trim();
						if (validar && !apellido.matches("^[a-zA-Z]+$")) {
							JOptionPane.showMessageDialog(null,
									"El apellido debe de contener unicamente letras y debe ser una única palabra",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							validar = false;
						}
						if (validar) {
							String apellidoFormateado = apellido.substring(0, 1).toUpperCase()
									+ apellido.substring(1).toLowerCase();
							atributosValues.add(apellidoFormateado);
						}

						break;
					case "IdNave":
						String nave = ip.tf().getText().trim();
						int idNave = 0;
						try {
							idNave = Integer.parseInt(nave);
							if (idNave <= 0) {
								JOptionPane.showMessageDialog(null,
										"El identificador de nave debe ser mayor que 0. No puede ser un campo vacío.",
										"ERROR", JOptionPane.ERROR_MESSAGE);
								validar = false;
							}

						} catch (NumberFormatException m) {

							JOptionPane.showMessageDialog(null,
									"El identificador de nave debe ser un numero entero mayor que 0, sin letras. No puede ser un campo vacío.",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							validar = false;
						}
						atributosValues.add(idNave);
						break;

					case "Cargo":

						String cargo = ip.tf().getText().trim();
						if (validar && !cargo.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$")) {
							JOptionPane.showMessageDialog(null,
									"El cargo debe contener unicamente letras. Puede tener varias palabras. No puede ser un campo vacio.",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							validar = false;
						}
						if (validar) {
							String cargoFormateado = cargo.substring(0, 1).toUpperCase()
									+ cargo.substring(1).toLowerCase();
							atributosValues.add(cargoFormateado);
						}

						break;
					case "Nomina":
						String nomina = ip.tf().getText().trim();
						int nominaInt = 0;
						try {
							nominaInt = Integer.parseInt(nomina);
							if (nominaInt <= 0) {
								JOptionPane.showMessageDialog(null,
										"La nomina debe ser un valor positivo mayor que cero.",
										"ERROR", JOptionPane.ERROR_MESSAGE);
								validar = false;
							}

						} catch (NumberFormatException m) {

							JOptionPane.showMessageDialog(null,
									"La nomina debe ser un numero",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							validar = false;
						}
						atributosValues.add(nominaInt);
						break;
					case "Bonificaciones":
						String bonif = ip.tf().getText().trim();
						int bonifInt = 0;
						try {
							bonifInt = Integer.parseInt(bonif);
							if (bonifInt <= 0) {
								JOptionPane.showMessageDialog(null,
										"Las bonificaciones debe ser un valor positivo mayor que cero.",
										"ERROR", JOptionPane.ERROR_MESSAGE);
								validar = false;
							}

						} catch (NumberFormatException m) {

							JOptionPane.showMessageDialog(null,
									"Las bonificaciones debe ser un numero",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							validar = false;
						}
						atributosValues.add(bonifInt);
						break;
					case "Responsabilidades":
						String resp = ip.tf().getText().trim();
						int respInt= 0;
						try {
							respInt = Integer.parseInt(resp);
							if (respInt < 0) {
								JOptionPane.showMessageDialog(null,
										"Las responsabilidades deben ser un valor positivo.",
										"ERROR", JOptionPane.ERROR_MESSAGE);
								validar = false;
							}

						} catch (NumberFormatException m) {

							JOptionPane.showMessageDialog(null,
									"Las responsabilidades deben ser un numero",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							validar = false;
						}
						atributosValues.add(respInt);
						break;
						
						
					default:
						JOptionPane.showMessageDialog(null, "Error interno, no se esperaba ese campo", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						validar = false;
					}
				}

				atributosValues.add(0);

				if (validar) {
					String type = tipos_combo_box.getSelectedItem().toString();
					if (type.equals("Jefe")) {
						Context contexto = new Context(Events.ALTA_PERSONAL_OK_VIEW,new TJefe((String) atributosValues.get(0), (String) atributosValues.get(1),
								(String) atributosValues.get(2), (Integer) atributosValues.get(4),
								(Integer) atributosValues.get(3), true,(Integer)atributosValues.get(5)
								,(Integer)atributosValues.get(7),(Integer)atributosValues.get(6)));
						Controller.getInstance().handle(contexto);
					} else if (type.equals("Empleado")) {
						Context contexto = new Context(Events.ALTA_PERSONAL_OK_VIEW,new TEmpleado((String) atributosValues.get(0), (String) atributosValues.get(1),
								(String) atributosValues.get(2), (Integer) atributosValues.get(5),
								(Integer) atributosValues.get(3), (String) atributosValues.get(4), true,(Integer)atributosValues.get(5)
								,(Integer)atributosValues.get(6)));
						Controller.getInstance().handle(contexto);
					}

				}

				dispose();
			}

		});

		ko = new JButton("CANCEL");
		ko.setToolTipText("Cancelar");
		ko.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.ALTA_PERSONAL_KO_VIEW, null));
			dispose();
		});
		updateAtributos(panelPrincipal, panelParaBotones);
		this.setContentPane(panelPrincipal);
		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);

	}

	private void updateAtributos(JPanel panelPrincipal, JPanel panelParaBotones) {
		for (InfoPanel ip : atributos) {
			panelPrincipal.remove(ip.p());
		}

		atributos.clear();
		String selected = tipos_combo_box.getSelectedItem().toString();
		String[] _atributos;
		switch (selected) {
		case "Jefe":
			_atributos = (new TJefe()).getAtributos();
			break;
		case "Empleado":
			_atributos = (new TEmpleado()).getAtributos();
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + selected);
		}

		for (int i = 1; i < _atributos.length; i++) {
			if(_atributos[i] != "Activo")
			{
				InfoPanel p = generarAtributo(_atributos[i]);
				atributos.add(p);
				panelPrincipal.add(p.p());
			}
			
		}

		panelParaBotones.add(ok);
		panelParaBotones.add(ko);

		panelPrincipal.add(panelParaBotones);
		panelPrincipal.revalidate();
		panelPrincipal.repaint();
	}

	private InfoPanel generarAtributo(String at) {
		JPanel panel = new JPanel();
		JLabel text = new JLabel(at);
		JTextField text_field = new JTextField(20);
		panel.add(text);
		panel.add(text_field);
		return new InfoPanel(panel, text, text_field);
	}

	@Override
	public void actualizar(Context context) {
		// Esta ventana NO debe actualizarse
		
	}
}

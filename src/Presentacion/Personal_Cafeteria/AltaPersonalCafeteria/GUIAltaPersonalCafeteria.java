package Presentacion.Personal_Cafeteria.AltaPersonalCafeteria;

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

import Negocio.Personal_Cafeteria.TPersonalCafeteria;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIAltaPersonalCafeteria extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;

	private record InfoPanel(JPanel p, JLabel l, JTextField tf) {};
    
	private List<InfoPanel> atributos;

	private JComboBox<String> tipos_combo_box;
	private JButton ok;
	private JButton ko;
	
	public GUIAltaPersonalCafeteria() {
		super("ALTA PERSONAL CAFETERIA");
		atributos = new ArrayList<>();
		this.initGUI();
	}
	
	public void initGUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		JPanel panelParaBotones = new JPanel();

		DefaultComboBoxModel<String> tipos_model = new DefaultComboBoxModel<>();
		String[] tipos = { "Jefe", "Empleado" };
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
					case "Salario Base":
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
					case "Turno":
						String turno = ip.tf().getText().trim();
						int turnoInt = 0;
						try {
							turnoInt = Integer.parseInt(turno);
							if (turnoInt <= 0) {
								JOptionPane.showMessageDialog(null,
										"El ID del turno debe ser un valor positivo mayor que cero.",
										"ERROR", JOptionPane.ERROR_MESSAGE);
								validar = false;
							}

						} catch (NumberFormatException m) {

							JOptionPane.showMessageDialog(null,
									"El ID del turno debe ser un numero",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							validar = false;
						}
						atributosValues.add(turnoInt);
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
					case "Puesto":

						String puesto = ip.tf().getText().trim();
						if (validar && !puesto.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$")) {
							JOptionPane.showMessageDialog(null,
									"El puesto debe contener unicamente letras. Puede tener varias palabras. No puede ser un campo vacio.",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							validar = false;
						}
						if (validar) {
							String puestoFormateado = puesto.substring(0, 1).toUpperCase()
									+ puesto.substring(1).toLowerCase();
							atributosValues.add(puestoFormateado);
						}

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

				if (validar) {
					String type = tipos_combo_box.getSelectedItem().toString();
					if (type.equals("Jefe")) {
						Context contexto = new Context(Events.ALTA_PERSONALCAFETERIA_OK_VIEW, new TPersonalCafeteria(-1,atributosValues.get(0).toString(), atributosValues.get(1).toString(),
								atributosValues.get(2).toString(), (Integer)atributosValues.get(6) ,
								"",-1,true, atributosValues.get(5).toString(),(Integer)atributosValues.get(3),
								(Integer)atributosValues.get(4),true));
						Controller.getInstance().handle(contexto);
					} else if (type.equals("Empleado")) {
						Context contexto = new Context(Events.ALTA_PERSONALCAFETERIA_OK_VIEW, new TPersonalCafeteria(-1,atributosValues.get(0).toString(), atributosValues.get(1).toString(),
								atributosValues.get(2).toString(), -1 , atributosValues.get(5).toString(),
								(Integer)atributosValues.get(6),false, "",(Integer)atributosValues.get(3) ,
								(Integer)atributosValues.get(4),true));
						Controller.getInstance().handle(contexto);
					}

				}

				dispose();
			}

		});

		ko = new JButton("CANCEL");
		ko.setToolTipText("Cancelar");
		ko.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.ALTA_PERSONALCAFETERIA_KO_VIEW, null));
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
			_atributos = new String[]{ "Nif", "Nombre", "Apellido", "Salario Base","Turno", "Cargo", "Responsabilidades" };
			break;
		case "Empleado":
			_atributos = new String[] { "Nif", "Nombre", "Apellido", "Salario Base","Turno", "Puesto", "Bonificaciones" };
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + selected);
		}

		for (int i = 0; i < _atributos.length; i++) {
			if(_atributos[i] != "Activo") {
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
		
	}
}
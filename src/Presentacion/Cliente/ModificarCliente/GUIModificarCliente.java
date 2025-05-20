package Presentacion.Cliente.ModificarCliente;

import java.awt.BorderLayout;
import java.util.List;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Cliente.TCliente;
import Negocio.Cliente.TEmpresa;
import Negocio.Cliente.TIndividual;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIModificarCliente extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	
	private record InfoPanel(JPanel p, JLabel l, JTextField tf) {};
	
	private List<InfoPanel> atributos;

	private JComboBox<String> tipos_combo_box;	
	List<JPanel> paneles;
	private JButton ok;
	private JButton cancel;
	
	public GUIModificarCliente() {
		super("MODIFICAR CLIENTE");
		atributos = new ArrayList<>();
		paneles = new ArrayList<>();
		this.initGUI();
	}

	private void initGUI() {
		JPanel panelPrincipal = new JPanel();
		JPanel panelSuperior = new JPanel();
		JPanel panelMedioSup = new JPanel();
		JPanel panelMedioInf = new JPanel();
		JPanel panelInferior = new JPanel();
		
		paneles.add(panelSuperior);
		paneles.add(panelMedioSup);
		paneles.add(panelMedioSup);
		paneles.add(panelMedioInf);
		paneles.add(panelInferior);
		
		DefaultComboBoxModel<String> tipos_model = new DefaultComboBoxModel<>();
		String[] tipos = TCliente.getTipos();
		
		for (int i = 0; i < tipos.length; i++) {
			tipos_model.addElement(tipos[i]);
		}
		
		tipos_combo_box = new JComboBox<>(tipos_model);
		tipos_combo_box.addActionListener((e) -> {
			updateAtributos(panelPrincipal, panelInferior);
		});
		
		panelPrincipal.add(tipos_combo_box);
		
		ok = new JButton("OK");
		ok.setToolTipText("Aceptar");
		
		ok.addActionListener((e) -> {
			boolean valido = true;
			List<Object> values = new ArrayList<>();
			
			for(InfoPanel ip : atributos) {
				String text = ip.l.getText();
				
				switch(text) {
				case "id":
					String idCliente = ip.tf.getText().trim();
					int id = 0;
					try {
						id = Integer.parseInt(idCliente);
						if (id <= 0) {
							valido = false;
							JOptionPane.showMessageDialog(null, "El id debe ser un número entero mayor que 0.", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
		
					} catch (NumberFormatException m) {
						valido = false;
						JOptionPane.showMessageDialog(null,
								"El id debe ser un número entero sin letras. Tampoco debe ser vacio", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					} 
					values.add(id);
					break;
			
				case "usuario":
					String usuario = ip.tf().getText().trim();
					if(valido && !usuario.matches("^[a-zA-Z]+$")){
						if(!usuario.isEmpty()){
							JOptionPane.showMessageDialog(null,
									"El usuario debe de contener unicamente letras y debe ser una única palabra",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							valido = false;
						}
						else
							usuario = "";
					
					}
					values.add(usuario);
					break;
				case "correo":
					String correo = ip.tf().getText().trim();
					if(valido && !correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
						if(!correo.isEmpty()){
							JOptionPane.showMessageDialog(null,
									"El correo introducido no es una direccion valida. Debe tener de estructura x@y.z",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							valido = false;
						}
						else
							correo = "";
					}
					values.add(correo);
					break;
				case "contrasena":
					String contrasena = ip.tf().getText().trim();
					
					if(valido && !contrasena.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!¡¿?()-_{}|\":;'<>.,\\[\\]\\/\\\\]).{8,}$")){
						if(!contrasena.isEmpty()){
							JOptionPane.showMessageDialog(null,
									"La contrasenya debe tener una minuscula, mayuscula, numero, caracter especial y longitud minima de 8 caracteres",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							valido = false;
						}
						else
							contrasena = "";
					}
					values.add(contrasena);
					break;
				case "nombre empresa":
					String nom_empresa = ip.tf().getText().trim();
					if(valido && !nom_empresa.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$")){	
						if(!nom_empresa.isEmpty()){
							JOptionPane.showMessageDialog(null,
									"El nombre de la empresa debe de contener unicamente letras aunque pueden ser varias palabras",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							valido = false;
						}
						else
							nom_empresa = "";
					}
					values.add(nom_empresa);
					break;
				case "codigo postal":
					String codigo_postalStr = ip.tf().getText().trim();
					int codigo_postal = 0;
					if(valido && !codigo_postalStr.matches("^\\d{5}$")){ 
						if (!codigo_postalStr.isEmpty()){
							JOptionPane.showMessageDialog(null,
									"El numero del codigo postal debe de contener unicamente 5 numeros",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							valido = false;
						}	
					}
					
					else if(valido)
						codigo_postal = Integer.parseInt(codigo_postalStr);
					values.add(codigo_postal);
					break;
				default:
					JOptionPane.showMessageDialog(null, "Error interno, no se esperaba ese campo", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					valido = false;
				}
				
			}
			
			if(valido) {
				String type = tipos_combo_box.getSelectedItem().toString();
				if (type.equals("Empresa")) {
					Controller.getInstance().handle(new Context(Events.MODIFICAR_CLIENTE_OK_VIEW,
							new TEmpresa((Integer) values.get(0) , (String) values.get(1), 
										(String) values.get(2), (String) values.get(3), true, 
										(String) values.get(4))));
				} else if (type.equals("Individual")) {
					Controller.getInstance().handle(new Context(Events.MODIFICAR_CLIENTE_OK_VIEW,
							new TIndividual((Integer) values.get(0), (String) values.get(1), (String) values.get(2),
									(String) values.get(3), true, (Integer) values.get(4))));
				}
			}
			else
				Controller.getInstance().handle(new Context(Events.CLIENTE_KO_VIEW, null));
			dispose();
		});
				
		cancel = new JButton("CANCEL");
		cancel.setToolTipText("Cancelar");
		cancel.addActionListener((e) -> {
				Controller.getInstance().handle(new Context(Events.CLIENTE_KO_VIEW, null));
				dispose();
		});
		
		updateAtributos(panelPrincipal, panelInferior);
		this.setContentPane(panelPrincipal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200,200);
		this.setSize(650, 230);
	}
	
	private void updateAtributos(JPanel panelPrincipal, JPanel panelParaBotones) {
		for (JPanel p : paneles) 
			p.removeAll();
		
		atributos.clear();
		String selected = tipos_combo_box.getSelectedItem().toString();
		String[] _atributos;
		switch (selected) {
		case "Empresa":
			_atributos = (new TEmpresa()).getAtributos();
			break;
		case "Individual":
			_atributos = (new TIndividual()).getAtributos();
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + selected);
		}
		
		for (int i = 0; i < _atributos.length-1; i++) {
			InfoPanel p = generarAtributo(_atributos[i]);
			atributos.add(p);
			paneles.get(i).add(p.p());
		}	

		panelParaBotones.add(ok);
		panelParaBotones.add(cancel);

		panelPrincipal.add(paneles.get(0), BorderLayout.BEFORE_FIRST_LINE);
		panelPrincipal.add(paneles.get(1), BorderLayout.LINE_START);
		panelPrincipal.add(paneles.get(3), BorderLayout.LINE_END);
		panelPrincipal.add(panelParaBotones, BorderLayout.AFTER_LAST_LINE);
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
	public void actualizar(Context context) {}
	
}


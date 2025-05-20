package Presentacion.Cliente.AltaCliente;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

public class GUIAltaCliente extends JFrame implements Observer{
private static final long serialVersionUID = 1L;

	private record InfoPanel(JPanel p, JLabel l, JTextField tf) {};
	
	private List<InfoPanel> atributos;

	private JComboBox<String> tipos_combo_box;	
	List<JPanel> paneles;
	private JButton ok;
	private JButton cancel;
	
	
	public GUIAltaCliente() {
		super("ALTA CLIENTE");
		atributos = new ArrayList<>();
		paneles = new ArrayList<>();
		this.initGUI();
	}
	
	private Object initGUI() {
		JPanel panelPrincipal = new JPanel();
		JPanel panelSuperior = new JPanel();
		JPanel panelMedioSup = new JPanel();
		JPanel panelMedioInf = new JPanel();
		JPanel panelInferior = new JPanel();
		
		paneles.add(panelSuperior);
		paneles.add(panelMedioSup);
		paneles.add(panelMedioSup);
		paneles.add(panelMedioInf);
		
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
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean valido = true;
				List<Object> values = new ArrayList<>();
				
				for(InfoPanel ip : atributos) {
					String text = ip.l.getText();
					
					switch(text) {
					case "usuario":
						String usuario = ip.tf().getText().trim();
						if(valido && !usuario.matches("^[a-zA-Z]+$")){
							JOptionPane.showMessageDialog(null,
									"El usuario debe de contener unicamente letras y debe ser una única palabra",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							valido = false;
						}
						values.add(usuario);
						break;
					case "correo":
						String correo = ip.tf().getText().trim();
						if(valido && !correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
							JOptionPane.showMessageDialog(null,
									"El correo introducido no es una direccion valida. Debe tener de estructura x@y.z",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							valido = false;
						}
						values.add(correo);
						break;
					case "contrasena":
						String contrasena = ip.tf().getText().trim();
						if(valido && !contrasena.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!¡¿?()-_{}|\":;'<>.,\\[\\]\\/\\\\]).{8,}$")){
							JOptionPane.showMessageDialog(null,
									"La contrasenya debe tener una minuscula, mayuscula, numero, caracter especial y longitud minima de 8 caracteres",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							valido = false;
						}
						values.add(contrasena);
						break;
					case "nombre empresa":
						String nom_empresa = ip.tf().getText().trim();
						if(valido && !nom_empresa.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$")){
							JOptionPane.showMessageDialog(null,
									"El nombre de la empresa debe de contener unicamente letras aunque pueden ser varias palabras",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							valido = false;
						}
						values.add(nom_empresa);
						break;
					case "codigo postal":
						String codigo_postal = ip.tf().getText().trim();
						if(valido && !codigo_postal.matches("^\\d{5}$")){
							JOptionPane.showMessageDialog(null,
									"El numero del codigo postal debe de contener unicamente 5 numeros",
									"ERROR", JOptionPane.ERROR_MESSAGE);
							valido = false;
						}
						values.add(Integer.parseInt(codigo_postal));
						break;
					default:
						JOptionPane.showMessageDialog(null, "Error interno, no se esperaba ese campo", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						valido = false;
					}
					
				}
				values.add(0);

				if (valido) {
					String type = tipos_combo_box.getSelectedItem().toString();
					if (type.equals("Empresa")) {
						Controller.getInstance().handle(new Context(Events.ALTA_CLIENTE_OK_VIEW,
								new TEmpresa((Integer) values.get(4) , (String) values.get(0), 
											(String) values.get(1), (String) values.get(2), true, 
											(String) values.get(3))));
					} else if (type.equals("Individual")) {
						Controller.getInstance().handle(new Context(Events.ALTA_CLIENTE_OK_VIEW,
								new TIndividual((Integer) values.get(4), (String) values.get(0), (String) values.get(1),
										(String) values.get(2), true, (Integer) values.get(3))));
					}

				}
				else 
					Controller.getInstance().handle(new Context(Events.CLIENTE_KO_VIEW, null));

				dispose();
			}
				
		});
		
		cancel = new JButton("CANCEL");
		cancel.setToolTipText("Cancelar");
		cancel.addActionListener((e)->{
				Controller.getInstance().handle(new Context(Events.CLIENTE_KO_VIEW, null));
				dispose();
		});
		
		updateAtributos(panelPrincipal, panelInferior);
		this.setContentPane(panelPrincipal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200,200);
		this.setSize(650, 170);
		
		return null;
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

		for (int i = 1; i < _atributos.length-1; i++) {
			InfoPanel p = generarAtributo(_atributos[i]);
			atributos.add(p);
			paneles.get(i - 1).add(p.p());
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
	public void actualizar(Context context) {
		// No actualiza 
	}
}
		


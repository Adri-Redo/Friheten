package Presentacion.Personal.PersonalPorNumHabilidades;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
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

public class GUIPersonalPorNumHabilidades extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public GUIPersonalPorNumHabilidades() {
		super("PERSONAL POR NUM HABILIDADES");
		this.initGUI();
	}

	private void initGUI() {

		//FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 5, 5);
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		JPanel panelParaUsuario = new JPanel();

	

		JLabel consultarHabilidad = new JLabel("Introduzca el numero de habilidades: ");
		JTextField cuadroTexto = new JTextField(20);
		cuadroTexto.setEditable(true);
		cuadroTexto.setToolTipText("Numero de habilidades que debe tener la persona.");

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
						JOptionPane.showMessageDialog(null, "El numero de habilidades debe ser mayor que 0.",
								"ERROR", JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException m) {
					validar = false;
					JOptionPane.showMessageDialog(null,
							"El numero de habilidades debe ser un numero entero mayor que 0, sin letras.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				}

				if (validar) {
					Object[] lista = new Object[2];
					lista[0] = "PersonalPorNumHabilidades";
					lista[1] = id;
					Controller.getInstance().handle(new Context(Events.MOSTRAR_PERSONAL_PORNUMHAB_OK_VIEW,lista));
				}

				dispose();
			}

		});

		JButton ko = new JButton("CANCEL");
		ko.setToolTipText("Cancelar");
		ko.addActionListener((e) -> {

			Controller.getInstance().handle(new Context(Events.MOSTRAR_PERSONAL_PORNUMHAB_KO_VIEW, null));
			dispose();
		});

		panelParaUsuario.add(consultarHabilidad);
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

		

		
	}

}

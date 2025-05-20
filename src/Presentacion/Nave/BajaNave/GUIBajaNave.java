package Presentacion.Nave.BajaNave;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Nave.TNave;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIBajaNave extends JFrame implements Observer {

	public GUIBajaNave() {
		super("Baja Nave");
		initGUI();
	}

	public void initGUI() {

		JPanel panelPrincipal = new JPanel();
		JPanel panelParaUsuario = new JPanel();

		JLabel bajaNave = new JLabel("Introduzca el ID de la nave: ");
		JTextField cuadroTexto = new JTextField(20);
		cuadroTexto.setEditable(true);
		cuadroTexto.setToolTipText("ID de la nave que dar de baja (solo numeros positivos)");

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Controller.getInstance().handle(new Context(Events.NAVE_VIEW, null));
					
				}catch(Exception f){
					f.printStackTrace();
				}finally {
					dispose();
				}
			}
		}); 
		
		JButton ok = new JButton("OK");
		ok.setToolTipText("Aceptar");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean validar = true;

				String idNave = cuadroTexto.getText().trim();
				int id = 0;
				try {
					id = Integer.parseInt(idNave);
					if (id <= 0) {
						validar = false;
						JOptionPane.showMessageDialog(null, "El id introducido debe ser mayor que 0.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException m) {

					validar = false;
					JOptionPane.showMessageDialog(null, " Ha introducido el id " + idNave
							+ "; el id introducido no debe contener letras ni caracteres especiales. Tampoco espacios ni puede estar vacio.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				}

				if (validar)
					Controller.getInstance().handle(new Context(Events.BAJA_NAVE_OK_VIEW, id));

				dispose();
			}

		});

		JButton ko = new JButton("CANCEL");
		ko.setToolTipText("Cancelar");
		ko.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().handle(new Context(Events.NAVE_VIEW, null));
				dispose();
			}

		});

		panelParaUsuario.add(bajaNave);
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
		// TODO Auto-generated method stub
		
	}

}

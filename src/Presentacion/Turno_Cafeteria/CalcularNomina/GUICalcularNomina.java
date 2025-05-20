package Presentacion.Turno_Cafeteria.CalcularNomina;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

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


public class GUICalcularNomina extends JFrame implements Serializable, Observer {

	private static final long serialVersionUID = 1L;

	JLabel resultado = new JLabel("Resultado: ");
	
	public GUICalcularNomina() {
		super("CALCULAR NOMINA");
		this.initGUI();
	}
	
	private void initGUI() {
		JPanel panelPrincipal = new JPanel();
		JPanel panelParaUsuario = new JPanel();
		JPanel panelParaResultado = new JPanel();
		
		JLabel IDCliente = new JLabel("ID del Turno: ");
		JTextField cuadroTextoID = new JTextField(15);
		cuadroTextoID.setEditable(true);
		cuadroTextoID.setToolTipText("ID Turno");
		

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Controller.getInstance().handle(new Context(Events.TURNO_KO_VIEW, null));
					
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

				try {
					if(cuadroTextoID.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "El id no puede estar en blanco.", "ERROR",JOptionPane.ERROR_MESSAGE);
						Controller.getInstance().handle(new Context(Events.TURNO_KO_VIEW, null));
					}
					else {
						Integer id = Integer.parseInt(cuadroTextoID.getText().trim());
						if(id <= 0) {
							JOptionPane.showMessageDialog(null, "El id introducido debe ser mayor que 0.", "ERROR",JOptionPane.ERROR_MESSAGE);
							Controller.getInstance().handle(new Context(Events.TURNO_KO_VIEW, null));
						}
						else 
							Controller.getInstance().handle(new Context(Events.TURNO_CALCULAR_NOMINA_OK_VIEW, id));
					}
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "El id introducido debe ser un numero.", "ERROR",JOptionPane.ERROR_MESSAGE);
					Controller.getInstance().handle(new Context(Events.TURNO_KO_VIEW, null));
				}
				dispose();
			}
		});
		
		JButton cancel = new JButton("CANCEL");
		cancel.setToolTipText("Cancelar");
		cancel.addActionListener((e)-> {
			Controller.getInstance().handle(new Context(Events.TURNO_KO_VIEW, null));
			dispose();
		});
		
		panelParaUsuario.add(IDCliente);
		panelParaUsuario.add(cuadroTextoID);
		panelParaUsuario.add(ok);
		panelParaUsuario.add(cancel);
		
		panelParaResultado.add(resultado);
	
		panelPrincipal.add(panelParaUsuario);
		panelPrincipal.add(panelParaResultado);
		
		this.setContentPane(panelPrincipal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200,200);	
		this.pack();
	}

	
	public void actualizar(Context context) {
		int evento = context.getEvent();
		switch (evento) {
			case Events.EXITO_TURNO_CALCULAR_NOMINA:
				JOptionPane.showMessageDialog(this, "Resultado: " + context.getData(), "EXITO", JOptionPane.INFORMATION_MESSAGE);
				break;
			case Events.ERROR_TURNO_NO_EXISTE:
				JOptionPane.showMessageDialog(this, "El turno no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
				break;
			case Events.ERROR_TURNO_DE_BAJA:
				JOptionPane.showMessageDialog(this, "El turno esta de baja", "ERROR", JOptionPane.ERROR_MESSAGE);
				break;
			case Events.ERROR_TURNO_CALCULAR_NOMINA:
				JOptionPane.showMessageDialog(this, "Error al calcular la nomina", "ERROR", JOptionPane.ERROR_MESSAGE);
				break;
			default:
				break;
		}
	}
}
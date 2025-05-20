package Presentacion.Cliente.BajaCliente;

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

public class GUIBajaCliente extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	
	public GUIBajaCliente() {
		super("BAJA CLIENTE");
		this.initGUI();
	}
	
	private Object initGUI() {
		JPanel panelPrincipal = new JPanel();
		JPanel panelParaUsuario = new JPanel();
		
		JLabel IDCliente = new JLabel("ID del Usuario: ");
		JTextField cuadroTextoID = new JTextField(15);
		cuadroTextoID.setEditable(true);
		cuadroTextoID.setToolTipText("ID Cliente");

		JButton ok = new JButton("OK");
		ok.setToolTipText("Aceptar");
		ok.addActionListener((e)->{
			try {
				if(cuadroTextoID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "El id no puede estar en blanco.", "ERROR",JOptionPane.ERROR_MESSAGE);
					Controller.getInstance().handle(new Context(Events.CLIENTE_KO_VIEW, null));
				}
				else {
					Integer id = Integer.parseInt(cuadroTextoID.getText().trim());
					if(id <= 0) {
						JOptionPane.showMessageDialog(null, "El id introducido debe ser mayor que 0.", "ERROR",JOptionPane.ERROR_MESSAGE);
						Controller.getInstance().handle(new Context(Events.CLIENTE_KO_VIEW, null));
					}
					else 
						Controller.getInstance().handle(new Context(Events.BAJA_CLIENTE_OK_VIEW, id));
				}
			}
			catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "El id introducido debe ser un numero.", "ERROR",JOptionPane.ERROR_MESSAGE);
				Controller.getInstance().handle(new Context(Events.CLIENTE_KO_VIEW, null));
			}
			dispose();
		});;
		JButton cancel = new JButton("CANCEL");
		cancel.setToolTipText("Cancelar");
		cancel.addActionListener((e)-> {
			Controller.getInstance().handle(new Context(Events.CLIENTE_KO_VIEW, null));
			dispose();
		});
		
		panelParaUsuario.add(IDCliente);
		panelParaUsuario.add(cuadroTextoID);
		panelParaUsuario.add(ok);
		panelParaUsuario.add(cancel);
	
		panelPrincipal.add(panelParaUsuario);
		
		this.setContentPane(panelPrincipal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200,200);	
		this.pack();
		
		return null;
	}

	@Override
	public void actualizar(Context context) {
		// No debe actualizar
		
	} 
}

package Presentacion.Cliente.Cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Negocio.Cliente.TCliente;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.FactoryPresentacion.FactoryPresentacion;

public class GUICliente extends JFrame  implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static JButton alta;
	static JButton baja;
	static JButton consultar;
	static JButton listar;
	static JButton modificar;

	Controller ctrl;
	
	public GUICliente() {
		super("CLIENTE");
		this.initGUI();
	}

	private void initGUI() {

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

		JPanel panelSuperior = new JPanel();// FlowLayout
		panelSuperior.setBackground(Color.DARK_GRAY);
		JPanel panelMedio = new JPanel();
		panelMedio.setBackground(Color.DARK_GRAY);
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(Color.DARK_GRAY);
		JPanel panelReturn = new JPanel();
		panelReturn.setBackground(Color.DARK_GRAY);

		alta = new JButton("ALTA CLIENTE");
		alta.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.ALTA_CLIENTE_VIEW, null));// Crea la vista de alta de habilidad
			dispose();
		});

		baja = new JButton("BAJA CLIENTE");
		baja.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.BAJA_CLIENTE_VIEW, null));// Crea la vista de baja de habilidad
			dispose();
		});

		consultar = new JButton("CONSULTAR CLIENTE");
		consultar.addActionListener((e) -> {	
			Controller.getInstance().handle(new Context(Events.CONSULTAR_CLIENTE_VIEW, null));
			dispose();
		});
		listar = new JButton("LISTAR CLIENTE");
		listar.addActionListener((e) -> {	
			Controller.getInstance().handle(new Context(Events.LISTAR_CLIENTE_VIEW, null));
		});
		
		modificar = new JButton("MODIFICAR CLIENTE");
		modificar.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.MODIFICAR_CLIENTE_VIEW, null));
			dispose();
		});
		
		JButton back = new JButton();
		back = new JButton();
		back.setToolTipText("Volver a la ventana principal");
		back.setIcon(new ImageIcon("resources/images/back.png"));
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setPreferredSize(new Dimension(150, 75));
		back.addActionListener((e) -> dispose());

		panelSuperior.add(alta);
		panelSuperior.add(baja);
		panelSuperior.add(consultar);
		panelMedio.add(listar);
		panelMedio.add(modificar);
		panelReturn.add(back);

		panelPrincipal.add(panelSuperior, BorderLayout.PAGE_START);
		panelPrincipal.add(panelMedio, BorderLayout.CENTER);
		panelPrincipal.add(panelInferior, BorderLayout.PAGE_END);
		panelPrincipal.add(panelReturn, BorderLayout.PAGE_START);
		

		this.setContentPane(panelPrincipal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.pack();
		this.setPreferredSize(new Dimension(400, 300));
	}

	@Override
	public void actualizar(Context context) {
		switch(context.getEvent()) {
		
		case Events.EXITO_CLIENTE_ALTA:
			TCliente cliente = (TCliente) context.getData();
			
			JOptionPane.showMessageDialog(null, "Se ha dado de alta el cliente " + cliente.getUsuario() + " correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case Events.EXITO_CLIENTE_BAJA:
			int id = (Integer) context.getData();
			
			JOptionPane.showMessageDialog(null, "Se ha dado de baja el cliente "+ id +" correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case Events.EXITO_CLIENTE_CONSULTAR:
			FactoryPresentacion.getInstance().generateGUI(Events.CONSULTAR_CLIENTE_VIEW).actualizar(context);
			break;
			
		case Events.EXITO_CLIENTE_MODIFICAR:
			int id1 = (Integer) context.getData();
			
			JOptionPane.showMessageDialog(null, "Se ha modificado el cliente con id " + id1 +" correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case Events.ERROR_CLIENTE_EXISTE:
			TCliente cliente1 = (TCliente) context.getData();
			
			JOptionPane.showMessageDialog(null, "El cliente " + cliente1.getUsuario() + " ya existe.",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		case Events.ERROR_CORREO_EXISTE:
			TCliente cliente2 = (TCliente) context.getData();
			
			JOptionPane.showMessageDialog(null, "El correo " + cliente2.getCorreo() + " ya existe.",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		case Events.ERROR_TIPOS_DISTINTOS:
			TCliente cliente3 = (TCliente) context.getData();
			
			JOptionPane.showMessageDialog(null, "Se ha intentado reactivar al cliente: " + cliente3.getUsuario() 
					+ " con un tipo distinto.",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		case Events.ERROR_CLIENTE_EXISTE_MOD:
			TCliente cliente4 = (TCliente) context.getData();
			
			JOptionPane.showMessageDialog(null, "Se ha intentado modificar al cliente: " + cliente4.getId() 
			+ " y el nombre modificado (" + cliente4.getUsuario() +") ya esta en uso",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		case Events.ERROR_CORREO_EXISTE_MOD:
			TCliente cliente5 = (TCliente) context.getData();
			
			JOptionPane.showMessageDialog(null, "Se ha intentado modificar al cliente: " + cliente5.getId() 
			+ " y el correo modificado (" + cliente5.getCorreo() +") ya esta en uso",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		case Events.ERROR_TIPOS_DISTINTOS_MOD:
			int idTipo = (Integer) context.getData();
			
			JOptionPane.showMessageDialog(null, "Se ha intentado reactivar al cliente: " + idTipo 
					+ " con un tipo distinto.",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		case Events.ERROR_CLIENTE_NO_EXISTE:
			int idNoExiste = (Integer) context.getData();
			
			JOptionPane.showMessageDialog(null, "El cliente con id " + idNoExiste + " no existe.",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}

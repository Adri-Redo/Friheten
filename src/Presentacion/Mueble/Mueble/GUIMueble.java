package Presentacion.Mueble.Mueble;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Negocio.Mueble.TMueble;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.FactoryPresentacion.FactoryPresentacion;

public class GUIMueble extends JFrame implements Observer{
	
	private static final long serialVersionUID = 1L;

	TMueble t_mueble;
	static JButton alta;
	static JButton baja;
	static JButton consultar;
	static JButton listar;
	static JButton modificar;
	static JButton mostrarporIDNave;
	static JButton back;
	
	public GUIMueble() {
		super("MUEBLE");
		this.initGUI();
	}
	
	private void initGUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.DARK_GRAY);
		JPanel panelMedio = new JPanel();
		panelMedio.setBackground(Color.DARK_GRAY);
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(Color.DARK_GRAY);
		
		alta = new JButton("ALTA MUEBLE");
		alta.addActionListener((e)->{ 
			Controller.getInstance().handle(new Context(Events.ALTA_MUEBLE_VIEW, null));
			dispose();
		});
		
		baja = new JButton("BAJA MUEBLE");
		baja.addActionListener((e)-> {
			Controller.getInstance().handle(new Context(Events.BAJA_MUEBLE_VIEW, null));
			dispose();
		});
		
		consultar = new JButton("CONSULTAR MUEBLE");
		consultar.addActionListener((e)-> { 			
			Controller.getInstance().handle(new Context(Events.CONSULTAR_MUEBLE_VIEW, null));
			dispose();
		});
		
		listar = new JButton("LISTAR MUEBLES");
		listar.addActionListener((e)->{ 
			Controller.getInstance().handle(new Context(Events.LISTAR_MUEBLE_VIEW, null));
		});
		
		modificar = new JButton("MODIFICAR MUEBLES");
		modificar.addActionListener((e)-> { 
			Controller.getInstance().handle(new Context(Events.MODIFICAR_MUEBLE_VIEW, null));
			dispose();
		});
		
		mostrarporIDNave = new JButton("MOSTRAR MUEBLE ID NAVE");
		mostrarporIDNave.addActionListener((e)->{
			Controller.getInstance().handle(new Context(Events.MOSTRAR_MUEBLE_POR_ID_NAVE_VIEW, null));
			dispose();
		});
		
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
		panelMedio.add(mostrarporIDNave);
		panelInferior.add(back);
		
		panelPrincipal.add(panelSuperior, BorderLayout.PAGE_START);
		panelPrincipal.add(panelMedio, BorderLayout.CENTER);
		panelPrincipal.add(panelInferior, BorderLayout.PAGE_END);
		
		this.setContentPane(panelPrincipal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200,200);
		this.pack();
	}

	@Override
	public void actualizar(Context context) {
		Object datos = context.getData();
		
		switch(context.getEvent()) {
		
		case Events.EXITO_MUEBLE_ALTA:
			TMueble mueble = (TMueble) datos;
			
			JOptionPane.showMessageDialog(null, "Se ha dado de alta el mueble " + mueble.getNombre() + " correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case Events.EXITO_MUEBLE_BAJA:
			int id = (Integer) datos;
			
			JOptionPane.showMessageDialog(null, "Se ha dado de baja el mueble "+ id +" correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case Events.EXITO_MUEBLE_CONSULTAR:
			FactoryPresentacion.getInstance().generateGUI(Events.CONSULTAR_MUEBLE_VIEW).actualizar(context);
			break;
			
		case Events.EXITO_MUEBLE_MODIFICAR:
			int id1 = (Integer) datos;
			
			JOptionPane.showMessageDialog(null, "Se ha modificado el mueble con id " + id1 +" correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case Events.EXITO_MUEBLE_MOSTRAR_POR_ID_NAVE:
			FactoryPresentacion.getInstance().generateGUI(Events.MOSTRAR_MUEBLE_POR_ID_NAVE_VIEW).actualizar(context);
			break;
			
		case Events.ERROR_MUEBLE_EXISTE:
			TMueble mueble3 = (TMueble) datos;
			if (mueble3.getActivo())
				JOptionPane.showMessageDialog(null, "El mueble " + mueble3.getNombre() + " ya existe.",
					"Error", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "El mueble " + mueble3.getNombre() + " ya existe.(Estado: Inactivo) ",
						"Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Events.ERROR_MUEBLE_NO_EXISTE:
			int idNoExiste = (Integer) datos;
			
			JOptionPane.showMessageDialog(null, "El mueble con id " + idNoExiste + " no existe.",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		case Events.ERROR_NO_HAY_MUEBLE_ASOCIADO_A_TAL_ID_NAVE:
			JOptionPane.showMessageDialog(null, "No hay ningun mueble asociado a esa nave o bien la nave no existe.",
					"ERROR", JOptionPane.ERROR_MESSAGE);		

			break;
		}
		
	}
}
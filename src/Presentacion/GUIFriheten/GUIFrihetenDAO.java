package Presentacion.GUIFriheten;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;


public class GUIFrihetenDAO extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static JButton habilidad;
	static JButton personal;
	static JButton mueble;
	static JButton nave;
	static JButton compra;
	static JButton cliente;

	public GUIFrihetenDAO() {
		super("FRIHETEN ~ TIENDA DE MUEBLES");
		this.initGUI();

	}

	private void initGUI() {

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		this.setMinimumSize(new Dimension(600, 600));

		JPanel panelSuperior = new JPanel();// FlowLayout
		panelSuperior.setBackground(Color.DARK_GRAY);
		JPanel panelMedio = new JPanel();
		panelMedio.setBackground(Color.DARK_GRAY);
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(Color.DARK_GRAY);

		habilidad = new JButton("HABILIDAD");
		habilidad.addActionListener((e) -> Controller.getInstance().handle(new Context(Events.HABILIDAD_VIEW,null)));

		personal = new JButton("PERSONAL");
		personal.addActionListener((e) -> Controller.getInstance().handle(new Context(Events.PERSONAL_VIEW, null)));

		mueble = new JButton("MUEBLES");
		mueble.addActionListener((e) -> Controller.getInstance().handle(new Context(Events.MUEBLE_VIEW, null)));

		nave = new JButton("NAVE");
		nave.addActionListener((e) -> Controller.getInstance().handle(new Context(Events.NAVE_VIEW, null)));

		compra = new JButton("COMPRA");
		compra.addActionListener((e) -> Controller.getInstance().handle(new Context(Events.COMPRA_VIEW, null)));

		cliente = new JButton("CLIENTE");
		cliente.addActionListener((e) -> Controller.getInstance().handle(new Context(Events.CLIENTE_VIEW, null)));

		panelSuperior.add(habilidad);
		panelSuperior.add(personal);
		panelSuperior.add(mueble);
		panelMedio.add(nave);
		panelMedio.add(compra);
		panelMedio.add(cliente);

		panelPrincipal.add(panelSuperior, BorderLayout.PAGE_START);
		panelPrincipal.add(panelMedio, BorderLayout.CENTER);
		panelPrincipal.add(panelInferior, BorderLayout.PAGE_END);

		this.setContentPane(panelPrincipal);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.pack();

	}

	@Override
	public void actualizar(Context context) {
		
	}
}

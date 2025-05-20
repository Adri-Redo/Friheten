package Presentacion.Personal.Personal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Negocio.Personal.TPersonal;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.FactoryPresentacion.FactoryPresentacion;

public class GUIPersonal extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static JButton alta;
	static JButton baja;
	static JButton consultar;
	static JButton listar;
	static JButton modificar;
	static JButton mostrarPersonalPorNif;
	static JButton mostrarPersonalPorID;
	static JButton mostrarPersonalPorHabilidad;
	static JButton back;
	static JButton mostrarPersonalPorNumHabilidades;
	static JButton mostrarPersonalPorRangodeSueldo;

	public GUIPersonal() {

		super("PERSONAL");
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

		alta = new JButton("ALTA PERSONAL");
		alta.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.ALTA_PERSONAL_VIEW, null));
			dispose();

		});

		baja = new JButton("BAJA PERSONAL");
		baja.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.BAJA_PERSONAL_VIEW, null));
			dispose();

		});

		consultar = new JButton("CONSULTAR PERSONAL");
		consultar.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.CONSULTAR_PERSONAL_VIEW, null));
			dispose();

		});

		listar = new JButton("LISTAR PERSONAL");
		listar.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.LISTAR_PERSONAL_VIEW, null));
			dispose();

		});

		modificar = new JButton("MODIFICAR PERSONAL");
		modificar.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.MODIFICAR_PERSONAL_VIEW, null));
			dispose();

		});

		mostrarPersonalPorNif = new JButton("MOSTRAR PERSONAL NIF");
		mostrarPersonalPorNif.setToolTipText("Muestra el personal según nif.");
		mostrarPersonalPorNif.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.MOSTRAR_PERSONAL_PORNIF_VIEW, null));
			dispose();

		});
		
		mostrarPersonalPorID = new JButton("MOSTRAR PERSONAL ID NAVE");
		mostrarPersonalPorID.setToolTipText("Muestra el personal según el id de la nave.");
		mostrarPersonalPorID.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.MOSTRAR_PERSONAL_PORID_NAVE_VIEW, null));
			dispose();

		});

		mostrarPersonalPorHabilidad = new JButton("MOSTRAR PERSONAL POR ID HABILIDAD");
		mostrarPersonalPorHabilidad.setToolTipText("Muestra el personal según el id de la habilidad.");
		mostrarPersonalPorHabilidad.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.MOSTRAR_PERSONAL_DE_UNA_HABILIDAD_VIEW, null));
			dispose();

		});
		
		mostrarPersonalPorNumHabilidades = new JButton("MOSTRAR PERSONAL POR NUM HABILIDADES");
		mostrarPersonalPorNumHabilidades.setToolTipText("Muestra el personal según el id de la habilidad.");
		mostrarPersonalPorNumHabilidades.addActionListener((e) ->{
			Controller.getInstance().handle(new Context(Events.MOSTRAR_PERSONAL_PORNUMHAB_VIEW, null));
			dispose();
		});
		
		mostrarPersonalPorRangodeSueldo = new JButton("MOSTRAR PERSONAL POR RANGO DE SUELDO");
		mostrarPersonalPorRangodeSueldo.setToolTipText("Muestra el personal según el rango de sueldo.");
		mostrarPersonalPorRangodeSueldo.addActionListener((e) ->{
			Controller.getInstance().handle(new Context(Events.MOSTRAR_PERSONAL_PORRANGOSUELDO_VIEW, null));
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
		panelMedio.add(mostrarPersonalPorID);
		panelMedio.add(mostrarPersonalPorHabilidad);
		panelMedio.add(mostrarPersonalPorNif);
		panelMedio.add(mostrarPersonalPorNumHabilidades);
		panelMedio.add(mostrarPersonalPorRangodeSueldo);
		panelInferior.add(back);

		panelPrincipal.add(panelSuperior, BorderLayout.PAGE_START);
		panelPrincipal.add(panelMedio, BorderLayout.CENTER);
		panelPrincipal.add(panelInferior, BorderLayout.PAGE_END);

		this.setContentPane(panelPrincipal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.pack();

	}

	@Override
	public void actualizar(Context context) {

		switch (context.getEvent()) {

		case Events.EXITO_PERSONAL_ALTA:

			TPersonal t = (TPersonal) context.getData();

			JOptionPane.showMessageDialog(null, "Se ha dado de alta a " + t.getNombre() + " " + t.getApellido()
					+ "\n con NIF " + t.getNif() + " correctamente.", "Info", JOptionPane.INFORMATION_MESSAGE);

			break;
		case Events.EXITO_PERSONAL_BAJA:

			JOptionPane.showMessageDialog(null, "Se ha dado de baja a la persona con id " + context.getData() + " correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);

			break;
		case Events.EXITO_PERSONAL_CONSULTAR:

			FactoryPresentacion.getInstance().generateGUI(Events.CONSULTAR_PERSONAL_VIEW).actualizar(context);

			break;
		case Events.EXITO_PERSONAL_MODIFICAR:

			t = (TPersonal) context.getData();

			JOptionPane.showMessageDialog(null, "Se ha modificado a la persona con id " + t.getId() + " correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);

			break;

		case Events.EXITO_PERSONAL_MOSTRAR_POR_ID_NAVE:

			FactoryPresentacion.getInstance().generateGUI(Events.MOSTRAR_PERSONAL_PORID_NAVE_VIEW).actualizar(context);

			break;

		case Events.EXITO_MOSTRAR_PERSONAL_DE_UNA_HABILIDAD:

			FactoryPresentacion.getInstance().generateGUI(Events.MOSTRAR_PERSONAL_DE_UNA_HABILIDAD_VIEW)
					.actualizar(context);

			break;

		case Events.ERROR_PERSONAL_EXISTE:

			TPersonal t2 = (TPersonal) context.getData();

			JOptionPane.showMessageDialog(null, "Ya existe la persona con el dni " + t2.getNif(), "ERROR",
					JOptionPane.ERROR_MESSAGE);

			break;

		case Events.ERROR_PERSONAL_NO_EXISTE:

			JOptionPane.showMessageDialog(null, "No existe la persona con el id " + context.getData() + " o ya esta dado de baja.",
					"ERROR", JOptionPane.ERROR_MESSAGE);

			break;

		case Events.NAVE_ERROR_NO_EXISTE:

			TPersonal t3 = (TPersonal) context.getData();

			JOptionPane.showMessageDialog(null, "No existe la nave con el id " + t3.getIdNave(), "ERROR",
					JOptionPane.ERROR_MESSAGE);

			break;
		case Events.ERROR_PERSONAL_YA_DADO_DE_BAJA:

			JOptionPane.showMessageDialog(null, "La persona con el id " + context.getData() + " ya estaba dado de baja.", "ERROR",
					JOptionPane.ERROR_MESSAGE);

			break;
		case Events.ERROR_JEFE_ANTES_ERA_EMPLEADO_O_VICEVERSA:

			JOptionPane.showMessageDialog(null,
					"Un jefe no puede pasar a ser empleado ni viceversa. Hay que mantener a cada uno en su respectivo cargo.",
					"ERROR", JOptionPane.ERROR_MESSAGE);

			break;

		case Events.ERROR_NO_HAY_PERSONAL_ASOCIADO_A_TAL_ID_NAVE:

			JOptionPane.showMessageDialog(null, "No hay ninguna persona asociada a esa nave o bien la nave no existe.",
					"ERROR", JOptionPane.ERROR_MESSAGE);

			break;

		case Events.ERROR_NO_TIENE_NINGUNA_PERSONA_VINCULADA:

			JOptionPane.showMessageDialog(null, "No hay ninguna persona asociada a esa habilidad", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			break;

		case Events.ERROR_HABILIDAD_NO_EXISTE:
			JOptionPane.showMessageDialog(null, "No existe la habilidad con el id " + context.getData(), "ERROR",
					JOptionPane.ERROR_MESSAGE);

			break;

		case Events.ERROR_NECESITAS_DESVINCULAR_HABILIDAD_A_PERSONAL:

			JOptionPane.showMessageDialog(null,
					"Antes de dar de baja a la persona necesitas desvincularla de todas sus habilidades.\nComprueba a que habilidades esta vinculada.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			break;

		}

	}

}

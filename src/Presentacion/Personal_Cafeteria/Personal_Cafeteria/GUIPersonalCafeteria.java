package Presentacion.Personal_Cafeteria.Personal_Cafeteria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Negocio.Personal_Cafeteria.TPersonalCafeteria;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.FactoryPresentacion.FactoryPresentacion;

public class GUIPersonalCafeteria extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static JButton alta;
	static JButton baja;
	static JButton consultar;
	static JButton listar;
	static JButton modificar;
	static JButton mostrarPersonalPorTurno;
	static JButton back;

	public GUIPersonalCafeteria() {

		super("PERSONAL CAFETERIA");
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

		alta = new JButton("ALTA PERSONAL CAFETERIA");
		alta.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.ALTA_PERSONALCAFETERIA_VIEW, null));
			dispose();

		});

		baja = new JButton("BAJA PERSONAL CAFETERIA");
		baja.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.BAJA_PERSONALCAFETERIA_VIEW, null));
			dispose();

		});

		consultar = new JButton("CONSULTAR PERSONAL CAFETERIA");
		consultar.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.CONSULTAR_PERSONALCAFETERIA_VIEW, null));
			dispose();

		});

		listar = new JButton("LISTAR PERSONAL CAFETERIA");
		listar.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.LISTAR_PERSONALCAFETERIA_VIEW, null));
			dispose();

		});

		modificar = new JButton("MODIFICAR PERSONAL CAFETERIA");
		modificar.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.MODIFICAR_PERSONALCAFETERIA_VIEW, null));
			dispose();

		});

		mostrarPersonalPorTurno = new JButton("MOSTRAR PERSONAL CAFETERIA TURNO");
		mostrarPersonalPorTurno.setToolTipText("Muestra el personal según su turno.");
		mostrarPersonalPorTurno.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.MOSTRAR_PERSONALCAFETERIA_PORID_TURNO_VIEW, null));
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
		panelMedio.add(mostrarPersonalPorTurno);
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

		TPersonalCafeteria t;
		switch (context.getEvent()) {

		case Events.EXITO_PERSONALCAFETERIA_ALTA:

			t = (TPersonalCafeteria) context.getData();

			JOptionPane.showMessageDialog(null, "Se ha dado de alta a " + t.getNombre() + " " + t.getApellidos()
					+ "\n con NIF " + t.getNif() + " correctamente.", "Info", JOptionPane.INFORMATION_MESSAGE);

			break;
		case Events.EXITO_PERSONALCAFETERIA_BAJA:

			JOptionPane.showMessageDialog(null, "Se ha dado de baja a la persona con id " + context.getData() + " correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);

			break;
		case Events.EXITO_PERSONALCAFETERIA_CONSULTAR:

			FactoryPresentacion.getInstance().generateGUI(Events.CONSULTAR_PERSONALCAFETERIA_VIEW).actualizar(context);

			break;
		case Events.EXITO_PERSONALCAFETERIA_MODIFICAR:

			t = (TPersonalCafeteria) context.getData();

			JOptionPane.showMessageDialog(null, "Se ha modificado a la persona con id " + t.getId() + " correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);

			break;

		case Events.EXITO_PERSONALCAFETERIA_MOSTRAR_POR_ID_TURNO:

			FactoryPresentacion.getInstance().generateGUI(Events.MOSTRAR_PERSONAL_PORID_NAVE_VIEW).actualizar(context);

			break;

		case Events.ERROR_PERSONALCAFETERIA_EXISTE:

			JOptionPane.showMessageDialog(null, "Ya existe una persona con el mismo NIF", "ERROR",
					JOptionPane.ERROR_MESSAGE);

			break;

		case Events.ERROR_PERSONALCAFETERIA_NO_EXISTE:
			t = (TPersonalCafeteria) context.getData();

			JOptionPane.showMessageDialog(null, "No existe la persona con el id " + t.getId() + " o ya esta dado de baja.",
					"ERROR", JOptionPane.ERROR_MESSAGE);

			break;
		case Events.ERROR_PERSONALCAFETERIA_ALTA:

			JOptionPane.showMessageDialog(null, "Error guardando el personal en la BD.",
					"ERROR", JOptionPane.ERROR_MESSAGE);

			break;
		case Events.ERROR_TURNO_NO_EXISTE:

			t = (TPersonalCafeteria) context.getData();
			JOptionPane.showMessageDialog(null, "No existe el turno con el id " + t.getTurno(),
					"ERROR", JOptionPane.ERROR_MESSAGE);

			break;
			
		case Events.ERROR_PERSONALCAFETERIA_YA_ACTIVO:
			t = (TPersonalCafeteria) context.getData();
			JOptionPane.showMessageDialog(null, "La persona con el id " + t.getId() + " ya está activa en la BD", "ERROR",
					JOptionPane.ERROR_MESSAGE);

			break;
		case Events.ERROR_PERSONALCAFETERIA_MODIFICAR:
			t = (TPersonalCafeteria) context.getData();
			JOptionPane.showMessageDialog(null, "La persona con el id " + t.getId() + " ya está activa en la BD", "ERROR",
					JOptionPane.ERROR_MESSAGE);

			break;

		case Events.ERROR_PERSONALCAFETERIA_YA_DADO_DE_BAJA:

			JOptionPane.showMessageDialog(null, "Error durante la modificación.", "ERROR",
					JOptionPane.ERROR_MESSAGE);

			break;
		
		case Events.ERROR_NO_HAY_PERSONALCAFETERIA_ASOCIADO_A_ID_TURNO:

			JOptionPane.showMessageDialog(null, "No hay ninguna persona asociada a ese turno o bien el turno no existe.",
					"ERROR", JOptionPane.ERROR_MESSAGE);

			break;
		}

	}

}

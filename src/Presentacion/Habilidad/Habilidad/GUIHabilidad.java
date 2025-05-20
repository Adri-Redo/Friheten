package Presentacion.Habilidad.Habilidad;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Negocio.Habilidad.THabilidad;
import Negocio.Habilidad.THabilidadPersonal;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.FactoryPresentacion.FactoryPresentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GUIHabilidad extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static JButton alta;
	static JButton baja;
	static JButton consultar;
	static JButton desvincular;
	static JButton listar;
	static JButton modificar;
	static JButton vincular;
	static JButton mostrar_por_persona;
	static JButton back;

	public GUIHabilidad() {
		super("HABILIDAD");
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

		alta = new JButton("ALTA HABILIDAD");
		alta.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.ALTA_HABILIDAD_VIEW, null));
			dispose();
		});

		baja = new JButton("BAJA HABILIDAD");
		baja.addActionListener((e) -> {

			Controller.getInstance().handle(new Context(Events.BAJA_HABILIDAD_VIEW, null));
			dispose();

		});

		consultar = new JButton("CONSULTAR HABILIDAD");
		consultar.addActionListener((e) -> {

			Controller.getInstance().handle(new Context(Events.CONSULTAR_HABILIDAD_VIEW, null));
			dispose();

		});

		desvincular = new JButton("DESVINCULAR HABILIDAD");
		desvincular.addActionListener((e) -> {

			Controller.getInstance().handle(new Context(Events.DESVINCULAR_HABILIDAD_VIEW, null));
			dispose();

		});

		listar = new JButton("LISTAR HABILIDAD");
		listar.addActionListener((e) -> {

			Controller.getInstance().handle(new Context(Events.LISTAR_HABILIDAD_VIEW, null));
			dispose();

		});

		modificar = new JButton("MODIFICAR HABILIDAD");
		modificar.addActionListener((e) -> {

			Controller.getInstance().handle(new Context(Events.MODIFICAR_HABILIDAD_VIEW, null));
			dispose();

		});

		vincular = new JButton("VINCULAR HABILIDAD");
		vincular.addActionListener((e) -> {

			Controller.getInstance().handle(new Context(Events.VINCULAR_HABILIDAD_VIEW, null));
			dispose();

		});

		mostrar_por_persona = new JButton("MOSTRAR HABILIDADES DE UNA PERSONA");
		mostrar_por_persona.addActionListener((e) -> {

			Controller.getInstance().handle(new Context(Events.MOSTRAR_HABILIDADES_DE_UNA_PERSONA_VIEW, null));
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
		panelSuperior.add(desvincular);
		panelMedio.add(listar);
		panelMedio.add(modificar);
		panelMedio.add(vincular);
		panelMedio.add(mostrar_por_persona);
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
		Object datos = context.getData();

		switch (context.getEvent()) {

		case Events.EXITO_HABILIDAD_ALTA:

			THabilidad t = (THabilidad) datos;

			JOptionPane.showMessageDialog(null, "Se ha dado de alta la habilidad " + t.getNombre() + " correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);

			break;
		case Events.EXITO_HABILIDAD_BAJA:

			int id = (Integer) datos;

			JOptionPane.showMessageDialog(null, "Se ha dado de baja la habilidad con id " + id + " correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);

			break;
		case Events.EXITO_HABILIDAD_CONSULTAR:

			FactoryPresentacion.getInstance().generateGUI(Events.CONSULTAR_HABILIDAD_VIEW).actualizar(context);
			break;
		case Events.EXITO_HABILIDAD_DESVINCULAR:

			THabilidadPersonal tHabPer = (THabilidadPersonal) datos;
			JOptionPane.showMessageDialog(
					null, "Se ha desviculado la habilidad con id " + tHabPer.getIdHabilidad()
							+ " \nde la persona con id " + tHabPer.getIdPersonal() + " correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);

			break;
		case Events.EXITO_HABILIDAD_MODIFICAR:

			THabilidad t1 = (THabilidad) datos;

			JOptionPane.showMessageDialog(null, "Se ha modificado la habilidad con id " + t1.getId()
					+ " \nal nombre de " + t1.getNombre() + " correctamente.", "Info", JOptionPane.INFORMATION_MESSAGE);

			break;
		case Events.EXITO_HABILIDAD_VINCULAR:

			tHabPer = (THabilidadPersonal) datos;

			JOptionPane
					.showMessageDialog(null,
							"Se ha vinculado la habilidad con id " + tHabPer.getIdHabilidad()
									+ " \n a la persona con id " + tHabPer.getIdPersonal() + " correctamente.",
							"Info", JOptionPane.INFORMATION_MESSAGE);

			break;

		case Events.EXITO_MOSTRAR_HABILIDAD_POR_PERSONA:

			FactoryPresentacion.getInstance().generateGUI(Events.MOSTRAR_HABILIDADES_DE_UNA_PERSONA_VIEW)
					.actualizar(context);

			break;

		case Events.ERROR_HABILIDAD_EXISTE:

			THabilidad t2 = (THabilidad) datos;

			JOptionPane.showMessageDialog(null, "Ya existe la habilidad con el nombre " + t2.getNombre(), "ERROR",
					JOptionPane.ERROR_MESSAGE);

			break;

		case Events.ERROR_HABILIDAD_NO_EXISTE:

			Integer idNoExiste = (Integer) datos;

			JOptionPane.showMessageDialog(null, "La habilidad con el id " + idNoExiste + " no existe.", "ERROR",
					JOptionPane.ERROR_MESSAGE);

			break;

		case Events.ERROR_HABILIDAD_YA_VINCULADA_A_PERSONAL:

			tHabPer = (THabilidadPersonal) datos;
			JOptionPane.showMessageDialog(
					null, "La habilidad con el id " + tHabPer.getIdHabilidad()
							+ " ya estaba \nvinculada a la persona con id " + tHabPer.getIdPersonal() + ".",
					"ERROR", JOptionPane.ERROR_MESSAGE);

			break;

		case Events.ERROR_PERSONAL_NO_EXISTE:

			JOptionPane.showMessageDialog(null,
					"No existe la persona con id " + datos + " en la base de datos o bien esta dado de baja.", "ERROR",
					JOptionPane.ERROR_MESSAGE);

			break;

		case Events.ERROR_HABILIDAD_NO_VINCULADA_A_PERSONAL:

			tHabPer = (THabilidadPersonal) datos;
			JOptionPane.showMessageDialog(
					null, "La habilidad con el id " + tHabPer.getIdHabilidad()
							+ " no esta \nvinculada a la persona con id " + tHabPer.getIdPersonal() + ".",
					"ERROR", JOptionPane.ERROR_MESSAGE);

			break;

		case Events.ERROR_NECESITAS_DESVINCULAR_HABILIDAD_A_PERSONAL:

			idNoExiste = (Integer) datos;

			JOptionPane.showMessageDialog(null, "La habilidad con el id " + idNoExiste
					+ " esta vinculada a alguna persona. Para dar de baja \nla habilidad debes de desvincular esa habilidad de todas las personas \na las que este vinculada",
					"ERROR", JOptionPane.ERROR_MESSAGE);

			break;

		case Events.ERROR_NO_TIENE_NINGUNA_HABILIDAD_VINCULADA:

			JOptionPane.showMessageDialog(null,
					"La persona con el id " + datos + " no esta vinculada a ninguna habilidad.", "ERROR",
					JOptionPane.ERROR_MESSAGE);

		}
	}

}

package Presentacion.Nave.Nave;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Nave.TNave;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.FactoryPresentacion.FactoryPresentacion;
import Presentacion.Nave.ConsultarNave.GUIConsultarNave;
import Presentacion.Nave.ListarNave.GUIListarNave;
import Presentacion.Nave.ModificarNave.GUIModificarNave;

public class GUINave extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TNave t_nave;
	static JButton alta;
	static JButton baja;
	static JButton consultar;
	static JButton listar;
	static JButton modificar;
	static JButton back;

	public GUINave() {
		super("NAVE");
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

		alta = new JButton("ALTA NAVE");
		alta.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.ALTA_NAVE_VIEW,null));
			dispose();
		});

		baja = new JButton("BAJA NAVE");
		baja.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.BAJA_NAVE_VIEW, null));
			dispose();
		});

		consultar = new JButton("CONSULTAR NAVE");
		consultar.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.CONSULTAR_NAVE_VIEW, null));
			dispose();
		});

		listar = new JButton("LISTAR NAVE");
		listar.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.LISTAR_NAVE_VIEW, null));
			dispose();
		});

		modificar = new JButton("MODIFICAR NAVE");
		modificar.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.MODIFICAR_NAVE_VIEW, null));
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
		switch(context.getEvent()) {
		
		case Events.RES_ALTA_NAVE_OK:
			TNave n = (TNave) datos;
			JOptionPane.showMessageDialog(null, "Se ha dado de alta la nave " + n.getNombre() + " correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case Events.RES_BAJA_NAVE_OK:
			int id = (Integer) datos;
			JOptionPane.showMessageDialog(null, "Se ha dado de baja la nave con el id: " + id + " en la base de datos", "Exito",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case Events.RES_CONSULTAR_NAVE_OK:
			FactoryPresentacion.getInstance().generateGUI(Events.CONSULTAR_NAVE_VIEW).actualizar(context);
			break;
		case Events.RES_MODIFICAR_NAVE_OK:
			TNave n1 = (TNave) datos;
			JOptionPane.showMessageDialog(null, "Se ha modificado la nave con id " + n1.getId()
			+ " \nal nombre de " + n1.getNombre() + " correctamente.", "Info", JOptionPane.INFORMATION_MESSAGE);
			break;
		case Events.NAVE_ERROR_YA_EXISTE:
			TNave n2 = (TNave) datos;
			JOptionPane.showMessageDialog(null, "Ya existe la nave con el nombre " + n2.getNombre(), "ERROR",
			JOptionPane.ERROR_MESSAGE);
			break;
		case Events.NAVE_ERROR_NO_EXISTE:
			int idNoExiste = (Integer) datos;
			JOptionPane.showMessageDialog(null, "La nave con el id " + idNoExiste + " no existe.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			break;
		case Events.NAVE_ERROR_DATOS_NO_VALIDOS:
			JOptionPane.showMessageDialog(null, "Error: Datos no validos", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}

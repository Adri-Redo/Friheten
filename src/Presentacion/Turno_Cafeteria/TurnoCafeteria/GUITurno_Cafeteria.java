package Presentacion.Turno_Cafeteria.TurnoCafeteria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Negocio.Turno_Cafeteria.TTurnoCafeteria;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.FactoryPresentacion.FactoryPresentacion;

public class GUITurno_Cafeteria extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JButton alta;
	private static JButton baja;
	private static JButton consultar;
	private static JButton listar;
	private static JButton modificar;
	private static JButton back;
	private static JButton calcularNomina;
	
	public GUITurno_Cafeteria () {
		super("Turno Cafeteria");
		this.initGUI();
	}
	
	private void initGUI() {
		this.setSize(400, 400);
		
		JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        
        panelPrincipal.setBorder(BorderFactory.createMatteBorder(0, 40, 40, 40, Color.WHITE));
        panelPrincipal.setBackground(Color.WHITE);
        
        JLabel titulo = new JLabel("TURNO", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28)); 
        titulo.setForeground(Color.WHITE);
        titulo.setOpaque(true); 
        titulo.setBackground(new Color(70, 130, 180));  

        titulo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 40));

        panelPrincipal.add(titulo, BorderLayout.NORTH);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 2, 20, 20));
        panelBotones.setBackground(Color.WHITE);
        
		alta = createStyledButton("ALTA");
		alta.addActionListener((e)->{ 
			Controller.getInstance().handle(new Context(Events.ALTA_TURNO_VIEW, null));
			dispose();
		});
		
		baja = createStyledButton("BAJA");
		baja.addActionListener((e)-> {
			Controller.getInstance().handle(new Context(Events.BAJA_TURNO_VIEW, null));
			dispose();
		});
		
		consultar = createStyledButton("CONSULTAR");
		consultar.addActionListener((e)-> { 			
			Controller.getInstance().handle(new Context(Events.CONSULTAR_TURNO_VIEW, null));
			dispose();
		});
		
		listar = createStyledButton("LISTAR");
		listar.addActionListener((e)->{ 
			Controller.getInstance().handle(new Context(Events.LISTAR_TURNO_VIEW, null));
		});
		
		modificar = createStyledButton("MODIFICAR");
		modificar.addActionListener((e)-> { 
			Controller.getInstance().handle(new Context(Events.MODIFICAR_TURNO_VIEW, null));
			dispose();
		});
		
		calcularNomina = createRedStyledButton("CALCULAR NOMINA");
		calcularNomina.addActionListener((e)-> { 
			Controller.getInstance().handle(new Context(Events.TURNO_CALCULAR_NOMINA_VIEW, null));
			dispose();
		});
		
		back = new JButton();
		back.setToolTipText("Volver a la ventana principal");
		back.setIcon(new ImageIcon("resources/images/back.png"));
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setPreferredSize(new Dimension(150, 75));
		back.addActionListener((e) -> dispose());

		panelBotones.add(alta);
		panelBotones.add(baja);
		panelBotones.add(consultar);
		panelBotones.add(listar);
		panelBotones.add(modificar);
		panelBotones.add(calcularNomina);
		panelPrincipal.add(back, BorderLayout.SOUTH);

		panelPrincipal.add(panelBotones, BorderLayout.CENTER);
		
		

		this.setContentPane(panelPrincipal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.pack();
		this.setPreferredSize(new Dimension(400, 300));
		
	}
	
	 private static JButton createRedStyledButton(String text) {
		 JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.BOLD, 14));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(255, 0, 0));  // Rojo
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 2));  // Borde oscuro
		button.setPreferredSize(new Dimension(250, 55));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Agregar un efecto de hover (cambio de color al pasar el mouse)
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				button.setBackground(new Color(200, 0, 0));  // Cambio de color más oscuro
			}

			public void mouseExited(MouseEvent e) {
				button.setBackground(new Color(255, 0, 0));  // Regresar al color original
			}
		});
		
		return button;
	 }

	 private static JButton createStyledButton(String text) {
		JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 130, 180));  // Azul marino grisáceo
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 2));  // Borde oscuro
        button.setPreferredSize(new Dimension(250, 55));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Agregar un efecto de hover (cambio de color al pasar el mouse)
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(50, 100, 150));  // Cambio de color más oscuro
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(70, 130, 180));  // Regresar al color original
            }
        });
        
        return button;
	 }
	
	@Override
	public void actualizar(Context context) {
		switch(context.getEvent()) {
		
		case Events.EXITO_TURNO_ALTA:
			TTurnoCafeteria turno = (TTurnoCafeteria) context.getData();
			
			JOptionPane.showMessageDialog(null, "Se ha dado de alta el turno " + turno.getNombreTurno() + " correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case Events.EXITO_TURNO_BAJA:
			int id = (Integer) context.getData();
			
			JOptionPane.showMessageDialog(null, "Se ha dado de baja el turno "+ id +" correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case Events.EXITO_TURNO_CONSULTAR:
			FactoryPresentacion.getInstance().generateGUI(Events.CONSULTAR_TURNO_VIEW).actualizar(context);
			break;
			
		case Events.EXITO_TURNO_MODIFICAR:
			int id1 = (Integer) context.getData();
			
			JOptionPane.showMessageDialog(null, "Se ha modificado el turno con id " + id1 +" correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case Events.ERROR_TURNO_EXISTE:
			TTurnoCafeteria turno1 = (TTurnoCafeteria) context.getData();
			
			JOptionPane.showMessageDialog(null, "El turno " + turno1.getNombreTurno() + " ya existe.",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		case Events.ERROR_NOMBRE_EXISTE:
			TTurnoCafeteria turno2 = (TTurnoCafeteria) context.getData();
			
			JOptionPane.showMessageDialog(null, "El nombre " + turno2.getNombreTurno() + " ya existe en la base de datos.",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		case Events.ERROR_TURNO_ERRONEO:
			JOptionPane.showMessageDialog(null, "Se ha encontrado un error al crear el turno",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		case Events.ERROR_TURNO_NO_EXISTE:
			int idNoExiste = (Integer) context.getData();
			
			JOptionPane.showMessageDialog(null, "El turno con id " + idNoExiste + " no existe.",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		case Events.ERROR_TURNO_DE_BAJA:
			int idBaja = (Integer) context.getData();
			
			JOptionPane.showMessageDialog(null, "El turno con id " + idBaja + " esta dado de baja.",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Events.ERROR_TURNO_VINCULADO_A_PERSONAL:
			JOptionPane.showMessageDialog(null, "El turno no se puede dar de baja porque esta vinculado a un empleado.",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
		
	
		
		
		
	}
	
	

}

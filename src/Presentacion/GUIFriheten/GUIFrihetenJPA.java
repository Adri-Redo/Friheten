package Presentacion.GUIFriheten;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;


public class GUIFrihetenJPA extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static JButton marca;
	static JButton personal;
	static JButton producto;
	static JButton turno;
	static JButton alergenos;
	static JButton compra;
	static JButton back;
	
	public GUIFrihetenJPA() {
		super("CAFETERIA");
		this.initGUI();

	}

	private void initGUI() {

		this.setSize(400, 400);
		
		JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        
        panelPrincipal.setBorder(BorderFactory.createMatteBorder(0, 40, 40, 40, Color.WHITE));
        panelPrincipal.setBackground(Color.WHITE);
        
        JLabel titulo = new JLabel("CAFETERIA", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28)); 
        titulo.setForeground(Color.WHITE);
        titulo.setOpaque(true); 
        titulo.setBackground(new Color(70, 130, 180));  

        titulo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 40));

        panelPrincipal.add(titulo, BorderLayout.NORTH);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 2, 20, 20));
        panelBotones.setBackground(Color.WHITE);
        
		marca = createStyledButton("MARCA");
		marca.addActionListener((e) ->  Controller.getInstance().handle(new Context(Events.MARCA_VIEW,null)));

		personal = createStyledButton("PERSONAL");
		personal.addActionListener((e) -> Controller.getInstance().handle(new Context(Events.PERSONALCAFETERIA_VIEW, null)));

		producto = createStyledButton("PRODUCTO");
		producto.addActionListener((e) -> Controller.getInstance().handle(new Context(Events.PRODUCTO_VIEW, null)));

		turno = createStyledButton("TURNO");
		turno.addActionListener((e) -> Controller.getInstance().handle(new Context(Events.TURNO_VIEW, null)));

		alergenos = createStyledButton("ALERGENOS");
		alergenos.addActionListener((e) -> Controller.getInstance().handle(new Context(Events.ALERGENO_VIEW, null)));

		compra = createStyledButton("COMPRA");
		compra.addActionListener((e) -> Controller.getInstance().handle(new Context(Events.COMPRA_CAF_VIEW, null)));
		
		
		back = new JButton();
		back.setToolTipText("Volver a la ventana principal");
		back.setIcon(new ImageIcon("resources/images/back.png"));
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setPreferredSize(new Dimension(150, 75));
		back.addActionListener((e) -> dispose());

		panelBotones.add(marca);
		panelBotones.add(personal);
		panelBotones.add(producto);
		panelBotones.add(turno);
		panelBotones.add(compra);
		panelBotones.add(alergenos);


		panelPrincipal.add(panelBotones, BorderLayout.CENTER);
		panelPrincipal.add(back,BorderLayout.SOUTH);
		
		this.setContentPane(panelPrincipal);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.pack();

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
		
	}
}

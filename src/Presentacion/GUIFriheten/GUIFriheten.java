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

import Negocio.Compra_Cafeteria.CompraCafeteria;
import Negocio.EntityManagerFactory.EntityManagerFactoryFactory;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class GUIFriheten extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static JButton Cafeteria;
	static JButton Friheten;
	static JButton exit;

	public GUIFriheten() {
		super("FRIHETEN ~ TIENDA DE MUEBLES");
		this.initGUI();

	}

	private void initGUI() {

		this.setSize(400, 400);	
		//instanciamos el entityManager para que se cree la bd al principio
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FrihetenUML");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		// Realiza las operaciones (crear, actualizar, borrar, etc.)
		// Ejemplo: insertar un objeto

		em.getTransaction().commit(); // Confirma los cambios
		em.close();
		emf.close();
		
		JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        
        panelPrincipal.setBorder(BorderFactory.createMatteBorder(0, 40, 40, 40, Color.WHITE));
        panelPrincipal.setBackground(Color.WHITE);
        
        JLabel titulo = new JLabel("Seleccionar proyecto", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28)); 
        titulo.setForeground(Color.WHITE);
        titulo.setOpaque(true); 
        titulo.setBackground(new Color(70, 130, 180));  

        titulo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 40));

        panelPrincipal.add(titulo, BorderLayout.NORTH);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 1, 20, 20));
        panelBotones.setBackground(Color.WHITE);
        

		Cafeteria = createStyledButton("CAFETERIA (JPA)");
		Cafeteria.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.CAFETERIA_VIEW,null));
			dispose();
		});

		Friheten = createStyledButton("TIENDA DE MUEBLES (DAO)");
		Friheten.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.TIENDA_MUEBLES_VIEW,null));
			dispose();
		});

		
		panelBotones.add(Cafeteria);
		panelBotones.add(Friheten);

		

		panelPrincipal.add(panelBotones, BorderLayout.CENTER);
		
		this.setContentPane(panelPrincipal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200,200);
		this.pack();

	}

	
	 private static JButton createStyledButton(String text) {
		 JButton button = new JButton(text);
	        button.setFont(new Font("Arial", Font.BOLD, 18));
	        button.setForeground(Color.WHITE);
	        button.setBackground(new Color(70, 130, 180));  // Azul marino grisáceo
	        button.setFocusPainted(false);
	        //button.setBorder(BorderFactory.createLineBorder(new Color(64, 137, 178), 2));  // Borde oscuro
	        button.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
	        button.setPreferredSize(new Dimension(280, 45));
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

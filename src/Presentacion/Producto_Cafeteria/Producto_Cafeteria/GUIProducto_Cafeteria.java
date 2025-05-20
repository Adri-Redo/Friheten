package Presentacion.Producto_Cafeteria.Producto_Cafeteria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import Negocio.Producto_Cafeteria.TProductoCafeteria;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.FactoryPresentacion.FactoryPresentacion;

public class GUIProducto_Cafeteria extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;


	TProductoCafeteria t_producto;
	static JButton alta;
	static JButton baja;
	static JButton consultar;
	static JButton listar;
	static JButton modificar;
	static JButton mostrarporIDMarca;
	static JButton mostrarporIDAlergeno;
	static JButton back;
	
	public GUIProducto_Cafeteria() {
		super("Producto cafeteria");
		this.initGUI();
	}
	
	private void initGUI() {
		
		this.setSize(400, 400);
		
		JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        
        panelPrincipal.setBorder(BorderFactory.createMatteBorder(0, 40, 40, 40, Color.WHITE));
        panelPrincipal.setBackground(Color.WHITE);
        
        JLabel titulo = new JLabel("PRODUCTO", SwingConstants.CENTER);
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
			Controller.getInstance().handle(new Context(Events.ALTA_PRODUCTO_VIEW, null));
			dispose();
		});
		
		baja = createStyledButton("BAJA");
		baja.addActionListener((e)-> {
			Controller.getInstance().handle(new Context(Events.BAJA_PRODUCTO_VIEW, null));
			dispose();
		});
		
		consultar = createStyledButton("CONSULTAR");
		consultar.addActionListener((e)-> { 			
			Controller.getInstance().handle(new Context(Events.CONSULTAR_PRODUCTO_VIEW, null));
			dispose();
		});
		
		listar = createStyledButton("LISTAR");
		listar.addActionListener((e)->{ 
			Controller.getInstance().handle(new Context(Events.LISTAR_PRODUCTO_VIEW, null));
		});
		
		modificar = createStyledButton("MODIFICAR");
		modificar.addActionListener((e)-> { 
			Controller.getInstance().handle(new Context(Events.MODIFICAR_PRODUCTO_VIEW, null));
			dispose();
		});
		
		mostrarporIDAlergeno = createStyledButton("CONSULTAR POR ALERGERNO ID");
		mostrarporIDAlergeno.addActionListener((e)->{
			Controller.getInstance().handle(new Context(Events.CONSULTAR_PRODUCTO_POR_ID_ALERGENO_VIEW, null));
			dispose();
		});
	
		mostrarporIDMarca = createStyledButton("CONSULTAR POR MARCA ID");
		mostrarporIDMarca.addActionListener((e)->{
			Controller.getInstance().handle(new Context(Events.CONSULTAR_PRODUCTO_POR_ID_MARCA_VIEW, null));
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
		panelBotones.add(consultar );
		panelBotones.add(listar);
		panelBotones.add(modificar);
		panelBotones.add(mostrarporIDAlergeno );
		panelBotones.add(mostrarporIDMarca );		
		panelBotones.add(back);
		
		panelPrincipal.add(panelBotones, BorderLayout.CENTER);
		panelPrincipal.add(back, BorderLayout.SOUTH);
		
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
		
		case Events.EXITO_PRODUCTO_ALTA:
			TProductoCafeteria producto = (TProductoCafeteria) datos;
			
			JOptionPane.showMessageDialog(null, "Se ha dado de alta el producto " + producto.getNombre() + " correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case Events.EXITO_PRODUCTO_BAJA:
			int id = (Integer) datos;
			
			JOptionPane.showMessageDialog(null, "Se ha dado de baja el producto "+ id +" correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case Events.EXITO_PRODUCTO_CONSULTAR:
			FactoryPresentacion.getInstance().generateGUI(Events.CONSULTAR_PRODUCTO_VIEW).actualizar(context);
			break;
			
		case Events.EXITO_PRODUCTO_MODIFICAR:
			int id1 = (Integer) datos;
			
			JOptionPane.showMessageDialog(null, "Se ha modificado el producto con id " + id1 +" correctamente.",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			break;
			
			
		case Events.ERROR_PRODUCTO_EXISTE:
			TProductoCafeteria producto3 = (TProductoCafeteria) datos;
			if (producto3.getActivo())
				JOptionPane.showMessageDialog(null, "El producto " + producto3.getNombre() + " ya existe.",
					"Error", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "El producto " + producto3.getNombre() + " ya existe.(Estado: Inactivo) ",
						"Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Events.ERROR_PRODUCTO_NO_EXISTE:
			int idNoExiste = (Integer) datos;
			
			JOptionPane.showMessageDialog(null, "El producto con id " + idNoExiste + " no existe.",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		}
		
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


}

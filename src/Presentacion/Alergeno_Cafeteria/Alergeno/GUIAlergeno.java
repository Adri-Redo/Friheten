package Presentacion.Alergeno_Cafeteria.Alergeno;

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

import Negocio.Alergeno_Cafeteria.TAlergeno;
import Negocio.Personal.TPersonal;
import Negocio.Producto_Cafeteria.TProductoCafeteria;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.FactoryPresentacion.FactoryPresentacion;

public class GUIAlergeno extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;


	TProductoCafeteria t_producto;
	static JButton alta;
	static JButton baja;
	static JButton consultar;
	static JButton listar;
	static JButton modificar;
	static JButton mostrarporIDProducto;
	static JButton back;
	static JButton vincular;
	static JButton desvincular;
	
	public GUIAlergeno() {
		super("Alergeno");
		this.initGUI();
	}
	
	private void initGUI() {
		
		this.setSize(400, 400);
		
		JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        
        panelPrincipal.setBorder(BorderFactory.createMatteBorder(0, 40, 40, 40, Color.WHITE));
        panelPrincipal.setBackground(Color.WHITE);
        
        JLabel titulo = new JLabel("ALERGENO", SwingConstants.CENTER);
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
			Controller.getInstance().handle(new Context(Events.ALTA_ALERGENO_VIEW, null));
			dispose();
		});
		
		baja = createStyledButton("BAJA");
		baja.addActionListener((e)-> {
			Controller.getInstance().handle(new Context(Events.BAJA_ALERGENO_VIEW, null));
			dispose();
		});
		
		consultar = createStyledButton("CONSULTAR");
		consultar.addActionListener((e)-> { 			
			Controller.getInstance().handle(new Context(Events.CONSULTAR_ALERGENO_VIEW, null));
			dispose();
		});
		
		listar = createStyledButton("LISTAR");
		listar.addActionListener((e)->{ 
			Controller.getInstance().handle(new Context(Events.LISTAR_ALERGENO_VIEW, null));
			dispose();
		});
		
		modificar = createStyledButton("MODIFICAR");
		modificar.addActionListener((e)-> { 
			Controller.getInstance().handle(new Context(Events.MODIFICAR_ALERGENO_VIEW, null));
			dispose();
		});
		
		mostrarporIDProducto = createStyledButton("CONSULTAR POR ID PRODUCTO");
		mostrarporIDProducto.addActionListener((e)->{
			Controller.getInstance().handle(new Context(Events.MOSTRAR_ALERGENO_PORID_PRODUCTO_VIEW,null));
			dispose();
		});
		vincular = createStyledButton("VINCULAR ALERGENO A PRODUCTO");
		vincular.addActionListener((e)->{
			Controller.getInstance().handle(new Context(Events.VINCULAR_ALERGENO_VIEW,null));
			dispose();
		});
		desvincular = createStyledButton("DESVINCULAR ALERGENO A PRODUCTO");
		desvincular.addActionListener((e)->{
			Controller.getInstance().handle(new Context(Events.DESVINCULAR_ALERGENO_VIEW,null));
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
		panelBotones.add(mostrarporIDProducto);
		panelBotones.add(back);
		panelBotones.add(vincular);
		panelBotones.add(desvincular);
		
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
		TAlergeno al;
		switch(context.getEvent()) {
		
		//EXITO
		case Events.EXITO_ALERGENO_ALTA:
			 int id = (Integer) context.getData();

			JOptionPane.showMessageDialog(null, "Se ha dado de alta el alergeno con id " + id +  " correctamente.", "Info", JOptionPane.INFORMATION_MESSAGE);
			break;
		case Events.EXITO_ALERGENO_BAJA:
			 al = (TAlergeno) context.getData();
			JOptionPane.showMessageDialog(null, "Se ha dado de baja el alergeno con id " + al.getIdAlergeno()+  " correctamente.", "Info", JOptionPane.INFORMATION_MESSAGE);
			break;
		case Events.EXITO_ALERGENO_CONSULTAR:
			FactoryPresentacion.getInstance().generateGUI(Events.CONSULTAR_ALERGENO_VIEW).actualizar(context);
			break;
		case Events.EXITO_ALERGENO_MODIFICAR:
			al = (TAlergeno) context.getData();
			JOptionPane.showMessageDialog(null, "Se ha modificado el alergeno con id con id " + al.getIdAlergeno()+  " correctamente.", "Info", JOptionPane.INFORMATION_MESSAGE);
			break;
		case Events.EXITO_ALERGENO_MOSTRAR_PORID_PRODUCTO:
			FactoryPresentacion.getInstance().generateGUI(Events.MOSTRAR_ALERGENO_PORID_PRODUCTO_VIEW).actualizar(context);
			break;
			
		case Events.EXITO_ALERGENO_VINCULAR:
			JOptionPane.showMessageDialog(null, "Alergeno y Producto vinculado correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
			break;
		case Events.EXITO_ALERGENO_DESVINCULAR:
			JOptionPane.showMessageDialog(null, "Alergeno y Producto desvinculado correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
			break;
		//ERRORES
		case Events.ERROR_ALERGENO_EXISTE:
			JOptionPane.showMessageDialog(null, "El alergeno ya esta dado de alta en la BD ", "Info", JOptionPane.ERROR_MESSAGE); 
			break;
		case Events.ERROR_ALERGENO_NO_EXISTE:
			JOptionPane.showMessageDialog(null, "El alergeno no existe ", "Info", JOptionPane.ERROR_MESSAGE); 
			break;
		case Events.ERROR_ALERGENO_YA_DADO_DE_BAJA:
			JOptionPane.showMessageDialog(null, "El alergeno ya se encuentra dado de baja ", "Info", JOptionPane.ERROR_MESSAGE); 
			break;
		case Events.ERROR_NO_HAY_ALERGENO_ASOCIADO_A_ID_PRODUCTO:
			JOptionPane.showMessageDialog(null, "No existen alergenos asociados a este producto ", "Info", JOptionPane.ERROR_MESSAGE); 
			break;
		case Events.ERROR_ALERGENO_ALTA:
			JOptionPane.showMessageDialog(null, "No se ha podido dar de alta el alergeno ", "Info", JOptionPane.ERROR_MESSAGE); 
			break;
		case Events.ERROR_ALERGENO_BAJA:
			JOptionPane.showMessageDialog(null, "No se ha podido dar de baja el alergeno ", "Info", JOptionPane.ERROR_MESSAGE); 
			break;
		case Events.ERROR_ALERGENO_CONSULTAR:
			JOptionPane.showMessageDialog(null, "No se ha encontrado el alergeno ", "Info", JOptionPane.ERROR_MESSAGE); 
			break;
		case Events.ERROR_ALERGENO_MODIFICAR:
			JOptionPane.showMessageDialog(null, "Error al modificar el alergeno", "Info", JOptionPane.ERROR_MESSAGE); 
			break;
		case Events.ERROR_ALERGENO_MOSTRAR_POR_ID_TURNO :
			JOptionPane.showMessageDialog(null, "Error al recuperar los alergenos del producto ", "Info", JOptionPane.ERROR_MESSAGE); 
			break;
		case Events.ERROR_ALERGENO_VINCULAR :
			JOptionPane.showMessageDialog(null, "Error al vincular el alergeno", "Info", JOptionPane.ERROR_MESSAGE); 
			break;
		case Events.ERROR_ALERGENO_DESVINCULAR:
			JOptionPane.showMessageDialog(null, "Alergeno y Producto ya desvinculados", "Info", JOptionPane.ERROR_MESSAGE); 
			break;
		case Events.ERROR_ALERGENO_ASOCIADO:
			JOptionPane.showMessageDialog(null, "El alergeno ya esta asociado a algun producto ", "Info", JOptionPane.ERROR_MESSAGE); 
			break;
		case Events.ERROR_ALERGENO_DADO_BAJA:
			JOptionPane.showMessageDialog(null, "El alergeno esta dado de baja", "Info", JOptionPane.ERROR_MESSAGE); 
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

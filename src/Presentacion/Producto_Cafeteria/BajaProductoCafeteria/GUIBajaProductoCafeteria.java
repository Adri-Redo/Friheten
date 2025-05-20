package Presentacion.Producto_Cafeteria.BajaProductoCafeteria;

import Negocio.Producto_Cafeteria.TProductoCafeteria;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Observer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUIBajaProductoCafeteria extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    public GUIBajaProductoCafeteria() {
        super("BAJA PRODUCTO CAFETERIA");
        this.initGUI();
    }

    private void initGUI() {
    	
    	
JPanel mainPanel = new JPanel(new BorderLayout());

		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.WHITE));
		mainPanel.setBackground(Color.WHITE);
		
        JLabel titulo = new JLabel("Dar de BAJA - productos", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 15)); 
        titulo.setForeground(Color.WHITE);
        titulo.setOpaque(true); 
        titulo.setBackground(new Color(70, 130, 180));  

        titulo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 40));
		
        mainPanel.add(titulo, BorderLayout.NORTH);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.WHITE);
        
        // **********************************************************************
        
        JPanel panelBusqueda = new JPanel();
        
		// texto y label
        
		JLabel idLabel = new JLabel("Introduzca el ID del producto: ");
		JTextField txtIDidentificador = new JTextField(5);
		txtIDidentificador.setColumns(20);
	
		panelBusqueda.add(idLabel);
		panelBusqueda.add(txtIDidentificador);
		
		// Botones
		JButton okButton = createStyledButton("Dar de baja");
		JButton cancel = createStyledButton("Cancelar");
		

		panelBusqueda.add(okButton);
		panelBusqueda.add(cancel);
		
		panelBusqueda.setBackground(Color.WHITE);
		
		panelBotones.add(panelBusqueda); //****
		
		// -----------------------------------------------------------




        
        okButton.addActionListener(e -> {
            try 
            {
            	if (chkIntegerAndPositive(txtIDidentificador.getText().trim(), "El identificador "))
                {
                    Integer id = Integer.parseInt(txtIDidentificador.getText().trim());
                  
                 	Controller.getInstance().handle(new Context(Events.BAJA_PRODUCTO_OK_VIEW, id));
					dispose();
                    
                }
            } 
            catch (NumberFormatException ex) 
            {
                JOptionPane.showMessageDialog(
                    null,
                    "El identificador debe ser un número mayor que 0, sin letras.",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });
   
        cancel.setToolTipText("Cancelar operación");
        cancel.addActionListener(e -> {
            Controller.getInstance()
                .handle(new Context(Events.PRODUCTO_VIEW, null));
            dispose();
        });
        
		mainPanel.add(panelBotones, BorderLayout.CENTER);
		
		this.setLocation(200,200);
		this.setSize(1600,900);
		this.setResizable(true); 
		
		setContentPane(mainPanel);
		setVisible(true);
		pack();
    }
    
	private Boolean chkIntegerAndPositive( Object item , String msg_error )
	{
		String strIsInteger = String.valueOf(item);
		if(!strIsInteger.isEmpty()) 
		{
			if(strIsInteger.matches("-?[0-9]+(\\.[0-9]+)?")) {
				int id = Integer.parseInt(strIsInteger);
				if(id <= 0) 
					JOptionPane.showMessageDialog(null, msg_error + " tiene que ser positivo.", "Error",JOptionPane.ERROR_MESSAGE);

				return true;
			}
			else 
				JOptionPane.showMessageDialog(null, msg_error + " tiene que ser numérico.", "Error",JOptionPane.ERROR_MESSAGE);
		}		
		return false;
	}
    
    private static JButton createStyledButton(String text) 
	 {
		 JButton button = new JButton(text);
	        button.setFont(new Font("Arial", Font.BOLD, 14));
	        button.setForeground(Color.WHITE);
	        button.setBackground(new Color(70, 130, 180));  // Azul marino grisáceo
	        button.setFocusPainted(false);
	        button.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 2));  // Borde oscuro
	        button.setPreferredSize(new Dimension(150, 35));
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
    	if(context != null) {
            switch (context.getEvent())
            {
            case Events.BAJA_PRODUCTO_OK_VIEW:
                int idProducto = (int) context.getData();
                
                if(idProducto == -3)
                	JOptionPane.showMessageDialog(null,"El producto NO EXISTE","Error",JOptionPane.ERROR_MESSAGE);                
                else if(idProducto < 0)
                	JOptionPane.showMessageDialog(null,"El producto ya esta dado de BAJA","Error",JOptionPane.ERROR_MESSAGE);
                else if(idProducto >= 0)
                	JOptionPane.showMessageDialog(null,"El producto  ' " +idProducto +" ' se ha dado de baja correctamente","Éxito",JOptionPane.INFORMATION_MESSAGE);
                
                break;
            case Events.ERROR_PRODUCTO_NO_EXISTE:
                int idNoExiste = (Integer) context.getData();
                JOptionPane.showMessageDialog(
                    null,
                    "El producto con identificador " +
                    idNoExiste +
                    " no existe.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                break;
            }		
    	}
    }
}

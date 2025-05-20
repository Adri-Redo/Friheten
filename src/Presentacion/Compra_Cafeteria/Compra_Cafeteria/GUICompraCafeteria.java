package Presentacion.Compra_Cafeteria.Compra_Cafeteria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Negocio.Compra.TCarrito;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUICompraCafeteria extends JFrame implements Serializable, Observer {
	private static final long serialVersionUID = 1L;
	private TCarrito t_carrito;

	
	public GUICompraCafeteria() {
		
		initGUI();
	}
	
	private void initGUI() {
				
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		buttonsPanel.setBackground(Color.DARK_GRAY);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.DARK_GRAY);
		JPanel panelMedio = new JPanel();
		panelMedio.setBackground(Color.DARK_GRAY);
		JPanel panelBajo = new JPanel();
		panelBajo.setBackground(Color.DARK_GRAY);
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(Color.DARK_GRAY);
		
		
		
		JButton abrirCompra = new JButton("ABRIR COMPRA");
		abrirCompra.addActionListener((e) -> {
			if(t_carrito == null)
			{
				Context contexto = new Context(Events.ABRIR_COMPRA_CAF_VIEW, null);
				Controller.getInstance().handle(contexto);
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No se puede abrir una compra si ya se esta realizando una", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			
		});
		
		
		JButton addProduct = new JButton("ANYADIR PRODUCTO");
		addProduct.addActionListener((e) ->{
			if(t_carrito == null)
			{
				JOptionPane.showMessageDialog(null, "Se debe abrir una compra primero", "Error",
						JOptionPane.ERROR_MESSAGE);
				
			}
			else
			{
				Context contexto = new Context(Events.ANYADIR_PRODUCTO_CAF_VIEW, t_carrito);
				Controller.getInstance().handle(contexto);
				dispose();
			}
			
		});
		
		
		JButton deleteProduct = new JButton("QUITAR PRODUCTO");
		deleteProduct.addActionListener((e) -> {
			if(t_carrito == null)
			{
				JOptionPane.showMessageDialog(null, "Se debe abrir una compra primero", "Error",
						JOptionPane.ERROR_MESSAGE);
				
			}
			else
			{
				Context contexto = new Context(Events.QUITAR_PRODUCTO_CAF_VIEW, t_carrito);
				Controller.getInstance().handle(contexto);
				dispose();
			}
			
		});
		
		
		JButton consultarCompra = new JButton("CONSULTAR COMPRA");
		consultarCompra.addActionListener((e) -> {
			Context contexto = new Context(Events.CONSULTAR_COMPRA_CAF_VIEW, t_carrito);
			Controller.getInstance().handle(contexto);
			dispose();
		});
		
		
		JButton mostrarCarrito = new JButton("MOSTRAR CARRITO");
		mostrarCarrito.addActionListener((e) -> {
			if(t_carrito == null)
			{
				JOptionPane.showMessageDialog(null, "Se debe abrir una compra primero", "Error",
						JOptionPane.ERROR_MESSAGE);
				
			}
			else
			{
				Context contexto = new Context(Events.MOSTRAR_CARRITO_CAF_VIEW, t_carrito);
				Controller.getInstance().handle(contexto);
				dispose();
				
			}
			
		});
		

		JButton devolucionProducto = new JButton("DEVOLUCION PRODUCTO");
		devolucionProducto.addActionListener((e) -> {
			Context contexto = new Context(Events.DEVOLUCION_PRODUCTO_CAF_VIEW, t_carrito);
			Controller.getInstance().handle(contexto);
			dispose();
		});
		

		
		JButton cerrarCompra = new JButton("CERRAR COMPRA");
		cerrarCompra.addActionListener((e) ->{
			
			if(t_carrito == null)
			{
				JOptionPane.showMessageDialog(null, "Se debe abrir una compra primero", "Error",
						JOptionPane.ERROR_MESSAGE);
				
			}
			else
			{
				Context contexto = new Context(Events.CERRAR_COMPRA_CAF_VIEW, t_carrito);
				Controller.getInstance().handle(contexto);
				dispose();
			}
			
		});
		
		
		JButton listarCompra = new JButton("LISTAR COMPRA");
		listarCompra.addActionListener((e) ->{
			Context contexto = new Context(Events.LISTAR_COMPRA_CAF_VIEW, t_carrito);
			Controller.getInstance().handle(contexto);
			dispose();
		});
		
		JButton modificarCompra = new JButton("MODIFICAR COMPRA");
		modificarCompra.addActionListener((e) ->{
			Context contexto = new Context(Events.MODIFICAR_COMPRA_CAF_VIEW, null);
			Controller.getInstance().handle(contexto);
			dispose();
		});
		
		
		JButton back = new JButton();
		back = new JButton();
		back.setToolTipText("Volver a la ventana principal");
		back.setIcon(new ImageIcon("resources/images/back.png"));
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setPreferredSize(new Dimension(150, 75));
		back.addActionListener((e) -> dispose());
		
		
		panelSuperior.add(abrirCompra);
		panelSuperior.add(cerrarCompra);
		panelMedio.add(addProduct);
		panelMedio.add(deleteProduct);
		panelMedio.add(mostrarCarrito);
		panelBajo.add(devolucionProducto);
		panelBajo.add(consultarCompra);
		panelBajo.add(listarCompra);
		panelBajo.add(modificarCompra);
		panelInferior.add(back);
		
		buttonsPanel.add(panelSuperior, BorderLayout.PAGE_START);
		buttonsPanel.add(panelMedio, BorderLayout.PAGE_START);
		buttonsPanel.add(panelBajo, BorderLayout.PAGE_START);
		buttonsPanel.add(panelInferior, BorderLayout.PAGE_START);
		
		setContentPane(buttonsPanel);
		setPreferredSize(new Dimension(600, 300));
		setLocation(200, 200);
		pack();
		setVisible(true);
	}

	@Override
	public void actualizar(Context context) {	
		
		switch(context.getEvent()) {
		
		case Events.ERROR_ABRIR_COMPRA_CAF:
			JOptionPane.showMessageDialog(null, "Cliente o personal no existentes intentelo de nuevo", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		
		case Events.EXITO_ABRIR_COMPRA_CAF:
			JOptionPane.showMessageDialog(null, "Compra abierta con éxito", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			
			t_carrito = context.getData() == null ? new TCarrito() : (TCarrito) context.getData();
			break;
			
		case Events.ERROR_CONSULTAR_COMPRA_CAF:
			JOptionPane.showMessageDialog(null, "No existe esa compra", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
			
		case Events.EXITO_CERRAR_COMPRA_CAF:
			JOptionPane.showMessageDialog(null, "La compra se ha cerrado con éxito", "Success", 
					JOptionPane.INFORMATION_MESSAGE);
			t_carrito = null;
			break;
			
		case Events.ERROR_DEVOLUCION_PRODUCTO_CAF:
			JOptionPane.showMessageDialog(null, "Ha habido un error al devolver el producto", "Error", 
					JOptionPane.INFORMATION_MESSAGE);
			t_carrito = null;
			break;
			
		case Events.EXITO_DEVOLUCION_PRODUCTO_CAF:
			JOptionPane.showMessageDialog(null, "Se ha devuelto el producto con exito", "Success", 
					JOptionPane.INFORMATION_MESSAGE);
			t_carrito = null;
			break;
		
		case Events.ERROR_MODIFICAR_COMPRA_CAF:
			JOptionPane.showMessageDialog(null, "Ha habido un error al modificar la compra", "Error", 
					JOptionPane.INFORMATION_MESSAGE);
			t_carrito = null;
			break;
		case Events.EXITO_MODIFICAR_COMPRA_CAF:
			JOptionPane.showMessageDialog(null, "Se ha modificado la compra con exito", "Success", 
					JOptionPane.INFORMATION_MESSAGE);
			t_carrito = null;
			break;
		default:
			t_carrito = context.getData() == null ? null : (TCarrito) context.getData();
			break;
		}
	}
}

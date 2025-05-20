/**
 * 
 */
package Presentacion.Compra_Cafeteria.ConsultarCompra;

import javax.swing.JFrame;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import Presentacion.Observer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Negocio.Compra.TLineaCompra;
import Negocio.Compra.TMostrarCompra;
import Negocio.Compra_Cafeteria.TLineaCompraCafeteria;
import Negocio.Compra_Cafeteria.TMostrarCompraCafeteria;
import Negocio.Compra_Cafeteria.TOACompraCafeteria;
import Negocio.Mueble.TMueble;
import Negocio.Producto_Cafeteria.TProductoCafeteria;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public class GUIConsultarCompraCafeteria extends JFrame implements Serializable, Observer {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private DefaultTableModel productModel;
	
	
	
	public GUIConsultarCompraCafeteria()
	{
		super("CONSULTAR COMPRA CAFETERIA");
		initGUI();
	}

	
	@SuppressWarnings("serial")
	private void initGUI() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Controller.getInstance().handle(new Context(Events.COMPRA_CAF_VIEW, null));
					
				}catch(Exception f){
					f.printStackTrace();
				}finally {
					dispose();
				}
			}
		});
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel topPanel = new JPanel(new FlowLayout());
		JPanel midPanel= new JPanel();
		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
		JPanel bottomPanel = new JPanel(new FlowLayout());
		
		
		JLabel idCompra_text = new JLabel("Introduzca el id de Compra");
		
		JTextField compra = new JTextField();
		compra.setPreferredSize(new Dimension(100,30));
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(compra.getText().equals(""))
				{
					
					JOptionPane.showMessageDialog(null, "Introduzca un id de Compra", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					Context contexto = new Context(Events.RES_CONSULTAR_COMPRA_CAF_ID_OK_VIEW, Integer.valueOf(compra.getText()));
					Controller.getInstance().handle(contexto);
					dispose();
				}
					
					
				
			}
		});
		
		JButton cancel = new JButton("Cancelar");

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().handle(new Context(Events.COMPRA_CAF_VIEW, null));
					
				}catch(Exception f){
					f.printStackTrace();
				}finally {
					dispose();
				}
			}
		});
		
		tableModel = new DefaultTableModel(new String[] {"ID Compra","Fecha", "Nombre Personal", "NIF Personal" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		JTable tablaCompra = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(tablaCompra);
		
		productModel = new DefaultTableModel(new String[] {"Nombre Producto","ID Producto","Unidades","Precio" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		JTable tablaProductos = new JTable(productModel);
		JScrollPane productPane = new JScrollPane(tablaProductos);
		
		
		topPanel.add(idCompra_text);
		topPanel.add(compra);
		topPanel.add(okButton);
		
		midPanel.add(scrollPane);
		JLabel products = new JLabel("Productos");
		products.setAlignmentX(CENTER_ALIGNMENT);
		midPanel.add(products);
		midPanel.add(productPane);
		
		bottomPanel.add(cancel);
		
		mainPanel.add(topPanel,BorderLayout.NORTH);
		mainPanel.add(midPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		setContentPane(mainPanel);
		setPreferredSize(new Dimension(700, 600));
		setLocation(200, 200);
		pack();
		setVisible(true);
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public void actualizar(Context context) {
		Object[] contextData;
		try{
			contextData = (Object[]) context.getData();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ID inválido o no asociado a ninguna compra", "Error", JOptionPane.ERROR_MESSAGE);
			return;	
		}
		TOACompraCafeteria compra = null;
		try { 
			compra = (TOACompraCafeteria) contextData[0];
		}
		catch (Exception e) {
			// No se ha introducido ningun id anteriormente
			return;
		};
		Set<TProductoCafeteria> listaProductos = (Set<TProductoCafeteria>) contextData[1];
		
		if(compra.getPersonal() != null){
			Object[] data = new Object[6];
			data[0] = compra.getCompra().getId();
			data[1] = compra.getCompra().getFecha();
			data[2] = compra.getPersonal().getNombre();
			data[3] = compra.getPersonal().getNif();
			tableModel.addRow(data);
			tableModel.fireTableDataChanged();
			
			
			
			for(TLineaCompraCafeteria a: compra.getLineas())
			{
				Object[] productData = new Object[4];
				productData[0] = getNombreProducto(a.getIdProducto(), listaProductos);
				productData[1] = a.getIdProducto();
				productData[2] = a.getUnidades();
				productData[3] = a.getPrecio();
				productModel.addRow(productData);
			}
			productModel.fireTableDataChanged();
		
		}
		
	}
	
	private String getNombreProducto(Integer id, Set<TProductoCafeteria> listaProductos) {
		for (TProductoCafeteria producto : listaProductos) {
			if (producto.getId()== id) return producto.getNombre();
		}
		return null;
	}
}
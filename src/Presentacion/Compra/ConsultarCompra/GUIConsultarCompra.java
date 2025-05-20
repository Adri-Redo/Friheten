/**
 * 
 */
package Presentacion.Compra.ConsultarCompra;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Negocio.Compra.TLineaCompra;
import Negocio.Compra.TMostrarCompra;
import Negocio.Mueble.TMueble;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;


public class GUIConsultarCompra extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private DefaultTableModel productModel;
	
	
	
	public GUIConsultarCompra()
	{
		super("Consultar Compra");
		initGUI();
	}

	
	@SuppressWarnings("serial")
	private void initGUI() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Controller.getInstance().handle(new Context(Events.COMPRA_VIEW, null));
					
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
					Context contexto = new Context(Events.RES_CONSULTAR_COMPRA_ID_OK_VIEW, Integer.valueOf(compra.getText()));
					Controller.getInstance().handle(contexto);
					dispose();
				}
					
					
				
			}
		});
		
		JButton cancel = new JButton("Cancelar");

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		tableModel = new DefaultTableModel(new String[] {"idCompra","Fecha","Usuario Cliente","Correo Cliente", 
				"Nombre Personal", "NIF Personal" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		JTable tablaCompra = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(tablaCompra);
		
		productModel = new DefaultTableModel(new String[] {"Nombre","idMueble","Unidades","Precio" }, 0) {
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
		Object[] contextData = (Object[]) context.getData();
		TMostrarCompra compra = null;
		try { 
			compra = (TMostrarCompra) contextData[0];
		}
		catch (Exception e) {
			// No se ha introducido ningun id anteriormente
			return;
		};
		List<TMueble> listaMuebles = (List<TMueble>) contextData[1];
		
		if(compra.getPersonal() != null && compra.getCliente().getActivo() != null){
			Object[] data = new Object[6];
			data[0] = compra.getCompra().getId();
			data[1] = compra.getCompra().getFecha();
			data[2] = compra.getCliente().getUsuario();
			data[3] = compra.getCliente().getCorreo();
			data[4] = compra.getPersonal().getNombre();
			data[5] = compra.getPersonal().getNif();
			tableModel.addRow(data);
			tableModel.fireTableDataChanged();
			
			
			
			for(TLineaCompra a: compra.getMuebles())
			{
				Object[] productData = new Object[4];
				productData[0] = getNombreMueble(a.getIdMueble(), listaMuebles);
				productData[1] = a.getIdMueble();
				productData[2] = a.getUnidades();
				productData[3] = a.getPrecio();
				productModel.addRow(productData);
			}
			productModel.fireTableDataChanged();
		
		}
		
	}
	
	private String getNombreMueble(Integer id, List<TMueble> listaMuebles) {
		for (TMueble mueble : listaMuebles) {
			if (mueble.getId()== id) return mueble.getNombre();
		}
		return null;
	}
}
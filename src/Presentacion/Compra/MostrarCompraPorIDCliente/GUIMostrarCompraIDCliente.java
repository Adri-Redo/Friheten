/**
 * 
 */
package Presentacion.Compra.MostrarCompraPorIDCliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Negocio.Compra.TLineaCompra;
import Negocio.Compra.TMostrarCompra;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;


@SuppressWarnings("serial")
public class GUIMostrarCompraIDCliente extends JFrame implements Observer{
	
	
	private DefaultTableModel tableModel;
	private DefaultTableModel productModel;
	@SuppressWarnings("rawtypes")
	private JComboBox seleccionCompra;
	private List<TMostrarCompra> compras;
	
	public GUIMostrarCompraIDCliente () {
		super("Mostrar Compra por id del Cliente");
		
		initGUI();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		
		
		
		JLabel texto = new JLabel("Introduzca el ID del cliente");
		JTextField clientInput = new JTextField();
		clientInput.setPreferredSize(new Dimension(100,20));
		
		JButton introducirCliente = new JButton("Introducir Cliente");
		
		introducirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(introducirCliente.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Introduzca un id de Cliente", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					Context contexto = new Context(Events.RES_MOSTRAR_COMPRA_POR_ID_CLIENTE_OK_VIEW
							,Integer.valueOf(clientInput.getText()));
					Controller.getInstance().handle(contexto);
					dispose();
				}
					
				
			}
		});
		
		seleccionCompra = new JComboBox(new DefaultComboBoxModel<String>());
		seleccionCompra.setBounds(50, 50, 150, 30);
		
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTablas((Integer) seleccionCompra.getSelectedItem());
					
				
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
		
		topPanel.add(texto);
		topPanel.add(clientInput);
		topPanel.add(introducirCliente);
		topPanel.add(seleccionCompra);
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
	private void actualizarComboBox()
	{
		for(TMostrarCompra a: compras)
		{
			seleccionCompra.addItem(a.getCompra().getId());
		}
	}
	
	private void actualizarTablas(Integer selectedItem) {
		boolean encontrado = false;
		tableModel.setRowCount(0);
		productModel.setRowCount(0);
		Iterator<TMostrarCompra> it = compras.iterator();
		while(it.hasNext() && !encontrado)
		{
			TMostrarCompra compra = it.next();
			if(compra.getCompra().getId() == selectedItem)
			{
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
//					productData[0] = a.getNombreMueble();
					productData[0] = "?";
					productData[1] = a.getIdMueble();
					productData[2] = a.getUnidades();
					productData[3] = a.getPrecio();
					productModel.addRow(productData);
				}
				productModel.fireTableDataChanged();
				
				encontrado = true;
			}
			
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actualizar(Context context) {
		compras = (List<TMostrarCompra>) context.getData();
		actualizarComboBox();
		
	}
}
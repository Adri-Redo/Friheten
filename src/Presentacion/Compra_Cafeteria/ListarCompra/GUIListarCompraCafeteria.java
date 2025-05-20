package Presentacion.Compra_Cafeteria.ListarCompra;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Negocio.Compra_Cafeteria.TCompraCafeteria;
import Negocio.Compra_Cafeteria.TLineaCompraCafeteria;
import Negocio.Compra_Cafeteria.TMostrarCompraCafeteria;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;


public class GUIListarCompraCafeteria extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	//private DefaultTableModel productModel;
	//@SuppressWarnings("rawtypes")
	//private static JComboBox selectCompra;
	private List<TCompraCafeteria> compras;
	
	public GUIListarCompraCafeteria()
	{
		super("Listar Compra Cafeteria");
		initGUI();
	}
	
	@SuppressWarnings({ "serial", "unchecked", "rawtypes" })
	private void initGUI() {
		this.setMinimumSize(new Dimension(600, 600));
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
		
		
		//selectCompra = new JComboBox(new DefaultComboBoxModel<String>());
		//selectCompra.setBounds(50, 50, 150, 30);
		
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTablas();
			}
		});
		
		JButton volver = new JButton("Volver");
		volver.addActionListener((a) ->{
			try {
				Controller.getInstance().handle(new Context(Events.COMPRA_CAF_VIEW, null));
				
			}catch(Exception f){
				f.printStackTrace();
			}finally {
				dispose();
			}
		});
		
		tableModel = new DefaultTableModel(new String[] {"ID Compra","Fecha", "ID Personal"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		JTable tablaCompra = new JTable(tableModel);
		tablaCompra.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(tablaCompra);
		
		/*productModel = new DefaultTableModel(new String[] {"Nombre Producto","ID Producto","Unidades","Precio" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};*/
		//JTable tablaProductos = new JTable(productModel);
		//JScrollPane productPane = new JScrollPane(tablaProductos);
		//JLabel idCompra = new JLabel("ID Compra: ");
		
		//topPanel.add(idCompra);
		//topPanel.add(selectCompra);
		//topPanel.add(okButton);
		
		midPanel.add(scrollPane);
		//JLabel products = new JLabel("Productos");
		//products.setAlignmentX(CENTER_ALIGNMENT);
		//midPanel.add(products);
		//midPanel.add(productPane);
		
		
		bottomPanel.add(volver);
		//bottomPanel.add(cancel);
		
		mainPanel.add(topPanel,BorderLayout.NORTH);
		mainPanel.add(midPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		setContentPane(mainPanel);
		setPreferredSize(new Dimension(700, 600));
		setLocation(200, 200);
		pack();
		setVisible(true);
		
	}

	private void actualizarTablas() {
		//boolean encontrado = false;
		tableModel.setNumRows(0);
		//productModel.setNumRows(0);
		Iterator<TCompraCafeteria> it = compras.iterator();
		while(it.hasNext())
		{
			TCompraCafeteria compra = it.next();
			
			//if(compra.getId() == selectedItem) {
				Object[] data = new Object[3];
				data[0] = compra.getId();
				data[1] = compra.getFecha();
				data[2] = compra.getIdPersonal();
				tableModel.addRow(data);
				tableModel.fireTableDataChanged();
				
				/*for(TLineaCompraCafeteria a: compra.get)
				{
					Object[] productData = new Object[4];
					productData[0] = a.getNombre();
					productData[1] = a.getIdProducto();
					productData[2] = a.getUnidades();
					productData[3] = a.getPrecio();
					productModel.addRow(productData);
				}
				productModel.fireTableDataChanged();*/
				
				//encontrado = true;
			//}
				
			
			
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	private void actualizarComboBox()
	{
		for(TCompraCafeteria a: compras)
		{
			//selectCompra.addItem(a.getId());
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actualizar(Context context) {
		compras = (List<TCompraCafeteria>) context.getData();
		actualizarTablas();
		//actualizarComboBox();
		
	}
}
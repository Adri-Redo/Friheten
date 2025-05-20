/**
 * 
 */
package Presentacion.Compra_Cafeteria.MostrarCarrito;

import javax.swing.JFrame;
import Presentacion.Observer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Negocio.Compra.TCarrito;
import Negocio.Compra.TLineaCarrito;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public class GUIMostrarCarritoCafeteria extends JFrame implements Observer, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable mostrarCarrito;
	private DefaultTableModel modelo;
	private static TCarrito carrito;

	public GUIMostrarCarritoCafeteria() {
		super("MOSTRAR CARRITO");

		initGUI();
	}

	private void initGUI() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Controller.getInstance().handle(new Context(Events.COMPRA_CAF_VIEW, carrito));
					
				}catch(Exception f){
					f.printStackTrace();
				}finally {
					dispose();
				}
			}
		});
		JLabel titulo = new JLabel("Mostrar Carrito Cafeteria");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		modelo = new DefaultTableModel();
		mostrarCarrito = new JTable(modelo);
		prepareCols();
		
		JScrollPane productPane = new JScrollPane(mostrarCarrito);
		
		mainPanel.add(titulo);
		mainPanel.add(productPane);
		

		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setLocation(200, 200);
		this.setSize(500, 600);

	}

	private void prepareCols() {
		modelo.setColumnCount(0);
		modelo.addColumn("Producto");
		modelo.addColumn("id");
		modelo.addColumn("Numero Productos");
		modelo.fireTableDataChanged();

	}


	private void actualizarTabla() {
		modelo.setRowCount(0);

		Set<TLineaCarrito> lineasCarrito = carrito.getLineasCarrito();
		if (lineasCarrito != null) {
			for (TLineaCarrito lineaCarrito : lineasCarrito) {
				Object[] fila = { lineaCarrito.getNombre(),lineaCarrito.getIdProducto(), lineaCarrito.getUnidades() };
				modelo.addRow(fila);
			}
		}
		modelo.fireTableDataChanged();
	}

	@Override
	public void actualizar(Context context) {
		carrito = (TCarrito) context.getData();
		actualizarTabla();
		
	}
}
/**
 * 
 */
package Presentacion.Compra_Cafeteria.QuitarProducto;

import javax.swing.JFrame;
import Presentacion.Observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public class GUIQuitarProductoCafeteria extends JFrame implements Observer, Serializable {
private static final long serialVersionUID = 1L;
	
	private JTable mostrarCarrito;
	private DefaultTableModel modelo;
	private JComboBox<String> seleccion;
	TCarrito carrito;
	
	public GUIQuitarProductoCafeteria()
	{
		super("QUITAR PRODUCTO CAFETERIA");
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
		JLabel titulo = new JLabel("Mostrar Carrito");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		modelo = new DefaultTableModel();
		mostrarCarrito = new JTable(modelo);
		mostrarCarrito.getTableHeader().setReorderingAllowed(false);
		prepareCols();
		
		seleccion = new JComboBox<>(new DefaultComboBoxModel<String>());
		
		JButton accept = new JButton("Aceptar");
		JButton cancel = new JButton("Cancelar");
		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] o = new Object[2];
				o[0] = carrito;
				o[1] = carrito.get_LineaCarrito_por_nombre(seleccion.getSelectedItem().toString());
				Context contexto = new Context(Events.RES_QUITAR_PRODUCTO_CAF_OK_VIEW, o);
				Controller.getInstance().handle(contexto);
				dispose();
				
			}
		});

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Context contexto = new Context(Events.RES_QUITAR_PRODUCTO_CAF_KO_VIEW, carrito);
				Controller.getInstance().handle(contexto);
				dispose();
			}
		});
		
		JScrollPane productPane = new JScrollPane(mostrarCarrito);
		JPanel aceptarCancelar = new JPanel();
		aceptarCancelar.add(accept);
		aceptarCancelar.add(cancel);
				
		mainPanel.add(titulo);
		mainPanel.add(productPane);
		mainPanel.add(seleccion);
		mainPanel.add(aceptarCancelar);
		
		

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
		modelo.addColumn("Num_unidades");
		modelo.fireTableDataChanged();

	}


	private void actualizarTabla(TCarrito carrito) {
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
	
	private void actualizarComboBox(TCarrito datos)
	{
		for(TLineaCarrito a : datos.getLineasCarrito())
		{
			seleccion.addItem(a.getNombre());
		}
	}


	@Override
	public void actualizar(Context context) {
		carrito = (TCarrito) context.getData();
		actualizarTabla((TCarrito) context.getData());
		actualizarComboBox((TCarrito) context.getData());
		
	}
}
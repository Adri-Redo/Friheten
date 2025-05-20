/**
 * 
 */
package Presentacion.Compra_Cafeteria.AnyadirProducto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Negocio.Compra.TCarrito;
import Negocio.Producto_Cafeteria.TProductoCafeteria;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public class GUIAddProductCafeteria extends JFrame implements Observer, Serializable {
	private static final long serialVersionUID = 1L;


	private static TProductoCafeteria productoSeleccionado;
	private int unidadesSeleccionadas;
	private static JComboBox<String> seleccionProducto;
	private static JComboBox<Integer> seleccionNum;
	private static DefaultComboBoxModel<String> producto_model;
	private static DefaultComboBoxModel<Integer> nums_model;

	private static Map<String, TProductoCafeteria> productos;
	private TCarrito carrito;

	public GUIAddProductCafeteria() {
		super("ANYADIR PRODUCTO CAFETERIA");
		productos = new HashMap<>();
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
		
		this.setMinimumSize(new Dimension(600, 600));
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel topPanel = new JPanel(new GridLayout());
		JPanel midPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JLabel titulo = new JLabel("Seleccione Producto a anyadir");
		topPanel.add(titulo);

		producto_model = new DefaultComboBoxModel<String>();
		nums_model = new DefaultComboBoxModel<Integer>();
		
		
		seleccionProducto = new JComboBox<>(producto_model);
		seleccionProducto.setBounds(50, 50, 150, 30);

		seleccionProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productoSeleccionado = productos.get((String) seleccionProducto.getSelectedItem());
				actualizarNumComboBox();
			}
		});
		
		topPanel.add(seleccionProducto);
		
		
		seleccionNum = new JComboBox<>(nums_model);
		seleccionNum.setBounds(50, 50, 150, 30);

		seleccionNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(seleccionNum.getSelectedItem() != null)
					unidadesSeleccionadas = (int) seleccionNum.getSelectedItem();

			}
		});

		

		JButton accept = new JButton("Aceptar");
		JButton cancel = new JButton("Cancelar");

		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (productoSeleccionado == null) {
					Context contexto = new Context();
					JOptionPane.showMessageDialog(null, "Error al añadir producto nulo.", "Error", JOptionPane.ERROR_MESSAGE);
					contexto.setData(carrito);
					contexto.setEvent(Events.RES_ANYADIR_PRODUCTO_CAF_KO_VIEW);
					Controller.getInstance().handle(contexto);
					dispose();
					return;
				}
				Object[] o = new Object[4];
				o[0] = carrito;
				o[1] = productoSeleccionado.getId();
				o[2] = unidadesSeleccionadas;
				o[3] = productoSeleccionado.getNombre();
				Context contexto = new Context(Events.RES_ANYADIR_PRODUCTO_CAF_OK_VIEW, o);
				Controller.getInstance().handle(contexto);
				dispose();
			}
		});

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Context contexto = new Context(Events.RES_ANYADIR_PRODUCTO_CAF_KO_VIEW, carrito);
				Controller.getInstance().handle(contexto);

				dispose();
			}
		});

		topPanel.add(seleccionNum);
		bottomPanel.add(accept);
		bottomPanel.add(cancel);

		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(midPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		setContentPane(mainPanel);
		setPreferredSize(new Dimension(600, 200));
		setLocation(300, 300);
		pack();
		setVisible(true);
	}

	
	public void actualizarNumComboBox()
	{
		if(productoSeleccionado != null)
		{
			seleccionNum.removeAllItems();
			int num = productoSeleccionado.getStock();
			for(int i = 0; i < num; i++)
			{
				seleccionNum.addItem(i);
			}
		}
		
		
		
	}

	@Override
	public void actualizar(Context context) {
		Object[] _datos = (Object[]) context.getData();
		carrito = (TCarrito) _datos[0];
		@SuppressWarnings("unchecked")
		HashSet<TProductoCafeteria> prods = (HashSet<TProductoCafeteria>) _datos[1];
		for (TProductoCafeteria p : prods) {
			productos.put(p.getNombre(), p);
		}
		
		for (Entry<String, TProductoCafeteria> p : productos.entrySet())
			seleccionProducto.addItem(p.getKey());
		
		
	}

}
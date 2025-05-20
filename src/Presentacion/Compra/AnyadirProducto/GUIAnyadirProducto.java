/**
 * 
 */
package Presentacion.Compra.AnyadirProducto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.Compra.TCarrito;
import Negocio.Mueble.TMueble;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIAnyadirProducto extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;


	private static TMueble muebleSeleccionado;
	private int unidadesSeleccionadas;
	private static JComboBox<String> seleccionMueble;
	private static JComboBox<Integer> seleccionNum;
	private static DefaultComboBoxModel<String> muebles_model;
	private static DefaultComboBoxModel<Integer> nums_model;

	private static Map<String, TMueble> productos;
	private TCarrito carrito;

	public GUIAnyadirProducto() {
		super("ANYADIR PRODUCTO");
		productos = new HashMap<>();
		initGUI();

	}

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
		
		JPanel topPanel = new JPanel(new GridLayout());
		JPanel midPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JLabel titulo = new JLabel("Seleccione Mueble a anyadir");
		topPanel.add(titulo);

		muebles_model = new DefaultComboBoxModel<String>();
		nums_model = new DefaultComboBoxModel<Integer>();
		
		
		seleccionMueble = new JComboBox<>(muebles_model);
		seleccionMueble.setBounds(50, 50, 150, 30);

		seleccionMueble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				muebleSeleccionado = productos.get((String) seleccionMueble.getSelectedItem());
				actualizarNumComboBox();
			}
		});
		
		topPanel.add(seleccionMueble);
		
		
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
				Object[] o = new Object[4];
				o[0] = carrito;
				o[1] = unidadesSeleccionadas;
				o[2] = muebleSeleccionado.getId();
				o[3] = muebleSeleccionado.getNombre();
				Context contexto = new Context(Events.RES_ANYADIR_PRODUCTO_OK_VIEW, o);
				Controller.getInstance().handle(contexto);
				dispose();
			}
		});

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Context contexto = new Context(Events.RES_ANYADIR_PRODUCTO_KO_VIEW, (Object) carrito);
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
		if(muebleSeleccionado != null)
		{
			seleccionNum.removeAllItems();
			int num = muebleSeleccionado.getStock();
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
		List<TMueble> muebles = (List<TMueble>) _datos[1];
		for (TMueble m : muebles) {
			productos.put(m.getNombre(), m);
		}
		
		for (Entry<String, TMueble> m : productos.entrySet())
			seleccionMueble.addItem(m.getKey());
		
		
	}
}
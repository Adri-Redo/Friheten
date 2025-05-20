package Presentacion.Compra.MostrarCarrito;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

import Negocio.Compra.TCarrito;

import Negocio.Compra.TLineaCarrito;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;


public class GUIMostrarCarrito extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable mostrarCarrito;
	private DefaultTableModel modelo;

	public GUIMostrarCarrito() {
		super("MOSTRAR CARRITO");

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
		JLabel titulo = new JLabel("Mostrar Carrito");
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
		modelo.addColumn("Mueble");
		modelo.addColumn("id");
		modelo.addColumn("Num_muebles");
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

	@Override
	public void actualizar(Context context) {
		actualizarTabla((TCarrito) context.getData());
		
	}

}
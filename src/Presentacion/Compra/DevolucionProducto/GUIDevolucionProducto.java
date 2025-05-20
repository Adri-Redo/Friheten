
package Presentacion.Compra.DevolucionProducto;

import javax.swing.JFrame;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Negocio.Compra.TMostrarCompra;
import Negocio.Mueble.TMueble;
import Negocio.Compra.TLineaCompra;

import javax.swing.JTextField;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Set;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;


import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Negocio.Compra.TCompra;

import javax.swing.JPanel;


public class GUIDevolucionProducto extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	
	private TCompra compra;

	private static JComboBox<String> seleccionMueble;
	
	private static DefaultComboBoxModel<String> muebles_modelo;

	public GUIDevolucionProducto(){
		super("DEVOLUCION PRODUCTO");
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
		
		JPanel topPanel = new JPanel(new FlowLayout());
		
		JPanel botPanel = new JPanel(new FlowLayout());
		
		JLabel idCompra = new JLabel("Introduzca ID compra: ");
		JLabel product = new JLabel("Introduzca producto a devolver");

		JTextField textoIdCompra = new JTextField();
		textoIdCompra.setPreferredSize(new Dimension(100,30));
		
		
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textoIdCompra.getText().equals(""))
				{
					
					JOptionPane.showMessageDialog(null, "Introduzca un ID de Compra", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					Context contexto = new Context(Events.RES_DEVOLUCION_PRODUCTO_ID_OK_VIEW, Integer.valueOf(textoIdCompra.getText()));
					Controller.getInstance().handle(contexto);
					dispose();
				}
					
					
				
			}
		});
		
		muebles_modelo = new DefaultComboBoxModel<>();
		seleccionMueble = new JComboBox<>(muebles_modelo);
		seleccionMueble.setBounds(50, 50, 150, 30);
		
		
		
		JButton accept = new JButton("Aceptar");
		JButton cancel = new JButton("Cancelar");
		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(seleccionMueble.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Introduzca nuevamente un ID de Compra", "Error",
							JOptionPane.ERROR_MESSAGE);
				}else {
					Object[] o = new Object[2];
					o[0] = compra;
					o[1] = seleccionMueble.getSelectedItem();
					Context contexto = new Context(Events.RES_DEVOLUCION_PRODUCTO_OK_VIEW, o);
					Controller.getInstance().handle(contexto);
					dispose();
				}
					
				
				
			}
		});

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		
		topPanel.add(idCompra);
		topPanel.add(textoIdCompra);
		topPanel.add(okButton);
		topPanel.add(product);
		topPanel.add(seleccionMueble);
		
		
		botPanel.add(accept);
		botPanel.add(cancel);
		
		
		mainPanel.add(topPanel);
		mainPanel.add(botPanel);
		
		setContentPane(mainPanel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		pack();
		setLocation(200, 200);
		setSize(450, 200);
		setVisible(true);
		
	
		
	}


	@Override
	public void actualizar(Context context) {
		if (context.getData() == null) return;
		Object[] data = (Object[]) context.getData();
		
		TMostrarCompra compras = (TMostrarCompra) data[0];
		@SuppressWarnings("unchecked")
		List<TMueble> listaMuebles = (List<TMueble>) data[1];
		
		compra = compras.getCompra();

		Set<TLineaCompra> muebles = compras.getMuebles();
		muebles_modelo.removeAllElements();
		
		for(TLineaCompra mueble: muebles) {
			seleccionMueble.addItem(getMuebleName(mueble.getIdMueble(), listaMuebles));
		}
		
	}
	
	private String getMuebleName(Integer id, List<TMueble> listaMuebles) {
		for (TMueble mueble : listaMuebles) {
			if (mueble.getId()== id) return mueble.getNombre();
		}
		return null;
	}
	
	
}
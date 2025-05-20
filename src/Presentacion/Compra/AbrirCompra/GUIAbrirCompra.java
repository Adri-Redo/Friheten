package Presentacion.Compra.AbrirCompra;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Compra.TCompra;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;


public class GUIAbrirCompra extends JFrame implements Observer {
	
	 
	private static final long serialVersionUID = 1L;
	private TCompra t_compra;

	public GUIAbrirCompra() {
		super("ABRIR COMPRA");
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
		
		
		JPanel centerPanel = new JPanel();
		
		JLabel idPersonal_label = new JLabel("ID del Personal");
		JLabel idCliente = new JLabel("ID del Cliente");
		
		JTextField idPersonal_textfield = new JTextField();
		JTextField idCliente_textfield = new JTextField();
		idCliente_textfield.setPreferredSize(new Dimension(100,20));
		idPersonal_textfield.setPreferredSize(idCliente_textfield.getPreferredSize());
		
		centerPanel.add(idPersonal_label);
		centerPanel.add(idPersonal_textfield);
		centerPanel.add(idCliente);
		centerPanel.add(idCliente_textfield);
		
		JPanel bottomPanel = new JPanel();
		JButton cancel = new JButton("Cancelar");
		JButton accept = new JButton("Aceptar");
		accept.addActionListener((e) -> {
			Integer id_Cliente = Integer.valueOf(idCliente_textfield.getText());
			Integer id_Personal = Integer.valueOf(idPersonal_textfield.getText());
			t_compra = new TCompra();
			t_compra.setIdCliente(id_Cliente);
			t_compra.setIdPersonal(id_Personal);
			
			Context contexto = new Context(Events.RES_ABRIR_COMPRA_OK_VIEW, t_compra);
			Controller.getInstance().handle(contexto);
			dispose();
		});
		cancel.addActionListener((e) -> {
			Context contexto = new Context(Events.RES_ABRIR_COMPRA_KO_VIEW, t_compra);
			Controller.getInstance().handle(contexto);
			dispose();
		});
		bottomPanel.add(accept);
		bottomPanel.add(cancel);	
		
		//mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.PAGE_START);
		mainPanel.add(bottomPanel, BorderLayout.PAGE_START);
		
		setContentPane(mainPanel);
		setPreferredSize(new Dimension(450, 200));
		setLocation(200, 200);
		pack();
		setVisible(true);
		
		
		
	}
	

	@Override
	public void actualizar(Context context) {
		//t_carrito = (TCarrito) context.getData();
		
	}
}

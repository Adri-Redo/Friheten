package Presentacion.Compra.CerrarCompra;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Negocio.Compra.TCarrito;
import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;


public class GUICerrarCompra extends JFrame implements Observer {
	

	private static final long serialVersionUID = 1L;
	
	public GUICerrarCompra() {
		super("CERRAR COMPRA");
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
		
		setContentPane(mainPanel);
		pack();
		setVisible(true);
	}


	@Override
	public void actualizar(Context context) {
			//
	}
}
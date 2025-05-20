/**
 * 
 */
package Presentacion.Compra_Cafeteria.CerrarCompra;

import javax.swing.JFrame;
import Presentacion.Observer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
public class GUICerrarCompra extends JFrame implements Observer, Serializable {
private static final long serialVersionUID = 1L;
	
	public GUICerrarCompra() {
		super("CERRAR COMPRA CAFETERIA");
		initGUI();
	}
	
	private void initGUI() {
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
		
		setContentPane(mainPanel);
		pack();
		setVisible(true);
	}


	@Override
	public void actualizar(Context context) {
			//
	}
}
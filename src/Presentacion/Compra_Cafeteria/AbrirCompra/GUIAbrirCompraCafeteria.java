/**
 * 
 */
package Presentacion.Compra_Cafeteria.AbrirCompra;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Compra_Cafeteria.TCompraCafeteria;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 * 
 * @author hugod
 * @generated "UML a JPA
 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public class GUIAbrirCompraCafeteria extends JFrame implements Observer, Serializable {

	private static final long serialVersionUID = 1L;
	private TCompraCafeteria t_compra_cafeteria;

	public GUIAbrirCompraCafeteria() {
		super("ABRIR COMPRA CAFETERIA");
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
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		
		JPanel centerPanel = new JPanel();
		
		JLabel idPersonal_label = new JLabel("ID del Personal");
		
		JTextField idPersonal_textfield = new JTextField();
		idPersonal_textfield.setPreferredSize(new Dimension(50, 20));
		
		centerPanel.add(idPersonal_label);
		centerPanel.add(idPersonal_textfield);
		
		JPanel bottomPanel = new JPanel();
		JButton cancel = new JButton("Cancelar");
		JButton accept = new JButton("Aceptar");
		accept.addActionListener((e) -> {
			Integer id_Personal = Integer.valueOf(idPersonal_textfield.getText());
			t_compra_cafeteria = new TCompraCafeteria();
			t_compra_cafeteria.setIdPersonal(id_Personal);
			
			Context contexto = new Context(Events.RES_ABRIR_COMPRA_CAF_OK_VIEW, t_compra_cafeteria);
			Controller.getInstance().handle(contexto);
			dispose();
		});
		
		cancel.addActionListener((e) -> {
			Context contexto = new Context(Events.RES_ABRIR_COMPRA_CAF_KO_VIEW, t_compra_cafeteria);
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
		// TODO Auto-generated method stub
		
	}
}
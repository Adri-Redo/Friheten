/**
 * 
 */
package Presentacion.Turno_Cafeteria.BajaTurnoCafeteria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author usuario_local
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIBajaTurnoCafeteria extends JFrame implements Serializable, Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	
	public GUIBajaTurnoCafeteria() {
		super("BAJA TURNO");
		this.initGUI();
	}
	
	private void initGUI() {
		JPanel panelPrincipal = new JPanel();
		JPanel panelParaUsuario = new JPanel();
		
		JLabel IDCliente = new JLabel("ID del Turno: ");
		JTextField cuadroTextoID = new JTextField(15);
		cuadroTextoID.setEditable(true);
		cuadroTextoID.setToolTipText("ID Turno");

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Controller.getInstance().handle(new Context(Events.TURNO_KO_VIEW, null));
					
				}catch(Exception f){
					f.printStackTrace();
				}finally {
					dispose();
				}
			}
		}); 
		
		JButton ok = new JButton("OK");
		ok.setToolTipText("Aceptar");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if(cuadroTextoID.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "El id no puede estar en blanco.", "ERROR",JOptionPane.ERROR_MESSAGE);
						Controller.getInstance().handle(new Context(Events.TURNO_KO_VIEW, null));
					}
					else {
						Integer id = Integer.parseInt(cuadroTextoID.getText().trim());
						if(id <= 0) {
							JOptionPane.showMessageDialog(null, "El id introducido debe ser mayor que 0.", "ERROR",JOptionPane.ERROR_MESSAGE);
							Controller.getInstance().handle(new Context(Events.TURNO_KO_VIEW, null));
						}
						else 
							Controller.getInstance().handle(new Context(Events.BAJA_TURNO_OK_VIEW, id));
					}
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "El id introducido debe ser un numero.", "ERROR",JOptionPane.ERROR_MESSAGE);
					Controller.getInstance().handle(new Context(Events.TURNO_KO_VIEW, null));
				}
				dispose();
			}
		});
		
		JButton cancel = new JButton("CANCEL");
		cancel.setToolTipText("Cancelar");
		cancel.addActionListener((e)-> {
			Controller.getInstance().handle(new Context(Events.TURNO_KO_VIEW, null));
			dispose();
		});
		
		panelParaUsuario.add(IDCliente);
		panelParaUsuario.add(cuadroTextoID);
		panelParaUsuario.add(ok);
		panelParaUsuario.add(cancel);
	
		panelPrincipal.add(panelParaUsuario);
		
		this.setContentPane(panelPrincipal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200,200);	
		this.pack();
	}

	/** 
	* (non-Javadoc)
	* @see Observer#actualizar(Context context)
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void actualizar(Context context) {
		// No debe actualizar
	}
}
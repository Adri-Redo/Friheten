/**
 * 
 */
package Presentacion.Turno_Cafeteria.AltaTurnoCafeteria;

import Presentacion.Observer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import Negocio.Turno_Cafeteria.TTurnoCafeteria;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author usuario_local
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIAltaTurnoCafeteria extends JFrame implements Observer, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	
	private JButton ok;
	private JButton cancel;
	private TTurnoCafeteria tTurno;
	
	public GUIAltaTurnoCafeteria() {
		super("ALTA TURNO");
		this.initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel questionPanel = new JPanel();
		JPanel bottomPanel = new JPanel();

		JPanel title = new JPanel();
		JLabel titleLabel = new JLabel("Dar de alta un nuevo turno en la base de datos");
		title.add(titleLabel);
		mainPanel.add(title, BorderLayout.NORTH);

		JPanel pregunta_nombre = new JPanel();
		JLabel nomLabel = new JLabel("Nombre: ");
		JTextField nom = new JTextField(10);
		pregunta_nombre.add(nomLabel);
		pregunta_nombre.add(nom);

		JPanel pregunta_num_horas = new JPanel();
		JLabel numHLabel = new JLabel("Numero de horas: ");
		JTextField numH = new JTextField(10);
		pregunta_num_horas.add(numHLabel);
		pregunta_num_horas.add(numH);

		questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
		
		JPanel a = new JPanel();
		JPanel b = new JPanel();
		a.setPreferredSize(new Dimension(50, 40));
		b.setPreferredSize(new Dimension(50, 50));
		questionPanel.add(a);
		questionPanel.add(pregunta_nombre);
		questionPanel.add(pregunta_num_horas);
		questionPanel.add(b);
		
		mainPanel.add(questionPanel, BorderLayout.CENTER);
		
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
		
		cancel = new JButton("CANCEL");
		cancel.setToolTipText("Cancelar");
		cancel.addActionListener((e)->{
				Controller.getInstance().handle(new Context(Events.TURNO_KO_VIEW, null));
				dispose();
		});
		
		ok = new JButton("OK");
		ok.setToolTipText("Aceptar");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean valido = true;
				
				tTurno = new TTurnoCafeteria();
				
				String nombre = nom.getText().trim();
				String numHoras = numH.getText().trim();
				if(!nombre.matches("^[a-zA-Z0-9]*[a-zA-Z0-9]+[a-zA-Z0-9]*$")) {
					JOptionPane.showMessageDialog(null,
							"El nombre debe de contener unicamente letras o numeros y tener al menos un caracter",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					valido = false;
				}
				else
					tTurno.setNombreTurno(nombre);
				
				if(valido && !numHoras.matches("^(1[0-9]|2[0-4]|[1-9])$")) {
					JOptionPane.showMessageDialog(null,
							"Las horas debe de contener unicamente numeros entre el 1-24",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					valido = false;
				}
				else if(valido) {
					tTurno.setNumHoras(Integer.parseInt(numHoras));
					Controller.getInstance().handle(new Context(Events.ALTA_TURNO_OK_VIEW, tTurno));
				}
				else 
					Controller.getInstance().handle(new Context(Events.TURNO_KO_VIEW, null));
				
				dispose();
				
			}
		});
		bottomPanel.add(ok);
		bottomPanel.add(cancel);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		setContentPane(mainPanel);
		setPreferredSize(new Dimension(500, 500));
		setLocation(500, 500);
		pack();
		this.setLocationRelativeTo(null);
		setVisible(true);
	}

	/** 
	* (non-Javadoc)
	* @see Observer#actualizar(Context context)
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void actualizar(Context context) {
		//No actualiza
	}
}
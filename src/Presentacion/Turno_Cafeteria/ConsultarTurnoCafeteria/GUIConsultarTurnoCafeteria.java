/**
 * 
 */
package Presentacion.Turno_Cafeteria.ConsultarTurnoCafeteria;

import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

import java.awt.FlowLayout;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Turno_Cafeteria.TTurnoCafeteria;


/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author usuario_local
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIConsultarTurnoCafeteria extends JFrame implements Observer, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	JLabel id;
	JLabel nombre;
	JLabel numH;
	
	public GUIConsultarTurnoCafeteria(){
		super("CONSULTAR TURNO");
		this.initGUI();
	}
	
private void initGUI() { //No se muestra contrasenya por que no tendria logica
		
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 5, 5);
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		JPanel panelParaUsuario = new JPanel();
		JPanel panelParaMostrar = new JPanel();
		panelParaMostrar.setLayout(new BoxLayout(panelParaMostrar, BoxLayout.Y_AXIS));

		JPanel panelParaMostrarID = new JPanel();
		panelParaMostrarID.setLayout(layout);
		JPanel panelParaMostrarNombre = new JPanel();
		panelParaMostrarNombre.setLayout(layout);
		JPanel panelParaMostrarNumHoras = new JPanel();
		panelParaMostrarNumHoras.setLayout(layout);

		JLabel consultarTurno = new JLabel("Introduzca el ID del turno: ");
		JTextField cuadroTexto = new JTextField(20);
		cuadroTexto.setEditable(true);
		cuadroTexto.setToolTipText("ID del turno que quieres consultar.");

		JButton ok = new JButton("OK");
		ok.setToolTipText("Aceptar");
		ok.addActionListener((e)-> {
			boolean validar = true;

			String idTurno = cuadroTexto.getText().trim();
			int id = 0;
				try {
					id = Integer.parseInt(idTurno);
					if (id <= 0) {

						validar = false;
						JOptionPane.showMessageDialog(null, "El id introducido debe ser mayor que 0.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException m) {
						validar = false;
						JOptionPane.showMessageDialog(null,
								"El id introducido no debe contener letras ni caracteres especiales. Recuerda no dejar el campo vacio.",
								"ERROR", JOptionPane.ERROR_MESSAGE);
					}
				if (validar) {
					Controller.getInstance().handle(new Context(Events.CONSULTAR_TURNO_OK_VIEW, id));
				}
				else
					Controller.getInstance().handle(new Context(Events.TURNO_KO_VIEW, null));//TODO AGREGAR EL KO GENERICO AL CODIGO.
				dispose();

		});
		
		JButton cancel = new JButton("CANCEL");
		cancel.setToolTipText("Cancelar");
		cancel.addActionListener((e)->{
				Controller.getInstance().handle(new Context(Events.TURNO_KO_VIEW, null));
				dispose();
		});
		
		
		panelParaUsuario.add(consultarTurno);
		panelParaUsuario.add(cuadroTexto);
		panelParaUsuario.add(ok);
		panelParaUsuario.add(cancel);
		
		this.id = new JLabel("ID TURNO: ");
		this.nombre = new JLabel("NOMBRE TURNO: ");
		this.numH = new JLabel ("Nº HORAS TURNO: ");
		
		panelParaMostrarID.add(id);
		panelParaMostrarNombre.add(nombre);
		panelParaMostrarNumHoras.add(numH);

		panelParaMostrar.add(panelParaMostrarID);
		panelParaMostrar.add(panelParaMostrarNombre);
		panelParaMostrar.add(panelParaMostrarNumHoras);
		
		panelPrincipal.add(panelParaUsuario);
		panelPrincipal.add(panelParaMostrar);

		this.setContentPane(panelPrincipal);
		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		
	}
	/** 
	* (non-Javadoc)
	* @see Observer#actualizar(Context context)
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/

	@Override
	public void actualizar(Context context) {
		TTurnoCafeteria turno = (TTurnoCafeteria) context.getData();
		if(turno != null) {
			if(turno.getActivo()) {
				this.id.setText("ID TURNO: " + turno.getIdTurno());
				this.nombre.setText("NOMBRE TURNO: " + turno.getNombreTurno());
				this.numH.setText("Nº HORAS TURNO: " + turno.getNumHoras());
				
			} else {
				JOptionPane.showMessageDialog(null, "El turno con el id " + turno.getIdTurno() + " esta dado de baja.", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		this.pack();
	}
}
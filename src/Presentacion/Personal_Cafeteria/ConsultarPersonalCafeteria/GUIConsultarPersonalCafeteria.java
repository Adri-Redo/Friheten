/**
 * 
 */
package Presentacion.Personal_Cafeteria.ConsultarPersonalCafeteria;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Negocio.Personal_Cafeteria.TPersonalCafeteria;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public class GUIConsultarPersonalCafeteria extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	
	private JLabel id;
	private JLabel nombre;
	private JLabel apellidos;
	private JLabel nif;
	private JLabel salario;
	private JLabel turno;
	
	private JLabel cargo;
	private JLabel responsabilidades;
	private JLabel puesto;
	private JLabel bonificaciones;
	
	public GUIConsultarPersonalCafeteria() {
		super("CONSULTAR PERSONAL CAFETERIA");
		this.initGUI();
	}
	
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void initGUI() {
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 5, 5);
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel userPanel = new JPanel();
		JPanel viewPanel = new JPanel();
		viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
		
		//Paneles datos personal
		JPanel idPanel = new JPanel();
		idPanel.setLayout(layout);
		JPanel namePanel = new JPanel();
		namePanel.setLayout(layout);
		JPanel apellidosPanel = new JPanel();
		apellidosPanel.setLayout(layout);
		JPanel nifPanel = new JPanel();
		nifPanel.setLayout(layout);
		JPanel salarioPanel = new JPanel();
		salarioPanel.setLayout(layout);
		JPanel turnoPanel = new JPanel();
		turnoPanel.setLayout(layout);
		
		//JEFE
		JPanel cargoPanel = new JPanel();
		cargoPanel.setLayout(layout);
		JPanel responsabilidadesPanel = new JPanel();
		responsabilidadesPanel.setLayout(layout);
		//PERSONAL
		JPanel puestoPanel = new JPanel();
		puestoPanel.setLayout(layout);
		JPanel bonificacionesPanel = new JPanel();
		bonificacionesPanel.setLayout(layout);
		
		JLabel consultarPersonal = new JLabel("Introduzca el ID del personal: ");
		JTextField cuadroTexto = new JTextField(20);
		cuadroTexto.setEditable(true);
		cuadroTexto.setToolTipText("ID del personal que quieres consultar.");
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Controller.getInstance().handle(new Context(Events.PERSONALCAFETERIA_VIEW, null));
					
				}catch(Exception f){
					f.printStackTrace();
				}finally {
					dispose();
				}
			}
		}); 
		
		JButton consultarButton = new JButton("Consultar");
		consultarButton.setToolTipText("Consultar");
		consultarButton.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean validar = true;
				String idPersonal = cuadroTexto.getText().trim();
				Integer id = 0;
				
				try {
					id = Integer.parseInt(idPersonal);
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
				//cambiar evento
				if(validar) Controller.getInstance().handle(new Context(Events.CONSULTAR_PERSONALCAFETERIA_OK_VIEW,id));
				dispose();
			}
		});
			
		
		JButton ko = new JButton("CANCEL");
		ko.setToolTipText("Cancelar");
		ko.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.PERSONALCAFETERIA_VIEW, null));
			dispose();

		});
		
		userPanel.add(consultarPersonal);
		userPanel.add(cuadroTexto);
		userPanel.add(consultarButton);
		userPanel.add(ko);
		
		this.id = new JLabel("ID PERSONAL: ");
		this.nombre = new JLabel("NOMBRE: ");
		this.apellidos = new JLabel("APELLIDOS: ");
		this.nif = new JLabel("NIF: ");
		this.salario = new JLabel("SALARIO BASE: ");
		this.turno = new JLabel("ID TURNO: ");
		
		this.cargo = new JLabel("CARGO: ");
		this.responsabilidades = new JLabel("RESPONSABILIDADES: ");
		this.puesto = new JLabel("PUESTO: ");
		this.bonificaciones = new JLabel("BONIFICACIONES: ");

		idPanel.add(id);
		namePanel.add(nombre);
		apellidosPanel.add(apellidos);
		nifPanel.add(nif);
		salarioPanel.add(salario);
		turnoPanel.add(turno);
		cargoPanel.add(this.cargo);
		responsabilidadesPanel.add(this.responsabilidades);
		puestoPanel.add(puesto);
		bonificacionesPanel.add(bonificaciones);

		viewPanel.add(idPanel);
		viewPanel.add(namePanel);
		viewPanel.add(apellidosPanel);
		viewPanel.add(nifPanel);
		viewPanel.add(salarioPanel);
		viewPanel.add(turnoPanel);
		viewPanel.add(cargoPanel);
		viewPanel.add(responsabilidadesPanel);
		viewPanel.add(puestoPanel);
		viewPanel.add(bonificacionesPanel);

		mainPanel.add(userPanel);
		mainPanel.add(viewPanel);

		this.setContentPane(mainPanel);
		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
	}

	/** 
	* (non-Javadoc)
	* @see Observer#actualizar(Context context)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@Override
	public void actualizar(Context context) {
		if(context.getData() != null) {
			TPersonalCafeteria tpc = (TPersonalCafeteria) context.getData();
			
			if(tpc.getActivo() == null || !tpc.getActivo()) {
				JOptionPane.showMessageDialog(null, "El personal con dicho id no está activo en la base de datos", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
			this.id.setText("ID: " + tpc.getId());
			this.nombre.setText("NOMBRE: " + tpc.getNombre());
			this.apellidos.setText("APELLIDOS: " + tpc.getApellidos());
			this.nif.setText("NIF: " + tpc.getNif());
			this.salario.setText("SALARIO BASE: " + tpc.getSalarioBase());
			this.turno.setText("ID TURNO: " + tpc.getTurno());
			
			
			if(tpc.getIsJefe()) { //JEFE
				this.cargo.setText("CARGO: "+tpc.getCargo());
				this.responsabilidades.setText("RESPONSABILIDADES: "+tpc.getResponsabilidades());
				this.cargo.setVisible(true);
				this.responsabilidades.setVisible(true);
				this.puesto.setVisible(false);
				this.bonificaciones.setVisible(false);
				
			}else { //PERSONAL
				this.puesto.setText("PUESTO: "+tpc.getPuesto());
				this.bonificaciones.setText("BONIFICACIONES: "+tpc.getBonificaciones());
				this.puesto.setVisible(true);
				this.bonificaciones.setVisible(true);
				this.cargo.setVisible(false);
				this.responsabilidades.setVisible(false);
			}
			
			this.revalidate();
			this.repaint();
			}
		
		}
			
}

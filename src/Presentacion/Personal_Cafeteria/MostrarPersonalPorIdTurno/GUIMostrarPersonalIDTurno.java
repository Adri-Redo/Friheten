/**
 * 
 */
package Presentacion.Personal_Cafeteria.MostrarPersonalPorIdTurno;

import javax.swing.JFrame;
import Negocio.Personal_Cafeteria.TPersonalCafeteria;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public class GUIMostrarPersonalIDTurno extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel _tableModel;
	
	public GUIMostrarPersonalIDTurno() {
		super("MOSTRAR PERSONAL POR EL ID DEL TURNO");
		this.initGUI();
	}
	
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void initGUI() {
		JPanel panelPrincipal = new JPanel();
		JPanel panelParaUsuario = new JPanel();

		JLabel mostrarPersonal = new JLabel("Introduzca el ID del turno: ");
		JTextField nombreTexto = new JTextField(20);
		nombreTexto.setEditable(true);
		nombreTexto.setToolTipText("ID del turno del cual quieres mostrar su personal");

		JButton ok = new JButton("OK");
		ok.setToolTipText("Mostrar personal con ese ID de turno");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean validar = true;
				String idNave = nombreTexto.getText().trim();
				int id = 0;
				try {

					id = Integer.parseInt(idNave);
					if (id <= 0) {
						validar = false;

						JOptionPane.showMessageDialog(null, "El id del turno debe ser mayor que 0.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException m) {

					validar = false;
					JOptionPane.showMessageDialog(null,
							"El id del turno debe ser un numero entero mayor que 0, no puede contener letras", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

				if (validar) {
					Controller.getInstance().handle(new Context(Events.MOSTRAR_PERSONALCAFETERIA_PORID_TURNO_OK_VIEW, id));

				}

				dispose();
			}

		});

		JButton ko = new JButton("CANCEL");
		ko.setToolTipText("Cancelar");
		ko.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.MOSTRAR_PERSONALCAFETERIA_PORID_TURNO_KO_VIEW, null));
			dispose();
		});

		panelParaUsuario.add(mostrarPersonal);
		panelParaUsuario.add(nombreTexto);
		panelParaUsuario.add(ok);
		panelParaUsuario.add(ko);

		panelPrincipal.add(panelParaUsuario);

		this.setContentPane(panelPrincipal);
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
	@SuppressWarnings("unchecked")
	@Override
	public void actualizar(Context context) {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel textPanel = new JPanel();
		JPanel wagePanel = new JPanel();
		JPanel infoPanel = new JPanel();

		JLabel sumaSalario = new JLabel("TOTAL SALARIOS: ");
		wagePanel.add(sumaSalario);
		mainPanel.add(wagePanel);
		
		JLabel label = new JLabel("Listado de todas las personas de la Base de Datos");
		textPanel.add(label);
		mainPanel.add(textPanel);

		this._tableModel = new DefaultTableModel(new Object[] { "Id", "NIF", "Nombre", "Apellido", "Turno",
				"¿Es jefe?" , "Cargo","Responsabilidades","Puesto","Bonificaciones"},
				0);

		JTable table = new JTable(this._tableModel){
	        private static final long serialVersionUID = 1L;

	        @Override
	        public boolean isCellEditable(int row, int col) {
	            return false;
	        }
	    };

		// Desactivar el ajuste automático de las columnas
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// Modelo de las columnas de nuestra tabla
		TableColumnModel columnModel = table.getColumnModel();

		// Establecer el ancho preferido para cada columna (puedes ajustar estos valores
		// según tus necesidades)
		columnModel.getColumn(0).setPreferredWidth(50);// ID
		columnModel.getColumn(1).setPreferredWidth(100);// NIF
		columnModel.getColumn(2).setPreferredWidth(100);// NOMBRE
		columnModel.getColumn(3).setPreferredWidth(100);// APELLIDO
		columnModel.getColumn(4).setPreferredWidth(100);// TURNO
		columnModel.getColumn(5).setPreferredWidth(100);// JEFE
		columnModel.getColumn(6).setPreferredWidth(100);// CARGO
		columnModel.getColumn(7).setPreferredWidth(150);// RESPONSABILIDADES
		columnModel.getColumn(8).setPreferredWidth(100);// PUESTO
		columnModel.getColumn(9).setPreferredWidth(150);// BONIFICACIONES

		table.setPreferredScrollableViewportSize(new Dimension(600, 400));

		JScrollPane scroll = new JScrollPane(table);
		infoPanel.add(scroll);
		mainPanel.add(infoPanel);

		this._tableModel.setRowCount(0);
		List<TPersonalCafeteria> listaPersonal = new ArrayList<TPersonalCafeteria>();
		if(context.getData()!=null) {
			listaPersonal = (List<TPersonalCafeteria>) context.getData();
		}
		
		int totalSalarios = 0;
		
		for (TPersonalCafeteria tP : listaPersonal) {
			if (tP.getActivo()) {
				if (tP.getIsJefe()) {
					Object[] rowData = { tP.getId(), tP.getNif(), tP.getNombre(), tP.getApellidos(),tP.getTurno(), "SI",
							tP.getCargo(),tP.getResponsabilidades()," - "," - " };
					this._tableModel.addRow(rowData);
				} else {

					Object[] rowData = { tP.getId(), tP.getNif(), tP.getNombre(), tP.getApellidos(), tP.getTurno(),"NO",
							" - "," - ",tP.getPuesto(),tP.getBonificaciones() };
					this._tableModel.addRow(rowData);
				}
				totalSalarios += tP.getSalarioBase();
			}
			sumaSalario.setFont(new Font("SansSerif", Font.BOLD, 20));
			sumaSalario.setText("TOTAL SALARIOS DEL TURNO " + listaPersonal.get(0).getTurno() 
					+ ": " + totalSalarios + "€");
			setContentPane(mainPanel);
			setPreferredSize(new Dimension(800, 700));
			setLocation(500, 500);
			pack();
			this.setLocationRelativeTo(null);
			setVisible(true);
		}
		
	}
}
/**
 * 
 */
package Presentacion.Personal_Cafeteria.ListarPersonalCafeteria;

import javax.swing.JFrame;
import Presentacion.Observer;
import Presentacion.Context.Context;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Negocio.Personal_Cafeteria.TPersonalCafeteria;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public class GUIListarPersonalCafeteria extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel _tableModel;
	private List<TPersonalCafeteria> _listaPersonal;
	
	public GUIListarPersonalCafeteria() {
		super("LISTAR PERSONAL CAFETERIA");
		this._listaPersonal = new ArrayList<>();
		this.initGUI();
	}
	
	public void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel textPanel = new JPanel();
		JPanel infoPanel = new JPanel();

		JLabel label = new JLabel("Listado de todas las personas de la Base de Datos");
		textPanel.add(label);
		mainPanel.add(textPanel);

		this._tableModel = new DefaultTableModel(new Object[] { "Id", "NIF", "Nombre", "Apellidos","Turno", 
				"¿Es jefe?" , "Cargo","Responsabilidades","Puesto","Bonificaciones"},
				0);

		JTable table = new JTable(this._tableModel) {

			/**
			 * 
			 */
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

		table.setPreferredScrollableViewportSize(new Dimension(700, 400));

		JScrollPane scroll = new JScrollPane(table);
		infoPanel.add(scroll);
		mainPanel.add(infoPanel);

		setContentPane(mainPanel);
		setPreferredSize(new Dimension(800, 700));
		setLocation(500, 500);
		pack();
		this.setLocationRelativeTo(null);
		setVisible(true);
	}

	/** 
	* (non-Javadoc)
	* @see Observer#actualizar(Context context)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/

	@SuppressWarnings("unchecked")
	@Override
	public void actualizar(Context context) {
		this._tableModel.setRowCount(0);

		this._listaPersonal = new ArrayList<TPersonalCafeteria>((List<TPersonalCafeteria>) context.getData());
		if (!this._listaPersonal.isEmpty()) {
			for (TPersonalCafeteria tP : this._listaPersonal) {
				if (tP.getActivo()) {
					if (tP.getIsJefe()) {
						Object[] rowData = { tP.getId(), tP.getNif(), tP.getNombre(), tP.getApellidos(),tP.getTurno(),"SI",
								tP.getCargo(),tP.getResponsabilidades()," - "," - " };
						this._tableModel.addRow(rowData);
					} else {

						Object[] rowData = { tP.getId(), tP.getNif(), tP.getNombre(), tP.getApellidos(),tP.getTurno(), "NO",
								" - "," - ",tP.getPuesto(),tP.getBonificaciones() };
						this._tableModel.addRow(rowData);
					}
				}

			}

		} else
			JOptionPane.showMessageDialog(null, "No hay personas dadas de alta en la base de datos", "Info",
					JOptionPane.INFORMATION_MESSAGE);
	}
}
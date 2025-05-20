/**
 * 
 */
package Presentacion.Turno_Cafeteria.ListarTurnoCafeteria;

import Negocio.Turno_Cafeteria.TTurnoCafeteria;

import java.awt.Dimension;
import java.awt.Font;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Presentacion.Observer;
import Presentacion.Context.Context;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author usuario_local
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIListarTurnoCafeteria extends JFrame implements Observer, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	
	private DefaultTableModel _tableModel;
	private List<TTurnoCafeteria> listaTurnos;

	public GUIListarTurnoCafeteria() {
		super("LISTAR TURNOS");
		this.listaTurnos = new ArrayList<>();
		this.initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel textPanel = new JPanel();
		JPanel infoPanel = new JPanel();

		JLabel label = new JLabel("Listado de todos los clientes de la Base de Datos");
		textPanel.add(label);
		mainPanel.add(textPanel);

		this._tableModel = new DefaultTableModel(new Object[] { "Id", "Nombre", "Nº Horas"}, 0);

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
		Font fontNegrita = new Font("SansSerif", Font.BOLD, 12);
		table.setFont(fontNegrita);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(700, 700));
		infoPanel.add(scroll);
		mainPanel.add(infoPanel);

		setContentPane(mainPanel);
		setPreferredSize(new Dimension(750, 750));
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
	@SuppressWarnings("unchecked")
	public void actualizar(Context context) {
		this._tableModel.setRowCount(0);

		this.listaTurnos = new ArrayList<TTurnoCafeteria>((List<TTurnoCafeteria>) context.getData());
		for (TTurnoCafeteria tT : this.listaTurnos) {
			if (tT.getActivo()) {
					Object[] rowData = { tT.getIdTurno(), tT.getNombreTurno(), tT.getNumHoras()};
					this._tableModel.addRow(rowData);
			}
		}
	}
}
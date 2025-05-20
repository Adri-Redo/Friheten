package Presentacion.Personal.ListarPersonal;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Negocio.Personal.TEmpleado;
import Negocio.Personal.TJefe;
import Negocio.Personal.TPersonal;
import Presentacion.Observer;
import Presentacion.Context.Context;

public class GUIListarPersonal extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DefaultTableModel _tableModel;
	private List<TPersonal> _listaPersonal;

	public GUIListarPersonal() {
		super("LISTAR PERSONAL");
		this._listaPersonal = new ArrayList<>();
		this.initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel textPanel = new JPanel();
		JPanel textPanel2 = new JPanel();
		JPanel infoPanel = new JPanel();

		JLabel label = new JLabel("Listado de todas las personas de la Base de Datos");
		JLabel label2 = new JLabel("Si la columna 'Cargo' esta vacia es Jefe. Si no Empleado");
		textPanel.add(label);
		textPanel2.add(label2);
		mainPanel.add(textPanel);
		mainPanel.add(textPanel2);

		this._tableModel = new DefaultTableModel(new Object[] { "Id", "NIF", "Nombre", "Apellido", "IdNave", "Cargo" , "Nomina"},
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
		columnModel.getColumn(4).setPreferredWidth(50);// IDNAVE
		columnModel.getColumn(5).setPreferredWidth(200);// CARGO
		columnModel.getColumn(6).setPreferredWidth(100);// CARGO

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

	@SuppressWarnings("unchecked")
	@Override
	public void actualizar(Context context) {
		this._tableModel.setRowCount(0);

		this._listaPersonal = new ArrayList<TPersonal>((List<TPersonal>) context.getData());
		if (!this._listaPersonal.isEmpty()) {
			for (TPersonal tP : this._listaPersonal) {
				if (tP.getActivo()) {
					if (tP instanceof TEmpleado) {

						TEmpleado e = (TEmpleado) tP;
						Object[] rowData = { tP.getId(), tP.getNif(), tP.getNombre(), tP.getApellido(), tP.getIdNave(),
								e.getCargo(),tP.getNomina() };
						this._tableModel.addRow(rowData);
					} else if (tP instanceof TJefe) {

						Object[] rowData = { tP.getId(), tP.getNif(), tP.getNombre(), tP.getApellido(),
								tP.getIdNave(),0,tP.getNomina()};
						this._tableModel.addRow(rowData);
					}
				}

			}

		} else
			JOptionPane.showMessageDialog(null, "No hay personas dadas de alta en la base de datos", "Info",
					JOptionPane.INFORMATION_MESSAGE);
	}


}

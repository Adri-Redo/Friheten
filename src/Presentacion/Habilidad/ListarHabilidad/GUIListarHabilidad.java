package Presentacion.Habilidad.ListarHabilidad;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Negocio.Habilidad.THabilidad;
import Presentacion.Observer;
import Presentacion.Context.Context;

public class GUIListarHabilidad extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DefaultTableModel _tableModel;
	private List<THabilidad> _listaHabilidades;

	public GUIListarHabilidad() {
		super("LISTAR HABILIDAD");
		this._listaHabilidades = new ArrayList<>();
		this.initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel textPanel = new JPanel();
		JPanel infoPanel = new JPanel();

		JLabel label = new JLabel("Listado de todas las habilidades de la Base de Datos");
		textPanel.add(label);
		mainPanel.add(textPanel);

		this._tableModel = new DefaultTableModel(new Object[] { "Id", "Nombre" }, 0);

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

		JScrollPane scroll = new JScrollPane(table);
		infoPanel.add(scroll);
		mainPanel.add(infoPanel);

		setContentPane(mainPanel);
		setPreferredSize(new Dimension(500, 500));
		setLocation(500, 500);
		pack();
		this.setLocationRelativeTo(null);
		setVisible(true);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void actualizar(Context context){
		this._tableModel.setRowCount(0);

		this._listaHabilidades = new ArrayList<THabilidad>((List<THabilidad>) context.getData());

		for (THabilidad tH : this._listaHabilidades) {
			if (tH.getActivo()) {

				Object[] rowData = { tH.getId(), tH.getNombre() };
				this._tableModel.addRow(rowData);

			}
		}

	}

}

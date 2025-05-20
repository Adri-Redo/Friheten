package Presentacion.Cliente.ListarClientes;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Negocio.Cliente.TCliente;
import Negocio.Cliente.TEmpresa;
import Negocio.Cliente.TIndividual;
import Presentacion.Observer;
import Presentacion.Context.Context;

public class GUIListarClientes extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DefaultTableModel _tableModel;
	private List<TCliente> _listaClientes;

	public GUIListarClientes() {
		super("LISTAR CLIENTES");
		this._listaClientes = new ArrayList<>();
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

		this._tableModel = new DefaultTableModel(new Object[] { "Id", "Usuario", "Correo", "Contrasenya", "Nombre", "CP", "Tipo"}, 0);

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

	@SuppressWarnings("unchecked")
	@Override
	public void actualizar(Context context) {
		this._tableModel.setRowCount(0);

		this._listaClientes = new ArrayList<TCliente>((List<TCliente>) context.getData());
		for (TCliente tC : this._listaClientes) {
			if (tC.getActivo()) {
				if (tC.getTipo()) {
					TEmpresa empre = (TEmpresa) tC;
					Object[] rowData = { tC.getId(), tC.getUsuario(), tC.getCorreo(), tC.getContrasena(), empre.getNombre(), "N/A", "Empresa" };
					this._tableModel.addRow(rowData);
				} else {
					TIndividual indi = (TIndividual) tC;
					Object[] rowData = { tC.getId(), tC.getUsuario(), tC.getCorreo(), tC.getContrasena(), "N/A", indi.getCodigoPostalStr(), "Individual" };
					this._tableModel.addRow(rowData);
				}
			}
		}

	}

	
	

}

package Presentacion.Personal.MostrarPersonalPorIDNave;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Negocio.Personal.TEmpleado;
import Negocio.Personal.TJefe;
import Negocio.Personal.TPersonal;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIMostrarPersonalPorIDNave extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DefaultTableModel _tableModel;

	public GUIMostrarPersonalPorIDNave() {
		super("MOSTRAR PERSONAL POR EL ID DE LA NAVE");
		this.initGUI();
	}

	private void initGUI() {
		JPanel panelPrincipal = new JPanel();
		JPanel panelParaUsuario = new JPanel();

		JLabel mostrarPersonal = new JLabel("Introduzca el ID de la nave: ");
		JTextField nombreTexto = new JTextField(20);
		nombreTexto.setEditable(true);
		nombreTexto.setToolTipText("ID de la nave de la cual quieres mostrar su personal");

		JButton ok = new JButton("OK");
		ok.setToolTipText("Mostrar personal con ese ID de nave");
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

						JOptionPane.showMessageDialog(null, "El id de la nave debe ser mayor que 0.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException m) {

					validar = false;
					JOptionPane.showMessageDialog(null,
							"El id de la nave debe ser un numero entero mayor que 0, no puede contener letras", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

				if (validar) {
					Controller.getInstance().handle(new Context(Events.MOSTRAR_PERSONAL_PORID_NAVE_OK_VIEW, id));

				}

				dispose();
			}

		});

		JButton ko = new JButton("CANCEL");
		ko.setToolTipText("Cancelar");
		ko.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.MOSTRAR_PERSONAL_PORID_NAVE_KO_VIEW, null));
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

	@SuppressWarnings("unchecked")
	@Override
	public void actualizar(Context context) {
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

		this._tableModel = new DefaultTableModel(new Object[] { "Id", "NIF", "Nombre", "Apellido", "IdNave", "Cargo" },
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
		columnModel.getColumn(4).setPreferredWidth(50);// IDNAVE
		columnModel.getColumn(5).setPreferredWidth(200);// CARGO

		table.setPreferredScrollableViewportSize(new Dimension(600, 400));

		JScrollPane scroll = new JScrollPane(table);
		infoPanel.add(scroll);
		mainPanel.add(infoPanel);

		this._tableModel.setRowCount(0);
		List<TPersonal> listaPersonal = new ArrayList<TPersonal>();
		if(context.getData()!=null) {
			listaPersonal = (List<TPersonal>) context.getData();
		}

		for (TPersonal tP : listaPersonal) {
			if (tP.getActivo()) {
				if (tP instanceof TEmpleado) {

					TEmpleado e = (TEmpleado) tP;
					Object[] rowData = { tP.getId(), tP.getNif(), tP.getNombre(), tP.getApellido(), tP.getIdNave(),
							e.getCargo() };
					this._tableModel.addRow(rowData);
				} else if (tP instanceof TJefe) {

					Object[] rowData = { tP.getId(), tP.getNif(), tP.getNombre(), tP.getApellido(), tP.getIdNave() };
					this._tableModel.addRow(rowData);
				}
			}

			setContentPane(mainPanel);
			setPreferredSize(new Dimension(800, 700));
			setLocation(500, 500);
			pack();
			this.setLocationRelativeTo(null);
			setVisible(true);
		}
		

	}
}
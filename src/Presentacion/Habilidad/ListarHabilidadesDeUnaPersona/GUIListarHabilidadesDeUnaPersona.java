package Presentacion.Habilidad.ListarHabilidadesDeUnaPersona;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
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

import Negocio.Habilidad.THabilidad;

import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIListarHabilidadesDeUnaPersona extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DefaultTableModel _tableModel;

	public GUIListarHabilidadesDeUnaPersona() {
		super("LISTAR HABILIDADES DE UNA PERSONA");
		this.initGUI();
	}

	private void initGUI() {

		JPanel panelPrincipal = new JPanel();
		JPanel panelParaUsuario = new JPanel();

		JLabel mostrarPersonal = new JLabel("Introduzca el ID de la persona: ");
		JTextField nombreTexto = new JTextField(20);
		nombreTexto.setEditable(true);
		nombreTexto.setToolTipText(
				"ID de la persona de la cual quieres mostrar las habilidades a las que está vinculada.");

		JButton ok = new JButton("OK");
		ok.setToolTipText("Mostrar habilidades vinculadas a esa persona.");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean validar = true;
				String idPersonal = nombreTexto.getText().trim();
				int id = 0;
				try {

					id = Integer.parseInt(idPersonal);
					if (id <= 0) {
						validar = false;

						JOptionPane.showMessageDialog(null, "El id de la persona debe ser mayor que 0.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException m) {

					validar = false;
					JOptionPane.showMessageDialog(null,
							"El id de la persona debe ser un numero entero mayor que 0, no puede contener letras ni estar vacio",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				}

				if (validar) {
					Controller.getInstance().handle(new Context(Events.MOSTRAR_HABILIDADES_DE_UNA_PERSONA_OK_VIEW, id));

				}

				dispose();
			}

		});

		JButton ko = new JButton("CANCEL");
		ko.setToolTipText("Cancelar");
		ko.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.HABILIDAD_CANCEL_VIEW, null));
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
		if(context.getData() != null)
		{
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			JPanel textPanel = new JPanel();
			JPanel infoPanel = new JPanel();

			JLabel label = new JLabel("Listado de todas las habilidades con ese Id de Persona de la Base de Datos");
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

			// Desactivar el ajuste automático de las columnas
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

//			// Modelo de las columnas de nuestra tabla
			TableColumnModel columnModel = table.getColumnModel();
	//
//			// Establecer el ancho preferido para cada columna (puedes ajustar estos valores
//			// según tus necesidades)
			columnModel.getColumn(0).setPreferredWidth(200);// ID
			columnModel.getColumn(1).setPreferredWidth(200);// Nombre

			table.setPreferredScrollableViewportSize(new Dimension(400, 400));

			JScrollPane scroll = new JScrollPane(table);
			infoPanel.add(scroll);
			mainPanel.add(infoPanel);

			this._tableModel.setRowCount(0);
			List<THabilidad> listaHabilidades = new LinkedList<>();
			if(context.getData() != null)
			{
				 listaHabilidades = (List<THabilidad>) context.getData();
			}
				
			

			for (THabilidad tH : listaHabilidades) {
				if (tH.getActivo()) {
					Object[] rowData = { tH.getId(), tH.getNombre() };
					this._tableModel.addRow(rowData);
				}

			}
			setContentPane(mainPanel);
			setPreferredSize(new Dimension(500, 500));
			setLocation(250, 250);
			pack();
			this.setLocationRelativeTo(null);
			setVisible(true);
		}
		
	}

}

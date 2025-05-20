package Presentacion.Mueble.MostrarMueblePorIdNave;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Negocio.Mueble.TMueble;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIMostrarMueblePorIdNave extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;

	private DefaultTableModel _tableModel;
	
	public GUIMostrarMueblePorIdNave() {
		super("MOSTRAR MUEBLE POR ID NAVE");
		this.initGUI();
	}
	
	
	private void initGUI() {
		JPanel panelPrincipal = new JPanel();
		JPanel panelParaUsuario = new JPanel();

		JLabel mostrarMueble = new JLabel("Introduzca el ID de la nave: ");
		JTextField nombreTexto = new JTextField(20);
		nombreTexto.setEditable(true);
		nombreTexto.setToolTipText("ID de la nave de la cual quieres mostrar sus muebles");

		JButton ok = new JButton("OK");
		ok.setToolTipText("Mostrar muebles con ese ID de nave");
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
					Controller.getInstance().handle(new Context(Events.MOSTRAR_MUEBLE_POR_ID_NAVE_OK_VIEW, id));
				}

				dispose();
			}

		});

		JButton ko = new JButton("CANCEL");
		ko.setToolTipText("Cancelar");
		ko.addActionListener((e) -> {
			Controller.getInstance().handle(new Context(Events.MUEBLE_VIEW, null));
			dispose();
		});

		panelParaUsuario.add(mostrarMueble);
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
	@Override
	public void actualizar(Context context) 
	{
		Boolean IsList = false; 
		List<TMueble> listaNaves = null;
		try
		{
			listaNaves  = (List<TMueble>) context.getData();
			IsList = true;
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		if (IsList && listaNaves != null)
		{
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			JPanel textPanel = new JPanel();
			JPanel infoPanel = new JPanel();
	
			JLabel label = new JLabel("Listado muebles por nave ID de la Base de Datos");
			textPanel.add(label);
			mainPanel.add(textPanel);
	
			this._tableModel = new DefaultTableModel(new Object[] { "Id", "Nombre", "Precio", "Cantidad", "Peso", "Largo", "Alto", "Ancho", "Materiales", "IdNave" },
					0);
	
			JTable table = new JTable(this._tableModel);
			 table.setBackground(Color.WHITE);
			 table.setOpaque(true);

			 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		       centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		        for (int i = 0; i < table.getColumnCount(); i++) 
		        {
		            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		        }
			 
			table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
			
			JScrollPane scroll = new JScrollPane(table);
			
			scroll.getViewport().setBackground(Color.WHITE);
			  
			
			TableColumnModel columnModel = table.getColumnModel();
		    TableColumn materialesColumn = columnModel.getColumn(8); 
		    materialesColumn.setPreferredWidth(materialesColumn.getPreferredWidth() * 8);
		    
		    TableColumnModel columnModel2 = table.getColumnModel();
		    TableColumn nombreColumn = columnModel2.getColumn(1); // El índice de la columna "Nombre"
		    nombreColumn.setPreferredWidth(nombreColumn.getPreferredWidth() * 3);
			
			infoPanel.add(scroll);
			mainPanel.add(infoPanel);
	
			this._tableModel.setRowCount(0);
			List<TMueble> listaMuebles = (List<TMueble>) context.getData();
	
			for (TMueble tM : listaMuebles)
			{
				if (tM.getActivo()) {
					Object[] rowData = {tM.getId(), tM.getNombre(), tM.getPrecio(), tM.getStock(),
							tM.getPeso(), tM.getMedX(), tM.getMedY(), tM.getMedZ(), tM.getMaterial(),
							tM.getIdNave()};
					
					this._tableModel.addRow(rowData);
				}
				
			
			}
			setContentPane(mainPanel);
			//setPreferredSize(new Dimension(1100, 700));
			setLocation(500, 500);
			pack();
			this.setLocationRelativeTo(null);
			setVisible(true);
		}
	}

}


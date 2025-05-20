package Presentacion.Nave.ListarNave;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Negocio.Nave.TNave;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIListarNave extends JFrame implements Observer{

	private DefaultTableModel tableModel;
	private List<TNave> naves;
	public GUIListarNave() {
		
		this.naves = new ArrayList<>();
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel textPanel = new JPanel();
		JPanel infoPanel = new JPanel();
		
		JLabel label = new JLabel("Listado de todas las naves de la Base de Datos");
		textPanel.add(label);
		mainPanel.add(textPanel);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Controller.getInstance().handle(new Context(Events.NAVE_VIEW, null));
					
				}catch(Exception f){
					f.printStackTrace();
				}finally {
					dispose();
				}
			}
		}); 
		
		
		tableModel = new DefaultTableModel(
				new Object[] {"ID","Nombre","Localización", "Capacidad"},0) {
			/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		

		JTable table = new JTable(tableModel);

		
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
	

	@Override
	public void actualizar(Context context) {
		
		
		this.tableModel.setRowCount(0);
		this.naves = (List<TNave>) context.getData();
		
		for(TNave n : this.naves) {
			if(n.getActivo()) {
				Object[] rowData = {n.getId(),
									n.getNombre(),
									n.getLocalizacion(),
									n.getCapacidad()};
				tableModel.addRow(rowData);
			}
		}
		tableModel.fireTableDataChanged();
	
	}

}

package Presentacion.Nave.ModificarNave;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Negocio.Nave.TNave;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class GUIModificarNave extends JFrame implements Observer{
	
	private static final long serialVersionUID = 1L;
	TNave nave_act;
	Map<Integer, TNave> naves;
	DefaultTableModel tableModel;
	List<TNave> lista_naves;
	boolean validar = true;

	public GUIModificarNave() {
		super("Modificar Nave");
		nave_act = new TNave();
		naves = new HashMap<>();
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());

		JPanel topPanel = new JPanel();
		JLabel title = new JLabel("Modificar nave");
		topPanel.add(title);
		
		JPanel midPanel = new JPanel();
		JPanel idPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel idLabel = new JLabel("Id: ");
		JTextField idTextField = new JTextField(5);
		idPanel.add(idLabel);
		idPanel.add(idTextField);
		
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
		
		JButton consultarButton = new JButton("Consultar");
		JButton modifyB = new JButton("Cambiar");
		modifyB.setVisible(false);
		
		consultarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(idTextField.getText().isEmpty()){
					validar = false;
					JOptionPane.showMessageDialog(null, "Error: El ID no puede estar vacio.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}else {
					Integer id = Integer.valueOf(idTextField.getText());
					if (naves.containsKey(id)) {
						if(tableModel.getRowCount() > 0) {
							tableModel.removeRow(0);
						}
						TNave n = naves.get(id);
						nave_act.setActivo(n.getActivo());
						nave_act.setCapacidad(n.getCapacidad());
						nave_act.setId(n.getId());
						nave_act.setLocalizacion(n.getLocalizacion());
						nave_act.setNombre(n.getNombre());
						Object[] datos = new Object[] {n.getId(), n.getNombre(), n.getLocalizacion(), n.getCapacidad() };
						tableModel.addRow(datos);
					} else {
						validar = false;
						JOptionPane.showMessageDialog(null, "No existe una nave con ese ID", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				dispose();
			}
			
		});
		
		modifyB.addActionListener((e)->{
			if(validar)	Controller.getInstance().handle(new Context( Events.MODIFICAR_NAVE_OK_VIEW, nave_act));
			dispose();
		});
		
		buttonPanel.add(consultarButton);
		buttonPanel.add(modifyB);
		midPanel.add(idPanel);
		midPanel.add(buttonPanel);

		JPanel botPanel = new JPanel();
		tableModel = new DefaultTableModel(new String[] { "ID", "Nombre", "Localización", "Capacidad" }, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column != 0;
			}
			
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				switch (columnIndex) {
				case 0:
					return nave_act.getId();
				case 1:
					return nave_act.getNombre();
				case 2:
					return nave_act.getLocalizacion();
				case 3:
					return nave_act.getCapacidad();
					default:
						return null;
				}
			}
			
			@Override
			public void setValueAt(Object value, int row, int column) {
				switch (column) {
				case 1:
					nave_act.setLocalizacion((String) value);
					modifyB.setVisible(true);
					tableModel.fireTableDataChanged();
					break;
				case 2:
					nave_act.setNombre((String)value);
					modifyB.setVisible(true);
					tableModel.fireTableDataChanged();
					break;
					
				case 3:
					String s = (String) value;
					nave_act.setCapacidad(Integer.parseInt(s));
					modifyB.setVisible(true);
					tableModel.fireTableDataChanged();
					break;
				}
				
				if (modifyB.isVisible())
				{
					mainPanel.setSize(700, 300);
					pack();
				}
			}
		};
		JTable table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		
		
		botPanel.add(scrollPane);
		
		mainPanel.add(topPanel, BorderLayout.PAGE_START);
		mainPanel.add(midPanel, BorderLayout.CENTER);
		mainPanel.add(botPanel, BorderLayout.PAGE_END);
		
		setContentPane(mainPanel);
		setVisible(true);
		pack();
	}


	@Override
	public void actualizar(Context context) {
		switch(context.getEvent()) 
		{
			case Events.RES_CONSULTAR_NAVE_OK:
				loadActiveMuebles(context.getData());
			break;
			case Events.RES_MODIFICAR_NAVE_OK:
				TNave nave = (TNave) context.getData();
				JOptionPane.showMessageDialog(null, "La nave : " + nave.getNombre() + " se ha modificado correctamente","Error", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				break;
			case Events.MODIFICAR_NAVE_KO_VIEW:
				int idNoExiste = (Integer) context.getData();
				JOptionPane.showMessageDialog(null, "La nave con identificador: " + idNoExiste + " no existe.","Error", JOptionPane.ERROR_MESSAGE);
				dispose();
				break;
		}	
	}
	
	private void loadActiveMuebles(Object nave)
	{
		lista_naves = (List<TNave>) nave;
		if(lista_naves != null) 
		{
			for (TNave n : lista_naves) 
			{
				if (n.getActivo())  naves.put(n.getId(), n);
			}
		}
	}
	
	
}

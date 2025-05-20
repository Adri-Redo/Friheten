/**
 * 
 */
package Presentacion.Mueble.ModificarMueble;

import javax.swing.JFrame;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Negocio.Mueble.TMueble;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GUIModificarMueble extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	TMueble mueble_act;
	Map<Integer, TMueble> muebles;
	DefaultTableModel tableModel;
	List<TMueble> lstmuebles = null;
	
	public GUIModificarMueble()
	{ 
		super("MODIFICAR MUEBLE");
		mueble_act = new TMueble();
		muebles = new HashMap<>();
		this.initGUI();
	}
	
	private void initGUI() {
		
		
		JPanel mainPanel = new JPanel(new BorderLayout());

		JPanel topPanel = new JPanel();
		JLabel title = new JLabel("Modificar mueble");
		topPanel.add(title);
		JLabel title2 = new JLabel("*Editarlo sobre la tabla");
		topPanel.add(title2);
		
		JPanel midPanel = new JPanel();
		JPanel idPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel idLabel = new JLabel("Id: ");
		JTextField idTextField = new JTextField(5);
		idPanel.add(idLabel);
		idPanel.add(idTextField);
		
		JButton consultarButton = new JButton("Consultar");
		JButton modifyB = new JButton("Cambiar");
		modifyB.setVisible(false);
		consultarButton.addActionListener((e) -> 
		{
			if(idTextField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Error: El ID no puede estar vacio.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			else 
			{
				if (chkIntegerAndPositive(idTextField.getText().trim(),"El campo ID"))
				{
					int id = Integer.parseInt(idTextField.getText().trim());					
					if (muebles.containsKey(id)) 
					{
						if(tableModel.getRowCount() > 0) 
							tableModel.removeRow(0);
						
						TMueble n = muebles.get(id);
						
						mueble_act.setId(n.getId());
						mueble_act.setNombre(n.getNombre());
						mueble_act.setPrecio(n.getPrecio());
						mueble_act.setStock(n.getStock());
						mueble_act.setPeso(n.getPeso());
						mueble_act.setMedX(n.getMedX());
						mueble_act.setMedY(n.getMedY());
						mueble_act.setMedZ(n.getMedZ());
						mueble_act.setMaterial(n.getMaterial());
						mueble_act.setIdNave(n.getIdNave());
						
						Object[] datos = new Object[] { n.getId(), n.getNombre(),n.getPrecio(),n.getStock(),n.getPeso(),n.getMedX(),n.getMedY(),n.getMedZ(),n.getMaterial(),n.getIdNave() };
						tableModel.addRow(datos);
						
						title2.setText(":::: Se esta modificando el mueble ::: " + n.getNombre().toUpperCase());
						
					} 
					else 
					{
						JOptionPane.showMessageDialog(null, "El mueble con identificador ::: " + id + " ::: no existe o esta Inactivo.","Error", JOptionPane.ERROR_MESSAGE);
					}
				 }
			}
		});
		
		modifyB.addActionListener((e)->{
			Controller.getInstance().handle(new Context(Events.MODIFICAR_MUEBLE_OK_VIEW, mueble_act));
		});

		JButton cancel = new JButton("Cancelar");
		cancel.setToolTipText("Cancelar");
		cancel.addActionListener((e)-> {
			Controller.getInstance().handle(new Context(Events.MUEBLE_VIEW, null));
			dispose();
	});
		
		buttonPanel.add(consultarButton);
		buttonPanel.add(modifyB);
		midPanel.add(idPanel);
		midPanel.add(buttonPanel);
		midPanel.add(cancel);
		
		JPanel botPanel = new JPanel();
		tableModel = new DefaultTableModel(new String[] {"ID", "Nombre", "Precio", "Cantidad", "Peso", "MedX", "MedY", "MedZ", "Materiales", "IDNave"}, 0) 
		{
			
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column != 0;
			}
			
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				switch (columnIndex) {
				case 0:
					return mueble_act.getId();
				case 1:
					return mueble_act.getNombre();
				case 2:           
					return mueble_act.getPrecio();
				case 3:           
					return mueble_act.getStock();
				case 4:           
					return mueble_act.getPeso();
				case 5:           
					return mueble_act.getMedX();
				case 6:           
					return mueble_act.getMedY();
				case 7:           
					return mueble_act.getMedZ();
				case 8:           
					return mueble_act.getMaterial();
				case 9:           
					return mueble_act.getIdNave();	
					default:
						return null;
				}
			}
			
			@Override
			public void setValueAt(Object value, int row, int column) {
				switch (column) {
				case 1:
					mueble_act.setNombre((String)value);
					modifyB.setVisible(true);
					tableModel.fireTableDataChanged();
					break;
				case 2: 
					if (chkDoublerAndPositive(value,"El campo precio"))
					{
						mueble_act.setPrecio(Double.valueOf((String.valueOf(value))));
						modifyB.setVisible(true);
						tableModel.fireTableDataChanged();
					}
						break;
				case 3:
					if (chkIntegerAndPositive(value,"El campo stock"))
					{
						mueble_act.setStock(Integer.parseInt( (String.valueOf(value))));
						modifyB.setVisible(true);
						tableModel.fireTableDataChanged();
					}
					break;
				case 4:        
					if (chkDoublerAndPositive(value,"El campo peso"))
					{
						mueble_act.setPeso(Double.valueOf((String.valueOf(value))));
						modifyB.setVisible(true);
						tableModel.fireTableDataChanged();
					}
					break;
				case 5:           
					if (chkDoublerAndPositive(value,"El campo medida X"))
					{
						mueble_act.setMedX(Double.valueOf((String.valueOf(value))));
						modifyB.setVisible(true);
						tableModel.fireTableDataChanged();
						break;
					}
				case 6:  
					if (chkDoublerAndPositive(value,"El campo medida Y"))
					{
						mueble_act.setMedY(Double.valueOf((String.valueOf(value))));
						modifyB.setVisible(true);
						tableModel.fireTableDataChanged();
						break;
					}
				case 7:           
					if (chkDoublerAndPositive(value,"El campo medida Z"))
					{
						mueble_act.setMedZ(Double.valueOf((String.valueOf(value))));
						modifyB.setVisible(true);
						tableModel.fireTableDataChanged();
						break;
					}
				case 8:           
					mueble_act.setMaterial((String)value);
					modifyB.setVisible(true);
					tableModel.fireTableDataChanged();
					break;
				case 9:           
					mueble_act.setIdNave(Integer.parseInt( (String.valueOf(value))));
					//modifyB.setVisible(true);
					//tableModel.fireTableDataChanged();
					break;
					default:	
				}
				
				if (modifyB.isVisible())
				{					
					consultarButton.setVisible(false);
					pack();
				}
			}
		};
		JTable table = new JTable(tableModel);
		
		 table.setBackground(Color.WHITE);
		 table.setOpaque(true);
		 
		 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	       centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	        for (int i = 0; i < table.getColumnCount(); i++) 
	        {
	            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	        }
		
		table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(Color.WHITE);
		
		TableColumnModel columnModel = table.getColumnModel();
	    TableColumn materialesColumn = columnModel.getColumn(8); 
	    materialesColumn.setPreferredWidth(materialesColumn.getPreferredWidth() * 8);
		  
		table.setFillsViewportHeight(true);

        
		botPanel.add(scrollPane);
		
		mainPanel.add(topPanel, BorderLayout.PAGE_START);
		mainPanel.add(midPanel, BorderLayout.CENTER);
		mainPanel.add(botPanel, BorderLayout.PAGE_END);
		
		this.setLocation(200,200);
		this.setSize(1600,900);
		this.setResizable(true); 
		
		setContentPane(mainPanel);
		setVisible(true);
		pack();
		
	}
	
	@Override
	public void actualizar(Context context) 
	{
		
		switch(context.getEvent()) 
		{
			// Cuando abrimos el view y cargamos todos los datos activos
			case Events.CONSULTAR_MUEBLE_VIEW:
				JOptionPane.showMessageDialog(null, "No hay ninguna lista con muebles ACTIVOS","Error", JOptionPane.ERROR_MESSAGE);
				setVisible(false);
			break;
			case Events.EXITO_MUEBLE_CONSULTAR:
				loadActiveMuebles(context.getData());
			break;
			// ----
			case Events.EXITO_MUEBLE_MODIFICAR:
				TMueble mueble = (TMueble) context.getData();
				JOptionPane.showMessageDialog(null, "El mueble ::: " + mueble.getNombre() + " ::: se ha modificado correctamente","Error", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				break;
			case Events.ERROR_MUEBLE_NO_EXISTE:
				int idNoExiste = (Integer) context.getData();
				JOptionPane.showMessageDialog(null, "El mueble con identificador ::: " + idNoExiste + " ::: no existe.","Error", JOptionPane.ERROR_MESSAGE);
				Controller.getInstance().handle(new Context(Events.MODIFICAR_MUEBLE_VIEW, mueble_act));
				break;
		}	
	}
	
	private void loadActiveMuebles(Object mueblesObj)
	{
		lstmuebles = (List<TMueble>) mueblesObj;
		if(lstmuebles != null) 
		{
			for (TMueble n : lstmuebles) 
			{
				if (n.getActivo())  muebles.put(n.getId(), n);
			}
		}
	}
	
	
	private Boolean chkIntegerAndPositive( Object item , String msg_error )
	{
		String strIsInteger = String.valueOf(item);
		if(!strIsInteger.isEmpty()) 
		{
			if(strIsInteger.matches("-?[0-9]+(\\.[0-9]+)?")) {
				int id = Integer.parseInt(strIsInteger);
				if(id <= 0) 
					JOptionPane.showMessageDialog(null, msg_error + " tiene que ser positivo.", "Error",JOptionPane.ERROR_MESSAGE);

				return true;
			}
			else 
				JOptionPane.showMessageDialog(null, msg_error + " tiene que ser numérico.", "Error",JOptionPane.ERROR_MESSAGE);
		}		
		return false;
	}
	
	private Boolean chkDoublerAndPositive( Object item , String msg_error )
	{
		String strIsInteger = String.valueOf(item);
		if(!strIsInteger.isEmpty()) 
		{
			if(strIsInteger.matches("-?[0-9]+(\\.[0-9]+)?")) {
				Double id = (Double.valueOf((String.valueOf(item))));
				if(id <= 0) 
					JOptionPane.showMessageDialog(null, msg_error + " tiene que ser positivo.", "Error",JOptionPane.ERROR_MESSAGE);
				return true;
			}
			else 
				JOptionPane.showMessageDialog(null, msg_error + " tiene que ser Double/Entero/Decimal.", "Error",JOptionPane.ERROR_MESSAGE);
		}		
		return false;
	}
	

}
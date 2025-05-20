/**
 * 
 */
package Presentacion.Mueble.ListarMuebles;

import javax.swing.JFrame;
import Presentacion.Observer;
import Presentacion.Context.Context;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Negocio.Mueble.TMueble;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author ll
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIListarMuebles extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private DefaultTableModel tableModel;
	private List<TMueble> listaMuebles;
	
	public GUIListarMuebles() {
		super("LISTAR MUEBLES");
		this.listaMuebles = new ArrayList<>();
		this.initGUI();
	}
	
	private void initGUI() {
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		
		// Crear un modelo de datos para el JTable
	    tableModel = new DefaultTableModel(
	            new Object[]{"ID", "Nombre", "Precio", "Cantidad", "Peso", "MedX", "MedY", "MedZ", "Materiales", "IDNave"}, 0);
	    
	    JTable table = new JTable(tableModel);
	    table.setBackground(Color.WHITE);
		 table.setOpaque(true);
		 
		  DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	       centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	        for (int i = 0; i < table.getColumnCount(); i++) 
	        {
	            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	        }
		 
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.getViewport().setBackground(Color.WHITE);
		
	    TableColumnModel columnModel = table.getColumnModel();
	    TableColumn materialesColumn = columnModel.getColumn(8); // El índice de la columna "Materiales"
	    materialesColumn.setPreferredWidth(materialesColumn.getPreferredWidth() * 8);
	    
	    TableColumnModel columnModel2 = table.getColumnModel();
	    TableColumn nombreColumn = columnModel2.getColumn(1); // El índice de la columna "Nombre"
	    nombreColumn.setPreferredWidth(nombreColumn.getPreferredWidth() * 3);
	    
	    panelPrincipal.add(scrollPane, BorderLayout.CENTER);
	    
		this.setContentPane(panelPrincipal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200,200);
		this.setSize(1000,500);
		this.setResizable(false); //Ventana inmovil
	}

	/** 
	* (non-Javadoc)
	* @see Observer#actualizar(Integer evento, Object datos)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void actualizar(Context context) 
	{
		this.tableModel.setRowCount(0);
		this.listaMuebles = (List<TMueble>) context.getData();
		
		for(TMueble tM : this.listaMuebles) 
		{
			if(tM.getActivo()) {
				Object[] rowData = {tM.getId(), tM.getNombre(), tM.getPrecio(), tM.getStock(), tM.getPeso(),tM.getMedX(), tM.getMedY(), tM.getMedZ(), tM.getMaterial(), tM.getIdNave()};
				this.tableModel.addRow(rowData);				
			}
		}
		
		if (tableModel.getRowCount() != 0)
			setTitle("LISTAR MUEBLES:: (Resultado listado de muebles - " + tableModel.getRowCount() + " muebles)"  );
		else
			setTitle("LISTAR MUEBLES");
		
	}
}
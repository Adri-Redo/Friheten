/**
 * 
 */
package Presentacion.Mueble.ConsultarMueble;

import javax.swing.JFrame;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.FactoryPresentacion.FactoryPresentacion;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Negocio.Mueble.TMueble;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GUIConsultarMueble extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	public GUIConsultarMueble(){
		super("CONSULTAR MUEBLE");
		this.initGUI();
	}
	
	private void initGUI() {
		JPanel panelPrincipal = new JPanel();
		JPanel panelParaUsuario = new JPanel();
		
		JLabel consultarMueble = new JLabel("Introduzca el ID del mueble que quiere consultar: ");
		JTextField cuadroTexto = new JTextField(10);
		cuadroTexto.setEditable(true);
		cuadroTexto.setToolTipText("ID Mueble");
		
		JButton ok = new JButton("OK");
		ok.setToolTipText("Aceptar");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean validar = true;
				String idMueble = cuadroTexto.getText().trim();
				int id = 0;
				try {

					id = Integer.parseInt(idMueble);
					if (id <= 0) {
						validar = false;

						JOptionPane.showMessageDialog(null, "El id del mueble debe ser mayor que 0.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException m) {

					validar = false;
					JOptionPane.showMessageDialog(null,
							"El id del mueble debe ser un numero entero mayor que 0, no puede contener letras", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

				if (validar) 
				{
					dispose();	
					Context contexto = new Context(Events.CONSULTAR_MUEBLE_OK_VIEW, id);
					Controller.getInstance().handle(contexto);
					

				}
			}

		});
		JButton cancel = new JButton("CANCEL");
		cancel.setToolTipText("Cancelar");
		cancel.addActionListener((e)-> {
			Controller.getInstance().handle(new Context(Events.MUEBLE_VIEW, null));
			dispose();
	});
		
		panelParaUsuario.add(consultarMueble);
		panelParaUsuario.add(cuadroTexto);
		panelParaUsuario.add(ok);
		panelParaUsuario.add(cancel);
		
		panelPrincipal.add(panelParaUsuario);
		
		this.setContentPane(panelPrincipal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200,200);
		this.pack();
		
	}
	
	
	public void actualizar(Context context)
	{
		
		switch(context.getEvent()) 
		{
			case Events.EXITO_MUEBLE_CONSULTAR:
				
				TMueble mueble = (TMueble) context.getData();
							
				getTableResult(mueble);
				
				break;
			case Events.ERROR_MUEBLE_NO_EXISTE:
				int idNoExiste = (Integer) context.getData();
				JOptionPane.showMessageDialog(null, "El mueble con identificador: " + idNoExiste + " no existe.","Error", JOptionPane.ERROR_MESSAGE);
				break;
		}
	}
	
	private void getTableResult(TMueble  tM)
	{
		
		JFrame frame = new JFrame("Datos del Mueble");
		
		if (tM.getActivo())
			frame.setTitle("CONSULTAR MUEBLE :: " + tM.getNombre().trim() + " (Estado: Activo)");
		else
			frame.setTitle("CONSULTAR MUEBLE :: " + tM.getNombre().trim() + " (Estado: Inactivo)");
		
		DefaultTableModel _tableModel = new DefaultTableModel(new Object[] { "Id", "Nombre", "Precio", "Cantidad", "Peso", "Largo", "Alto", "Ancho", "Materiales", "IdNave" },0);

        JTable table = new JTable(_tableModel);
        
        table.setBackground(Color.WHITE);
		 table.setOpaque(true);
		 
		   DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	       centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	        for (int i = 0; i < table.getColumnCount(); i++) 
	        {
	            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	        }
		 
		 /*
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);//ID
		columnModel.getColumn(1).setPreferredWidth(200);//NOMBRE
		columnModel.getColumn(2).setPreferredWidth(50);//PRECIO
		columnModel.getColumn(3).setPreferredWidth(50);//CANTIDAD
		columnModel.getColumn(4).setPreferredWidth(50);//PESO
		columnModel.getColumn(5).setPreferredWidth(50);//MEDX
		columnModel.getColumn(6).setPreferredWidth(50);//MEDY
		columnModel.getColumn(7).setPreferredWidth(50);//MEDZ
		columnModel.getColumn(8).setPreferredWidth(300);//MATERIALES
		columnModel.getColumn(9).setPreferredWidth(50);//IDNAVE
		table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        */
        JScrollPane scrollPane = new JScrollPane(table);
        
        scrollPane.getViewport().setBackground(Color.WHITE);
		TableColumnModel columnModel = table.getColumnModel();
		
	    TableColumn materialesColumn = columnModel.getColumn(8); 
	    materialesColumn.setPreferredWidth(materialesColumn.getPreferredWidth() * 8);
	    
	    TableColumn materialesColumn2 = columnModel.getColumn(1); 
	    materialesColumn2.setPreferredWidth(materialesColumn2.getPreferredWidth() * 6);
	    
        frame.add(scrollPane);
        _tableModel.setRowCount(0);
        
        Object[] rowData = {tM.getId(), tM.getNombre(), tM.getPrecio(), tM.getStock(),
				tM.getPeso(), tM.getMedX(), tM.getMedY(), tM.getMedZ(), tM.getMaterial(),
				tM.getIdNave()};
		
		_tableModel.addRow(rowData);
        
		frame.setPreferredSize(new Dimension(1000, 200));
        frame.pack();
        frame.setLocation(200,200);
        frame.setVisible(true);
	}
	
}
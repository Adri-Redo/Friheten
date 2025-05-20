/**
 * 
 */
package Presentacion.Mueble.BajaMueble;

import javax.swing.JFrame;
import Presentacion.Observer;
import Presentacion.Context.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.FactoryPresentacion.FactoryPresentacion;

import javax.swing.JTextField;

import Negocio.Mueble.TMueble;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author ill
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIBajaMueble extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;

	
	public GUIBajaMueble(){
		super("BAJA MUEBLE");
		this.initGUI();
	}
	
	private void initGUI() {
		JPanel panelPrincipal = new JPanel();
		JPanel panelParaUsuario = new JPanel();
		
		JLabel bajaMueble = new JLabel("Introduzca el ID del mueble que quiere dar de baja: ");
		JTextField cuadroTexto = new JTextField(10);
		cuadroTexto.setEditable(true);
		cuadroTexto.setToolTipText("ID Mueble");
		
		JButton ok = new JButton("OK");
		ok.setToolTipText("Aceptar");
		ok.addActionListener((e)->{
			try {
				Integer id = Integer.parseInt(cuadroTexto.getText().trim());
				if(id <= 0) 
				{
					JOptionPane.showMessageDialog(null, "El identificador del mueble debe ser mayor que 0.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					Context contexto = new Context(Events.BAJA_MUEBLE_OK_VIEW, id);
					Controller.getInstance().handle(contexto);
				}
			}
			catch(NumberFormatException ex) 
			{
				JOptionPane.showMessageDialog(null, "El identificador debe ser un numero mayor que 0, sin letras.",
						"ERROR", JOptionPane.ERROR_MESSAGE);			
			}
			dispose();
		});;
		
		
		JButton cancel = new JButton("CANCEL");
		cancel.setToolTipText("Cancelar");
		cancel.addActionListener((e)-> {
			Controller.getInstance().handle(new Context(Events.MUEBLE_VIEW, null));
			dispose();
		});
		
		panelParaUsuario.add(bajaMueble);
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
			case Events.EXITO_MUEBLE_BAJA:
				TMueble muebleE = (TMueble) context.getData();	
				JOptionPane.showMessageDialog(null, "El mueble ::: " + muebleE.getNombre().trim() + " se ha dado de baja correctamente ", "Error", JOptionPane.INFORMATION_MESSAGE);
			
			break;
			case Events.EXITO_MUEBLE_MODIFICAR:
				TMueble mueble = (TMueble) context.getData();	
				if (mueble.getActivo())
					JOptionPane.showMessageDialog(null, "El mueble ::: " + mueble.getNombre().trim() + " :: no pudo darse de baja!!.","Error", JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "El mueble ::: " + mueble.getNombre().trim() + " :: ya se encuentra INACTIVO","Error", JOptionPane.ERROR_MESSAGE);
					
			break;
			case Events.ERROR_MUEBLE_NO_EXISTE:
				int idNoExiste = (Integer) context.getData();
				JOptionPane.showMessageDialog(null, "El mueble con identificador:: " + idNoExiste + " - no existe.","Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		}
	}
}
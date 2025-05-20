/**
 * 
 */
package Presentacion.Mueble.AltaMueble;

import javax.swing.JFrame;
import Presentacion.Observer;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.FactoryPresentacion.FactoryPresentacion;
import Presentacion.Context.Context;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Negocio.Mueble.TMueble;



/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author ill
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIAltaMueble extends JFrame implements Observer {
	
	TMueble t_mueble;
	
	private static final long serialVersionUID = 1L;

	
	public GUIAltaMueble() {
		super("ALTA MUEBLE");
		this.initGUI();
	}
	
	private void initGUI() {
		JPanel panelPrincipal = new JPanel();
		
		JPanel panelSuperior = new JPanel();
		JPanel panelMedioSup = new JPanel();
		JPanel panelMedioInf = new JPanel();
		JPanel panelInferior = new JPanel();
		
		JLabel NombreMueble = new JLabel("Nombre del Mueble: ");
		JTextField cuadroTextoNom = new JTextField(15);
		cuadroTextoNom.setEditable(true);
		cuadroTextoNom.setToolTipText("Nombre Mueble");
		
		JLabel PrecioMueble = new JLabel("Precio del Mueble: ");
		JTextField cuadroTextoPrecio = new JTextField(5);
		cuadroTextoPrecio.setEditable(true);
		cuadroTextoPrecio.setToolTipText("Precio Mueble");
		
		JLabel CantidadMueble = new JLabel("Cantidad de Muebles: ");
		JTextField cuadroTextoCant = new JTextField(5);
		cuadroTextoCant.setEditable(true);
		cuadroTextoCant.setToolTipText("Cantidad Muebles");
		
		JLabel PesoMueble = new JLabel("Peso del Mueble: ");
		JTextField cuadroTextoPeso = new JTextField(5);
		cuadroTextoPeso.setEditable(true);
		cuadroTextoPeso.setToolTipText("Peso Mueble");
		
		JLabel MedidaMueble = new JLabel("Medidas: ");
		
		JLabel XMueble = new JLabel("largo: ");
		JTextField cuadroTextoX = new JTextField(5);
		cuadroTextoX.setEditable(true);
		cuadroTextoX.setToolTipText("Medida X");
		
		JLabel YMueble = new JLabel("alto: ");
		JTextField cuadroTextoY = new JTextField(5);
		cuadroTextoY.setEditable(true);
		cuadroTextoY.setToolTipText("Medida Y");
		
		JLabel ZMueble = new JLabel("ancho: ");
		JTextField cuadroTextoZ = new JTextField(5);
		cuadroTextoZ.setEditable(true);
		cuadroTextoZ.setToolTipText("Medida Z");
		
		JLabel MaterialesMueble = new JLabel("Materiales del Mueble: ");
		JTextField cuadroTextoMat = new JTextField(25);
		cuadroTextoMat.setEditable(true);
		cuadroTextoMat.setToolTipText("Materiales");
		
		JLabel IDNave = new JLabel("ID de la nave: ");
		JTextField cuadroTextoIDNave = new JTextField(5);
		cuadroTextoIDNave.setEditable(true);
		cuadroTextoIDNave.setToolTipText("Id Nave");
		
		panelSuperior.add(NombreMueble);
		panelSuperior.add(cuadroTextoNom);
		panelSuperior.add(PrecioMueble);
		panelSuperior.add(cuadroTextoPrecio);
		panelMedioSup.add(MedidaMueble);
		panelMedioSup.add(XMueble);
		panelMedioSup.add(cuadroTextoX);
		panelMedioSup.add(YMueble);
		panelMedioSup.add(cuadroTextoY);
		panelMedioSup.add(ZMueble);
		panelMedioSup.add(cuadroTextoZ);
		panelMedioInf.add(MaterialesMueble);
		panelMedioInf.add(cuadroTextoMat);
		panelMedioInf.add(IDNave);
		panelMedioInf.add(cuadroTextoIDNave);
		panelInferior.add(PesoMueble);
		panelInferior.add(cuadroTextoPeso);
		panelInferior.add(CantidadMueble);
		panelInferior.add(cuadroTextoCant);
		
		
		JButton cancel = new JButton("CANCEL");
		cancel.setToolTipText("Cancelar");
		cancel.addActionListener((e)-> {
			Controller.getInstance().handle(new Context(Events.MUEBLE_VIEW, null));
			dispose();
		});
		
		JButton ok = new JButton("OK");
		ok.setToolTipText("Aceptar");
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean datosCorrectos = true;
				List<String> errores = new ArrayList<>();
				
				String nombre, materiales;
				Double precio= 0.0, medx = 0.0, medy = 0.0, medz = 0.0, peso = 0.0;
				Integer idNave = 0, cantidad = 0;
				
				TMueble tmueble = new TMueble();
				
				nombre = cuadroTextoNom.getText();
				if(nombre.isEmpty()) {
					datosCorrectos = false;
					errores.add("Insertar el nombre del mueble.");
				}
				else if(!nombre.matches("^[a-zA-ZñÑ\\s]*$")) {  //Compruebo que el nombre no tiene numeros ni símbolos
					datosCorrectos = false;
					errores.add("El nombre del mueble solo puede tener caracteres no númericos.");
				}
				
				String textoPrecio = cuadroTextoPrecio.getText().trim();
				if(textoPrecio.isEmpty()) {
					datosCorrectos = false;
					errores.add("Insertar el precio del mueble.");
				}
				else if (textoPrecio.matches("-?[0-9]+(\\.[0-9]+)?")) { // Compruebo si solo tiene numeros
				    precio = Double.parseDouble(textoPrecio);
				    if(precio<=0) {
				    	datosCorrectos= false;
				    	errores.add("El precio tiene que ser positivo.");					    
				    }
				} 
				else {
					datosCorrectos = false;
					errores.add("El precio tiene que ser numérico.");
				}
					
				String textoMedX = cuadroTextoX.getText().trim();
				if(textoMedX.isEmpty()) {
					datosCorrectos = false;
					errores.add("Insertar la medida de largo del mueble.");
				}
				else if (textoMedX.matches("-?[0-9]+(\\.[0-9]+)?")) { 
				    medx = Double.parseDouble(textoMedX);
				    if(medx<=0) {
				    	datosCorrectos= false;
				    	errores.add("La medida de largo tiene que ser positiva.");
				    }
				} 
				else {
				    datosCorrectos = false;
				    errores.add("La medida de largo tiene que ser numérica.");
				}
					
				String textoMedY = cuadroTextoY.getText().trim();
				if(textoMedY.isEmpty()) {
					datosCorrectos = false;
					errores.add("Insertar la medida de alto del mueble.");
				}
				else if (textoMedY.matches("-?[0-9]+(\\.[0-9]+)?")) { 
				    medy = Double.parseDouble(textoMedY);
				    if(medy<=0) {
				    	datosCorrectos= false;
					   	errores.add("La medida de alto tiene que ser positiva.");
				    }
				} 
				else {
				    datosCorrectos = false;
				    errores.add("La medida de alto tiene que ser numérica.");
				}
					
				String textoMedZ = cuadroTextoZ.getText().trim();
				if(textoMedZ.isEmpty()) {
					datosCorrectos = false;
					errores.add("Insertar la medida de ancho del mueble.");
				}
				else if (textoMedZ.matches("-?[0-9]+(\\.[0-9]+)?")) { 
				    medz = Double.parseDouble(textoMedZ);
				    if(medz<=0) {
				    	datosCorrectos= false;
					   	errores.add("La medida de ancho tiene que ser positiva.");
					}
				} 
				else {
				    datosCorrectos = false;
				    errores.add("La medida de ancho tiene que ser numérica.");
				}
				
				materiales = cuadroTextoMat.getText();
				if(materiales.isEmpty()) {
					datosCorrectos = false;
					errores.add("Insertar los materiales del mueble.");
				}
				
				String textoIdNave = cuadroTextoIDNave.getText().trim();
				if(textoIdNave.isEmpty()) {
					datosCorrectos = false;
					errores.add("Insertar el ID de la Nave en la que está el mueble.");
				}
				else if(textoIdNave.matches("-?[0-9]+(\\.[0-9]+)?")) {
					idNave = Integer.parseInt(textoIdNave);
					if(idNave <= 0) {
						datosCorrectos = false;
						errores.add("El ID de la Nave tiene que ser positivo.");
					}
				}
				else {
					datosCorrectos = false;
					errores.add("El ID de la Nave tiene que ser numérico.");
				}
				
				String textoPeso = cuadroTextoPeso.getText().trim();
				if(textoPeso.isEmpty()) {
					datosCorrectos = false;
					errores.add("Insertar el peso del mueble.");
				}
				else if (textoPeso.matches("-?[0-9]+(\\.[0-9]+)?")) { 
				    peso = Double.parseDouble(textoPeso);
				    if(peso<=0) {
				    	datosCorrectos= false;
				    	errores.add("El peso tiene que ser positivo.");
				    }
				} 
			
				else {
				    datosCorrectos = false;
				    errores.add("El peso tiene que ser numérico.");
				}
				
				
				String textoCantidad = cuadroTextoCant.getText().trim();
				if(textoCantidad.isEmpty()) {
					datosCorrectos = false;
					errores.add("Insertar la cantidad de muebles que hay.");
				}
				else if(textoCantidad.matches("-?[0-9]+(\\.[0-9]+)?")) {
					cantidad = Integer.parseInt(textoCantidad);
					if(cantidad <= 0) {
						datosCorrectos = false;
						errores.add("La cantidad tiene que ser positiva.");
					}
				}
				else {
					datosCorrectos = false;
					errores.add("La cantidad tiene que ser numérica.");
				}
				
				
				if(datosCorrectos) {
					tmueble.setActivo(true);
					tmueble.setIdNave(idNave);
					tmueble.setMaterial(materiales);
					tmueble.setMedX(medx);
					tmueble.setMedY(medy);
					tmueble.setMedZ(medz);
					tmueble.setNombre(nombre);
					tmueble.setPeso(peso);
					tmueble.setPrecio(precio);
					tmueble.setStock(cantidad);
					
					// Se formatean los string para aceptar minusculas, mayusculas etc.
					String nombreFormateado = nombre.substring(0, 1).toUpperCase()
							+ nombre.substring(1).toLowerCase();
					String materialesFormateado = materiales.substring(0, 1).toUpperCase()
							+ materiales.substring(1).toLowerCase();
					
					TMueble tm = new TMueble(0, nombreFormateado, precio, cantidad, peso, medx, medy, medz, materialesFormateado, idNave, true);
					Context contexto = new Context(Events.ALTA_MUEBLE_OK_VIEW, tm);
					Controller.getInstance().handle(contexto);
				}
				else {
					StringBuilder mensajeError = new StringBuilder();
			        for (String error : errores) {
			            mensajeError.append(error).append("\n");
			        }
			        JOptionPane.showMessageDialog(null, mensajeError.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
					
				}
			}
			
		});
		
		panelInferior.add(cancel);
		panelInferior.add(ok);
		
		
		panelPrincipal.add(panelSuperior, BorderLayout.BEFORE_FIRST_LINE);
		panelPrincipal.add(panelMedioSup, BorderLayout.LINE_START);
		panelPrincipal.add(panelMedioInf, BorderLayout.CENTER);
		panelPrincipal.add(panelInferior, BorderLayout.LINE_END);
		
		this.setContentPane(panelPrincipal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200,200);
		this.setSize(790, 210);		
	}

	@Override
	public void actualizar(Context context) 
	{
	
		switch(context.getEvent()) 
		{
			case Events.EXITO_MUEBLE_ALTA:
				TMueble mueble = (TMueble) context.getData();
				JOptionPane.showMessageDialog(null, "Se ha dado de alta el mueble :: " + mueble.getNombre() + " :: correctamente.","Info", JOptionPane.INFORMATION_MESSAGE);
				this.setVisible(false);
				break;	
			case Events.ERROR_MUEBLE_EXISTE:
				TMueble muebleError = (TMueble) context.getData();
				if (muebleError.getActivo())
					JOptionPane.showMessageDialog(null, "El mueble :: " + muebleError.getNombre() + " :: ya existe. (Estado: ACTIVO) ","Error", JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "El mueble " + muebleError.getNombre() + " :: ya existe. (Estado: INACTIVO) ","Error", JOptionPane.ERROR_MESSAGE);
				this.setVisible(false);
				break;
			case Events.ERROR_MUEBLE_NO_EXISTE:
				
				TMueble muebleNoAlta = (TMueble) context.getData();
				JOptionPane.showMessageDialog(null, "Error en ALTA MUEBLE :::  " + muebleNoAlta.getNombre()  + " - No se pudo dar de alta en","Error", JOptionPane.ERROR_MESSAGE);
				this.setVisible(false);
				break;
		}
	}
}
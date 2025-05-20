/**
 * 
 */
package Integracion.Mueble;

import Negocio.Mueble.TMueble;
import java.util.List;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author mvill
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public interface DAOMueble {
	
	public Integer create(TMueble mueble); //Dar de alta un mueble
	public TMueble read(Integer id);//Consultar mueble por ID
	public List<TMueble> readAll();//Lista de todos los muebles
	public Integer update(TMueble mueble); //Cambiar atributos del mueble
	public Integer delete(Integer id);//Dar de baja un mueble
	public TMueble readByName(String name);//Consultar un mueble por su nombre
	public List<TMueble> readMueblePorNave(Integer idNave); // Consultar los muebles que tiene una nave
}
/**
 * 
 */
package Negocio.Mueble;

import java.util.List;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author mvill
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface SAMueble {
	public Integer create(TMueble mueble);
	public TMueble read(Integer id);
	public  List<TMueble> readAll();
	public Integer update(TMueble tMueble);
	public Integer delete(Integer id);
	public List<TMueble> readMueblePorNave(int idNave);

	
}
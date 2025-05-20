/**
 * 
 */
package Negocio.Compra_Cafeteria;

import Negocio.Personal_Cafeteria.TPersonalCafeteria;
import java.util.List;
import java.util.Set;
/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public class TOACompraCafeteria {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private TCompraCafeteria compra;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private TPersonalCafeteria personal;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Set<TLineaCompraCafeteria> lineas;

	
	
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param compra
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setCompra(TCompraCafeteria compra) {
		this.compra = compra;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TCompraCafeteria getCompra() {
		return compra;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param personal
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setPersonal(TPersonalCafeteria personal) {
		this.personal = personal;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TPersonalCafeteria getPersonal() {
		return personal;
	}

	public Set<TLineaCompraCafeteria> getLineas() {
		return lineas;
	}

	public void setLineas(Set<TLineaCompraCafeteria> lineas) {
		this.lineas = lineas;
	}
	
	
}
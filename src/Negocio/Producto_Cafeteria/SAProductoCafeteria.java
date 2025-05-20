/**
 *
 */
package Negocio.Producto_Cafeteria;

import java.util.Set;

/**
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public interface SAProductoCafeteria {
	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tproducto_caf
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Integer create(TProductoCafeteria tproducto_caf);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Integer delete(Integer id);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param producto
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Integer update(TProductoCafeteria producto);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TProductoCafeteria read(Integer id);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Set<TProductoCafeteria> readAll();

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idAlergeno
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Set<TProductoCafeteria> readByAlergenos(Integer idAlergeno);
	
	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idAlergeno
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Set<TProductoCafeteria> readByIdMarca(Integer idAlergeno);
}

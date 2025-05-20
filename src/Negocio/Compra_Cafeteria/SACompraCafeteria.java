/**
 * 
 */
package Negocio.Compra_Cafeteria;

import java.util.List;

import Negocio.Compra.TCarrito;
import Negocio.Compra.TLineaCarrito;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public interface SACompraCafeteria {

	public TCarrito create(TCompraCafeteria tcompra_caf);

	public Integer update(TCompraCafeteria compraNueva);
	
	public Integer returnProduct(Integer IDCompra, Integer IDProducto, Integer cantidad);

	public List<TCompraCafeteria> readAll();

	public TCarrito addProduct(TCarrito carrito, Integer idProducto, Integer unidades, String nombreProducto);

	public TCarrito deleteProduct(TCarrito carrito, TLineaCarrito lc);

	public void close(TCarrito carrito);

	public TOACompraCafeteria read(Integer idCompra);
}
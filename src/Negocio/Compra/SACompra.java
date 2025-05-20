/**
 * 
 */
package Negocio.Compra;

import java.util.List;

/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 * 
 * @author mvill
 * @generated "UML a Java
 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface SACompra {
    
    public TCarrito createCarrito(TCompra tCompra);

    public Integer delete(Integer idCompra);

    public Integer update(TCompra nuevos_datos);

    public List<TMostrarCompra> readAll();

    public TMostrarCompra read(Integer idCompra);

    public List<TMostrarCompra> readByIDCliente(Integer idCliente);

    public TCarrito addProduct(TCarrito carrito, Integer unidades, Integer idMueble, String nombre);

    public TCarrito deleteProduct(TCarrito carrito, String nombre);

    public void close(TCarrito carrito);
}
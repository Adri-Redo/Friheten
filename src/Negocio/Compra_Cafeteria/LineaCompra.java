/**
 * 
 */
package Negocio.Compra_Cafeteria;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

import java.io.Serializable;

import Negocio.Producto_Cafeteria.Producto;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.LineaCompra.findByprecio", query = "select obj from LineaCompra obj where :precio = obj.precio "),
		@NamedQuery(name = "Negocio.LineaCompra.findBycantidad", query = "select obj from LineaCompra obj where :cantidad = obj.cantidad "),
		@NamedQuery(name = "Negocio.LineaCompra.findByproducto", query = "select obj from LineaCompra obj where :producto = obj.producto "),
		@NamedQuery(name = "Negocio.LineaCompra.findBycompra", query = "select obj from LineaCompra obj where :compra = obj.compra ") })
public class LineaCompra implements Serializable {

	private static final long serialVersionUID = 0;

	public LineaCompra() {
	}

	@EmbeddedId private LineaCompraId id;

	public void setId(LineaCompraId id) {
		this.id = id;
	}

	public LineaCompraId getId() {
		return id;
	}

	private Double precio;

	private Integer cantidad;

	
	@ManyToOne
	@MapsId("idproducto") private Producto producto;

	
	@ManyToOne
	@MapsId("id_compra")private CompraCafeteria compra;

	public LineaCompra(CompraCafeteria compra, Producto producto) {
		this.compra = compra;
		this.producto = producto;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public CompraCafeteria getCompra() {
		return compra;
	}

	public void setCompra(CompraCafeteria compra) {
		this.compra = compra;
	}

	public Double getPrecio() {
		return precio;
	}

	public Integer getCantidad() {
		return cantidad;
	}
	
	
}
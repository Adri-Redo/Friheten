/**
 * 
 */
package Negocio.Compra_Cafeteria;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author hugod
 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public class TCompraCafeteria {

	private Integer idCompra;

	private String fecha;

	private Double precio_total;

	private Boolean activo;

	private Integer idPersonal;

	// Constructor
    public TCompraCafeteria(Integer idCompra, String fecha, double precio_total, Boolean activo, Integer idPersonal) {
        this.idCompra = idCompra;
        this.fecha = fecha;
        this.precio_total = precio_total;
        this.activo = activo;
        this.idPersonal = idPersonal;
    }
    
    public TCompraCafeteria() {
    	
    }
	
	public void setId(Integer id) {
		this.idCompra = id;
	}

	public Integer getId() {
		return this.idCompra;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFecha() {
		return fecha;
	}

	public void setPrecioTotal(Double precio) {
		this.precio_total = precio;
	}

	public Double getPrecioTotal() {
		return this.precio_total;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setIdPersonal(Integer id) {
		this.idPersonal = id;
	}

	public Integer getIdPersonal() {
		return this.idPersonal;
	}
}
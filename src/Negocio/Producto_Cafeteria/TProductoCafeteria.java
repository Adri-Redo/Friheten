/**
 *
 */
package Negocio.Producto_Cafeteria;

/**
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author hugod
 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public class TProductoCafeteria {
	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // añado el autoincrementeado*/
	private Integer idProducto;
	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private String nombre;
	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private double precio;
	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Integer stock;
	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private char nivelNutricion;
	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Boolean activo;

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/

	private String TipoComida;

	private Integer nivelAzucar;
	
	private Integer idMarca;

	public TProductoCafeteria() {}

	public TProductoCafeteria(Integer idProducto, String nombre, double precio, Integer stock, char nivelNutricion,
			Boolean activo, String TipoComida , Integer nivelAzucar, Integer idMarca)
	{
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.nivelNutricion = nivelNutricion;
		this.activo = activo;
		this.setTipoComida(TipoComida);
		this.setNivelAzucar(nivelAzucar);
		this.setIdMarca(idMarca);
	}

	public void setId(Integer id) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Integer getId() {
		// begin-user-code
		// TODO Auto-generated method stub
		return this.idProducto;
		// end-user-code
	}

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param nombre
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setNombre(String nombre) {
		// begin-user-code
		// TODO Auto-generated method stub
		this.nombre = nombre;
		// end-user-code
	}

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public String getNombre() {
		// begin-user-code
		// TODO Auto-generated method stub
		return this.nombre;
		// end-user-code
	}

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param precio
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setPrecio(double precio) {
		// begin-user-code
		// TODO Auto-generated method stub
		this.precio = precio;
		// end-user-code
	}

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public double getPrecio() {
		// begin-user-code
		// TODO Auto-generated method stub
		return this.precio;
		// end-user-code
	}

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param stock
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setStock(int stock) {
		// begin-user-code
		// TODO Auto-generated method stub
		this.stock = stock;
		// end-user-code
	}

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int getStock() {
		// begin-user-code
		// TODO Auto-generated method stub
		return this.stock;
		// end-user-code
	}

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param nivelNutricion
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setNivelNutricion(char nivelNutricion) {
		// begin-user-code
		// TODO Auto-generated method stub
		this.nivelNutricion = nivelNutricion;
		// end-user-code
	}

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public char getNivelNutricion() {
		// begin-user-code
		// TODO Auto-generated method stub
		return this.nivelNutricion;
		// end-user-code
	}

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param activo
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setActivo(boolean activo) {
		// begin-user-code
		// TODO Auto-generated method stub
		this.activo = activo;
		// end-user-code
	}

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Boolean getActivo() {
		// begin-user-code
		// TODO Auto-generated method stub
		return this.activo;
		// end-user-code
	}

	public String getTipoComida() {
		return TipoComida;
	}

	public void setTipoComida(String tipoComida) {
		TipoComida = tipoComida;
	}

	public Integer getNivelAzucar() {
		return nivelAzucar;
	}

	public void setNivelAzucar(Integer nivelAzucar) {
		this.nivelAzucar = nivelAzucar;
	}
	
    public Integer getIdMarca() {
        return idMarca;
    }
    
    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }
}

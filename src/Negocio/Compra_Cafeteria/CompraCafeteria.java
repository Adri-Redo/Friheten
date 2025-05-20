/**
 * 
 */
package Negocio.Compra_Cafeteria;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

import Negocio.Personal_Cafeteria.PersonalCafeteria;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Compra_Cafeteria.findByid_compra", query = "select obj from CompraCafeteria obj where :id_compra = obj.id_compra "),
		@NamedQuery(name = "Negocio.Compra_Cafeteria.findByfecha", query = "select obj from CompraCafeteria obj where :fecha = obj.fecha "),
		@NamedQuery(name = "Negocio.Compra_Cafeteria.findByprecioTotal", query = "select obj from CompraCafeteria obj where :precioTotal = obj.precioTotal "),
		@NamedQuery(name = "Negocio.Compra_Cafeteria.findBypersonal", query = "select obj from CompraCafeteria obj where :personal = obj.personal "),
		@NamedQuery(name = "Negocio.Compra_Cafeteria.findByactivo", query = "select obj from CompraCafeteria obj where :activo = obj.activo "),
		@NamedQuery(name = "Negocio.Compra_Cafeteria.findBylineaCompra", query = "select obj from CompraCafeteria obj where :lineaCompra MEMBER OF obj.lineaCompra "),
		@NamedQuery(name = "Negocio.Compra_Cafeteria.readAll", query = "select obj from CompraCafeteria obj ")
})
public class CompraCafeteria implements Serializable {

	private static final long serialVersionUID = 0;

	public CompraCafeteria() {
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_compra;

	private String fecha;

	private Double precioTotal;

	@ManyToOne
	private PersonalCafeteria personal;

	private Boolean activo;

	@OneToMany(mappedBy = "compra", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<LineaCompra> lineaCompra;
	
	@Version
	private int version;
	
	public void setId(Integer id) {
		this.id_compra = id;
	}

	public Integer getID() {
		return this.id_compra;
	}

	public void setFecha(String fecha) {
	    this.fecha = fecha;
	}

	public String getFecha() {
		return this.fecha.toString();
	}

	public void setPrecioTotal(double precio) {
		this.precioTotal = precio;
	}

	public double getPrecioTotal() {
		return this.precioTotal;
	}


	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


	public Boolean getActivo() {
		return activo;
	}
	
	public Set<LineaCompra> getLineasCompra(){
		return lineaCompra;
	}
	
	public void setLineasCompra(Set<LineaCompra> lineasCompra) {
		this.lineaCompra = lineasCompra;
	}
	
	public PersonalCafeteria getPersonal() {
		return personal;
	}
	
	public void setPersonal(PersonalCafeteria personal) {
		this.personal = personal;
	}

}
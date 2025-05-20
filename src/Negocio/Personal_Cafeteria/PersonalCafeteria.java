/**
 * 
 */
package Negocio.Personal_Cafeteria;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.io.Serializable;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;

import java.util.HashSet;
import java.util.Set;

import Negocio.Compra_Cafeteria.CompraCafeteria;
import Negocio.Turno_Cafeteria.Turno;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.persistence.ManyToOne;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
		@NamedQuery(name = "Negocio.PersonalCafeteria.findAll", 
            query = "SELECT obj FROM PersonalCafeteria obj"),
		@NamedQuery(name = "Negocio.PersonalCafeteria.findByid", query = "select obj from PersonalCafeteria obj where :id = obj.id "),
@NamedQuery(name = "Negocio.PersonalCafeteria.findByNif", query = "select obj from PersonalCafeteria obj where :Nif = obj.nif ")})
public abstract class PersonalCafeteria implements Serializable {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private static final long serialVersionUID = 0;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public PersonalCafeteria() {
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Version
	private int version;

	protected String nombre;

	protected String apellidos;

	protected String nif;

	protected Integer salarioBase;

	@OneToMany(mappedBy = "personal")
	private Set<CompraCafeteria> compra;

	@ManyToOne
	protected Turno turno;

	private Boolean activo;
	
	protected double nomina;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id=id;
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif=nif;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre=nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos=apellidos;
	}

	public Integer getSalarioBase() {
		return this.salarioBase;
	}

	public void setSalarioBase(Integer salarioBase) {
		this.salarioBase=salarioBase;
	}

	public Turno getTurno() {
		return this.turno;
	}

	public void setTurno(Turno turno) {
		this.turno=turno;
	}

	public Set<CompraCafeteria> getCompra() {
		return compra;
	}

	public void setCompra(Set<CompraCafeteria> compra) {
		this.compra = compra;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getNIF() {
		return nif;
	}

	public void setNIF(String nIF) {
		nif = nIF;
	}
	
	public double getNomina() {
		return nomina;
	}

	public void setNomina(double nomina) {
		this.nomina = nomina;
	}
	
	public abstract double calcularNomina();
	
	public abstract TPersonalCafeteria toTransfer();
}
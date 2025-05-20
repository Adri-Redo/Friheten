/**
 * 
 */
package Negocio.Turno_Cafeteria;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.io.Serializable;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;
import java.util.Set;

import Negocio.Personal_Cafeteria.PersonalCafeteria;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@NamedQueries({ @NamedQuery(name = "Negocio.Turno.findAll", query = "select obj from Turno obj"),
		@NamedQuery(name = "Negocio.Turno.findByid", query = "select obj from Turno obj where :id = obj.id ")
		})
public class Turno implements Serializable {
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
	public Turno() {
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@OneToMany(mappedBy = "turno", fetch = FetchType.EAGER)
	private Set<PersonalCafeteria> personal;
	
	@Version
	private int version;
	private Integer num_horas;
	private String nombre_turno;
	private Boolean activo;
	
	public boolean getActivo() {
		return activo;
	}
	
	public int getIdTurno() {
		return id;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int getNumHoras() {
		return num_horas;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	
	public String getNombreTurno() {
		return nombre_turno;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
		
	public void setIdTurno(Integer id) {
		this.id = id;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setNumHoras(Integer numHoras) {
		this.num_horas = numHoras;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setNombreTurno(String nombreTurno) {
		this.nombre_turno = nombreTurno;
	}

	public Set<PersonalCafeteria> getPersonal() {
		return personal;
	}

//	public void setPersonal(Set<PersonalCafeteria> personal) {
//		this.personal = personal;
//	}
	
}
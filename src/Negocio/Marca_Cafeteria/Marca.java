/**
 *
 */
package Negocio.Marca_Cafeteria;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import Negocio.Producto_Cafeteria.Producto;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.persistence.NamedQueries;
// Ya no se necesita ManyToOne aquí si la relación es unidireccional desde Producto
// import jakarta.persistence.ManyToOne;


@Entity
@NamedQueries({
    @NamedQuery(name = "Negocio.Marca_Cafeteria.Marca.findByid", query = "select obj from Marca obj where obj.id = :id"),
    @NamedQuery(name = "Negocio.Marca_Cafeteria.Marca.findBynombre", query = "select obj from Marca obj where obj.nombre = :nombre"),
    @NamedQuery(name = "Negocio.Marca_Cafeteria.Marca.findAll", query = "select obj from Marca obj")
})
public class Marca implements Serializable {

    private static final long serialVersionUID = 0;


    public Marca() {
    }


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String categoria;

    @OneToMany(mappedBy = "marca")
    private Set<Producto> productos = new HashSet<>();;

    private Boolean activo;

    @Version
    private int version;

    /**
     * <!-- begin-UML-doc -->
     * <!-- end-UML-doc -->
     * @return
     * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    public Integer getId() {
        // begin-user-code
        // TODO Auto-generated method stub
        return this.id;
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
     * @return
     * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    public String getCategoria() {
        // begin-user-code
        // TODO Auto-generated method stub
        return this.categoria;
        // end-user-code
    }


    /**
     * <!-- begin-UML-doc -->
     * <!-- end-UML-doc -->
     * @param id
     * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    public void setId(Integer id) {
        // begin-user-code
        // TODO Auto-generated method stub
        this.id = id;
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
     * @param categoria
     * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    public void setCategoria(String categoria) {
        // begin-user-code
        // TODO Auto-generated method stub
        this.categoria = categoria;
        // end-user-code
    }

    public Set<Producto> getProductos() {
        return this.productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
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
        // end-user-codeS
    }


    /**
     * <!-- begin-UML-doc -->
     * <!-- end-UML-doc -->
     * @param activo
     * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
     */
    public void setActivo(Boolean activo) {
        // begin-user-code
        // TODO Auto-generated method stub
        this.activo = activo;
        // end-user-code
    }

    public int getVersion() {
        return version;
    }


    public void setVersion(int version) {
        this.version = version;
    }
}

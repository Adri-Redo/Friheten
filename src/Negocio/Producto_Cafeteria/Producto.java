package Negocio.Producto_Cafeteria;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.io.Serializable;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;
import java.util.Set;
import Negocio.Alergeno_Cafeteria.Alergeno;
import Negocio.Compra_Cafeteria.LineaCompra;
import Negocio.Marca_Cafeteria.Marca;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "Negocio.Producto.findByidProducto", query = "select obj from Producto obj where obj.idProducto = :idProducto"),
    @NamedQuery(name = "Negocio.Producto.findBynombre", query = "select obj from Producto obj where obj.nombre = :nombre"),
    @NamedQuery(name = "Negocio.Producto.findAll", query = "select obj from Producto obj")
})
public abstract class Producto implements Serializable {

    private static final long serialVersionUID = 0;

    public Producto() {
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @ManyToMany(mappedBy = "producto")
    private Set<Alergeno> alergeno;

    private String nombre;

    private double precio;

    private Integer stock;

    private char nivelNutricion;

    private Boolean activo;

    @OneToMany(mappedBy = "producto")
    private Set<LineaCompra> lineaCompra;

    @Version
    private int version;

    public void setId(Integer id) {
        this.idProducto = id;
    }

    public Integer getId() {
        return this.idProducto;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return this.stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setNivelNutricion(char NivelNutricion) {
        this.nivelNutricion = NivelNutricion;
    }

    public char getNivelNutricion() {
        return this.nivelNutricion;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Boolean getActivo() {
        return this.activo;
    }

    public Set<Alergeno> getAlergenos() {
        return this.alergeno;
    }

    public abstract TProductoCafeteria toTransfer();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Producto other = (Producto) obj;
        if ((this.idProducto == null) ? (other.idProducto != null) : !this.idProducto.equals(other.idProducto)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return idProducto != null ? idProducto.hashCode() : 0;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Set<Alergeno> getAlergeno() {
        return alergeno;
    }

    public void setAlergeno(Set<Alergeno> alergeno) {
        this.alergeno = alergeno;
    }

    public Set<LineaCompra> getLineaCompra() {
        return lineaCompra;
    }

    public void setLineaCompra(Set<LineaCompra> lineaCompra) {
        this.lineaCompra = lineaCompra;
    }
}

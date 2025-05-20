/**
 * 
 */
package Negocio.Mueble;

import java.io.Serializable;

public class TMueble implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Double precio;
	private String material;
	private Integer stock;
	private String nombre;
	private Double peso;
	private Double medX;
	private Double medY;
	private Double medZ;
	private Integer idNave;
	private Boolean activo;

	
	public TMueble(Integer id, String nombre, Double precio, Integer cantidad, Double peso, Double x, Double y, Double z, String material, Integer idNave, Boolean activo) {
		this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = cantidad;
        this.peso = peso;
        this.medX = x;
        this.medY = y;
        this.medZ = z;
        this.material = material;
        this.idNave = idNave;
        this.activo = activo;
	}

	public TMueble() {}

	public Integer getId() {
		return this.id;
	}

	public Double getPrecio() {
		return this.precio;
	}
	
	public String getMaterial() {
		return this.material;
	}

	public Integer getStock() {
		return this.stock;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Double getPeso() {
		return this.peso;
	}

	public Double getMedX() {
		return this.medX;
	}

	public Double getMedY() {
		return this.medY;
	}
	
	public Double getMedZ() {
		return this.medZ;
	}
	public Integer getIdNave() {
		return this.idNave;
	}

	public Boolean getActivo() {
		return this.activo;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public void setMedX(Double medX) {
		this.medX = medX;
	}

	public void setMedY(Double medY) {
		this.medY = medY;
	}

	public void setMedZ(Double medZ) {
		this.medZ = medZ;
	}
	public void setIdNave(Integer idNave) {
		this.idNave = idNave;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	} 
	
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {

			return true;
		}
		if (obj == null || this.getClass() != obj.getClass()) {

			return false;
		}
		TMueble mueble = (TMueble) obj;
		
		boolean b_material = this.material.equals(mueble.material);
		boolean b_nombre = (this.nombre.equals(mueble.nombre));
		
		boolean b_medX = (this.medX.compareTo(mueble.medX)) == 0;
		boolean b_medY = (this.medY.compareTo(mueble.medY)) == 0;
		boolean b_medZ = (this.medZ.compareTo(mueble.medZ)) == 0;
		boolean b_peso = (this.peso.compareTo(mueble.peso))==0;
		boolean b_precio = (this.precio.compareTo(mueble.precio))==0;
		
		boolean b_idNave = (this.idNave.compareTo(mueble.idNave)) == 0;
		boolean b_id = (this.id.compareTo(mueble.id)) == 0;
		boolean b_stock = (this.stock.compareTo(mueble.stock)) == 0;
 
		
		boolean sol = b_material && b_medX && b_medY && b_medZ && b_peso && b_precio && b_idNave && b_nombre && b_id && b_stock;
		
		return sol;
	}

}

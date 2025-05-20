package Negocio.Producto_Cafeteria;

import jakarta.persistence.Entity;

@Entity
public class Bebida extends Producto{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int nivelAzucar;

	public int getNivelAzucar() {
		return nivelAzucar;
	}

	public void setNivelAzucar(int nivelAzucar) {
		this.nivelAzucar = nivelAzucar;
	}

	@Override
	public TProductoCafeteria toTransfer() {
	    return new TBebida(
	            this.getId(),
	            this.getNombre(),
	            this.getPrecio(),
	            this.getStock(),
	            this.getNivelNutricion(),
	            this.getActivo(),
	            this.getNivelAzucar(),
	            this.getMarca() != null ? this.getMarca().getId() : null
	        );
	}
}

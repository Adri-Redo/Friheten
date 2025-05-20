package Negocio.Producto_Cafeteria;

import jakarta.persistence.Entity;

@Entity
public class Comida extends Producto{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String tipoComida;

	public String getTipoComida() {
		return tipoComida;
	}
	public void setTipoComida(String tipoComida) {
		this.tipoComida = tipoComida;
	}

	@Override
	public TProductoCafeteria toTransfer() {
	    return new TComida(
	            this.getId(),
	            this.getNombre(),
	            this.getPrecio(),
	            this.getStock(),
	            this.getNivelNutricion(),
	            this.getActivo(),
	            this.getTipoComida(),
	            this.getMarca() != null ? this.getMarca().getId() : null
	        );
	}
}

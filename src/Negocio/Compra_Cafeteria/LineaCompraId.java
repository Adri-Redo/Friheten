package Negocio.Compra_Cafeteria;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class LineaCompraId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_compra;
	private Integer idproducto;
	
	public LineaCompraId() {
		
	}
	
	public LineaCompraId(Integer id_compra, Integer idproducto) {
		this.id_compra = id_compra;
		this.idproducto =idproducto;
	}

	public Integer getid_compra() {
		return id_compra;
	}

	public void setIdCompra(Integer id_compra) {
		this.id_compra = id_compra;
	}

	public Integer getIDProducto() {
		return idproducto;
	}

	public void setIDProducto(Integer idproducto) {
		this.idproducto = idproducto;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof LineaCompraId))
			return false;
		LineaCompraId pk = (LineaCompraId) obj;
		if (!(id_compra == pk.getid_compra()))
			return false;
		if (!(idproducto == pk.getIDProducto()))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int hashcode = 0;
		hashcode += id_compra;
		hashcode +=idproducto;
		return hashcode;
	}
}

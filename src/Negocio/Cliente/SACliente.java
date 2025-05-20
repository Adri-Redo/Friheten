package Negocio.Cliente;

import java.util.List;

public interface SACliente {

	public Integer create(TCliente tCliente);

	public TCliente read(Integer id);

	public List<TCliente> readAll();

	public Integer update(TCliente tMueble);

	public Integer delete(Integer id);

}

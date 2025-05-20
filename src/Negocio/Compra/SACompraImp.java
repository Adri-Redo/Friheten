
package Negocio.Compra;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Integracion.Cliente.DAOCliente;
import Integracion.Compra.DAOCompra;
import Integracion.Compra.DAOLineaCompra;
import Integracion.FactoryIntegracion.FactoryIntgr;
import Integracion.FactoryIntegracion.FactoryIntgrImp;
import Integracion.Mueble.DAOMueble;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Cliente.TCliente;
import Negocio.Mueble.TMueble;
import Negocio.Personal.TPersonal;

public class SACompraImp implements SACompra {
	
	public TCarrito createCarrito(TCompra tCompra) {
		Transaction transaccion = null;
		try {
			transaccion = TransactionManager.getInstance().nuevaTransaccion();
			transaccion.start();
			if (FactoryIntgr.getInstance().generateDAOPersonal().read(tCompra.getIdPersonal()) == null
					|| FactoryIntgr.getInstance().generateDAOCliente().read(tCompra.getIdCliente()) == null)
				return null;

			TCarrito carrito = new TCarrito();
			carrito.setIdCliente(tCompra.getIdCliente());
			carrito.setIdPersonal(tCompra.getIdPersonal());
			transaccion.commit();
			return carrito;
		}catch(Exception e) {
			if(transaccion != null) {
				transaccion.rollback();
			}
			return null;
		}
	}

	public Integer delete(Integer idCompra) {
		Transaction transaccion = null;
		try {
			transaccion = TransactionManager.getInstance().nuevaTransaccion();
			transaccion.start();
			TCompra t_compra = FactoryIntgr.getInstance().generateDAOCompra().read(idCompra);

			if (t_compra != null) {
				transaccion.commit();
				return FactoryIntgr.getInstance().generateDAOCompra().delete(idCompra);
			}else {
				transaccion.rollback();
				return -1;
			}
		}catch(Exception e) {
			if(transaccion != null) {
				transaccion.rollback();
			}
			return -1;
		}
	}

	public Integer update(TCompra nuevos_datos) {
		Integer error = -1;
		Transaction transaccion = null;
		try {
			transaccion = TransactionManager.getInstance().nuevaTransaccion();
			transaccion.start();

				if(FactoryIntgr.getInstance().generateDAOCliente().read(nuevos_datos.getIdCliente()) != null) {
					if(FactoryIntgr.getInstance().generateDAOPersonal().read(nuevos_datos.getIdPersonal()) != null) {
						Date fecha_nueva = Date.valueOf(nuevos_datos.getFecha());
						java.util.Date today = new java.util.Date();
						if(!fecha_nueva.after(today)) {
							int res = FactoryIntgr.getInstance().generateDAOCompra().update(nuevos_datos.getId(), nuevos_datos);
							transaccion.commit();
							return res;
						}else {
							transaccion.rollback();
							return error;
						}
					}else {
						transaccion.rollback();
						return error;
					}
				}else {
					transaccion.rollback();
					return error;
				}
		}catch(Exception e) {
			transaccion.rollback();
			return error;
		}
	}
	
	

	public List<TMostrarCompra> readAll() {
		Transaction transaccion = null;
		try {
			transaccion = TransactionManager.getInstance().nuevaTransaccion();
			transaccion.start();
			List<TMostrarCompra> info = new ArrayList<>();
			Set<TCompra> compras = FactoryIntgr.getInstance().generateDAOCompra().readAll();
			for (TCompra compra : compras) {
				TPersonal personal = FactoryIntgr.getInstance().generateDAOPersonal().read(compra.getIdPersonal());
				TCliente cliente = FactoryIntgr.getInstance().generateDAOCliente().read(compra.getIdCliente());
				Set<TLineaCompra> lineas = FactoryIntgr.getInstance().generateDAOLineaCompra()
						.readLineasCompraPorCompra(compra.getId());
				info.add(new TMostrarCompra(personal, cliente, compra, lineas));
			}
			transaccion.commit();
			return info;
		}catch(Exception e) {
			transaccion.rollback();
			return null;
		}
	}

	public TMostrarCompra read(Integer idCompra) {
		Transaction transaccion = null;
		try {
			transaccion = TransactionManager.getInstance().nuevaTransaccion();
			transaccion.start();
			TCompra compra = FactoryIntgr.getInstance().generateDAOCompra().read(idCompra);
			if (compra == null) {
				transaccion.rollback();
				return null;
			}
			TPersonal personal = FactoryIntgr.getInstance().generateDAOPersonal().read(compra.getIdPersonal());
			TCliente cliente = FactoryIntgr.getInstance().generateDAOCliente().read(compra.getIdCliente());
			Set<TLineaCompra> lineas = FactoryIntgr.getInstance().generateDAOLineaCompra()
					.readLineasCompraPorCompra(idCompra);

			transaccion.commit();
			return new TMostrarCompra(personal, cliente, compra, lineas);
		}catch(Exception e) {
			transaccion.rollback();
			return null;
		}
		
	}

	public List<TMostrarCompra> readByIDCliente(Integer idCliente) {
		Transaction transaccion = null;
		try {
			transaccion = TransactionManager.getInstance().nuevaTransaccion();
			transaccion.start();
			List<TMostrarCompra> info = new ArrayList<>();
			Set<TCompra> compras = FactoryIntgr.getInstance().generateDAOCompra().readByIDCliente(idCliente);
			for (TCompra compra : compras) {
				TPersonal personal = FactoryIntgr.getInstance().generateDAOPersonal().read(compra.getIdPersonal());
				TCliente cliente = FactoryIntgr.getInstance().generateDAOCliente().read(compra.getIdCliente());
				Set<TLineaCompra> lineas = FactoryIntgr.getInstance().generateDAOLineaCompra()
						.readLineasCompraPorCompra(compra.getId());
				info.add(new TMostrarCompra(personal, cliente, compra, lineas));
			}
			transaccion.commit();
			return info;
		}catch(Exception e) {
			transaccion.rollback();
			return null;
		}
	}

	public TCarrito addProduct(TCarrito carrito, Integer unidades, Integer idMueble, String nombre) {
		TLineaCarrito lc = new TLineaCarrito();
		lc.setIdProducto(idMueble);
		lc.setUnidades(unidades);
		lc.setNombreProducto(nombre);
		carrito.addLineaCarrito(lc);

		return carrito;
	}

	public TCarrito deleteProduct(TCarrito carrito, String nombre) {
		carrito.deleteLineaCarrito(carrito.get_LineaCarrito_por_nombre(nombre));
		return carrito;
	}

	public void close(TCarrito carrito) {
		Transaction transaccion = null;
		try {
			transaccion = TransactionManager.getInstance().nuevaTransaccion();
			transaccion.start();
			try {
				FactoryIntgr factory = FactoryIntgr.getInstance();
				DAOCliente dao_cliente = factory.generateDAOCliente();
				TCliente cliente = dao_cliente.read(carrito.getIdCliente());
				TCompra compra = new TCompra();
				compra.setFecha(LocalDateTime.now().toString());
				compra.setActivo(true);
				compra.setIdPersonal(carrito.getIdPersonal());
				compra.setIdCliente(carrito.getIdCliente());
				int precioTotal = 0;
				for (TLineaCarrito lc : carrito.getLineasCarrito()) {
					DAOMueble dao_mueble = factory.generateDAOMueble();
					TMueble mueble = dao_mueble.read(lc.getIdProducto());
					precioTotal += mueble.getPrecio() * lc.getUnidades();
				}
				compra.setPrecioTotal(precioTotal);
				DAOCompra dao_compra = factory.generateDAOCompra();
				int id_compra = dao_compra.create(compra);

				if (cliente != null) {
					Set<TLineaCarrito> lista_carrito = carrito.getLineasCarrito();
					for (TLineaCarrito lc : lista_carrito) {
						DAOMueble dao_mueble = factory.generateDAOMueble();
						TMueble mueble = dao_mueble.read(lc.getIdProducto());

						if (mueble.getId() == lc.getIdProducto() && mueble.getStock() >= lc.getUnidades()) {
							TLineaCompra l_compra = new TLineaCompra();
							l_compra.setIdCompra(id_compra);
							l_compra.setIdMueble(lc.getIdProducto());
							l_compra.setUnidades(lc.getUnidades());
							l_compra.setPrecio(mueble.getPrecio());

							// actualizamos el stock de muebles
							mueble.setStock(mueble.getStock() - lc.getUnidades());
							FactoryIntgr.getInstance().generateDAOMueble().update(mueble);

							DAOLineaCompra dao_linea_compra = factory.generateDAOLineaCompra();
							dao_linea_compra.create(l_compra);
						}else {
							transaccion.rollback();
						}
					}
				}else {
					transaccion.rollback();
				}

				transaccion.commit();
			} catch (Exception e2) {
				transaccion.rollback();
			}
		} catch (Exception e) {
			transaccion.rollback();
		}
	}

}
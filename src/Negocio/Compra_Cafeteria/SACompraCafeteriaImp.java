/**
 * 
 */
package Negocio.Compra_Cafeteria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Negocio.Compra.TCarrito;
import Negocio.Compra.TLineaCarrito;
import Negocio.EntityManagerFactory.EntityManagerFactoryFactory;
import Negocio.Personal_Cafeteria.PersonalCafeteria;
import Negocio.Personal_Cafeteria.TPersonalCafeteria;
import Negocio.Producto_Cafeteria.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

public class SACompraCafeteriaImp implements SACompraCafeteria {

	public TCarrito create(TCompraCafeteria tcompra_caf) {
		TCarrito carrito = null;

		EntityManagerFactory eFactory = EntityManagerFactoryFactory.getInstance();
		EntityManager eManager = null;
		EntityTransaction eTransaction = null;
		try {
			eManager = eFactory.createEntityManager();
			eTransaction = eManager.getTransaction();
			eTransaction.begin();

			PersonalCafeteria personal = eManager.find(PersonalCafeteria.class, tcompra_caf.getIdPersonal(),
					LockModeType.OPTIMISTIC);
			if (personal != null && personal.getActivo()) {
				carrito = new TCarrito();
				carrito.setIdPersonal(tcompra_caf.getIdPersonal());
				eTransaction.commit();
			} else {
				eTransaction.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (eTransaction != null) {
				eTransaction.rollback();
			}
		} finally {
			eManager.close();
		}

		return carrito;
	}

	public Integer update(TCompraCafeteria compraNueva) {
		EntityManagerFactory eFactory = EntityManagerFactoryFactory.getInstance();
		EntityManager eManager = null;
		EntityTransaction eTransaction = null;
		try {
			eManager = eFactory.createEntityManager();
			eTransaction = eManager.getTransaction();
			eTransaction.begin();

			CompraCafeteria compra = eManager.find(CompraCafeteria.class, compraNueva.getId());
			compra.setFecha(compraNueva.getFecha());
			if (compra.getPersonal().getId() != compraNueva.getIdPersonal()) {
				eManager.lock(compra.getPersonal(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				PersonalCafeteria nuevo_personal = eManager.find(PersonalCafeteria.class, compraNueva.getIdPersonal(),
						LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				if (!nuevo_personal.getActivo()) {
					return -1;
				}
				compra.setPersonal(nuevo_personal);
			}

			eTransaction.commit();
		} catch (Exception e) {
			if (eTransaction != null) {
				eTransaction.rollback();
			}
			return -1;
		} finally {
			eManager.close();
		}

		return 0;
	}

	public Integer returnProduct(Integer IDCompra, Integer IDProducto, Integer cantidad) {
		EntityManagerFactory eFactory = EntityManagerFactoryFactory.getInstance();
		EntityManager eManager = null;

		try {
			eManager = eFactory.createEntityManager();
			EntityTransaction eTransaction = eManager.getTransaction();
			eTransaction.begin();

			CompraCafeteria compra = eManager.find(CompraCafeteria.class, IDCompra);
			Producto producto = eManager.find(Producto.class, IDProducto);

			if (compra != null && producto != null) {
				Set<LineaCompra> lineasCompra = compra.getLineasCompra();
				double precioARestar = 0;
				for (LineaCompra lc : lineasCompra) {
					if (lc.getProducto().getId() == IDProducto) {
						if (lc.getCantidad() >= cantidad) {
							if (lc.getCantidad() == cantidad) {
								LineaCompra lineacomp = eManager.find(LineaCompra.class, lc.getId());
								compra.getLineasCompra().remove(lineacomp);
								eManager.remove(lineacomp);
							} else {
								lc.setCantidad(lc.getCantidad() - cantidad);
								lc.setPrecio(lc.getPrecio() - lc.getProducto().getPrecio() * cantidad);
							}
							precioARestar += producto.getPrecio() * cantidad;
							producto.setStock(producto.getStock() + cantidad);
						} else {
							eTransaction.rollback();
							eManager.close();
							return -1;
						}
					}
				}
				compra.setPrecioTotal(compra.getPrecioTotal() - precioARestar);
				eTransaction.commit();
				eManager.close();
			} else {
				return -1;
			}
		} catch (PersistenceException e) {
			
		}

		return 0;
	}

	public List<TCompraCafeteria> readAll() {
		List<CompraCafeteria> listaCompras = new ArrayList<CompraCafeteria>();
		EntityManagerFactory eFactory = EntityManagerFactoryFactory.getInstance();
		EntityManager eManager = null;

		try {
			eManager = eFactory.createEntityManager();
			EntityTransaction eTransaction = eManager.getTransaction();
			eTransaction.begin();

			TypedQuery<CompraCafeteria> query = eManager.createNamedQuery("Negocio.Compra_Cafeteria.readAll",
					CompraCafeteria.class);
			listaCompras = query.getResultList();
			eTransaction.commit();

		} catch (PersistenceException e) {

		} finally {
			eManager.close();
		}

		List<TCompraCafeteria> listaComprasCafeteria = new ArrayList<TCompraCafeteria>();
		if (listaCompras != null) {
			for (CompraCafeteria c : listaCompras) {
				Integer idPersonal = c.getPersonal().getId();
				listaComprasCafeteria.add(
						new TCompraCafeteria(c.getID(), c.getFecha(), c.getPrecioTotal(), c.getActivo(), idPersonal));
			}
		}

		return listaComprasCafeteria;
	}

	public TCarrito addProduct(TCarrito carrito, Integer idProducto, Integer unidades, String nombreProducto) {
		TLineaCarrito lc = new TLineaCarrito(nombreProducto, idProducto, unidades);
		carrito.addLineaCarrito(lc);
		return carrito;
	}

	public TCarrito deleteProduct(TCarrito carrito, TLineaCarrito lc) {
		carrito.deleteLineaCarrito(lc);
		return carrito;
	}

	public void close(TCarrito carrito) {
		EntityManagerFactory eFactory = EntityManagerFactoryFactory.getInstance();
		EntityManager eManager = null;
		try {
			eManager = eFactory.createEntityManager();
			EntityTransaction eTransaction = eManager.getTransaction();
			eTransaction.begin();
			PersonalCafeteria personal = eManager.find(PersonalCafeteria.class, carrito.getIdPersonal(),
					LockModeType.OPTIMISTIC);

			CompraCafeteria compra = new CompraCafeteria();
			compra.setActivo(true);
			compra.setFecha(LocalDateTime.now().toString());
			compra.setPersonal(personal);
			double precioTotal = 0;
			eManager.flush(); // Al hacer flush se genera el ID en la entidad y no es necesario hacer commit

			Set<LineaCompra> lineasCompra = new HashSet<LineaCompra>();
			for (TLineaCarrito lc : carrito.getLineasCarrito()) {
				Producto producto = eManager.find(Producto.class, lc.getIdProducto());
				if (producto != null && producto.getActivo()) {
					precioTotal += producto.getPrecio() * lc.getUnidades();
					compra.setPrecioTotal(precioTotal);
					if (producto.getStock() >= lc.getUnidades()) {
						LineaCompraId lineaCompraID = new LineaCompraId();
						lineaCompraID.setIdCompra(compra.getID());
						lineaCompraID.setIDProducto(producto.getId());

						LineaCompra lineaCompra = new LineaCompra();
						lineaCompra.setCantidad(lc.getUnidades());
						lineaCompra.setCompra(compra);
						lineaCompra.setId(lineaCompraID);
						lineaCompra.setProducto(producto);
						lineaCompra.setPrecio(producto.getPrecio() * lc.getUnidades());
						lineasCompra.add(lineaCompra);
						producto.setStock(producto.getStock() - lc.getUnidades());
						eManager.persist(lineaCompra);
					}
				} else {
					eTransaction.rollback();
				}
			}
			
			if(precioTotal == 0) {
				compra.setPrecioTotal(precioTotal);
			}
			
			eManager.persist(compra);
			eTransaction.commit();
		} catch (PersistenceException e) {

		} finally {
			eManager.close();
		}
	}

	public TOACompraCafeteria read(Integer idCompra) {
		TOACompraCafeteria toa_compra = null;

		CompraCafeteria compra = null;
		EntityManagerFactory eFactory = EntityManagerFactoryFactory.getInstance();
		EntityManager eManager = null;

		try {
			eManager = eFactory.createEntityManager();
			EntityTransaction eTransaction = eManager.getTransaction();
			eTransaction.begin();
			// se puede resolver con un solo bucle
			compra = eManager.find(CompraCafeteria.class, idCompra);

			if (compra == null) {
				eTransaction.rollback();
			} else {
				PersonalCafeteria personal = eManager.find(PersonalCafeteria.class, compra.getPersonal().getId());
				Set<LineaCompra> lineasCompra = compra.getLineasCompra();
				toa_compra = new TOACompraCafeteria();
				TCompraCafeteria tcompra = new TCompraCafeteria(compra.getID(), compra.getFecha(),
						compra.getPrecioTotal(), compra.getActivo(), compra.getPersonal().getId());

				TPersonalCafeteria tpersonal = personal.toTransfer();

				toa_compra.setCompra(tcompra);
				toa_compra.setPersonal(tpersonal);
				Set<TLineaCompraCafeteria> tLineasCompra = new HashSet<TLineaCompraCafeteria>();
				for (LineaCompra lc : lineasCompra) {
					tLineasCompra.add(new TLineaCompraCafeteria(lc.getProducto().getId(), lc.getCompra().getID(),
							lc.getCantidad(), lc.getPrecio(), lc.getProducto().getNombre()));
				}
				toa_compra.setLineas(tLineasCompra);
				eTransaction.commit();
			}

		} catch (PersistenceException e) {

		} finally {
			eManager.close();

		}

		return toa_compra;
	}
}
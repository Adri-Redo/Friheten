/**
 *
 */
package Negocio.Producto_Cafeteria;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import Negocio.Marca_Cafeteria.Marca;
import Negocio.Alergeno_Cafeteria.Alergeno;
import Negocio.EntityManagerFactory.EntityManagerFactoryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import jakarta.persistence.NoResultException;

/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 *
 * @author hugod
 * @generated "UML a JPA
 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public class SAProductoCafeteriaImp implements SAProductoCafeteria {

	public synchronized Integer create(TProductoCafeteria tproducto_caf) {
	    Integer idProducto = -1;
	    EntityTransaction t = null;
	    EntityManager em = null;

	    // 1. Validación inicial del objeto transfer
	    if (tproducto_caf == null) {
	        return -1;
	    }
	    if (!(tproducto_caf instanceof TComida) && !(tproducto_caf instanceof TBebida)) {
	         System.err.println("Error en SAProducto.create: El TProductoCafeteria recibido no es TComida ni TBebida.");
	         return -3;
	    }
	    
        if (tproducto_caf.getIdMarca() == null || tproducto_caf.getIdMarca() <= 0) {
            return -5; // ID Marca inválido o no proporcionado
       }

	    EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();

       try {
            em = emf.createEntityManager();
            t = em.getTransaction();
            t.begin();

            Marca marca = em.find(Marca.class, tproducto_caf.getIdMarca());
            if (marca == null) {
                // Marca no encontrada
                if (t != null && t.isActive()) t.rollback();
                return -6;
            }
            if (marca.getActivo() == null || !marca.getActivo()) {
                 // Marca encontrada pero no está activa
                 if (t != null && t.isActive()) t.rollback();
                 return -7; 
            }

            // 2. Buscar si ya existe un producto con el mismo nombre
            Producto productoExistente = null;
            try {
                 productoExistente = em
                    .createNamedQuery("Negocio.Producto.findBynombre", Producto.class)
                    .setParameter("nombre", tproducto_caf.getNombre())
                    .getSingleResult();
                 
            } catch (NoResultException e) {
                productoExistente = null; // No existe, se puede crear
            }


            if (productoExistente == null) {
                // 3. El producto es nuevo, crear la entidad correspondiente
                Producto producto;

                if (tproducto_caf instanceof TComida) {
                    Comida comidaEntity = new Comida();
                    comidaEntity.setTipoComida(tproducto_caf.getTipoComida());
                    producto = comidaEntity;
                } else { // Es TBebida
                    Bebida bebidaEntity = new Bebida();
                    bebidaEntity.setNivelAzucar(tproducto_caf.getNivelAzucar());
                    producto = bebidaEntity;
                }

                // 4. Establecer los atributos comunes (heredados de Producto)
                producto.setActivo(true);
                producto.setNombre(tproducto_caf.getNombre());
                producto.setPrecio(tproducto_caf.getPrecio());
                producto.setStock(tproducto_caf.getStock());
                producto.setNivelNutricion(tproducto_caf.getNivelNutricion());
                producto.setMarca(marca);
                
                marca.getProductos().add(producto);
                
                // 5. Persistir la nueva entidad
                em.persist(producto);
                t.commit();
                idProducto = producto.getId();

            } else {
                // 6. El producto ya existe, verificar si está inactivo para reactivarlo
                if (productoExistente.getActivo()) {
                    idProducto = -4;
                } else {
                    productoExistente.setActivo(true);
                    productoExistente.setNombre(tproducto_caf.getNombre());
                    productoExistente.setPrecio(tproducto_caf.getPrecio());
                    productoExistente.setStock(tproducto_caf.getStock());
                    productoExistente.setNivelNutricion(tproducto_caf.getNivelNutricion());
                    productoExistente.setMarca(marca);
                    
                    marca.getProductos().add(productoExistente);

                    if (tproducto_caf instanceof TComida && productoExistente instanceof Comida) {
                        ((Comida) productoExistente).setTipoComida(((TComida) tproducto_caf).getTipoComida());
                    } else if (tproducto_caf instanceof TBebida && productoExistente instanceof Bebida) {
                        Integer nivelAzucar = ((TBebida) tproducto_caf).getNivelAzucar();
                        ((Bebida) productoExistente).setNivelAzucar(nivelAzucar != null ? nivelAzucar : 0);
                    }

                    t.commit();
                    idProducto = productoExistente.getId();
                }
            }
        } catch (Exception e) {
            if (t != null && t.isActive()) {
                t.rollback();

            }
            idProducto = -2; // Error general de transacción
        } finally {
            // 7. cerrar el EntityManager
            if (em != null) {
                em.close();
            }
        }

        return idProducto;
    }


	public Integer delete(Integer idProducto) {
		
		// El id producto no puede estar vacio
		if (idProducto == null)
			return -3;

		// El id producto no puede ser negativo
		if (idProducto < 0)
			return -2;

		EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
		EntityManager em = null;
		EntityTransaction t = null;

		try {
			em = emf.createEntityManager();
			t = em.getTransaction();
			t.begin();

			Producto deleteProducto = em.find(Producto.class, idProducto);

			if (deleteProducto != null) {
				
				if (deleteProducto.getActivo()) {
					//solo lo damos de baja si no tiene alergenos vinculados
					boolean tieneAlergenos = false;
					for (Alergeno alergeno : deleteProducto.getAlergenos()) {
						if (alergeno.getActivo()) {
							tieneAlergenos = true;
							break;
						}
					}
					if(!tieneAlergenos) {
						deleteProducto.setActivo(false); // lo damos de baja
						t.commit();
					}
					
				} else {
					idProducto = -2;
					t.rollback();
				}
			} else {
				t.rollback();
				idProducto = -3;
			}

			em.close();
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}
			if (em != null) {
				em.close();
			}
		}

		return idProducto;
	}

	public Integer update(TProductoCafeteria producto) {
	    // 1. Validación inicial del objeto transfer y ID
	    if (producto == null) {
	        return -1; // Error: tproducto es null
	    }
	    if (producto.getId() == null || producto.getId() <= 0) {
	        return -8; // ID Producto inválido
	    }

	    if (producto.getIdMarca() == null || producto.getIdMarca() <= 0) {
	        return -5; // ID Marca inválido o no proporcionado
	    }

	    EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
	    EntityManager em = null;
	    EntityTransaction t = null;
	    Integer resultId = producto.getId();

	    try {
	        em = emf.createEntityManager();
	        t = em.getTransaction();
	        t.begin();

	        // 2. Buscar la Marca y validar
	        Marca marca = em.find(Marca.class, producto.getIdMarca());
	        if (marca == null) {
	            if (t != null && t.isActive()) t.rollback();
	            return -6; // Marca no encontrada
	        }
	        if (marca.getActivo() == null || !marca.getActivo()) {
	             if (t != null && t.isActive()) t.rollback();
	             return -7; // Marca encontrada pero no está activa
	        }

	        // 3. Buscar el producto a actualizar
	        Producto productoExistente = em.find(Producto.class, resultId);

	        // 4. Validar existencia y estado del producto
	        if (productoExistente == null) {
	             if (t != null && t.isActive()) t.rollback();
	             return -3; // Producto no encontrado
	        }
	        if (!productoExistente.getActivo()) {
	             if (t != null && t.isActive()) t.rollback();
	             return -4; // Producto no está activo, no se puede modificar
	        }

	        // 5. Verificar unicidad del nuevo nombre (si cambió)
	        String nombreNuevo = producto.getNombre();
	        if (nombreNuevo == null || nombreNuevo.trim().isEmpty()) {
                 if (t != null && t.isActive()) t.rollback();
                 return -1; 
            }
            nombreNuevo = nombreNuevo.trim();

	        if (!nombreNuevo.equals(productoExistente.getNombre())) {
	            Producto productoConMismoNombre = em
	                .createQuery("Negocio.Producto.findBynombre", Producto.class)
	                .setParameter("nombre", nombreNuevo)
	                .getSingleResult();

	            if (productoConMismoNombre != null) {
	                if (t != null && t.isActive()) t.rollback();
	                return -9;
	            }
	        }
	        
	        boolean esComidaExistente = productoExistente instanceof Comida;
	        boolean esBebidaExistente = productoExistente instanceof Bebida;
	        boolean esTComidaInput = producto instanceof TComida;
	        boolean esTBebidaInput = producto instanceof TBebida;

	        // si el existente es Comida pero el input NO es TComida, o
	        // si el existente es Bebida pero el input NO es TBebida, es un error.
	        // (no se puede modificar de Comida a Bebida)
	        if ((esComidaExistente && !esTComidaInput) || (esBebidaExistente && !esTBebidaInput)) {
	             if (t != null && t.isActive()) t.rollback();
	             return -10;
	        }

	        // 6. Actualizar campos comunes
	        productoExistente.setNombre(nombreNuevo);
	        productoExistente.setPrecio(producto.getPrecio());
	        productoExistente.setStock(producto.getStock());
	        productoExistente.setNivelNutricion(producto.getNivelNutricion());
	        productoExistente.setMarca(marca);

	        // 7. Actualizar campos específicos (basado en el tipo de la entidad y el tipo del transfer)
	        if (productoExistente instanceof Comida && producto instanceof TComida) {
	            String tipoComidaInput = ((TComida) producto).getTipoComida();
	            if (tipoComidaInput == null || tipoComidaInput.trim().isEmpty()) {
                    if (t != null && t.isActive()) t.rollback();
                    return -1;
                }
	            ((Comida) productoExistente).setTipoComida(tipoComidaInput.trim());

	        } else if (productoExistente instanceof Bebida && producto instanceof TBebida) {
	            Integer nivelAzucar = ((TBebida) producto).getNivelAzucar();
	            if (nivelAzucar == null || nivelAzucar < 0) {
                    if (t != null && t.isActive()) t.rollback();
                    return -1;
                }
	            ((Bebida) productoExistente).setNivelAzucar(nivelAzucar); // Usar el valor validado
	        }

	        // 8. Commit
	        t.commit();
	        return resultId;

	    } catch (Exception e) {
	        if (t != null && t.isActive()) {
	            t.rollback();
	        }
	        e.printStackTrace();
	        return -2;
	    } finally {
	        // 9. Cerrar EntityManager
	        if (em != null) {
	            em.close();
	        }
	    }
	}

	public TProductoCafeteria read(Integer id) {
	    TProductoCafeteria producto = null;

	    if (id <= 0) {
	        return null;
	    }

	    EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
	    EntityManager em = null;
	    EntityTransaction t = null;

	    try {
	        em = emf.createEntityManager();
	        t = em.getTransaction();
	        t.begin();

	        Producto p = em.find(Producto.class, id);

	        if (p != null && p.getActivo()) {
				producto = p.toTransfer();
	            t.commit();
	        } else {
	            t.rollback();
	        }
	    } catch (Exception e) {
	        if (t != null && t.isActive()) {
	            t.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }

	    return producto;
	}

    public Set<TProductoCafeteria> readAll() {
        Set<TProductoCafeteria> resultList = new HashSet<>();
        EntityManager em = null;
        EntityTransaction t = null;
        EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();

        try {
            em = emf.createEntityManager();
            t = em.getTransaction();
            t.begin();

            List<Producto> productos = em
                .createNamedQuery("Negocio.Producto.findAll", Producto.class)
                .getResultList();

            t.commit();

            for (Producto productoEntity : productos) {
                resultList.add(productoEntity.toTransfer());
            }

        } catch (Exception e) {
            if (t != null && t.isActive()) {
            	t.rollback();
            }
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return resultList;
    }

	public Set<TProductoCafeteria> readByAlergenos(Integer idAlergeno) {
		Set<TProductoCafeteria> result = new HashSet<>();
		EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
		EntityManager em = null;
		EntityTransaction t = null;

		if (idAlergeno == null || idAlergeno <= 0) {
			return result;
		}

		try {
			em = emf.createEntityManager();
			t = em.getTransaction();
			t.begin();

			Alergeno alergeno = em.find(Alergeno.class, idAlergeno);

			if (alergeno != null && alergeno.getActivo()) {
				Set<Producto> productosEntities = alergeno.getProductos();

				result = productosEntities.stream()
						.filter(Producto::getActivo)
						.map(Producto::toTransfer)
						.collect(Collectors.toSet());

				t.commit();
			} else {
				if (t != null && t.isActive())
					t.rollback();
			}

		} catch (Exception e) {
			if (t != null && t.isActive()) {
				t.rollback();
			}
			e.printStackTrace();
			return null;
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return result;
	}
	
	// añadida por el cambio de relación de marca y producto
	public Set<TProductoCafeteria> readByIdMarca(Integer idMarca) {
		Set<TProductoCafeteria> result = new HashSet<>();
		EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
		EntityManager em = null;
		EntityTransaction t = null;

		if (idMarca == null || idMarca <= 0) {
			return result;
		}

		try {
			em = emf.createEntityManager();
			t = em.getTransaction();
			t.begin();

			Marca marca = em.find(Marca.class, idMarca);

			if (marca != null && marca.getActivo()) {
				Set<Producto> productosEntities = marca.getProductos();

				result = productosEntities.stream()
						.filter(Producto::getActivo)
						.map(Producto::toTransfer)
						.collect(Collectors.toSet());

				t.commit();
			} else {
				if (t != null && t.isActive())
					t.rollback();
			}

		} catch (Exception e) {
			if (t != null && t.isActive()) {
				t.rollback();
			}
			e.printStackTrace();
			return null;
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return result;
	}
}

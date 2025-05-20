/**
 * 
 */
package Negocio.Marca_Cafeteria;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import Negocio.EntityManagerFactory.EntityManagerFactoryFactory;
import Negocio.Producto_Cafeteria.Producto;
import Negocio.Producto_Cafeteria.TProductoCafeteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;



public class SAMarcaImp implements SAMarca {
	
	/*
	 * ERRORES:
	 * -1: marca es null
	 * -2: excepcion creando marca
	 * -3: producto es null
	 * 
	*/
	@Override
	public synchronized Integer create(TMarca marca) {
		int idMarca = -1;
		EntityTransaction t = null;
		
		if(marca != null) {
			EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
			EntityManager em = null;
			
			try {
				em = emf.createEntityManager();
				t = em.getTransaction();
				
				t.begin();
				
				// Obtener todas las marcas
	            List<Marca> todasMarcas = em.createNamedQuery("Negocio.Marca_Cafeteria.Marca.findAll", Marca.class).getResultList();
	            
	            Marca marcaPorNombre = null;
	            for(Marca m : todasMarcas) {
	                if(m.getNombre().equals(marca.getNombre())) {
	                    marcaPorNombre = m;
	                }
	            }
								
				if(marcaPorNombre == null) {
					Marca m = new Marca();
					
					m.setActivo(true);
					m.setNombre(marca.getNombre());
					m.setCategoria(marca.getCategoria());
					em.persist(m);
					t.commit();
					idMarca = m.getId();
				}else {
					if(marcaPorNombre.getActivo()) {
						idMarca = -1;
						t.rollback();
					}else{
						marcaPorNombre.setActivo(true);
						t.commit();
						idMarca = marcaPorNombre.getId();
					}
				}
				em.close();
			}catch(Exception e) {
				e.printStackTrace();
				idMarca = -2;
				if(t != null) t.rollback();
				if(em!=null) em.close();
			}
			
		}
		return idMarca;
		
	}

	
	public TMarca read(Integer id) {
		
		TMarca tm = null;
		
		if(id<= 0) {
			return tm;
		}
		
			EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
			EntityManager em = null;
			EntityTransaction t = null;
			
			try {
				em = emf.createEntityManager();
				t = em.getTransaction();
				
				t.begin();
				Marca m = em.find(Marca.class, id);
				
				if(m != null) {
					
					tm = new TMarca(m.getId(), m.getNombre(), m.getCategoria(), m.getActivo());
					t.commit();
				}else {
					t.rollback();
				}
				em.close();
			}catch(Exception e) {
				if(t != null) {
					t.rollback();
				}
				if(em != null) {
					em.close();
				}
			}
		return tm;
		
	}

	
	/*
	 *ERRORES: 
	 *-1: marca no encontrada
	 *-2: errores durante la modificacion
	 *-3: producto no encontrado
	 * 
	 */
	
	public Integer update(TMarca marca) {
		
		int idMarca = marca.getId();
		if(idMarca<=0) return -1;
		
			
		EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
		EntityManager em = null;
		EntityTransaction t = null;
		
			try {
				em = emf.createEntityManager();
				t = em.getTransaction();
				
				t.begin();
				
				Marca m = em.find(Marca.class, marca.getId());
				
				if(m == null) {
					idMarca = -1;
					t.rollback();
				}else {
					if(m.getActivo()) {
						m.setNombre(marca.getNombre());
						m.setCategoria(marca.getCategoria());
						idMarca = m.getId();
						t.commit();
					}else {
						idMarca = -1;
						t.rollback();
					}
				}
				em.close();
			}
			catch(Exception e) {
				if(t != null) {
					t.rollback();
				}
				if(em != null) {
					em.close();
				}
			}
			
		return idMarca;
		
	}

	
	public Integer delete(Integer id) {
		if(id<=0) return -2;
		else if (id != null) {

	        EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
	        EntityManager em = null;
	        EntityTransaction t = null;
	        
	        try {
	            em = emf.createEntityManager();
	            t = em.getTransaction();

	            t.begin();

	            
	            Marca m = em.find(Marca.class,  id);

	            if(m!=null) {
	            	if(m.getActivo()) {
	            		Set<Producto> productos = m.getProductos();
	            		if(!productos.isEmpty()) {
	                        
	                        boolean todosInactivos = true;
	                        for(Producto producto : productos) {
	                            if(producto.getActivo()) {  
	                                todosInactivos = false;
	                                break;
	                            }
	                        }
	                        
	                        if(todosInactivos) {
	                            m.setActivo(false);
	                            t.commit();
	                        } else {
	                            id = -4;  // Hay productos activos
	                            t.rollback();
	                        }
	                    } else {
	                        // Lista de productos vacía
	                        m.setActivo(false);
	                        t.commit();
	                    }
	            		
	            	}else {
	            		id = -2;
	            		t.rollback();
	            	}
	            }else {
	            	t.rollback();
	            	id = -3;
	            }
	            em.close();
	        
	        } catch (Exception e) {
				if(t != null) {
					t.rollback();
				}
				if(em != null) {
					em.close();
				}
	        }
	    }

	    return id;
		
	}

	
	public List<TMarca> readAll() {
		List<TMarca> lista = null;
		
		EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
		EntityManager em = null;
		EntityTransaction t = null;
		
		try {
			em = emf.createEntityManager();
			t = em.getTransaction();
			
			t.begin();
			
			List<Marca> listaMarca = em.createNamedQuery("Negocio.Marca_Cafeteria.Marca.findAll", Marca.class).getResultList();
			
			if(listaMarca != null) {
				lista = new ArrayList<>();
				
				for(Marca m : listaMarca) {
					TMarca tm;
					
					tm = new TMarca(m.getId(),m.getNombre(),m.getCategoria(),m.getActivo());
					
					lista.add(tm);
				}
				
				t.commit();
			}else {
				t.rollback();
			}
			em.close();
		} catch(Exception e) {
			if(t != null) {
				t.rollback();
			}
			if(em != null) {
				em.close();
			}
		}
		
		return lista;
	}
	public Set<TProductoCafeteria> readProductosbyMarca(Integer idMarca) {
		
		Set<TProductoCafeteria> tp = new HashSet<TProductoCafeteria>();
		//No creo que se pueda hacer esto (preguntar)
		Set<Producto> productos = null;

		EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
		EntityManager em = null;
		EntityTransaction t = null;
		
		try {
			em = emf.createEntityManager();
			t = em.getTransaction();
			
			t.begin();
			Marca marc = em.find(Marca.class, idMarca);
			
			if(marc != null && marc.getActivo()) {
				productos = marc.getProductos();
				for(Producto p : productos) {
					TProductoCafeteria producto = p.toTransfer();
					if(producto.getActivo()) tp.add(producto);
				}
				t.commit();
			}
			else {
				t.rollback();
			}
			em.close();

		}catch(Exception e) {
			if(t != null) {
				t.rollback();
			}
			if(em != null) {
				em.close();
			}
		}
		return tp;	
	}
}
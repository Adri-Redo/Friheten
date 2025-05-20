/**
 * 
 */
package Negocio.Alergeno_Cafeteria;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Negocio.EntityManagerFactory.EntityManagerFactoryFactory;
import Negocio.Marca_Cafeteria.Marca;
import Negocio.Marca_Cafeteria.TMarca;
import Negocio.Personal_Cafeteria.PersonalCafeteria;
import Negocio.Producto_Cafeteria.Producto;
import Negocio.Turno_Cafeteria.Turno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;


public class SAAlergenoImp implements SAAlergeno {
	
	//ERRORES:
		// -1: Alergeno es null
		// -2: excepcion creando Alergeno
		// -3: producto es null
		// -4: Alergeno ya activo
	
	public synchronized Integer create(TAlergeno alergeno) {
		int idAlergeno = -1;
		EntityTransaction t = null;
		
		if (alergeno != null) {
			
			EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
			EntityManager em = null;
			
			try {
				em = emf.createEntityManager();
				t = em.getTransaction();
				
				t.begin();
				
				//Buscar alergeno en BD
				
				List<Alergeno> alergenosporNombre = em.createNamedQuery("Negocio.Alergeno.findBynombre",Alergeno.class)
						.setParameter("nombre", alergeno.getNombre()).getResultList();
				
				//Crear alergeno
				if(alergenosporNombre.isEmpty()) {
					
					Alergeno al = new Alergeno();
					
					al.setActivo(true);
					al.setFuente(alergeno.getFuente());
					al.setNombre(alergeno.getNombre());
					al.setRiesgo(alergeno.getRiesgo());
					em.persist(al);
					t.commit();
					idAlergeno = al.getId();
					
				}else {//Personal no activo
					if(alergenosporNombre.get(0).getActivo()){//Personal es activo
						idAlergeno = -4;
						t.rollback();
					}else {
						Alergeno al = alergenosporNombre.get(0);
						al.setActivo(true);
						al.setFuente(alergeno.getFuente());
						al.setNombre(alergeno.getNombre());
						al.setRiesgo(alergeno.getRiesgo());
						t.commit();
						idAlergeno = alergenosporNombre.get(0).getId();
					}
					
				}
				em.close();
				
				
			}catch(Exception e) {
				e.printStackTrace();
				idAlergeno = -2;
				if(t != null) {
					t.rollback();
				}
				if(em != null) {
					em.close();
				}
				if(emf != null) {
					
				}
			}
		}
			
		return idAlergeno;
	}

	
	public TAlergeno read(Integer id) {
		TAlergeno al = null;
		
		if(id<= 0) {
			return al;
		}
			
		
			EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
			EntityManager em = null;
			EntityTransaction t = null;
			
			try {
				em = emf.createEntityManager();
				t = em.getTransaction();
				
				t.begin();
				Alergeno a = em.find(Alergeno.class, id);
				
				if(a != null) {
					al = new TAlergeno();
					al.setFuente(a.getFuente());
					al.setIdAlergeno(a.getId());
					al.setRiesgo(a.getRiesgo());
					al.setActivo(a.getActivo());
					al.setNombre(a.getNombre());
					
					t.commit();
				}else {
					t.rollback();
				}
				em.close();
				;
			}catch(Exception e) {
				if(t != null) {
					t.rollback();
				}
				if(em != null) {
					em.close();
				}
			}
		return al;	
	}

	//ERRORES:
		// -1: id no es valido
		// -2: excepcion creando Alergeno
		// -3: el alergeno no existe
		// -4: Alergeno no esta activo
	
	public Integer update(TAlergeno alergeno) {
		
		int id = alergeno.getIdAlergeno();
		if(alergeno.getIdAlergeno()<= 0) {
			return -1;
		}
			
		
			EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
			EntityManager em = null;
			EntityTransaction t = null;
			
			try {
				em = emf.createEntityManager();
				t = em.getTransaction();
				
				t.begin();
				Alergeno a = em.find(Alergeno.class, alergeno.getIdAlergeno());
				
				if(a != null) {
					if(a.getActivo()) {
						a.setFuente(alergeno.getFuente());
						a.setNombre(alergeno.getNombre());
						a.setRiesgo(alergeno.getRiesgo());
						id = a.getId();
						t.commit();
					}
					else {
						id = -4;
						t.rollback();
					}
					
				}else {
					t.rollback();
					id = -3;
				}
				em.close();
				;
			}catch(Exception e) {
				if(t != null) {
					t.rollback();
				}
				if(em != null) {
					em.close();
				}
				
			}
		return id;	
	}

	
	public Integer delete(Integer id) {
		if(id<= 0) {
			return -1;
		}
			
		
			EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
			EntityManager em = null;
			EntityTransaction t = null;
			
			try {
				em = emf.createEntityManager();
				t = em.getTransaction();
				t.begin();
				Alergeno a = em.find(Alergeno.class, id);
				

				if(a != null) {
					if(a.getActivo()) {
						// si no esta vinculado a ningun producto
						boolean vinculado = false;
						for(Producto p: a.getProductos()) {
							//realmente no haria falta comprobar si estan activos ya que no se puede dar de baja un producto si esta vinculado a un alergeno
							if(p.getActivo()) {
								vinculado = true;
							}
						}
						if(!vinculado) {
							a.setActivo(false); // lo damos de baja
							t.commit();
						}
						else {
							id = -4;
							t.rollback();
						}
						
						
						
					}
					else {
						id = -2;
						t.rollback();
					}
					
				}else {
					t.rollback();
					id = -3;
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
		return id;
	}

	
	public Integer link(Integer idProducto, Integer idAlergeno) {
		int error = -1;
		if(idProducto <= 0 || idAlergeno <= 0) {
			return error;
		}
			
		
			EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
			EntityManager em = null;
			EntityTransaction t = null;
			
			try {
				em = emf.createEntityManager();
				t = em.getTransaction();
				
				t.begin();
				//aunque JPA ya actualiza de manera automatica al modificar el objeto, lo hacemos explicitamente para que no haya problemas con otros 
				//proveedores de persistencia que no sean jakarta
				Alergeno a = em.find(Alergeno.class, idAlergeno, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				Producto p = em.find(Producto.class,idProducto,LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		
				if(a != null && p != null) {
					if(a.getActivo() && p.getActivo()) {
						//si no estan vinculados ya
						
						if(!a.getProductos().contains(p)) {
							//anyadimos el producto a la lista de alergenos que es la poseedora de la relacion
							a.getProductos().add(p);
							
							t.commit();
							error = 1;
						}
						else {
							error = -5;
							t.rollback();
						}
					}
					else {
						error = -4;
						t.rollback();
					}
					
				}else {
					t.rollback();
					error = -3;
				}
				em.close();
				
			}catch(Exception e) {
				e.printStackTrace();
				error = -2;
				if(t != null) {
					t.rollback();
				}
				if(em != null) {
					em.close();
				}
				
			}
		return error;
	}
	
	public Integer unLink(Integer idProducto,Integer idAlergeno) {
		int error = -1;
		if(idProducto <= 0 || idAlergeno <= 0) {
			return error;
		}
			
		
			EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
			EntityManager em = null;
			EntityTransaction t = null;
			
			try {
				em = emf.createEntityManager();
				t = em.getTransaction();
				
				t.begin();
				//mismo caso que en vincular
				Alergeno a = em.find(Alergeno.class, idAlergeno,LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				Producto p = em.find(Producto.class,idProducto,LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				if(a != null && p != null) {
					if(a.getActivo() && p.getActivo()) {
						//si estan vinculados 
						if(a.getProductos().contains(p)) {
							a.getProductos().remove(p);
							t.commit();
							error = 1;
						}
						else {
							error = -5;
							t.rollback();
						}
					}
					else {
						error = -4;
						t.rollback();
					}
					
				}else {
					t.rollback();
					error = -3;
				}
				em.close();
				
			}catch(Exception e) {
				e.printStackTrace();
				error = -2;
				if(t != null) {
					t.rollback();
				}
				if(em != null) {
					em.close();
				}
				
			}
		return error;
		
	}

	
	public Set<TAlergeno> readAll() {
		Set<TAlergeno> al = null;

		EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
		EntityManager em = null;
		EntityTransaction t = null;
		
		try {
			em = emf.createEntityManager();
			t = em.getTransaction();
			
			t.begin();
			List<Alergeno> alergenos = em.createNamedQuery("Negocio.Alergeno.findAll",Alergeno.class).getResultList();
			
			if(alergenos != null) {
				al = new HashSet<>();
				for(Alergeno a: alergenos) {
					TAlergeno alergeno = new TAlergeno();
					alergeno.setFuente(a.getFuente());
					alergeno.setIdAlergeno(a.getId());
					alergeno.setRiesgo(a.getRiesgo());
					alergeno.setActivo(a.getActivo());
					alergeno.setNombre(a.getNombre());
					al.add(alergeno);
				}
				
				
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
		return al;	
	}

	
	public Set<TAlergeno> readbyProduct(Integer idProducto) {
		Set<TAlergeno> al = null;

		EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
		EntityManager em = null;
		EntityTransaction t = null;
		
		try {
			em = emf.createEntityManager();
			t = em.getTransaction();
			
			t.begin();
			Producto prod = em.find(Producto.class, idProducto);
			if(prod != null) {
				al = new HashSet<>();
				for(Alergeno a: prod.getAlergenos()) {			
					TAlergeno alergeno = new TAlergeno();
					alergeno.setFuente(a.getFuente());
					alergeno.setIdAlergeno(a.getId());
					alergeno.setRiesgo(a.getRiesgo());
					alergeno.setActivo(a.getActivo());
					alergeno.setNombre(a.getNombre());
					al.add(alergeno);
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
		return al;	
	}
}
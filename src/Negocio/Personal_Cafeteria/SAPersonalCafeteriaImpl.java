/**
 * 
 */
package Negocio.Personal_Cafeteria;

import java.util.ArrayList;
import java.util.List;

import Negocio.EntityManagerFactory.EntityManagerFactoryFactory;
import Negocio.Turno_Cafeteria.Turno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;

/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 * 
 * @author hugod
 * @generated "UML a JPA
 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public class SAPersonalCafeteriaImpl implements SAPersonalCafeteria {

	// ERRORES:
	// -1: personal es null
	// -2: excepcion creando personal
	// -3: turno es null
	// -4: personal ya activo
	// -5: ya hay personal con mismo nif
	@Override
	public synchronized Integer create(TPersonalCafeteria personal) {
		int idPersonal = -1;

		if (personal != null) {

			EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
			EntityManager em = null;
			EntityTransaction t = null;

			try {
				em = emf.createEntityManager();
				t = em.getTransaction();
				t.begin();
				
				List<PersonalCafeteria> mismoNif = em
						.createNamedQuery("Negocio.PersonalCafeteria.findByNif", PersonalCafeteria.class)
						.setParameter("Nif", personal.getNif()).getResultList();
				
				//comprobamos el turno
				Turno turno = em.find(Turno.class, personal.getTurno(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);

				if (turno == null || !turno.getActivo()) {
					t.rollback();
					return -3;
				}
				
				// Sacamos datos del transfer
				String nombre = personal.getNombre();
				String apellidos = personal.getApellidos();
				String nif = personal.getNif();
				Integer salario = personal.getSalarioBase();
				
				if (mismoNif.size() == 0) { //No existe en BD
					PersonalCafeteria pc;
					if (personal.getIsJefe()) {
						pc = new Jefe();
					} else {
						pc = new Empleado();
					}
					pc.setNombre(nombre);
					pc.setApellidos(apellidos);
					pc.setNif(nif);
					pc.setSalarioBase(salario);
					pc.setTurno(turno);
					pc.setActivo(true);

					if (personal.getIsJefe()) {
						String cargo = personal.getCargo();
						Integer responsabilidades = personal.getResponsabilidades();
						((Jefe) pc).setCargo(cargo);
						((Jefe) pc).setResponsabilidades(responsabilidades);
					} else {
						Integer bonificaciones = personal.getBonificaciones();
						String puesto = personal.getPuesto();
						((Empleado) pc).setBonificaciones(bonificaciones);
						((Empleado) pc).setPuesto(puesto);
					}

					em.persist(pc);
					t.commit();
					idPersonal = pc.getId();
					
				}else if(mismoNif.size()==1) {//Existe
					PersonalCafeteria pc = mismoNif.get(0);
					if (pc.getActivo()) {// Personal esta activo
						t.rollback();
						return -4;
					}
					pc.setNombre(nombre);
					pc.setApellidos(apellidos);
					pc.setSalarioBase(salario);
					pc.setTurno(turno);
					pc.setActivo(true);
					
					if (personal.getIsJefe()) {
						String cargo = personal.getCargo();
						Integer responsabilidades = personal.getResponsabilidades();
						((Jefe) pc).setCargo(cargo);
						((Jefe) pc).setResponsabilidades(responsabilidades);
					} else {
						Integer bonificaciones = personal.getBonificaciones();
						String puesto = personal.getPuesto();
						((Empleado) pc).setBonificaciones(bonificaciones);
						((Empleado) pc).setPuesto(puesto);
					}
					
					t.commit();
					idPersonal = pc.getId();
					
				}else {
					t.rollback();
					em.close();
					return -5;
				}
				em.close();

			} catch (Exception e) {
				e.printStackTrace();
				if(t != null) {
					t.rollback();
				}
				if(em != null) {
					em.close();
				}
				return -2;
			}
		}
		return idPersonal;
	}

	@Override
	public TPersonalCafeteria read(Integer id) {
		TPersonalCafeteria tp = null;

		// El id no es mayor de 0
		if (id <= 0)
			return tp;

		EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
		EntityManager em = null;
		EntityTransaction t = null;

		try {
			em = emf.createEntityManager();
			t = em.getTransaction();

			t.begin();

			// Buscar personal en BD
			PersonalCafeteria pc = em.find(PersonalCafeteria.class, id);

			if (pc != null) {

				// JEFE
				if (pc instanceof Jefe) {
					Jefe jefe = (Jefe) pc;
					tp = new TPersonalCafeteria(jefe.getId(), jefe.getNif(), jefe.getNombre(), jefe.getApellidos(),
							jefe.getResponsabilidades(), null, null, true, jefe.getCargo(), jefe.getSalarioBase(),
							jefe.getTurno().getIdTurno(), jefe.getActivo());
				} else if (pc instanceof Empleado) {
					Empleado empleado = (Empleado) pc;
					tp = new TPersonalCafeteria(empleado.getId(), empleado.getNif(), empleado.getNombre(),
							empleado.getApellidos(), null, empleado.getPuesto(), empleado.getBonificaciones(), false,
							null, empleado.getSalarioBase(), empleado.getTurno().getIdTurno(), empleado.getActivo());
				}

				t.commit();
			} else {
				t.rollback();
			}

			em.close();

		} catch (Exception e) {
			if(t != null) {
				t.rollback();
			}
			if(em != null) {
				em.close();
			}
			return null;
		}
		return tp;
	}

	@Override
	public List<TPersonalCafeteria> readAll() {

		List<TPersonalCafeteria> lista = null;

		EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
		EntityManager em = null;
		EntityTransaction t = null;
		try {
			em = emf.createEntityManager();
			t = em.getTransaction();

			t.begin();

			List<PersonalCafeteria> listaPersonal = em
					.createNamedQuery("Negocio.PersonalCafeteria.findAll", PersonalCafeteria.class).getResultList();

			lista = new ArrayList<>();

			for (PersonalCafeteria pc : listaPersonal) {
				TPersonalCafeteria tpc = null;

				// JEFE
				if (pc instanceof Jefe) {
					Jefe jefe = (Jefe) pc;
					tpc = new TPersonalCafeteria(jefe.getId(), jefe.getNif(), jefe.getNombre(), jefe.getApellidos(),
							jefe.getResponsabilidades(), null, null, true, jefe.getCargo(), jefe.getSalarioBase(),
							jefe.getTurno().getIdTurno(), jefe.getActivo());
				} else if (pc instanceof Empleado) {
					Empleado empleado = (Empleado) pc;
					tpc = new TPersonalCafeteria(empleado.getId(), empleado.getNif(), empleado.getNombre(),
							empleado.getApellidos(), null, empleado.getPuesto(), empleado.getBonificaciones(), false,
							null, empleado.getSalarioBase(), empleado.getTurno().getIdTurno(), empleado.getActivo());
				}

				lista.add(tpc);
			}

			t.commit();
			em.close();

		} catch (Exception e) {
			if(t != null) {
				t.rollback();
			}
			if(em != null) {
				em.close();
			}
			return null;
		}

		return lista;
	}

	// ERRORES:
	// -1: personal no encontrado
	// -2: error durante la modificacion
	// -3: turno no encontrado
	// -4: existe personal con mismo nif
	// -5: personal no activo
	@Override
	public Integer update(TPersonalCafeteria personal) {
		int idPersonal = personal.getId();

		EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
		EntityManager em = null;
		EntityTransaction t = null;

		try {
			em = emf.createEntityManager();
			t = em.getTransaction();

			t.begin();

			// Buscar personal en BD
			PersonalCafeteria pc = em.find(PersonalCafeteria.class, idPersonal);

			// Verificar si el personal existe
			if (pc == null) {
				t.rollback();
				return -1; // Error, personal no encontrado
			}

			if (!pc.getActivo()) {
				return -5;
			}

			// Obtener los datos del personal
			String nombre = personal.getNombre();
			String apellidos = personal.getApellidos();
			String nif = personal.getNif();
			Integer salario = personal.getSalarioBase();
			Integer turnoId = personal.getTurno();
			Boolean isJefe = personal.getIsJefe(); 

			// Buscar si el NIF ya lo tiene otro personal
			List<PersonalCafeteria> resultList = em
					.createNamedQuery("Negocio.PersonalCafeteria.findByNif", PersonalCafeteria.class)
					.setParameter("Nif", nif).getResultList();
			if (resultList.size() > 0) {
				t.rollback();
				return -4;
			}

			pc.setNombre(nombre != null ? nombre : pc.getNombre());
			pc.setApellidos(apellidos != null ? apellidos : pc.getApellidos());
			pc.setNif(nif != null ? nif : pc.getNif());
			pc.setSalarioBase(salario != null ? salario : pc.getSalarioBase());

			//Si no han cambiado el turno, nada
			// Actualizar turno si es válido
			if(turnoId>0) {
				//Notificamos al turno antiguo de la desvinculación
				em.lock(pc.getTurno(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				//Notificar turno nuevo de vinculación
				Turno turno = em.find(Turno.class, turnoId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				if(turno!=null && turno.getActivo())
					pc.setTurno(turno);
				else {
					t.rollback();
					em.close();
					return -3;
				}
			}
			

			// Actualizar cargo o bonificaciones según el tipo de personal
			if (isJefe) {
				String cargo = personal.getCargo();
				Integer responsabilidades = personal.getResponsabilidades();
				((Jefe) pc).setCargo(cargo != null ? cargo : ((Jefe) pc).getCargo());
				((Jefe) pc).setResponsabilidades(
						responsabilidades != null ? responsabilidades : ((Jefe) pc).getResponsabilidades());
			} else {
				Integer bonificaciones = personal.getBonificaciones();
				String puesto = personal.getPuesto();
				((Empleado) pc).setBonificaciones(
						bonificaciones != null ? bonificaciones : ((Empleado) pc).getBonificaciones());
				((Empleado) pc).setPuesto(puesto != null ? puesto : ((Empleado) pc).getPuesto());
			}
			t.commit();

			em.close();

		} catch (Exception e) {
			e.printStackTrace();
			if(t != null) {
				t.rollback();
			}
			if(em != null) {
				em.close();
			}
			return -2; 
		}

		return idPersonal;
	}

	// ERRORES:
	// -1: personal no encontrado
	// -2: error durante la eliminacion
	// -3: personal ya dado de baja
	// -4: id no válido
	@Override
	public Integer delete(Integer id) {

		if (id >=0) {

			EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
			EntityManager em = emf.createEntityManager();
			EntityTransaction t = em.getTransaction();

			try {
				t.begin();

				// Buscar el personal en la base de datos
				PersonalCafeteria pc = em.find(PersonalCafeteria.class, id);
				
				
				

				// Si no se encuentra el personal, retornar error -1
				if (pc == null) {
					t.rollback();
					return -1;
				} else {
					// Si el personal está activo, lo desactivamos
					if (pc.getActivo()) {
						pc.setActivo(false);
						
						em.lock(pc.getTurno(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
						
						t.commit();
						em.close();
						return id;
					} else {
						return -3;
					}
				}

				
			} catch (Exception e) {
				if(t != null) {
					t.rollback();
				}
				if(em != null) {
					em.close();
				}
				return -2;
			}
		}

		return -4;
	}

	@Override
	public List<TPersonalCafeteria> readPersonalByTurno(Integer idTurno) {
		List<TPersonalCafeteria> personalSet = new ArrayList<>();

		if (idTurno < 0) {
			return null;
		}
		
		EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
		EntityManager em = null;
		EntityTransaction t = null;

		try {
			em = emf.createEntityManager();
			t = em.getTransaction();

			t.begin();

			// Obtengo todos los personales
			List<PersonalCafeteria> listaPersonal = em
					.createNamedQuery("Negocio.PersonalCafeteria.findAll", PersonalCafeteria.class)
					.getResultList();

			// Filtrar por turno y convertir el resultado en un conjunto de TPersonalCafeteria
			for (PersonalCafeteria pc : listaPersonal) {
				if(pc.getTurno().getIdTurno() == idTurno) {
					TPersonalCafeteria transfer = pc.toTransfer();
					
					personalSet.add(transfer);
				}
			}
			t.commit();
			em.close();

		} catch (Exception e) {
			if(t != null) {
				t.rollback();
			}
			if(em != null) {
				em.close();
			}
			return null;
		}

		return personalSet;
	}

	@Override
	public Double calcularNomina(Integer id) { 
		if(id < 0) return -1.0;
		
		EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		
		try {
			t.begin();
			
			PersonalCafeteria pc = em.find(PersonalCafeteria.class, id, LockModeType.OPTIMISTIC);
			if(pc == null || !pc.getActivo()) return -1.0;

			Double nomina = pc.calcularNomina();
			t.commit();
			em.close();
			return nomina;
		} catch (Exception e) {
			if(t != null) {
				t.rollback();
			}
			if(em != null) {
				em.close();
			}
			return -1.0;
		}
	}
}
/**
 * 
 */
package Negocio.Turno_Cafeteria;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import Negocio.EntityManagerFactory.EntityManagerFactoryFactory;
import Negocio.Personal_Cafeteria.PersonalCafeteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;

/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 * 
 * @author usuario_local
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class SATurnoImp implements SATurno {
	
	//ERRORES:
	//-1: Error creando en BD
	//-2: Turno con mismo nombre activo ya existe
	public synchronized Integer create(TTurnoCafeteria turno) {
		int idTurno = -1;

		if (turno != null) {

			EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
			EntityManager em = null;
			EntityTransaction t = null;

			try {
				em = emf.createEntityManager();
				t = em.getTransaction();

				t.begin();

				// Buscar turno en BD
				// Turno tc = em.find(Turno.class, idTurno,
				// LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				List<Turno> turnos = em.createNamedQuery("Negocio.Turno.findAll", Turno.class).getResultList();

				Turno tc = null;

				for (Turno _turno : turnos) {
					if (_turno.getNombreTurno().equals(turno.getNombreTurno())) {
						tc = _turno;
						break;
					}
				}

				if (tc != null) {
					if (tc.getActivo()) {
						t.rollback();
						idTurno = -2;
					} else {
						tc.setActivo(true);
						tc.setNumHoras(turno.getNumHoras());
						t.commit();
						idTurno = tc.getIdTurno();
					}
				} else {
					tc = new Turno();

					// Sacamos datos del transfer
					String nombre = turno.getNombreTurno();
					Integer numH = turno.getNumHoras();

					tc.setNombreTurno(nombre);
					tc.setNumHoras(numH);
					tc.setActivo(true);
					em.persist(tc);
					t.commit();
					idTurno = tc.getIdTurno();
				}

				em.close();

			} catch (Exception e) {
				e.printStackTrace();
				if (t != null)
					t.rollback();
				if (em != null)
					em.close();

			}
		}

		return idTurno;
	}

	//ERRORES:
	//-1: Error eliminando
	//-2: turno no existe
	//-3: turno ya inactivo
	//-4: id no válido
	public Integer delete(Integer id) {
		int idTurno = -1;

		if (id >= 0) {
			idTurno = id;

			EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
			EntityManager em = null;
			EntityTransaction t = null;
			try {
				em = emf.createEntityManager();
				t = em.getTransaction();

				t.begin();

				// Buscar el turno en la base de datos
				Turno turno = em.find(Turno.class, idTurno);

				// Si no se encuentra el turno, retornar error -1
				if (turno == null) {
					idTurno = -2;
					t.rollback();
				} else {
					// Si el turno está activo, lo desactivamos
					boolean personalactivo = false;
					em.refresh(turno); //Hacemos refresh para que pueda tener personal ya que si no no lo tiene
					for(PersonalCafeteria p : turno.getPersonal()) {
						if(p.getActivo()) {
							personalactivo = true;
							break;
						}
					}
					
					if (!personalactivo) {
						if (turno.getActivo()) {
							turno.setActivo(false);
							t.commit();
						} else {
							idTurno = -3;
							t.rollback();
						}
					}
					else {
						idTurno = -4;
						t.rollback();
					}
					
				}

				em.close();
			} catch (Exception e) {
				e.printStackTrace();
				idTurno = -3;
				if (t != null)
					t.rollback();
				if (em != null)
					em.close();
			}
		} else
			return -4;

		return idTurno;
	}

	public TTurnoCafeteria read(Integer id) {
		TTurnoCafeteria tc = null;

		if (id < 0) {
			return tc;
		}

		EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
		EntityManager em = null;
		EntityTransaction t = null;
		try {
			em = emf.createEntityManager();
			t = em.getTransaction();

			t.begin();
			Turno turn = em.find(Turno.class, id);

			if (turn != null) {
				tc = new TTurnoCafeteria(turn.getIdTurno(), turn.getNumHoras(), turn.getNombreTurno(),
						turn.getActivo());
				t.commit();

			} else
				t.rollback();

			em.close();

		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}
			if (em != null) {
				em.close();
			}
		}
		return tc;

	}

	public List<TTurnoCafeteria> readAll() {
		List<TTurnoCafeteria> lista = null;

		EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
		EntityManager em = null;
		EntityTransaction t = null;
		try {
			em = emf.createEntityManager();
			t = em.getTransaction();

			t.begin();

			List<Turno> listaTurno = em.createNamedQuery("Negocio.Turno.findAll", Turno.class).getResultList();

			if (!listaTurno.isEmpty()) {
				lista = new ArrayList<>();

				for (Turno turn : listaTurno) {

					TTurnoCafeteria tc;
					tc = new TTurnoCafeteria(turn.getIdTurno(), turn.getNumHoras(), turn.getNombreTurno(),
							turn.getActivo());
					lista.add(tc);
				}
				t.commit();
			} else {
				t.rollback();
			}

			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (t != null) {
				t.rollback();
			}
			if (em != null) {
				em.close();
			}
		}

		return lista;
	}

	//ERRORES:
	//-1: Error actualizando
	//-2: no existe un turno con ese id
	//-3: el turno está inactivo
	//-4: el nombre ya está en uso por otro turno
	public Integer update(TTurnoCafeteria turno) {
		int idTurno = -1;

		if (turno != null) {
			idTurno = turno.getIdTurno();
			EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
			EntityManager em = null;
			EntityTransaction t = null;
			try {
				em = emf.createEntityManager();
				t = em.getTransaction();

				t.begin();

				List<Turno> turnos = em.createNamedQuery("Negocio.Turno.findAll", Turno.class).getResultList();

				Turno tc = null;

				for (Turno _turno : turnos) {
					if(turno.getNombreTurno().equals(_turno.getNombreTurno()) && 
							turno.getIdTurno()!=_turno.getIdTurno())
						return -4;
					
					if (_turno.getIdTurno() == turno.getIdTurno()) {
						tc = _turno;
						break;
					}
				}
				if (tc != null) {

					if(tc.getActivo()) {
						String nombreTurno = turno.getNombreTurno();
						Integer numHoras = turno.getNumHoras();

						tc.setNombreTurno(nombreTurno);
						tc.setNumHoras(numHoras);
						t.commit();
						idTurno = tc.getIdTurno();
					}else {
						idTurno = -3;
						t.rollback();
					}
				} else {
					idTurno = -2;
					t.rollback();
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

		}
		return idTurno;
	}

	//ERRORES:
	//-1:el turno no existe
	//-2: error calculando nómina
	@Override
	public Double calcularNomina(Integer id) {
		Double nomina = null;
		EntityManagerFactory emf = EntityManagerFactoryFactory.getInstance();
		EntityManager em = null;
		EntityTransaction t = null;
		try {
			em = emf.createEntityManager();
			t = em.getTransaction();
			
			t.begin();
			Turno turno = em.find(Turno.class, id, LockModeType.OPTIMISTIC);
			em.refresh(turno); //Hacemos refresh para que pueda tener personal ya que si no no lo tiene
			if (turno != null) {
				nomina = 0.0;
				
				for (PersonalCafeteria p : turno.getPersonal()) {
					em.lock(p, LockModeType.OPTIMISTIC);
					nomina += p.calcularNomina();
				}
				t.commit();
				em.close();
			}
			else {
				nomina = -1.0;
				t.rollback();
			}
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}
			if (em != null) {
				em.close();
			}
			nomina = -2.0;
		}

		return nomina;
	}
}
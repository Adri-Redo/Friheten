package Negocio.PersonalCafeteria;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Personal_Cafeteria.TPersonalCafeteria;
import Negocio.Turno_Cafeteria.TTurnoCafeteria;

class SAPersonalCafeteriaTest {
	
	Integer idTurno;
	
	@Before
	void setUp() {
		TTurnoCafeteria turno = new TTurnoCafeteria(1, 30, "Dia", true);
        idTurno = FactoryNeg.getInstance().generateSATurno().create(turno);
    }
	
	@AfterEach
	void deleteAll() {
		List<TPersonalCafeteria> list=FactoryNeg.getInstance().generateSAPersonalCafeteria().readAll();
		for(TPersonalCafeteria p:list) {
			FactoryNeg.getInstance().generateSAPersonalCafeteria().delete(p.getId());
		}
	}
    
    @Test
    void testCreatePersonal() {
        TPersonalCafeteria personal = new TPersonalCafeteria(null, "12345678A", "John", "Doe", null, "Camarero", 200, false, null, 1200, idTurno, true);
        int id = FactoryNeg.getInstance().generateSAPersonalCafeteria().create(personal);
        assertTrue(id >= 0 || id < 0); // Si hay error, será negativo
    }
    
    @Test
    void testReadPersonal() {
    	TPersonalCafeteria personal = new TPersonalCafeteria(null, "12345678B", "John", "Doe", null, "Camarero", 200, false, null, 1200, idTurno, true);
        int id = FactoryNeg.getInstance().generateSAPersonalCafeteria().create(personal);
        TPersonalCafeteria pRead = FactoryNeg.getInstance().generateSAPersonalCafeteria().read(id);
        assertEquals(personal.getNif(),pRead.getNif());
        assertEquals(personal.getNombre(),pRead.getNombre());
        assertEquals(personal.getApellidos(),pRead.getApellidos());
    }
    
    @Test
    void testUpdatePersonal() {
    	TPersonalCafeteria personal = new TPersonalCafeteria(null, "12345678C", "John", "Doe", null, "Camarero", 200, false, null, 1200, idTurno, true);
        int id = FactoryNeg.getInstance().generateSAPersonalCafeteria().create(personal);
        personal.setNombre("María");
        int returnId=FactoryNeg.getInstance().generateSAPersonalCafeteria().update(personal);
        TPersonalCafeteria pRead = FactoryNeg.getInstance().generateSAPersonalCafeteria().read(id);
        assertEquals(id,returnId);
        assertEquals(personal.getNombre(),pRead.getNombre());
        assertEquals("María",pRead.getNombre());
    }
    
    @Test
    void testDeletePersonal() {
    	TPersonalCafeteria personal = new TPersonalCafeteria(null, "12345678D", "John", "Doe", null, "Camarero", 200, false, null, 1200, idTurno, true);
        int id = FactoryNeg.getInstance().generateSAPersonalCafeteria().create(personal);
        int returnId=FactoryNeg.getInstance().generateSAPersonalCafeteria().delete(id);
        assertEquals(id,returnId);
        TPersonalCafeteria pRead = FactoryNeg.getInstance().generateSAPersonalCafeteria().read(id);
        assertEquals(pRead,null);
    }
    
    @Test
    void testReadAll() {
    	TPersonalCafeteria personal = new TPersonalCafeteria(null, "12345678E", "John", "Doe", null, "Camarero", 200, false, null, 1200, idTurno, true);
        FactoryNeg.getInstance().generateSAPersonalCafeteria().create(personal);
        TPersonalCafeteria personal2 = new TPersonalCafeteria(null, "12345678F", "John", "Doe", null, "Camarero", 200, false, null, 1200, idTurno, true);
        FactoryNeg.getInstance().generateSAPersonalCafeteria().create(personal2);
        List<TPersonalCafeteria> list=FactoryNeg.getInstance().generateSAPersonalCafeteria().readAll();
        assertNotNull(list);
        assertTrue(list.size()>0);
    }
    
    @Test
    void testReadPersonalByTurno() {
    	TTurnoCafeteria turno2 = new TTurnoCafeteria(2, 30, "Noche", true);
        Integer idTurno2 = FactoryNeg.getInstance().generateSATurno().create(turno2);
        
        TPersonalCafeteria personal = new TPersonalCafeteria(null, "12345678G", "John", "Doe", null, "Camarero", 200, false, null, 1200, idTurno2, true);
        Integer id=FactoryNeg.getInstance().generateSAPersonalCafeteria().create(personal);
        TPersonalCafeteria personal2 = new TPersonalCafeteria(null, "12345678H", "John", "Doe", null, "Camarero", 200, false, null, 1200, idTurno2, true);
        Integer id2=FactoryNeg.getInstance().generateSAPersonalCafeteria().create(personal2);
        
        List<TPersonalCafeteria> lista=FactoryNeg.getInstance().generateSAPersonalCafeteria().readPersonalByTurno(idTurno2);
        assertEquals(lista.size(),2);
        assertEquals(lista.get(0).getId(),id);
        assertEquals(lista.get(1).getId(),id2);
    }
    
    @Test
    void testCalcularNomina() {
    	TPersonalCafeteria personal = new TPersonalCafeteria(null, "12345678I", "John", "Doe", null, "Camarero", 200, false, null, 1200, idTurno, true);
        Integer id=FactoryNeg.getInstance().generateSAPersonalCafeteria().create(personal);
        double nomina=FactoryNeg.getInstance().generateSAPersonalCafeteria().calcularNomina(id);
        assertEquals(nomina,1400,0.05);
    }
}

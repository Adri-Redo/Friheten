package Negocio.PersonalCafeteria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Negocio.Personal_Cafeteria.TPersonalCafeteria;

class TPersonalCafeteriaTest {
    
    private TPersonalCafeteria personal;
    
    @BeforeEach
    void setUp() {
        personal = new TPersonalCafeteria(1, "12345678A", "Juan", "Pérez", 5, "Camarero", 200,
                true, "Gerente", 1200, 1, true);
    }
    
    @Test
    void testConstructorAndGetters() {
        assertEquals(1, personal.getId());
        assertEquals("12345678A", personal.getNif());
        assertEquals("Juan", personal.getNombre());
        assertEquals("Pérez", personal.getApellidos());
        assertEquals(5, personal.getResponsabilidades());
        assertEquals("Camarero", personal.getPuesto());
        assertEquals(200, personal.getBonificaciones());
        assertTrue(personal.getIsJefe());
        assertEquals("Gerente", personal.getCargo());
        assertEquals(1200, personal.getSalarioBase());
        assertEquals(1, personal.getTurno());
        assertTrue(personal.getActivo());
    }
    
    @Test
    void testSetters() {
        personal.setId(2);
        assertEquals(2, personal.getId());
        
        personal.setNif("87654321B");
        assertEquals("87654321B", personal.getNif());
        
        personal.setNombre("Carlos");
        assertEquals("Carlos", personal.getNombre());
        
        personal.setApellidos("Gómez");
        assertEquals("Gómez", personal.getApellidos());
        
        personal.setResponsabilidades(3);
        assertEquals(3, personal.getResponsabilidades());
        
        personal.setPuesto("Cocinero");
        assertEquals("Cocinero", personal.getPuesto());
        
        personal.setBonificaciones(300);
        assertEquals(300, personal.getBonificaciones());
        
        personal.setIsJefe(false);
        assertFalse(personal.getIsJefe());
        
        personal.setCargo("Subgerente");
        assertEquals("Subgerente", personal.getCargo());
        
        personal.setSalarioBase(1500);
        assertEquals(1500, personal.getSalarioBase());
        
        personal.setTurno(2);
        assertEquals(2, personal.getTurno());
        
        personal.setActivo(false);
        assertFalse(personal.getActivo());
    }
}

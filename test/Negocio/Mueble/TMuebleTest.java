package Negocio.Mueble;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TMuebleTest {
	
	@Test
	void testsFuncionesTMueble() {
		TMueble mueble = new TMueble(1, "NombreMueble", 50.30, 500, 20.21,13.45,15.00 ,9.62, "Madera de Roble", 1, false);
		
		assertEquals(1, mueble.getId());
		
		assertEquals("NombreMueble", mueble.getNombre());
		mueble.setNombre("Estanteria Hoiffron");
		assertEquals("Estanteria Hoiffron", mueble.getNombre());
		
		assertEquals(50.30, mueble.getPrecio());
		mueble.setPrecio(49.35);
		assertEquals(49.35, mueble.getPrecio());
		
		assertEquals(500, mueble.getStock());
		mueble.setStock(450);
		assertEquals(450, mueble.getStock());
		
		assertEquals(20.21, mueble.getPeso());
		mueble.setPeso(30.45);
		assertEquals(30.45, mueble.getPeso());
		
		assertEquals(13.45, mueble.getMedX());
		mueble.setMedX(15.12);
		assertEquals(15.12, mueble.getMedX());

		assertEquals(15.00, mueble.getMedY());
		mueble.setMedY(15.61);
		assertEquals(15.61, mueble.getMedY());
		
		assertEquals(9.62, mueble.getMedZ());
		mueble.setMedZ(10.00);
		assertEquals(10.00, mueble.getMedZ());
		
		assertEquals("Madera de Roble", mueble.getMaterial());
		mueble.setMaterial("Plastico");
		assertEquals("Plastico", mueble.getMaterial());
		
		assertEquals(1, mueble.getIdNave());
		mueble.setIdNave(2);
		assertEquals(2, mueble.getIdNave());
		
		assertEquals(false, mueble.getActivo());
		mueble.setActivo(true);
		assertEquals(true, mueble.getActivo());
	}
}

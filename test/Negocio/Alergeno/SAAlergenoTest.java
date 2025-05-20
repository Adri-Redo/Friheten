package Negocio.Alergeno;

import Negocio.Alergeno_Cafeteria.TAlergeno;
import Negocio.EntityManagerFactory.EntityManagerFactoryFactory;
import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Marca_Cafeteria.TMarca;
import Negocio.Producto_Cafeteria.TComida;
import Negocio.Producto_Cafeteria.TProductoCafeteria;
import jakarta.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class SAAlergenoTest {
	@BeforeEach
	public void clearDB() {
		EntityManagerFactoryFactory.getInstance();
		for(TAlergeno alergeno : FactoryNeg.getInstance().generateSAAlergeno().readAll()) {
			FactoryNeg.getInstance().generateSAAlergeno().unLink(alergeno.getIdAlergeno(),1);
			FactoryNeg.getInstance().generateSAAlergeno().delete(alergeno.getIdAlergeno());
		}
		
	}
	@Test
	public void testCreate() {
		TAlergeno alergeno1 = new TAlergeno();
		alergeno1.setFuente("lactosa");
		alergeno1.setNombre("Leche");
		alergeno1.setRiesgo(2);
		int r1 = FactoryNeg.getInstance().generateSAAlergeno().create(alergeno1);
		assertEquals(1, r1, "No ha devuelto el id que deberia");
		TAlergeno alergeno2 = new TAlergeno();
		alergeno2.setFuente("aerosoles");
		alergeno2.setNombre("Marisco");
		alergeno2.setRiesgo(3);
		int r2 = FactoryNeg.getInstance().generateSAAlergeno().create(alergeno2);
		assertEquals(2, r2, "No ha devuelto el id que deberia");
	}
	@Test
	public void testRead() {
		TAlergeno alergeno1 = new TAlergeno();
		alergeno1.setFuente("lactosa");
		alergeno1.setNombre("Leche");
		alergeno1.setRiesgo(2);
		int r1 = FactoryNeg.getInstance().generateSAAlergeno().create(alergeno1);
		assertEquals(1, r1, "No ha devuelto el id que deberia");
		TAlergeno result = FactoryNeg.getInstance().generateSAAlergeno().read(r1);
		assertEquals(result.getIdAlergeno(), r1, "No ha devuelto el Alergeno que deberia");
		
	}
	
	@Test
	public void testReadAll() {
		TAlergeno alergeno1 = new TAlergeno();
		alergeno1.setFuente("lactosa");
		alergeno1.setNombre("Leche");
		alergeno1.setRiesgo(2);
		int r1 = FactoryNeg.getInstance().generateSAAlergeno().create(alergeno1);
		assertEquals(1, r1, "No ha devuelto el id que deberia");
		
		TAlergeno alergeno2 = new TAlergeno();
		alergeno2.setFuente("aerosoles");
		alergeno2.setNombre("Marisco");
		alergeno2.setRiesgo(3);
		int r2 = FactoryNeg.getInstance().generateSAAlergeno().create(alergeno2);
		assertEquals(2, r2, "No ha devuelto el id que deberia");
		
		assertEquals(FactoryNeg.getInstance().generateSAAlergeno().readAll().size(), 2, "No ha devuelto la lista de Alergenos que deberia");
		
	}
	@Test
	public void testUpdate() {
		TAlergeno alergeno1 = new TAlergeno();
		alergeno1.setFuente("lactosa");
		alergeno1.setNombre("Leche");
		alergeno1.setRiesgo(2);
		int r1 = FactoryNeg.getInstance().generateSAAlergeno().create(alergeno1);
		assertEquals(1, r1, "No ha devuelto el id que deberia");
		
		alergeno1.setIdAlergeno(r1);
		alergeno1.setFuente("nueces");
		alergeno1.setNombre("Nuez");
		alergeno1.setRiesgo(3);
		
		int r2 = FactoryNeg.getInstance().generateSAAlergeno().update(alergeno1);
		assertEquals(r2, 1, "No ha devuelto el id que deberia");
		
	}
	@Test
	public void testDelete() {
		TAlergeno alergeno1 = new TAlergeno();
		alergeno1.setFuente("lactosa");
		alergeno1.setNombre("Leche");
		alergeno1.setRiesgo(2);
		int r1 = FactoryNeg.getInstance().generateSAAlergeno().create(alergeno1);
		assertEquals(1, r1, "No ha devuelto el id que deberia");
		
		int r2 = FactoryNeg.getInstance().generateSAAlergeno().delete(r1);
		assertEquals(r2, 1, "No ha devuelto el id que deberia");
		
	}
	@Test
	public void testLink() {
		TAlergeno alergeno1 = new TAlergeno();
		alergeno1.setFuente("lactosa");
		alergeno1.setNombre("Leche");
		alergeno1.setRiesgo(2);
		int r1 = FactoryNeg.getInstance().generateSAAlergeno().create(alergeno1);
		assertEquals(1, r1, "No ha devuelto el id que deberia al crear el alergeno");
		
		TMarca marca = new TMarca();
		marca.setNombre("Marca Test");
		marca.setCategoria("Categoria Test");
		int r3 = FactoryNeg.getInstance().generateSAMarca().create(marca);
		assertEquals(1, r3, "No ha devuelto el id que deberia al crear la marca");
		
		TProductoCafeteria producto1 = new TComida();
		producto1.setNombre("Producto Test");
		producto1.setPrecio(1.0);
		producto1.setStock(10);
		producto1.setTipoComida("Comida");
		producto1.setIdMarca(r3);
		int r2 = FactoryNeg.getInstance().generateSAProducto().create(producto1);
		assertEquals(1, r2, "No ha devuelto el id que deberia");
		assertEquals(FactoryNeg.getInstance().generateSAAlergeno().link(r1, r2), 1, "No ha devuelto el id que deberia");
	}
	@Test
	public void testUnLink() {
		TAlergeno alergeno1 = new TAlergeno();
		alergeno1.setFuente("lactosa");
		alergeno1.setNombre("Leche");
		alergeno1.setRiesgo(2);
		int r1 = FactoryNeg.getInstance().generateSAAlergeno().create(alergeno1);
		assertEquals(1, r1, "No ha devuelto el id que deberia al crear el alergeno");
		
		TMarca marca = new TMarca();
		marca.setNombre("Marca Test");
		marca.setCategoria("Categoria Test");
		int r3 = FactoryNeg.getInstance().generateSAMarca().create(marca);
		assertEquals(1, r3, "No ha devuelto el id que deberia al crear la marca");
		
		TProductoCafeteria producto1 = new TComida();
		producto1.setNombre("Producto Test");
		producto1.setPrecio(1.0);
		producto1.setStock(10);
		producto1.setTipoComida("Comida");
		producto1.setIdMarca(r3);
		int r2 = FactoryNeg.getInstance().generateSAProducto().create(producto1);
		assertEquals(1, r2, "No ha devuelto el id que deberia");
		
		assertEquals(FactoryNeg.getInstance().generateSAAlergeno().link(r1, r2), 1, "No ha devuelto el id que deberia");
		
		assertEquals(FactoryNeg.getInstance().generateSAAlergeno().unLink(r1, r2), 1, "No ha devuelto el id que deberia");
		
	}
}

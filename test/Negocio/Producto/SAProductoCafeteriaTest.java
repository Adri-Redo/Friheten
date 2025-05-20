package Negocio.Producto;

import Negocio.Producto_Cafeteria.TProductoCafeteria;
import Negocio.EntityManagerFactory.EntityManagerFactoryFactory;
import Negocio.FactoryNegocio.FactoryNeg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class SAProductoCafeteriaTest {

    @BeforeEach
    public void clearDB() {
        EntityManagerFactoryFactory.getInstance();
    }

    @Test
    public void testCreateBebida() {
        TProductoCafeteria bebida = new TProductoCafeteria();
        bebida.setNombre("Coca Cola");
        bebida.setPrecio(2.50);
        bebida.setStock(100);
        bebida.setNivelNutricion('D');
        bebida.setActivo(true);
        //bebida.setComidaOBebida(false);
        bebida.setNivelAzucar(8);

        int r1 = FactoryNeg.getInstance().generateSAProducto().create(bebida);
        assertEquals(1, r1, "No ha devuelto el id que debería");

        TProductoCafeteria result = FactoryNeg.getInstance().generateSAProducto().read(r1);
        assertEquals(result.getId(), r1, "No ha devuelto el Producto que debería");
    }

    @Test
    public void testCreateComida() {
        TProductoCafeteria comida = new TProductoCafeteria();
        comida.setNombre("Hamburguesa");
        comida.setPrecio(8.99);
        comida.setStock(50);
        comida.setNivelNutricion('C');
        comida.setActivo(true);
        //comida.setComidaOBebida(true);
        comida.setTipoComida("Fast Food");

        int r1 = FactoryNeg.getInstance().generateSAProducto().create(comida);
        assertEquals(1, r1, "No ha devuelto el id que debería");

        TProductoCafeteria result = FactoryNeg.getInstance().generateSAProducto().read(r1);
        assertEquals(result.getId(), r1, "No ha devuelto el Producto que debería");
    }

    @Test
    public void testCreateDuplicado() {
        TProductoCafeteria producto1 = new TProductoCafeteria();
        producto1.setNombre("Producto Test");
        producto1.setPrecio(1.0);
        producto1.setStock(10);
        producto1.setNivelNutricion('A');
        producto1.setActivo(true);

        int r1 = FactoryNeg.getInstance().generateSAProducto().create(producto1);
        assertEquals(1, r1, "No ha devuelto el id que debería");

        TProductoCafeteria producto2 = new TProductoCafeteria();
        producto2.setNombre("Producto Test"); // mismo nombre
        producto2.setPrecio(2.0);
        producto2.setStock(20);
        producto2.setNivelNutricion('B');
        producto2.setActivo(true);

        int r2 = FactoryNeg.getInstance().generateSAProducto().create(producto2);
        assertEquals(-4, r2, "Debería devolver -4 por nombre duplicado");
    }

    @Test
    public void testDelete() {
        TProductoCafeteria producto = new TProductoCafeteria();
        producto.setNombre("Producto Delete");
        producto.setPrecio(1.0);
        producto.setStock(10);
        producto.setNivelNutricion('A');
        producto.setActivo(true);

        int r1 = FactoryNeg.getInstance().generateSAProducto().create(producto);
        assertEquals(1, r1, "No ha devuelto el id que debería");

        int deleteResult = FactoryNeg.getInstance().generateSAProducto().delete(r1);
        assertEquals(r1, deleteResult, "No ha devuelto el id que debería al eliminar");

        TProductoCafeteria result = FactoryNeg.getInstance().generateSAProducto().read(r1);
        assertNull(result, "El producto debería ser null después de eliminarlo");
    }

    @Test
    public void testUpdate() {
        TProductoCafeteria producto = new TProductoCafeteria();
        producto.setNombre("Producto Update");
        producto.setPrecio(1.0);
        producto.setStock(10);
        producto.setNivelNutricion('A');
        producto.setActivo(true);

        int r1 = FactoryNeg.getInstance().generateSAProducto().create(producto);
        assertEquals(1, r1, "No ha devuelto el id que debería");

        producto.setId(r1);
        producto.setPrecio(2.0);
        producto.setStock(20);

        int updateResult = FactoryNeg.getInstance().generateSAProducto().update(producto);
        assertEquals(r1, updateResult, "No ha devuelto el id que debería al actualizar");

        TProductoCafeteria result = FactoryNeg.getInstance().generateSAProducto().read(r1);
        assertEquals(2.0, result.getPrecio(), "El precio no se actualizó correctamente");
        assertEquals(20, result.getStock(), "El stock no se actualizó correctamente");
    }
}

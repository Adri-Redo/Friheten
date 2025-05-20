package Integracion.Mueble;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Integracion.FactoryIntegracion.FactoryIntgr;
import Negocio.Mueble.TMueble;

class DAOMuebleTest 
{

	@Test
	public void testsFuncionesDAOMueble() 
	{
		
		int resultado;
		
		TMueble mueble = new TMueble(1, "NombreMueble2", 50.30, 500, 20.21,13.45,15.00 ,9.62, "Madera de Roble", 1, true);
		
		/*********************** CREATE *****************************/
		
		resultado = FactoryIntgr.getInstance().generateDAOMueble().create(mueble);
		
		/*
		 * Superior a 0 , se ha dado alta correctamente
		 * Si devuelve -1, error al darlo de alta
		 * Si devuelve -2, el nombre del mueble ya existe en la BBDD
		 * */
		
		if (resultado > 0)
		{
			mueble.setId(resultado);
			assertEquals(resultado, resultado, "Se agrego mueble a la BBDD");
			
		}
		else if (resultado == -2)
		{
			assertEquals(-2, resultado, "El mueble ya existe");
		}		
		else if (resultado == -1)
		{
			assertEquals(-1, resultado, "Error al agregar mueble a la BBDD");
		}
		else
		{
			assertEquals(-3, resultado, "No hay conexion con la BBDD o error al insertar mueble");
		}
		
		/*********************** READ *****************************/
		
		assertEquals(mueble, FactoryIntgr.getInstance().generateDAOMueble().read(resultado));
		
		/*********************** READ_ALL *****************************/
		
		List<TMueble> muebles = new ArrayList<>();
		muebles = FactoryIntgr.getInstance().generateDAOMueble().readAll();
		
		if (muebles.size() == 0)
			assertEquals(0, muebles.size(), "La lista no contiene los elementos que nosotros queremos");
		else
			assertTrue(muebles.contains(mueble));
		
		/*********************** UPDATE *****************************/
		
		mueble.setMedX(333.0);
		assertEquals(1, FactoryIntgr.getInstance().generateDAOMueble().update(mueble));
		
		
		/*********************** READ_MUEBLE_POR_NAVE *****************************/
		
		List<TMueble> mueblesByNave = new ArrayList<>();
		mueblesByNave = FactoryIntgr.getInstance().generateDAOMueble().readMueblePorNave(2);
		System.out.println(mueblesByNave.size());
		
		if (muebles.size() == 0)
			assertEquals(1, mueblesByNave.size() , "La lista no contiene los elementos que nosotros queremos");
		else
			assertTrue(mueblesByNave.contains(mueble));
		
		
		/*********************** DELETE *****************************/
		
		assertEquals(1, FactoryIntgr.getInstance().generateDAOMueble().delete(mueble.getId()));

		
	}
}

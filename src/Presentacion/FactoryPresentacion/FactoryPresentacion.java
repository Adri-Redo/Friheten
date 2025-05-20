/**
 * 
 */
package Presentacion.FactoryPresentacion;

import Presentacion.Observer;

public abstract class FactoryPresentacion {

	private static FactoryPresentacion instance;

	public static FactoryPresentacion getInstance() {

		if (instance == null)
			instance = new FactoryPresentacionImp();

		return instance;
	}

	public abstract Observer generateGUI(int event);// De esto se encarga la implementacion.
}
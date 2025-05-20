/**
 * 
 */
package Presentacion.Producto_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Producto_Cafeteria.TProductoCafeteria;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public class Command_BajaProductoOk_View implements Command {
	/** 
	* (non-Javadoc)
	 * @return 
	* @see Command#execute(Context context)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Entry<Integer, Context> execute(Context context) {
		Integer idProducto = (Integer) context.getData();
		int res = FactoryNeg.getInstance().generateSAProducto().delete(idProducto);
		Context new_context = new Context();
		
		if(res == -1) {
			new_context.setEvent(Events.ERROR_PRODUCTO_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.ERROR_PRODUCTO_NO_EXISTE,new_context);
		}
		else {
			new_context.setEvent(Events.BAJA_PRODUCTO_OK_VIEW);
			new_context.setData(res);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.BAJA_PRODUCTO_VIEW,new_context);
		}
	}
}
/**
 * 
 */
package Presentacion.Producto_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Mueble.TMueble;
import Negocio.Producto_Cafeteria.Producto;
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
public class Command_ListarProductoOk_View implements Command {
	/** 
	* (non-Javadoc)
	 * @return 
	* @see Command#execute(Context context)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Entry<Integer, Context> execute(Context context) {

		
		Set<TProductoCafeteria> tList = FactoryNeg.getInstance().generateSAProducto().readAll();
		Context new_context =  new Context();
		
		if (tList.isEmpty())
		{
			new_context.setEvent(Events.ERROR_PRODUCTO_NO_EXISTE);
			new_context.setData(null);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.LISTAR_PRODUCTO_VIEW,new_context);
		}
		else
		{
			new_context.setEvent(Events.EXITO_PRODUCTO_CONSULTAR);
			new_context.setData(tList);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.LISTAR_PRODUCTO_VIEW,new_context);
		}	
	}
}
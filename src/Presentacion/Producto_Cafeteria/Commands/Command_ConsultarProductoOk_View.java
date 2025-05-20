/**
 * 
 */
package Presentacion.Producto_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
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
public class Command_ConsultarProductoOk_View implements Command {
	/** 
	* (non-Javadoc)
	 * @return 
	* @see Command#execute(Context context)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Entry<Integer, Context> execute(Context context) 
	{
		
		Integer Idproducto = (int) context.getData();
		
		TProductoCafeteria res = FactoryNeg.getInstance().generateSAProducto().read(Idproducto);
		
		Context new_context = new Context();
		
		if(res == null) 
		{
			new_context.setEvent(Events.ERROR_PRODUCTO_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.CONSULTAR_PRODUCTO_VIEW,new_context);
		}
		else 
		{
			new_context.setData(res);
			new_context.setEvent(Events.CONSULTAR_PRODUCTO_OK_VIEW);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.CONSULTAR_PRODUCTO_VIEW,new_context);
		}
	}
}
/**
 * 
 */
package Presentacion.Marca_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Marca_Cafeteria.TMarca;
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
public class Command_ModificarMarcaOk_View implements Command {
	/** 
	* (non-Javadoc)
	* @see Command#execute(Context context)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@Override
	public Entry<Integer, Context> execute(Context context) {
		TMarca m = (TMarca) context.getData();
		
		
		
		int resultado = FactoryNeg.getInstance().generateSAMarca().update(m);
		Context new_context = new Context();
		new_context.setData(m);
		
		if(resultado == -1) {
			new_context.setEvent(Events.MARCA_ERROR_DESCONOCIDO);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MARCA_VIEW, new_context);
			
		}else {
			new_context.setEvent(Events.RES_MODIFICAR_MARCA_OK);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MARCA_VIEW, new_context);
		}
	}
}
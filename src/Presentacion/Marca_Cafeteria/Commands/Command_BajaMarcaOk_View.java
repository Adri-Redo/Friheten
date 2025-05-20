/**
 * 
 */
package Presentacion.Marca_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author jaimea
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public class Command_BajaMarcaOk_View implements Command {
	/** 
	* (non-Javadoc)
	 * @return 
	* @see Command#execute(Context context)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Entry<Integer, Context> execute(Context context) {
		// begin-user-code
		// TODO Auto-generated method stub
		int resultado = FactoryNeg.getInstance().generateSAMarca().delete((Integer) context.getData());
		Context new_context = new Context();
		new_context.setData(context.getData());
		
		if(resultado == -1 || resultado == 0) {
			new_context.setEvent(Events.MARCA_ERROR_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MARCA_VIEW,new_context);

		} else if (resultado == -2 || resultado == -3) {
			new_context.setEvent(Events.MARCA_ERROR_DESCONOCIDO);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MARCA_VIEW,new_context);

		} else if (resultado == -4) {
			new_context.setEvent(Events.MARCA_ERROR_PRODUCTOS_VINCULADOS_ACTIVOS);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MARCA_VIEW,new_context);
		}
		else {
			new_context.setEvent(Events.RES_BAJA_MARCA_OK);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MARCA_VIEW,new_context);

		}
		// end-user-code
	}
}
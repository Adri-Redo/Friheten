/**
 * 
 */
package Presentacion.Alergeno_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Alergeno_Cafeteria.TAlergeno;
import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public class Command_DesvincularAlergenoOk_View implements Command {
	/** 
	* (non-Javadoc)
	 * @return 
	* @see Command#execute(Context context)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@Override
	public Entry<Integer, Context> execute(Context context) {
	    TAlergeno al = (TAlergeno) context.getData();
	    int resultado = FactoryNeg.getInstance().generateSAAlergeno().unLink(al.getIdProducto(), al.getIdAlergeno());
	    Context new_context = new Context();
	    
	    if (resultado == -1) {
	        new_context.setEvent(Events.ERROR_ALERGENO_NO_EXISTE);
	        new_context.setData(al);
	    } else if (resultado == -2) {
	        new_context.setEvent(Events.ERROR_ALERGENO_DESVINCULAR);
	        new_context.setData(al.getIdAlergeno());
	    } else if (resultado == -3) {
	        new_context.setEvent(Events.ERROR_ALERGENO_NO_EXISTE);
	        new_context.setData(al.getIdProducto());
	    } else if (resultado == -4) {
	        new_context.setEvent(Events.ERROR_ALERGENO_DADO_BAJA);
	        new_context.setData(al.getIdProducto());
	    } else if (resultado == -5) {
	        new_context.setEvent(Events.ERROR_ALERGENO_DESVINCULAR);
	        new_context.setData(al.getIdProducto());
	    } else {
	        new_context.setEvent(Events.EXITO_ALERGENO_DESVINCULAR);
	        new_context.setData(context.getData());
	    }

	    return new AbstractMap.SimpleEntry<Integer, Context>(Events.ALERGENO_VIEW, new_context);
	}
}
/**
 * 
 */
package Presentacion.Alergeno_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public class Command_AltaAlergenoKo_View implements Command {
	/** 
	* (non-Javadoc)
	 * @return 
	* @see Command#execute(Context context)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@Override
	public Entry<Integer, Context> execute(Context context) {
	    Context new_context = new Context();
	    return new AbstractMap.SimpleEntry<Integer, Context>(Events.ALERGENO_CANCEL_VIEW, new_context);
	}
}
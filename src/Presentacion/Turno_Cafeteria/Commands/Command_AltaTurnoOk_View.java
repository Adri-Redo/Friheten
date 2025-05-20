/**
 * 
 */
package Presentacion.Turno_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Turno_Cafeteria.TTurnoCafeteria;
import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author usuario_local
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class Command_AltaTurnoOk_View implements Command {
	/** 
	* (non-Javadoc)
	* @see Command#execute(Context context)
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/

	@Override
	public Entry<Integer, Context> execute(Context context) {
		TTurnoCafeteria turno = (TTurnoCafeteria) context.getData();
		int resultado = FactoryNeg.getInstance().generateSATurno().create(turno);
		Context new_context =  new Context();
		new_context.setData(context.getData());

		if (resultado == -1) // NO se ha podido crear
			new_context.setEvent(Events.ERROR_TURNO_EXISTE);
		else if(resultado == -2) 
			new_context.setEvent(Events.ERROR_NOMBRE_EXISTE);
		else // Mensaje de que todo ha ido bien
			new_context.setEvent(Events.EXITO_TURNO_ALTA);
		
		
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.TURNO_VIEW, new_context);
	}
}
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
public class Command_ModificarTurnoOk_View implements Command {
	/** 
	* (non-Javadoc)
	* @see Command#execute(Context context)
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	@Override
	public Entry<Integer, Context> execute(Context context) {
		TTurnoCafeteria tTurno = (TTurnoCafeteria) context.getData();
		Context new_context = new Context();
		int resultado = FactoryNeg.getInstance().generateSATurno().update(tTurno);

		if (resultado == -1) {// NOMBRE TURNO EXISTENTE
			new_context.setEvent(Events.ERROR_TURNO_ACTUALIZANDO);
			new_context.setData(tTurno);
		}
		else if(resultado == -2) { // ID NO EXISTE
			new_context.setEvent(Events.ERROR_TURNO_NO_EXISTE);
			new_context.setData(tTurno.getIdTurno());
		}
		else if(resultado == -3) { // ID DE BAJA
			new_context.setEvent(Events.ERROR_TURNO_DE_BAJA);
			new_context.setData(tTurno.getIdTurno());
		}
		else if(resultado == -4) {
			new_context.setEvent(Events.ERROR_NOMBRE_EXISTE);
			new_context.setData(tTurno.getIdTurno());
		}
		else {
			new_context.setEvent(Events.EXITO_TURNO_MODIFICAR);
			new_context.setData(tTurno.getIdTurno());
			
		}
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.TURNO_VIEW, new_context);
	}
}
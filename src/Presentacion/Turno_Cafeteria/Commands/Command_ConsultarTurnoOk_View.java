/**
 * 
 */
package Presentacion.Turno_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Turno_Cafeteria.TTurnoCafeteria;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author usuario_local
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class Command_ConsultarTurnoOk_View implements Command {
	/** 
	* (non-Javadoc)
	* @see Command#execute(Context context)
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	@Override
	public Entry<Integer, Context> execute(Context context) {
		TTurnoCafeteria tTurno = FactoryNeg.getInstance().generateSATurno().read((Integer) context.getData());
		Context new_context = new Context();
		new_context.setData(context.getData());

		if (tTurno == null) {// No existe
			new_context.setEvent(Events.ERROR_TURNO_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer, Context>(Events.TURNO_VIEW, new_context);

		} else {
			new_context.setEvent(Events.EXITO_TURNO_CONSULTAR);
			new_context.setData(tTurno);
			return new AbstractMap.SimpleEntry<Integer, Context>(Events.CONSULTAR_TURNO_VIEW, new_context);
		}
	}
}
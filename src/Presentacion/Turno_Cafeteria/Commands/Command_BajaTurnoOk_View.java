/**
 * 
 */
package Presentacion.Turno_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

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
public class Command_BajaTurnoOk_View implements Command {
	/** 
	* (non-Javadoc)
	* @see Command#execute(Context context)
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/

	@Override
	public Entry<Integer, Context> execute(Context context) {
		int resultado = FactoryNeg.getInstance().generateSATurno().delete((Integer) context.getData());
		Context new_context = new Context();
		new_context.setData(context.getData());

		if(resultado == -3) {
			new_context.setEvent(Events.ERROR_TURNO_DE_BAJA);
			
		}
		else if (resultado == -2 || resultado==-1) {
			new_context.setEvent(Events.ERROR_TURNO_NO_EXISTE);
			
		}
		else if (resultado == -4) {
			new_context.setEvent(Events.ERROR_TURNO_VINCULADO_A_PERSONAL);
			
		}
		else {
			new_context.setEvent(Events.EXITO_TURNO_BAJA);
			
		}
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.TURNO_VIEW,new_context);
	}
}
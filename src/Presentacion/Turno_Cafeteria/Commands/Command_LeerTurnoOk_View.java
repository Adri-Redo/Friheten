/**
 * 
 */
package Presentacion.Turno_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Personal_Cafeteria.TPersonalCafeteria;
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
public class Command_LeerTurnoOk_View implements Command {
	/** 
	* (non-Javadoc)
	* @see Command#execute(Context context)
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/

	@Override
	public Entry<Integer, Context> execute(Context context) {
		Integer id = (Integer) context.getData();
		TTurnoCafeteria tp = FactoryNeg.getInstance().generateSATurno().read(id);
		Context new_context =  new Context();
		new_context.setData(tp);
		if (tp == null) {// NO existe el turno
			new_context.setEvent(Events.ERROR_TURNO_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.TURNO_VIEW,new_context);	
		}else // Mensaje de que todo ha ido bien
		{
			new_context.setEvent(Events.EXITO_TURNO_CONSULTAR);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.CONSULTAR_TURNO_VIEW,new_context);
		}
	}
}
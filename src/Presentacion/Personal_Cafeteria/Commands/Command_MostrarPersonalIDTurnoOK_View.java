/**
 * 
 */
package Presentacion.Personal_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Personal_Cafeteria.TPersonalCafeteria;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public class Command_MostrarPersonalIDTurnoOK_View implements Command {
	/** 
	* (non-Javadoc)
	* @see Command#execute(Context context)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@Override
	public Entry<Integer, Context> execute(Context context) {
		Integer idTurno = (Integer) context.getData();
		List<TPersonalCafeteria> personalSet = FactoryNeg.getInstance().generateSAPersonalCafeteria()
				.readPersonalByTurno(idTurno);
		Context new_context =  new Context();
		new_context.setData(personalSet);
		
		
		new_context.setEvent(Events.EXITO_PERSONALCAFETERIA_MOSTRAR_POR_ID_TURNO);
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.MOSTRAR_PERSONALCAFETERIA_PORID_TURNO_VIEW,new_context);

	}
}
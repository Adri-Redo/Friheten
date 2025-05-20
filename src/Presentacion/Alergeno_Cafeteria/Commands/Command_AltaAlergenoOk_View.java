/**
 * 
 */
package Presentacion.Alergeno_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Alergeno_Cafeteria.TAlergeno;
import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Habilidad.THabilidad;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author hugod
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public class Command_AltaAlergenoOk_View implements Command {
	/** 
	* (non-Javadoc)
	 * @return 
	* @see Command#execute(Context context)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@Override
	public Entry<Integer, Context> execute(Context context) {
	    TAlergeno al = (TAlergeno) context.getData();
	    int resultado = FactoryNeg.getInstance().generateSAAlergeno().create(al);
	    Context new_context = new Context();
	    new_context.setData(resultado);

	    if (resultado < 0) { // NO se ha podido crear
	        new_context.setEvent(Events.ERROR_ALERGENO_EXISTE);
	    } else { // Mensaje de que todo ha ido bien
	        new_context.setEvent(Events.EXITO_ALERGENO_ALTA);
	    }

	    return new AbstractMap.SimpleEntry<Integer, Context>(Events.ALERGENO_VIEW, new_context);
	}
}
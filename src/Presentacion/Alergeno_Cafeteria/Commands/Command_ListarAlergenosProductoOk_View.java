/**
 * 
 */
package Presentacion.Alergeno_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

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
public class Command_ListarAlergenosProductoOk_View implements Command {
	
	@Override
	public Entry<Integer, Context> execute(Context context) {
	    Set<TAlergeno> alergenos = FactoryNeg.getInstance().generateSAAlergeno().readbyProduct((Integer) context.getData());
	    Context new_context = new Context();
	    Integer event = Events.ALERGENO_VIEW;
	    if (alergenos == null) {
	        new_context.setEvent(Events.ERROR_ALERGENO_NO_EXISTE);
	        new_context.setData(context.getData());
	    } else if (alergenos.isEmpty()) {
	        new_context.setEvent(Events.ERROR_NO_HAY_ALERGENO_ASOCIADO_A_ID_PRODUCTO);
	        new_context.setData(context.getData());
	    } else {
	        new_context.setEvent(Events.EXITO_ALERGENO_MOSTRAR_PORID_PRODUCTO);
	        event = Events.LISTAR_ALERGENO_VIEW;
	        new_context.setData(alergenos);
	    }

	    return new AbstractMap.SimpleEntry<Integer, Context>(event, new_context);

	}
}
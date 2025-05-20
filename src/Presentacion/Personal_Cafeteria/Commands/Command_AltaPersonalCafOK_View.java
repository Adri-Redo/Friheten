/**
 * 
 */
package Presentacion.Personal_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Personal_Cafeteria.TPersonalCafeteria;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_AltaPersonalCafOK_View implements Command {
	
	public Entry<Integer, Context> execute(Context context) {
		TPersonalCafeteria tp = (TPersonalCafeteria) context.getData();
		int resultado = FactoryNeg.getInstance().generateSAPersonalCafeteria().create(tp);
		Context new_context =  new Context();
		new_context.setData(context.getData());
		if (resultado == -1 || resultado == -5) {
			new_context.setEvent(Events.ERROR_PERSONALCAFETERIA_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.PERSONALCAFETERIA_VIEW,new_context);	
		}else if (resultado == -2) {
			new_context.setEvent(Events.ERROR_PERSONALCAFETERIA_ALTA);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.PERSONALCAFETERIA_VIEW,new_context);	
		}else if (resultado == -3) {
			new_context.setEvent(Events.ERROR_TURNO_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.PERSONALCAFETERIA_VIEW,new_context);	
		}else if (resultado == -4) {
			new_context.setEvent(Events.ERROR_PERSONALCAFETERIA_YA_ACTIVO);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.PERSONALCAFETERIA_VIEW,new_context);	
		} else // Mensaje de que todo ha ido bien
		{
			new_context.setEvent(Events.EXITO_PERSONALCAFETERIA_ALTA);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.PERSONALCAFETERIA_VIEW,new_context);
		}
	}
}
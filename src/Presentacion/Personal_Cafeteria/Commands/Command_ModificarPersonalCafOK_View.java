package Presentacion.Personal_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Personal_Cafeteria.TPersonalCafeteria;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ModificarPersonalCafOK_View implements Command {
	
	@Override
	public Entry<Integer, Context> execute(Context context) {
		int resultado = FactoryNeg.getInstance().generateSAPersonalCafeteria()
				.update((TPersonalCafeteria)context.getData());
		Context new_context =  new Context();
		new_context.setData(context.getData());
		if (resultado == -1) {// NO existe el personal
			new_context.setEvent(Events.ERROR_PERSONALCAFETERIA_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.PERSONALCAFETERIA_VIEW,new_context);	
		}else if (resultado == -2) {
			new_context.setEvent(Events.ERROR_PERSONALCAFETERIA_MODIFICAR);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.PERSONALCAFETERIA_VIEW,new_context);	
		}else if (resultado == -3) {
			new_context.setEvent(Events.ERROR_TURNO_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.PERSONALCAFETERIA_VIEW,new_context);	
		}else if (resultado == -4) {// Existe personal con mismo NIF
			new_context.setEvent(Events.ERROR_PERSONALCAFETERIA_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.PERSONALCAFETERIA_VIEW,new_context);	
		}else // Mensaje de que todo ha ido bien
		{
			new_context.setEvent(Events.EXITO_PERSONALCAFETERIA_MODIFICAR);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.PERSONALCAFETERIA_VIEW,new_context);
		}
	}
}
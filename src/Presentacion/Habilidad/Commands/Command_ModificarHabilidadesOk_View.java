package Presentacion.Habilidad.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Habilidad.THabilidad;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ModificarHabilidadesOk_View implements Command {

	@Override
	public Entry<Integer, Context> execute(Context context) {
		THabilidad hab = (THabilidad) context.getData();
		int resultado = FactoryNeg.getInstance().generateSAHabilidad().update(hab);
		Context new_context = new Context();

		if (resultado == -1) {
			new_context.setEvent(Events.ERROR_HABILIDAD_NO_EXISTE);
			new_context.setData(hab.getId());
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.HABILIDAD_VIEW,new_context);
		} else if (resultado == -2) {
			new_context.setEvent(Events.ERROR_HABILIDAD_EXISTE);
			new_context.setData(hab);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.HABILIDAD_VIEW,new_context);
		} else {
			new_context.setEvent(Events.EXITO_HABILIDAD_MODIFICAR);
			new_context.setData(context.getData());
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.HABILIDAD_VIEW,new_context);
		}
			
			
	}

}

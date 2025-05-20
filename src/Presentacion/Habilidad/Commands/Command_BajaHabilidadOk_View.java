package Presentacion.Habilidad.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;
public class Command_BajaHabilidadOk_View implements Command {

	@Override
	public Entry<Integer, Context> execute(Context context) {
		int resultado = FactoryNeg.getInstance().generateSAHabilidad().delete((Integer) context.getData());
		Context new_context = new Context();
		new_context.setData(context.getData());

		if (resultado == -1) {// NO se ha podido crear
			new_context.setEvent(Events.ERROR_HABILIDAD_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.HABILIDAD_VIEW,new_context);
			
		} else if (resultado == -2) {
			new_context.setEvent(Events.ERROR_NECESITAS_DESVINCULAR_HABILIDAD_A_PERSONAL);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.HABILIDAD_VIEW,new_context);
			

		} else
		{
			new_context.setEvent(Events.EXITO_HABILIDAD_BAJA);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.HABILIDAD_VIEW,new_context);
		}
			
			
	}

}

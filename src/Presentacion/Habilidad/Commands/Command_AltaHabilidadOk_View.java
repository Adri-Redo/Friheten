package Presentacion.Habilidad.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Habilidad.THabilidad;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;
public class Command_AltaHabilidadOk_View implements Command{
	@Override
	public Entry<Integer, Context> execute(Context context) {
		THabilidad hab = (THabilidad) context.getData();
		int resultado = FactoryNeg.getInstance().generateSAHabilidad().create(hab);
		Context new_context =  new Context();
		new_context.setData(context.getData());

		if (resultado == -1) {// NO se ha podido crear
			
			new_context.setEvent(Events.ERROR_HABILIDAD_EXISTE);
			
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.HABILIDAD_VIEW,new_context);
			
		} else // Mensaje de que todo ha ido bien
		{
			new_context.setEvent(Events.EXITO_HABILIDAD_ALTA);
			
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.HABILIDAD_VIEW,new_context);
		}
			
	}

}
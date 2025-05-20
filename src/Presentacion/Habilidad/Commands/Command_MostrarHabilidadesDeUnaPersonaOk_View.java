package Presentacion.Habilidad.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Habilidad.THabilidad;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_MostrarHabilidadesDeUnaPersonaOk_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		List<THabilidad> habilidades = FactoryNeg.getInstance().generateSAHabilidad()
				.readHabilidadPorPersonal((Integer) context.getData());
		Context new_context = new Context();
		if (habilidades == null) {
			new_context.setEvent(Events.ERROR_PERSONAL_NO_EXISTE);
			new_context.setData(context.getData());
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.HABILIDAD_VIEW,new_context);
			
		} else if (habilidades.isEmpty()) {
			new_context.setEvent(Events.ERROR_NO_TIENE_NINGUNA_HABILIDAD_VINCULADA);
			new_context.setData(context.getData());
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.HABILIDAD_VIEW,new_context);

		} else {
			new_context.setEvent(Events.EXITO_MOSTRAR_HABILIDAD_POR_PERSONA);
			new_context.setData(habilidades);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.HABILIDAD_VIEW,new_context);
			
		}
	}

}

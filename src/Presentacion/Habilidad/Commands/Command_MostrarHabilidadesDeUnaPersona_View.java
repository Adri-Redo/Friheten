package Presentacion.Habilidad.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_MostrarHabilidadesDeUnaPersona_View implements Command {

	@Override
	public Entry<Integer, Context> execute(Context context) {
		context.setData(null);
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.MOSTRAR_HABILIDADES_DE_UNA_PERSONA_VIEW,context);
	}

}

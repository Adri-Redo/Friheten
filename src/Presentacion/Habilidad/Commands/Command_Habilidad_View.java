package Presentacion.Habilidad.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_Habilidad_View implements Command {

	@Override
	public Entry<Integer, Context> execute(Context context) {
		
		// en este caso devuelve el context que le llega como parametro porque no importa pero en otro caso ensamblaria un context
		//con los datos que le llegan del SA
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.HABILIDAD_VIEW,context);
	}

}

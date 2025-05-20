package Presentacion.Personal_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_BajaPersonalCaf_View implements Command {
	
	public Entry<Integer, Context> execute(Context context) {
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.BAJA_PERSONALCAFETERIA_VIEW,context);	
	}
}
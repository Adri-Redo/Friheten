package Presentacion.Personal.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ConsultarPersonal_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.CONSULTAR_PERSONAL_VIEW,context);
	}

}

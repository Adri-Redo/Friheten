package Presentacion.Alergeno_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_Alergeno_View implements Command {

	@Override
	public Entry<Integer, Context> execute(Context context) {
		// TODO Auto-generated method stub
		Context newcontext = new Context();
		newcontext.setData(context.getData());
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.ALERGENO_VIEW, newcontext);
	}

}

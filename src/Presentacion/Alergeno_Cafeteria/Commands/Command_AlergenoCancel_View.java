package Presentacion.Alergeno_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_AlergenoCancel_View implements Command{

	@Override
	public SimpleEntry<Integer, Context> execute(Context context) {
		// TODO Auto-generated method stub
		Context returncontext = new Context();
		returncontext.setData(context.getData());
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.ALERGENO_CANCEL_VIEW, returncontext);
	}

}

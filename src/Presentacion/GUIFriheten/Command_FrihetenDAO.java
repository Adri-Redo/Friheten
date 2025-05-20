package Presentacion.GUIFriheten;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_FrihetenDAO implements Command{

	@Override
	public Entry<Integer,Context>execute(Context context) {
		
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.TIENDA_MUEBLES_VIEW, context);
	}

}

package Presentacion.Nave.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_BajaNave_View implements Command{

		@Override
		public Entry<Integer, Context> execute(Context context) {
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.BAJA_NAVE_VIEW,context);
		}
}

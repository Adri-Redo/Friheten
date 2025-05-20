package Presentacion.Command;

import java.util.Map.Entry;

import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public interface Command {

		public Entry<Integer,Context> execute(Context context);
}

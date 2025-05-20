/**
 * 
 */
package Presentacion.Alergeno_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;


public class Command_ListarAlergenosProductoKo_View implements Command {
	@Override
	public Entry<Integer, Context> execute(Context context) {
	    Context new_context = new Context();
	    return new AbstractMap.SimpleEntry<Integer, Context>(Events.ALERGENO_VIEW, new_context);

	}
}
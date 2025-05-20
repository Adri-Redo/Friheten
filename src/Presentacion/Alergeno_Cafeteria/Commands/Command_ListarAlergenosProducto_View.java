package Presentacion.Alergeno_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ListarAlergenosProducto_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
	    Context new_context = new Context();
	    return new AbstractMap.SimpleEntry<Integer, Context>(Events.MOSTRAR_ALERGENO_PORID_PRODUCTO_VIEW, new_context);

	}

}

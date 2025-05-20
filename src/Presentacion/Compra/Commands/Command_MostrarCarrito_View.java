package Presentacion.Compra.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_MostrarCarrito_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.MOSTRAR_CARRITO_VIEW, context);
	}

}

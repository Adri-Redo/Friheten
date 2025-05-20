package Presentacion.Compra.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_DevolucionProducto_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		Context new_context = new Context();
		new_context.setData(null);
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.DEVOLUCION_PRODUCTO_VIEW, new_context);
	}

}

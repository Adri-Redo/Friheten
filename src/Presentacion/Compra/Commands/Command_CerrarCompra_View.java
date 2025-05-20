package Presentacion.Compra.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Compra.TCarrito;
import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_CerrarCompra_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		FactoryNeg.getInstance().generateSACompra().close((TCarrito) context.getData());
		context.setEvent(Events.EXITO_CERRAR_COMPRA);
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.CERRAR_COMPRA_VIEW, context);
	}

}

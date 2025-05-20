package Presentacion.Compra.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_MostrarCompra_IDCliente_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		context.setData(FactoryNeg.getInstance().generateSACompra().readAll());
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.MOSTRAR_COMPRA_POR_ID_CLIENTE_VIEW, context);
	}

}

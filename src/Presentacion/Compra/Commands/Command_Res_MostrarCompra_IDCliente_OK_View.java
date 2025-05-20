package Presentacion.Compra.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_Res_MostrarCompra_IDCliente_OK_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		Object comprasCliente = FactoryNeg.getInstance().generateSACompra().readByIDCliente((int) context.getData());
		Context new_context = new Context();
		new_context.setData(comprasCliente);
		
		if (comprasCliente == null) 
			new_context.setEvent(Events.ERROR_MOSTRAR_COMPRA_POR_ID_CLIENTE);

		else {
			new_context.setEvent(Events.EXITO_MOSTRAR_COMPRA_POR_ID_CLIENTE);
			new_context.setData(comprasCliente);
		}
		
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.MOSTRAR_COMPRA_POR_ID_CLIENTE_VIEW, new_context);
	}

}

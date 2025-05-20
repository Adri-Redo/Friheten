package Presentacion.Cliente.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Cliente.TCliente;
import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ConsultarClienteOk_View implements Command {

	@Override
	public Entry<Integer, Context> execute(Context context) {
		TCliente tCliente= FactoryNeg.getInstance().generateSACliente().read((Integer) context.getData());
		Context new_context = new Context();
		new_context.setData(context.getData());

		if (tCliente == null) {// No existe
			new_context.setEvent(Events.ERROR_CLIENTE_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer, Context>(Events.CLIENTE_VIEW, new_context);

		} else {
			new_context.setEvent(Events.EXITO_CLIENTE_CONSULTAR);
			new_context.setData(tCliente);
			return new AbstractMap.SimpleEntry<Integer, Context>(Events.CONSULTAR_CLIENTE_VIEW, new_context);
		}
		
	}

}

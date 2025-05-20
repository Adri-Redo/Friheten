package Presentacion.Cliente.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_BajaClienteOk_View implements Command {

	@Override
	public Entry<Integer, Context> execute(Context context) {
		int resultado = FactoryNeg.getInstance().generateSACliente().delete((Integer) context.getData());
		Context new_context = new Context();
		new_context.setData(context.getData());

		if (resultado == -1) {// NO se ha podido crear
			new_context.setEvent(Events.ERROR_CLIENTE_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.CLIENTE_VIEW,new_context);
			
		} 
		else {
			new_context.setEvent(Events.EXITO_CLIENTE_BAJA);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.CLIENTE_VIEW,new_context);
		}
	}
}

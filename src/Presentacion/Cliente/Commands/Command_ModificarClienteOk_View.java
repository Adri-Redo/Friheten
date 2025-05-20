package Presentacion.Cliente.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Cliente.TCliente;
import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ModificarClienteOk_View implements Command {

	@Override
	public Entry<Integer, Context> execute(Context context) {
		TCliente tCliente = (TCliente) context.getData();
		Context new_context = new Context();
		int resultado = FactoryNeg.getInstance().generateSACliente().update(tCliente);

		if (resultado == -1) {// No existe
			new_context.setEvent(Events.ERROR_TIPOS_DISTINTOS_MOD);
			new_context.setData(tCliente.getId());
		} 
		else if (resultado == -2) {// Usuario rep
			new_context.setEvent(Events.ERROR_CLIENTE_EXISTE_MOD);
			new_context.setData(tCliente);
		}
		else if(resultado == -3) { //Correo rep
			new_context.setEvent(Events.ERROR_CORREO_EXISTE_MOD);
			new_context.setData(tCliente);
		}
		else if(resultado == -4) {
			new_context.setEvent(Events.ERROR_CLIENTE_NO_EXISTE);
			new_context.setData(tCliente.getId());
		}
		else {
			new_context.setEvent(Events.EXITO_CLIENTE_MODIFICAR);
			new_context.setData(tCliente.getId());
			
		}
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.CLIENTE_VIEW, new_context);
	}

}

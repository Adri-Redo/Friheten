package Presentacion.Cliente.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Negocio.Cliente.TCliente;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_AltaClienteOk_View implements Command {

	@Override
	public Entry<Integer, Context> execute(Context context) {
		TCliente cli = (TCliente) context.getData();
		int resultado = FactoryNeg.getInstance().generateSACliente().create(cli);
		Context new_context =  new Context();
		new_context.setData(context.getData());

		if (resultado == -1) // NO se ha podido crear
			new_context.setEvent(Events.ERROR_CLIENTE_EXISTE);
		else if(resultado == -2) 
			new_context.setEvent(Events.ERROR_CORREO_EXISTE);
		else if(resultado == -3) 
			new_context.setEvent(Events.ERROR_TIPOS_DISTINTOS);
		
		else // Mensaje de que todo ha ido bien
			new_context.setEvent(Events.EXITO_CLIENTE_ALTA);
		
		
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.CLIENTE_VIEW, new_context);
	}

}

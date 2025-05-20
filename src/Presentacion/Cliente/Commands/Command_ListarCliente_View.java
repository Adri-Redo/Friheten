package Presentacion.Cliente.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Cliente.TCliente;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ListarCliente_View implements Command {

	@Override
	public Entry<Integer, Context> execute(Context context) {
		List<TCliente> lista_cli = FactoryNeg.getInstance().generateSACliente().readAll();
		Context new_context = new Context();
		new_context.setData(lista_cli);
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.LISTAR_CLIENTE_VIEW, new_context);
	}

}

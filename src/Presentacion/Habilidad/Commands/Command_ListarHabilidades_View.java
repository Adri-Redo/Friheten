package Presentacion.Habilidad.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Habilidad.THabilidad;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ListarHabilidades_View implements Command {

	@Override
	public Entry<Integer, Context> execute(Context context) {
		List<THabilidad> lista_hab = FactoryNeg.getInstance().generateSAHabilidad().readAll();
		Context new_context = new Context();
		new_context.setData(lista_hab);
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.LISTAR_HABILIDAD_VIEW,new_context);
	}

}

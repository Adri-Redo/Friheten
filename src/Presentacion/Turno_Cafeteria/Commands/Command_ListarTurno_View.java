package Presentacion.Turno_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Turno_Cafeteria.TTurnoCafeteria;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ListarTurno_View implements Command {

	@Override
	public Entry<Integer, Context> execute(Context context) {
		List<TTurnoCafeteria> lista_tur = FactoryNeg.getInstance().generateSATurno().readAll();
		Context new_context = new Context();
		new_context.setData(lista_tur);
		return new AbstractMap.SimpleEntry<Integer, Context>(Events.LISTAR_TURNO_VIEW, new_context);
	}

}

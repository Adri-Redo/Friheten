package Presentacion.Alergeno_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import Negocio.Alergeno_Cafeteria.TAlergeno;
import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Habilidad.THabilidad;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ListarAlergeno_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
	    Set<TAlergeno> lista_hab = FactoryNeg.getInstance().generateSAAlergeno().readAll();
	    Context new_context = new Context();
	    new_context.setData(lista_hab);
	    return new AbstractMap.SimpleEntry<Integer, Context>(Events.LISTAR_ALERGENO_VIEW, new_context);

	}

}

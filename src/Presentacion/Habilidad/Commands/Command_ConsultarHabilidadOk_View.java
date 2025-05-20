package Presentacion.Habilidad.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Habilidad.THabilidad;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ConsultarHabilidadOk_View implements Command {

	@Override
	public Entry<Integer, Context> execute(Context context) {
		THabilidad tConsultar = FactoryNeg.getInstance().generateSAHabilidad().read((Integer) context.getData());
		Context new_context = new Context();
		new_context.setData(context.getData());

		if (tConsultar == null) {// No existe
			new_context.setEvent(Events.ERROR_HABILIDAD_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer, Context>(Events.HABILIDAD_VIEW, new_context);

		} else {
			new_context.setEvent(Events.EXITO_HABILIDAD_CONSULTAR);
			new_context.setData(tConsultar);
			return new AbstractMap.SimpleEntry<Integer, Context>(Events.CONSULTAR_HABILIDAD_VIEW, new_context);
		}

	}

}

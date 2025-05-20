package Presentacion.Alergeno_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.Alergeno_Cafeteria.TAlergeno;
import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Habilidad.THabilidad;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ConsultarAlergenoOk_View implements Command {

	@Override
	public Entry<Integer, Context> execute(Context context) {
	    TAlergeno tConsultar = FactoryNeg.getInstance().generateSAAlergeno().read((Integer) context.getData());
	    Context new_context = new Context();
	    if (tConsultar == null) { // No existe
	        new_context.setEvent(Events.ERROR_ALERGENO_NO_EXISTE);
	    } else {
	        new_context.setEvent(Events.EXITO_ALERGENO_CONSULTAR);
	        new_context.setData(tConsultar);
	    }

	    return new AbstractMap.SimpleEntry<Integer, Context>(Events.CONSULTAR_ALERGENO_VIEW, new_context);
	}

}

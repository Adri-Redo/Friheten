package Presentacion.Habilidad.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Habilidad.THabilidadPersonal;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_VincularHabilidadOk_View implements Command {

	@Override
	public Entry<Integer, Context> execute(Context context) {
		THabilidadPersonal habPer = (THabilidadPersonal) context.getData();
		int resultado = FactoryNeg.getInstance().generateSAHabilidad().createHabilidadPersonal(habPer);
		Context new_context = new Context();

		if (resultado == -1) {// NO se ha podido crear
			new_context.setEvent(Events.ERROR_HABILIDAD_YA_VINCULADA_A_PERSONAL);
			new_context.setData(habPer);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.HABILIDAD_VIEW,new_context);
			

		} else if (resultado == -2) {
			new_context.setEvent(Events.ERROR_HABILIDAD_NO_EXISTE);
			new_context.setData(habPer.getIdHabilidad());
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.HABILIDAD_VIEW,new_context);
			
		} else if (resultado == -3) {
			new_context.setEvent(Events.ERROR_PERSONAL_NO_EXISTE);
			new_context.setData(habPer.getIdPersonal());
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.HABILIDAD_VIEW,new_context);
			

		}

		else {
			new_context.setEvent(Events.EXITO_HABILIDAD_VINCULAR);
			new_context.setData(context.getData());
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.HABILIDAD_VIEW,new_context);
		}
			
	}

}

package Presentacion.Personal_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Personal_Cafeteria.TPersonalCafeteria;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ConsultarPersonalCafOK_View implements Command {

	@Override
	public Entry<Integer, Context> execute(Context context) {
		Integer id = (Integer) context.getData();
		TPersonalCafeteria tp = FactoryNeg.getInstance().generateSAPersonalCafeteria().read(id);
		Context new_context =  new Context();
		new_context.setData(tp);
		if (tp == null) {// NO existe el personal
			new_context.setEvent(Events.ERROR_PERSONALCAFETERIA_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.PERSONALCAFETERIA_VIEW,new_context);	
		}else // Mensaje de que todo ha ido bien
		{
			new_context.setEvent(Events.EXITO_PERSONALCAFETERIA_CONSULTAR);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.CONSULTAR_PERSONALCAFETERIA_VIEW,new_context);
		}
	}
}
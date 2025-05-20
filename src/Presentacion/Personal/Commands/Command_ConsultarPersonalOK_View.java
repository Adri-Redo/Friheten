package Presentacion.Personal.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Personal.TPersonal;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ConsultarPersonalOK_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		Integer id = (Integer) context.getData();
		TPersonal tp = FactoryNeg.getInstance().generateSAPersonal().read(id);
		Context new_context =  new Context();
		new_context.setData(tp);
		if (tp == null) {// NO existe el personal
			new_context.setEvent(Events.ERROR_PERSONAL_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.ERROR_PERSONAL_NO_EXISTE,new_context);	
		}else // Mensaje de que todo ha ido bien
		{
			new_context.setEvent(Events.EXITO_PERSONAL_CONSULTAR);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.CONSULTAR_PERSONAL_VIEW,new_context);
		}
	}

}

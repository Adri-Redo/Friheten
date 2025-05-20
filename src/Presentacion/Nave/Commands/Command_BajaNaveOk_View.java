package Presentacion.Nave.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_BajaNaveOk_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		int resultado = FactoryNeg.getInstance().generateSANave().delete((Integer) context.getData());
		Context new_context = new Context();
		new_context.setData(context.getData());

		if (resultado == -1 || resultado == 0) {
			new_context.setEvent(Events.NAVE_ERROR_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.NAVE_VIEW,new_context);
			
		} else if (resultado == -2) {
			new_context.setEvent(Events.NAVE_ERROR_DESCONOCIDO);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.NAVE_VIEW,new_context);
			

		} else
		{
			new_context.setEvent(Events.RES_BAJA_NAVE_OK);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.NAVE_VIEW,new_context);
		}
			
			
	}

}

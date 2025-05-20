package Presentacion.Personal_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_BajaPersonalCafOK_View implements Command {
	
	@Override
	public Entry<Integer, Context> execute(Context context) {
		Integer id = (Integer) context.getData();
		int resultado = FactoryNeg.getInstance().generateSAPersonalCafeteria().delete(id);
		Context new_context =  new Context();
		new_context.setData(context.getData());
		if (resultado == -1) {// Personal no existe
			new_context.setEvent(Events.ERROR_PERSONALCAFETERIA_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.PERSONALCAFETERIA_VIEW,new_context);	
		}else if (resultado == -2) {
			new_context.setEvent(Events.ERROR_PERSONALCAFETERIA_BAJA);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.PERSONALCAFETERIA_VIEW,new_context);	
		}else if (resultado == -3) {//Personal ya dado de baja
			new_context.setEvent(Events.ERROR_PERSONALCAFETERIA_YA_DADO_DE_BAJA);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.PERSONALCAFETERIA_VIEW,new_context);	
		}else // Mensaje de que todo ha ido bien
		{
			new_context.setEvent(Events.EXITO_PERSONALCAFETERIA_BAJA);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.PERSONALCAFETERIA_VIEW,new_context);
		}
	}
}
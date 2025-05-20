package Presentacion.Personal.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Personal.TPersonal;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_AltaPersonalOK_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		TPersonal tp = (TPersonal) context.getData();
		int resultado = FactoryNeg.getInstance().generateSAPersonal().create(tp);
		Context new_context =  new Context();
		new_context.setData(context.getData());
		if (resultado == -1) {// NO se ha podido crear
			new_context.setEvent(Events.ERROR_PERSONAL_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.ERROR_PERSONAL_EXISTE,new_context);	
		}else if (resultado == -2) {//Nave no existe
			new_context.setEvent(Events.NAVE_ERROR_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.NAVE_ERROR_NO_EXISTE,new_context);	
		}else if (resultado == -3) {//Jefe antes personal o viceversa
			new_context.setEvent(Events.ERROR_JEFE_ANTES_ERA_EMPLEADO_O_VICEVERSA);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.ERROR_JEFE_ANTES_ERA_EMPLEADO_O_VICEVERSA,new_context);	
		} else // Mensaje de que todo ha ido bien
		{
			new_context.setEvent(Events.EXITO_PERSONAL_ALTA);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.PERSONAL_VIEW,new_context);
		}
	}

}

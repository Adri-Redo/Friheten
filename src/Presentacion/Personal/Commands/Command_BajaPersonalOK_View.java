package Presentacion.Personal.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_BajaPersonalOK_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		Integer id = (Integer) context.getData();
		int resultado = FactoryNeg.getInstance().generateSAPersonal().delete(id);
		Context new_context =  new Context();
		new_context.setData(context.getData());
		if (resultado == -1) {// Personal no existe
			new_context.setEvent(Events.ERROR_PERSONAL_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.ERROR_PERSONAL_NO_EXISTE,new_context);	
		}else if (resultado == -2) {//Personal ya dado de baja
			new_context.setEvent(Events.ERROR_PERSONAL_YA_DADO_DE_BAJA);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.ERROR_PERSONAL_YA_DADO_DE_BAJA,new_context);	
		}else if (resultado == -3) {//Necesitas desvincular la habilidad
			new_context.setEvent(Events.ERROR_NECESITAS_DESVINCULAR_HABILIDAD_A_PERSONAL);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.ERROR_NECESITAS_DESVINCULAR_HABILIDAD_A_PERSONAL,new_context);	
		} else // Mensaje de que todo ha ido bien
		{
			new_context.setEvent(Events.EXITO_PERSONAL_BAJA);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.PERSONAL_VIEW,new_context);
		}
	}

}

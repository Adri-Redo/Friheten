package Presentacion.Mueble.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Mueble.TMueble;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ModificarMueble_View implements Command{
	
	@Override
	public Entry<Integer,Context> execute(Context context)
	{
		List<TMueble> tList = FactoryNeg.getInstance().generateSAMueble().readAll();
		Context new_context =  new Context();
		
		if (tList == null)
		{
			new_context.setEvent(Events.CONSULTAR_MUEBLE_VIEW);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MODIFICAR_MUEBLE_VIEW,new_context);
		}
		else
		{
			new_context.setEvent(Events.EXITO_MUEBLE_CONSULTAR);
			new_context.setData(tList);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MODIFICAR_MUEBLE_VIEW,new_context);
		}	

	}
}
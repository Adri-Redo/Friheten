package Presentacion.Mueble.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Mueble.TMueble;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ConsultaMuebleOK_View implements Command{
	

	
	
	@Override
	public Entry<Integer,Context> execute(Context context)
	{
		int idMueble = (int) context.getData();
		TMueble getMueble = FactoryNeg.getInstance().generateSAMueble().read(idMueble);
		
		Context new_context =  new Context();
		
		if (getMueble.getId() == null)
		{
			new_context.setData(idMueble);
			new_context.setEvent(Events.ERROR_MUEBLE_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.CONSULTAR_MUEBLE_VIEW,new_context);
		}
		else	
		{
			new_context.setData(getMueble);
			new_context.setEvent(Events.EXITO_MUEBLE_CONSULTAR);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.CONSULTAR_MUEBLE_VIEW,new_context);
		}
	}
	
}
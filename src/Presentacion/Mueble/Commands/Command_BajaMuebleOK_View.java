package Presentacion.Mueble.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Mueble.TMueble;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_BajaMuebleOK_View implements Command
{	

	@Override
	public Entry<Integer,Context> execute(Context context)
	{
		Context new_context =  new Context();
		int muebleID = (int) context.getData();
		int resultado = -1;
		
		TMueble Mueble = FactoryNeg.getInstance().generateSAMueble().read(muebleID);
		if (Mueble != null)
		{
			new_context.setData(Mueble);
			
			if (Mueble.getActivo())
			{
				resultado = FactoryNeg.getInstance().generateSAMueble().delete(muebleID);
				Mueble = FactoryNeg.getInstance().generateSAMueble().read(muebleID);
				
				if (!Mueble.getActivo())
					new_context.setEvent(Events.EXITO_MUEBLE_BAJA);
				else
					new_context.setEvent(Events.EXITO_MUEBLE_MODIFICAR);
			}
			else
				new_context.setEvent(Events.EXITO_MUEBLE_MODIFICAR);
		}
		else
		{
			new_context.setData((Object) muebleID);
			new_context.setEvent(Events.ERROR_MUEBLE_NO_EXISTE);
		}
		
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.BAJA_MUEBLE_VIEW,new_context);
	}
}

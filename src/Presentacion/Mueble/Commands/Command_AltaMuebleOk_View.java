package Presentacion.Mueble.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Mueble.TMueble;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;


public class Command_AltaMuebleOk_View implements Command
{	
	@Override
	public Entry<Integer,Context> execute(Context context)
	{
		TMueble mueble = (TMueble) context.getData();

		int resultado = FactoryNeg.getInstance().generateSAMueble().create((TMueble) mueble);
		
		Context new_context =  new Context();
		new_context.setData(mueble);		
				
	    if (resultado == -2)	
		{
			mueble.setId(resultado);
			new_context.setData(mueble);	
			new_context.setEvent(Events.ERROR_MUEBLE_EXISTE);
		}
		else if (resultado >= 0)	
		{
			mueble.setId(resultado);
			new_context.setData(mueble);	
			new_context.setEvent(Events.EXITO_MUEBLE_ALTA);
		}
		else  
		{
			mueble.setId(resultado);
			new_context.setData(mueble);	
			new_context.setEvent(Events.ERROR_MUEBLE_NO_EXISTE);	
		}
			
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.ALTA_MUEBLE_VIEW,new_context);
	}

}


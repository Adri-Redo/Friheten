package Presentacion.Mueble.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Mueble.TMueble;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ModificarMuebleOK_View implements Command{
	
	@Override
	public Entry<Integer,Context> execute(Context context)
	{
		Context new_context =  new Context();
		new_context.setData(context.getData());
		
		int resultado = FactoryNeg.getInstance().generateSAMueble().update( (TMueble) context.getData());
		
		if (resultado < 1)
			new_context.setEvent(Events.ERROR_MUEBLE_NO_EXISTE);
		else  
			new_context.setEvent(Events.EXITO_MUEBLE_MODIFICAR);
		
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.MODIFICAR_MUEBLE_VIEW,new_context);
	 }
	
	
}
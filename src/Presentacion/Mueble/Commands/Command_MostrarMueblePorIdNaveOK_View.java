package Presentacion.Mueble.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Mueble.TMueble;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_MostrarMueblePorIdNaveOK_View implements Command{
	
	@Override
	public Entry<Integer,Context> execute(Context context)
	{
		int idNave = (int) context.getData();
		List<TMueble> mueblePorIdNave = FactoryNeg.getInstance().generateSAMueble().readMueblePorNave(idNave);
		
		Context new_context =  new Context();
		
		if (mueblePorIdNave.isEmpty())
		{
			new_context.setEvent(Events.ERROR_NO_HAY_MUEBLE_ASOCIADO_A_TAL_ID_NAVE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MUEBLE_VIEW,new_context);
		}
		else	
		{
			new_context.setEvent(Events.EXITO_MUEBLE_MOSTRAR_POR_ID_NAVE);
			new_context.setData(mueblePorIdNave);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MOSTRAR_MUEBLE_POR_ID_NAVE_VIEW,new_context);
		}
		
	}
	
}
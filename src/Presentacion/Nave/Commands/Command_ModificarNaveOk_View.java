package Presentacion.Nave.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Mueble.TMueble;
import Negocio.Nave.TNave;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ModificarNaveOk_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		
		TNave n = (TNave) context.getData();
		int resultado = FactoryNeg.getInstance().generateSANave().update(n);
		
		Context new_context = new Context();
		new_context.setData(context.getData());
		
		if(resultado == -1) {
			new_context.setEvent(Events.NAVE_ERROR_DESCONOCIDO);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.NAVE_VIEW, new_context);
		}else {
			new_context.setEvent(Events.RES_MODIFICAR_NAVE_OK);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.NAVE_VIEW, new_context);
		}
	}

}

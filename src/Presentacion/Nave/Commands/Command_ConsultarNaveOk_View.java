package Presentacion.Nave.Commands;

import java.util.AbstractMap;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Nave.TNave;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ConsultarNaveOk_View implements Command{

	@Override
	public Entry<Integer, Context> execute(Context context) {
		TNave tNave = FactoryNeg.getInstance().generateSANave().read((Integer) context.getData());
		Context new_context = new Context();
		new_context.setData(context.getData());
		
		if(tNave == null) {
			new_context.setEvent(Events.NAVE_ERROR_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.NAVE_VIEW, new_context);	
		}else {
			new_context.setEvent(Events.RES_CONSULTAR_NAVE_OK );
			new_context.setData(tNave);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.NAVE_VIEW, new_context);
		}
	}

}

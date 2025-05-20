package Presentacion.Nave.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Nave.TNave;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ModificarNave_View implements Command{
	@Override
	public Entry<Integer, Context> execute(Context context) {
		Context new_context = new Context();
		if(context.getData() == null) {
			new_context.setEvent(Events.CONSULTAR_NAVE_VIEW);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MODIFICAR_NAVE_VIEW, new_context);
		}else {
			new_context.setEvent(Events.RES_CONSULTAR_NAVE_OK);
			TNave n = FactoryNeg.getInstance().generateSANave().read((Integer)context.getData());
			new_context.setData(n);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.MODIFICAR_NAVE_VIEW, new_context);
		}
	}
}

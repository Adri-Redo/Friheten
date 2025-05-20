package Presentacion.Personal.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Personal.TPersonal;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_MostrarPersonalPorNumHabOK_View implements Command {

	@Override
	public Entry<Integer, Context> execute(Context context) {
		List<TPersonal> lista_result = FactoryNeg.getInstance().generateSAPersonal().query(context.getData());
		Context new_context = new Context();
		if(lista_result == null)
		{
			new_context.setEvent(Events.ERROR_PERSONAL_NO_EXISTE);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.ERROR_PERSONAL_NO_EXISTE,new_context);	
		}else {
			new_context.setData(lista_result);
			return new AbstractMap.SimpleEntry<Integer,Context>(Events.LISTAR_PERSONAL_VIEW,new_context);
		}
	}

}

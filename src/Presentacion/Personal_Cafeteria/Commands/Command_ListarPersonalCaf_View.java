package Presentacion.Personal_Cafeteria.Commands;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Personal_Cafeteria.TPersonalCafeteria;
import Presentacion.Command.Command;
import Presentacion.Context.Context;
import Presentacion.Controller.Events;

public class Command_ListarPersonalCaf_View implements Command {
	
	@Override
	public Entry<Integer, Context> execute(Context context) {
		List<TPersonalCafeteria> tp = (List<TPersonalCafeteria>) FactoryNeg.getInstance().generateSAPersonalCafeteria().readAll();
		Context new_context =  new Context();
		new_context.setData(tp);
		return new AbstractMap.SimpleEntry<Integer,Context>(Events.LISTAR_PERSONALCAFETERIA_VIEW,new_context);
	}
}